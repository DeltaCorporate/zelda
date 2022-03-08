package AppV1.vue.Affichages;

import AppV1.controleur.MonObservateurPersonnage;
import AppV1.modele.Environnement;
import AppV1.modele.Items.Armes.Arc;
import AppV1.modele.Items.Armes.Gun;
import AppV1.modele.Items.Items;
import AppV1.modele.Personnages.Personnage;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Utils.Terrain;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AffichagePersonnage {
private static int i = 0;

	 int low = 10;
	 int high = 500;


 
    private int RandomPos(int high, int low) {
        Random random = new Random();
        return random.nextInt(high - low) + low;
    }	
    public void afficherElements(Zelda zelda, Environnement env, Terrain terrain, Pane mapPane){
    	
        for (Personnage p : env.getListePersonnage()) {
        	p.setPersonnagePosition(zelda,terrain);
        }

        for(Items o : env.getItems()){
    		do {
    		o.setX(RandomPos(high, low));
    		o.setY(RandomPos(high, low));
    		}
            while (env.presenceCollisionTerrain((int)(o.getX()-mapPane.getTranslateX()), (int) (o.getY()-mapPane.getTranslateY()), 30, 30, o.getId(), zelda, terrain));
        }
    }
    public ImageView afficherBalle (Zelda z, BorderPane p, int direction, int x, int y) {
    	Image imgBalle = new Image ((getClass().getResourceAsStream("../../vue/Images/Items/Armes/Munition/bullet.png")), 16, 16, true, true);
    	Image imgFleche = new Image ((getClass().getResourceAsStream("../../vue/Images/Items/Armes/Munition/arrow.png")), 16,16, true, true);
    	//Image imgFleche = 
    	ImageView view = null;

    	if (z.getArmes() != null) {
    		if (z.getArmes() instanceof Gun) {
    			view = new ImageView(imgBalle);
    		}
    		else if (z.getArmes() instanceof Arc) {
    			view = new ImageView(imgFleche);
    		}
    	}
    	if (view != null) {
    		if (direction == 1) { // vers la haut
    			view.setRotate(90);
    		}
    		if (direction == 3) { // vers le bas
    			view.setRotate(-90);
    		}
    		if (direction == 4) {// à gauche
    			view.setRotate(-view.getRotate());
    		}
    		view.setId("b"+i);
			view.setY(y); // placer la balle au milieu de Y de Zelda car zelda fait 32px de hauteur donc 32px/2 == milieu du perso
			view.setX(x); // placer la balle au milieu de X de Zelda 16px/2
    		i++;
        	p.getChildren().add(view);
    	}
    	return view;
    }
 	public void setPremierPlanJoueur(BorderPane pane, Personnage zelda, ObservableList<Personnage> observableList) {
		ArrayList<Personnage> liste = new ArrayList<Personnage>(); 
		liste.add(zelda);
		
		MonObservateurPersonnage obs = new MonObservateurPersonnage(pane);
		//boucle qui met tout les personnage de l'environnement dans une liste par ordre de valeur y croissant
		for (Personnage p : observableList) {
			liste.add(p);
		}
		
		//Met la liste dans l'ordre croissant en fonction de Y
		Collections.sort(liste);
		ArrayList<Personnage> liste2 = liste;

		//rajoute un sprite pour chaque personnage dans l'ordre croissant de Y
		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < liste2.size(); j++) {
				Personnage p0 = liste.get(i);
				Personnage p1 = liste2.get(j);
				if (p0.conditionCollision(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight(), p1.getId())) {
					obs.enleverSprite(liste.get(i), pane);
					obs.enleverSprite(liste2.get(j), pane);
					obs.creerSprite(liste.get(i), pane);
					obs.creerSprite(liste2.get(j), pane);
				}
			}
		}	
 	}
 	
 	
 
}
