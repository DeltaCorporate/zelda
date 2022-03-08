package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Animal extends PersoTuable {

	private String id;
	private static int i = 0;
	public Animal (Environnement e, int vitesse, int ptsVie) {
		super(e, 0, 0, ptsVie);
		this.x = new SimpleIntegerProperty(0);
		this.y = new SimpleIntegerProperty(0);
//		this.direction = 1;
		this.vitesse = vitesse;
		this.id = "#"+i+"C";
		this.i++;
		this.env = e;
	}
	public int getVitesse() {
		return this.vitesse;
	}

	public void setX(int x) {
		this.x.set(x);
	}
	public void setY(int y) {
		this.y.set(y);
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	} 
	public Environnement getEnvironnement() {
		return this.env;
	}
	public String getId() {
		return this.id;
	}
	public abstract boolean seDeplacer(Zelda zelda, Terrain terrain, int paneTranslateX, int paneTranslateY);
}
