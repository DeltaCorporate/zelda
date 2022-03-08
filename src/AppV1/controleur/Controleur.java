package AppV1.controleur;

import AppV1.modele.Environnement;
import AppV1.modele.Items.Items;
import AppV1.modele.Magasin;
import AppV1.modele.Personnages.Dragon;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Affichages.AffichageMagasin;
import AppV1.vue.Affichages.AffichagePersonnage;
import AppV1.vue.Affichages.AffichageTerrain;
import AppV1.vue.DiscussionVue;
import AppV1.vue.Utils.Terrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private BorderPane pane;

    @FXML
    private Label or;
    @FXML
    private Pane mapPane;

    @FXML
    private BorderPane stage;

    private Terrain terrain;
    @FXML
    private Label label;
    @FXML
    private Pane PaneInventaire;
    private Environnement env;
    private Zelda zelda;
    private KeyBoardManager keyBoardManager;
    private Timeline gameLoop;
    private AffichageTerrain affichageTerrain;
    private AffichagePersonnage affichagePersonnage;
    private AffichageMagasin affichageMagasin;

    private DiscussionVue discussionVue;
    private Dragon boss;

    public Controleur(KeyBoardManager keyBoardManager) {
        this.keyBoardManager = keyBoardManager;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.env = new Environnement(512, 512);
        this.terrain = new Terrain("src/AppV1/vue/Images/Carte/map.tmx");
        this.affichageTerrain = new AffichageTerrain("../Images/Carte/tileset.png");
        this.affichagePersonnage = new AffichagePersonnage();
        this.discussionVue = new DiscussionVue();
        this.affichageMagasin = new AffichageMagasin();
        //Ajout des personnages secondaires et zelda dans l'environnement
        this.zelda = new Zelda(env, 250, 100, 0, 100, new Magasin(env, stage, pane));
        MonObservateurVieZelda obsVieZelda = new MonObservateurVieZelda(pane);
        MonObservateurDirection obsZeldaDirection = new MonObservateurDirection(pane, zelda);
        MonObservateurItem obsItem = new MonObservateurItem(pane);
        MonObservateurItemZelda obsZelda = new MonObservateurItemZelda(PaneInventaire);
        MonObservateurPersonnage obsPerso = new MonObservateurPersonnage(pane);
        this.env.getListePersonnage().addListener(obsPerso);
        this.zelda.getDirectionProperty().addListener(obsZeldaDirection);
        this.zelda.getPtsVieProperty().addListener(obsVieZelda);
        this.env.getItems().addListener(obsItem);
        this.zelda.getlisteObjets().addListener(obsZelda);
        this.env.addPersonnage(zelda);
        this.env.creePersos();
        this.boss = new Dragon(env);
        this.env.addPersonnage(boss);
        this.env.creerObjet(pane);
        this.zelda.setPtsVie(100);

        this.zelda.getArgentPorteMonnaieProperty().addListener((obs,old,nouv)-> this.or.setText(nouv.toString()));


        pane.setClip(new Rectangle(520, 530));
        mapPane.translateXProperty().bind(zelda.getXProperty().multiply(-1));
        mapPane.translateYProperty().bind(zelda.getYProperty().multiply(-1));


        //afficher le terrain de tuile
        affichageTerrain.afficherTerrain(mapPane, terrain);
        //creation des sprites pour zelda et autres perso
        affichagePersonnage.afficherElements(zelda, env, terrain, mapPane);
        zelda.setPersonnagePosition(zelda, this.terrain);


        initLoop();
        gameLoop.play();
    }


    public void choisirObjet() {
        for (Node c : this.PaneInventaire.getChildren()) {
            if (c instanceof Button) {
                Items o = this.zelda.getItem(c.getId());
                ((Button) c).setOnMouseClicked(e -> zelda.objetEnMain(o));
            }
        }
    }

    public void initLoop() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame f = new KeyFrame(
                Duration.seconds(0.1),
                (ev -> {
                    if (this.zelda.estEnVie()) {
                        keyBoardManager.mecaniqueZelda(zelda, terrain, discussionVue, affichageMagasin, label, mapPane, pane, stage);
                        this.env.deplacerEnnemis(zelda, terrain, (int) mapPane.getTranslateX(), (int) mapPane.getTranslateY());
                        affichagePersonnage.setPremierPlanJoueur(pane, zelda, this.env.getListePersonnage());
                        this.choisirObjet();
                        this.env.suppMort();

                        if (this.env.getListePersonnage().contains(boss)) {
                            boss.deplacementSpecial(zelda, terrain);
                        }
                    } else {
                        label.setText("GAME OVER");
                    }
                }
                ));
        gameLoop.getKeyFrames().add(f);
    }
}

