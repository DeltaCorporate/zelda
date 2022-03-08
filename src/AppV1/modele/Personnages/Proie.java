package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;

import java.util.Random;

public abstract class Proie extends Animal {
	private static double tauxDeDeplacement;
	public Proie(Environnement env, int v, int pv, double tauxDeplacement) {
		super(env, v, pv);
		this.tauxDeDeplacement = tauxDeplacement;
	}

	public boolean seDeplacer(Zelda zelda, Terrain terrain, int paneTranslateX, int paneTranslateY) {
		return true;
	}
}
