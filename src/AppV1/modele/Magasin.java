package AppV1.modele;

import AppV1.modele.Items.Armes.Gun;
import AppV1.modele.Items.CleFinale;
import AppV1.modele.Items.Items;
import AppV1.modele.Items.Nourriture;
import AppV1.modele.Items.PotionVie;
import AppV1.modele.Personnages.Zelda;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class Magasin {
	private Environnement env;
	private BorderPane pane ;
	private ArrayList<Items> listeObjetsEnVente;
	private BorderPane map;
	public Magasin(Environnement env, BorderPane pane, BorderPane map) {
		this.env = env;
		this.pane = pane;
		this.map = map;
		this.listeObjetsEnVente = new ArrayList<Items>();
		initMagasin();
	}
	
	public boolean estAuMagasin(Zelda z) {
		return ((z.getX() >= 144 && z.getX()<=192)&& z.getY() == 56);
	}
	
	public void initMagasin() {
		for (int i = 0; i < 100; i++) {
			this.listeObjetsEnVente.add(new Gun(env, 165, 110, 100, 1, map));
			this.listeObjetsEnVente.add(new PotionVie(env, 165, 110));
			this.listeObjetsEnVente.add(new Nourriture(env, 165, 110));
		}
		this.listeObjetsEnVente.add(new CleFinale(env, 640, 50));
	}
	
	public String acheterPotion1(Zelda z) throws Exception {
		String etatAchat;
		if (estAuMagasin(z)) {
			PotionVie p = this.getPotionVie();
			z.setArgentPorteMonnaie(z.getArgentPorteMonnaie()-p.getCout());
			z.getEnv().addObjet(p);
			this.listeObjetsEnVente.remove(p);
			etatAchat = "    Félicitations !";
		}
		else {
			etatAchat = "Allez au magasin !";
		}
		return etatAchat;
	}
	
	public String acheterGun(Zelda z) throws Exception {
		String etatAchat;
		if (estAuMagasin(z)) {
			Gun g = this.getGun();
			z.setArgentPorteMonnaie(z.getArgentPorteMonnaie()-g.getCout());
			z.getEnv().addObjet(g);
			this.listeObjetsEnVente.remove(g);
			etatAchat = "    Félicitations !";
		}
		else {
			etatAchat = "Allez au magasin !";
		}
		return etatAchat;
	}
	
	public String acheterNourriture(Zelda z) throws Exception {
		String etatAchat = "";
		if (estAuMagasin(z)) {
			Nourriture n = this.getNourriture();
			z.setArgentPorteMonnaie(z.getArgentPorteMonnaie()-n.getCout());
			z.getEnv().addObjet(n);
			this.listeObjetsEnVente.remove(n);
			etatAchat = "    Félicitations !";
		}
		else {
			etatAchat = "Allez au magasin !";
		}
		return etatAchat;
	}
	
	public String acheterCle(Zelda z, Label info) {
		String etatAchat = "";
		if (estAuMagasin(z)) {
			CleFinale cleF = this.getCleFinale();
			if (z.verifieTroisFragment()) {
				z.getEnv().addObjet(cleF);
				this.listeObjetsEnVente.remove(cleF);
				etatAchat = "    Félicitations !";
				info.setText("Affrontez le dragon et récupérer la clé dans le tipi près du Dragon");
			}
			else
				etatAchat = "Cherchez les fragments";
		}
		else {
			etatAchat = "Allez au magasin !";
		}
		return etatAchat;
	}
	
	public PotionVie getPotionVie() {
		for (Items items : listeObjetsEnVente) {
			if (items instanceof PotionVie) {
				return (PotionVie) items;
			}
		}
		return null;
	}
	
	public Gun getGun() {
		for (Items items : listeObjetsEnVente) {
			if (items instanceof Gun) {
				return (Gun) items;
			}
		}
		return null;
	}
	
	public Nourriture getNourriture() {
		for (Items items : listeObjetsEnVente) {
			if (items instanceof Nourriture) {
				return (Nourriture) items;
			}
		}
		return null;
	}
	
	public CleFinale getCleFinale() {
		for (Items items : listeObjetsEnVente) {
			if (items instanceof CleFinale) {
				return (CleFinale) items;
			}
		}
		return null;
	}
	
	
	
}
