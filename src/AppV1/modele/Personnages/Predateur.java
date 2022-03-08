package AppV1.modele.Personnages;

import AppV1.controleur.Bfs;
import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;

public abstract class Predateur extends Animal {
	private int ptsAttaque;
	private double tauxDeDeplacement;
	private double porteeAttaque;
	public Predateur (Environnement env, int v, int pv, int attaque, double tauxDeplacement, double porteeAttaque) {
		super (env, v, pv);
		this.ptsAttaque = attaque;
		this.tauxDeDeplacement = tauxDeplacement;
		this.porteeAttaque = porteeAttaque;
	}
	public void porterAttaque (Zelda z) {
		int diffMax = 32;
		int diffMin = -32;
		int diffX = this.getX() - z.getX();
		int diffY = this.getY() - z.getY();
		if (z.estEnVie() && (diffX<=diffMax && diffX>=diffMin) && (diffY<=diffMax && diffY >= diffMin)) {
			z.setPtsVie(z.getPtsVie() - this.ptsAttaque);
		}
	}
	public boolean seDeplacer(Zelda zelda, Terrain terrain,  int paneTranslateX, int paneTranslateY) {
		if (Math.random()<tauxDeDeplacement) {
			Bfs b = new Bfs();
			int direction = b.directionBfs(zelda, this, this.getEnv(), terrain, this.getPorteeAttaque(), paneTranslateX, paneTranslateY);
			if (direction == 1 && !zelda.conditionCollision(this.getX()+8, this.getY(), this.getWidth(), this.getHeight(), this.getId())) {
				this.setX(this.getX()+8);
			}
			else if (direction == -1 && !zelda.conditionCollision(this.getX()-8, this.getY(), this.getWidth(), this.getHeight(), this.getId())) {
				this.setX(this.getX()-8);
			}
			else if (direction == +2 && !zelda.conditionCollision(this.getX(), this.getY()+8, this.getWidth(), this.getHeight(), this.getId())) {
				this.setY(this.getY()+8);
			}
			else if (direction == -2 && !zelda.conditionCollision(this.getX(), this.getY()-8, this.getWidth(), this.getHeight(), this.getId())) {
				this.setY(this.getY()-8);
			}
			if (Math.random()<0.5) {
				this.porterAttaque(zelda);
			}
		}
		return true;
	}
	public double getPorteeAttaque() {
		return porteeAttaque;
	}
}
