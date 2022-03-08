package AppV1.controleur;


import AppV1.modele.Items.Armes.Arc;
import AppV1.modele.Items.Armes.Bouclier;
import AppV1.modele.Items.Armes.Epee;
import AppV1.modele.Items.Armes.Gun;
import AppV1.modele.Items.CasqueChevalier;
import AppV1.modele.Items.ChapeauMagique;
import AppV1.modele.Items.CleFinale;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.modele.Items.Nourriture;
import AppV1.modele.Items.PotionVie;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class MonObservateurItem implements ListChangeListener<Items>{
	private Pane p;
	private static int i = 0;
	public MonObservateurItem(Pane p) {
		// TODO Auto-generated constructor stub
		this.p = p;
	}
	
	public ImageView creerObjet(Items o, Pane p) {
        Image imgEpee = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Items/Armes/epee.png")).toExternalForm(), 16, 16, true, true);
        Image imgArc = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Items/Armes/arc.png")).toExternalForm(), 30, 30, true, true);
        Image imgBouclier = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Items/Armes/bouclier.png")).toExternalForm(), 30, 30, true, true);
        Image imgGun = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Items/Armes/gun.png")).toExternalForm(), 30, 30, true, true);
        Image imgChapeauMagique = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/chapeauMagique.png")).toExternalForm(), 16, 16, true, true);
        Image imgCasqueChevalier = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/casqueChevalier.png")).toExternalForm(), 16, 16, true, true);
        Image imgNourriture = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/nourriture.png")).toExternalForm(), 16, 16, true, true);
        Image imgPotion = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/potion.png")).toExternalForm(), 16, 16, true, true);
        Image imgFragmentCle = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/fragmentCle.png")).toExternalForm(), 16, 16, true, true);
        Image imgFragmentCle2 = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/fragment2.png")).toExternalForm(), 16, 16, true, true);
        Image imgFragmentCle3 = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/fragment3.png")).toExternalForm(), 16, 16, true, true);
        Image imgCleFinale = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/cleFinale.png")).toExternalForm(), 16, 16, true, true);
        ImageView objetView = null;

        if (o instanceof Epee) {
            objetView = new ImageView(imgEpee);
        } else if(o instanceof Arc){
            objetView = new ImageView(imgArc);
        }else if(o instanceof Bouclier){
            objetView = new ImageView(imgBouclier);
        }else if(o instanceof Gun){
            objetView = new ImageView(imgGun);
        }else if (o instanceof ChapeauMagique) {
			objetView = new ImageView(imgChapeauMagique);
		}else if (o instanceof CasqueChevalier) {
			objetView = new ImageView(imgCasqueChevalier);
		}else if (o instanceof Nourriture) {
			objetView = new ImageView(imgNourriture);
		}else if (o instanceof PotionVie) {
			objetView = new ImageView(imgPotion);
		}else if (o instanceof FragmentCle) {
			if (i == 0) {
				objetView = new ImageView(imgFragmentCle);
			}
			else if (i == 1) {
				objetView = new ImageView(imgFragmentCle2);
			}
			else if (i == 2) {
				objetView = new ImageView(imgFragmentCle3);
			}
			i++;
		}else if (o instanceof CleFinale) {
			objetView = new ImageView(imgCleFinale);
		}
        if(objetView != null){
        	
        	if (!(p.getId().equals("PaneInventaire"))) {
        		objetView.translateXProperty().bind(o.getXProperty());
        		objetView.translateYProperty().bind(o.getYProperty());
        		p.getChildren().add(objetView);
        	}
    		objetView.setId(o.getId());
        }
		return objetView;
    }
	public void enleverItem(Items i, Pane pane) {
		pane.getChildren().remove(pane.lookup("#"+i.getId()));

	}
	/*
	public void verifiePresenceItem(Items i, int t) {
		if (! this.z.getlisteObjets().contains(i)) {
			this.posX -= 40;
			this.inventaire.getChildren().remove(t-1);
		}
	}
	public void ajouterInventaire(Items a) {
		if (this.z.getlisteObjets().contains(a))
				creerObjet(a, inventaire);
	}
	*/
	@Override
	public void onChanged(Change<? extends Items> c) {
		// TODO Auto-generated method stub
		while (c.next()) {
			for (Items nouveau : c.getAddedSubList()) {
				//ajouterInventaire(nouveau);
				creerObjet(nouveau, p);
			}
			for (Items supp : c.getRemoved()) {
				//verifiePresenceItem(supp,i);
				enleverItem(supp, p);
			}
		}
	}

}

