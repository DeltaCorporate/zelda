package AppV1.controleur;

import AppV1.modele.Personnages.Chevalier;
import AppV1.modele.Personnages.Dragon;
import AppV1.modele.Personnages.Loup;
import AppV1.modele.Personnages.Magicien;
import AppV1.modele.Personnages.Mouton;
import AppV1.modele.Personnages.Personnage;
import AppV1.modele.Personnages.Poule;
import AppV1.modele.Personnages.Princesse;
import AppV1.modele.Personnages.Sanglier;
import AppV1.modele.Personnages.Voleur;
import AppV1.modele.Personnages.Zelda;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class MonObservateurPersonnage implements ListChangeListener<AppV1.modele.Personnages.Personnage>{

	private Pane panneau;
	public MonObservateurPersonnage(Pane panneau) {
		this.panneau = panneau;
	}
	public void creerSprite(Personnage p, Pane pane) {
        Image imgChevalier = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/chevalier.png")).toExternalForm(), 16,32,true,true);
        Image imgMagicien = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/magicien.png")).toExternalForm(),16,32,true, true);
        Image imgPrincesse = new Image(Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/princesse.png")).toExternalForm(),16,32,true,true);
        Image imgVoleur = new Image(Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/voleur.png")).toExternalForm(), 16,32, true, true);
        Image imgMouton = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Animaux/mouton.png")).toExternalForm(), 16, 16, true, true);
        Image imgPoule = new Image(Objects.requireNonNull(getClass().getResource("../vue/Images/Animaux/poule.png")).toExternalForm(), 16, 16, true, true);
        Image imgSanglier = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Animaux/sanglier.png")).toExternalForm(), 16, 16 , true, true);
        Image imgLoup = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Animaux/loup.png")).toExternalForm(), 16, 16, true , true);
        Image imgDragon = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Animaux/dragon.png")).toExternalForm(), 32 , 32, true , true);

        ImageView persoView = null;
        
        if (p instanceof Mouton) {
            persoView = new ImageView(imgMouton);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(0);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if (p instanceof Poule) {
            persoView = new ImageView(imgPoule);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(0);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if (p instanceof Loup) {
            persoView = new ImageView (imgLoup);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(0);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if(p instanceof Sanglier){
            persoView = new ImageView(imgSanglier);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(0);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if( p instanceof Zelda){
    		MonObservateurDirection obsZeldaDirection = new MonObservateurDirection(pane, (Zelda)p);
            obsZeldaDirection.creerSprite(pane, p.getDirection());
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(16);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if (p instanceof Magicien){
            persoView = new ImageView(imgMagicien);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(16);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if (p instanceof Chevalier){
            persoView = new ImageView(imgChevalier);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(16);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if (p instanceof Voleur){
            persoView = new ImageView(imgVoleur);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(16);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        else if (p instanceof Princesse){
            persoView = new ImageView(imgPrincesse);
            p.setwidth(16);
	        p.setheight(16);
	        p.setTrompeurPositionY(16);
	        p.setFauxY(p.getY()+p.getTrompeurPositionY());
        } else if(p instanceof Dragon){
            persoView = new ImageView(imgDragon);
            p.setwidth(32);
            p.setheight(32);
            p.setTrompeurPositionY(8);
            p.setFauxY(p.getY()+p.getTrompeurPositionY());
        }
        

        // ils ont le meme identifiant
        if(persoView != null){
            persoView.setId(p.getId());
            persoView.translateXProperty().bind(p.getXProperty());
            persoView.translateYProperty().bind(p.getYProperty());
            pane.getChildren().add(persoView);
        }
    }
	public void enleverSprite(Personnage p, Pane pane) {
		pane.getChildren().remove(pane.lookup("#"+p.getId()));

	}
	@Override
	public void onChanged(Change<? extends Personnage> c) {
		// TODO Auto-generated method stub
		
		while (c.next()) {
			for (Personnage p : c.getAddedSubList()) {
				creerSprite(p, this.panneau);
			}
			for (Personnage mort : c.getRemoved()) {
				enleverSprite(mort, this.panneau);
			}
		}
		
	}

}