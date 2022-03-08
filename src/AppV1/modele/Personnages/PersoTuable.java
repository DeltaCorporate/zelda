package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PersoTuable extends Personnage {
	private IntegerProperty ptsVie;
	public PersoTuable(Environnement env, int x, int y, int PtsVie) {
		super(env, x, y);
		this.ptsVie = new SimpleIntegerProperty(PtsVie);
		// TODO Auto-generated constructor stub
	}
	public IntegerProperty getPtsVieProperty() {
		return this.ptsVie;
	}
	public void perdreVie(int degat) {
		if (estEnVie()) {
			this.setPtsVie(this.getPtsVie()-degat);
		}
	}
	public void setPtsVie(int ptsVie) {
		this.ptsVie.setValue(ptsVie);
	}
	public int getPtsVie() {
		return this.ptsVie.getValue();
	}
	public boolean estEnVie() {
		return this.getPtsVie()>0;
	}
	@Override
	public boolean seDeplacer(Zelda zelda, Terrain terrain, int paneTranslateX, int paneTranslateY) {
		return false;
	}

}
