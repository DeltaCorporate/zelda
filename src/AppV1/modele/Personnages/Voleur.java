package AppV1.modele.Personnages;

import AppV1.controleur.Bfs;
import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;

public class Voleur extends PersoOffensif {
	private double tauxDeDeplacement;
	private double porteeAttaque;


	public Voleur(Environnement env, int x, int y, int ptsVie, int ptsAttaque, double tauxDeplacement, double porteeAttaque) {
		super(env, x, y, ptsVie, ptsAttaque);
		this.tauxDeDeplacement = tauxDeplacement;
		this.porteeAttaque = porteeAttaque;
	}

	
	public void attaquer(Zelda zelda) throws Exception {
		int distanceAttaque = 16;
		int distanceX = this.getX()-zelda.getX();
		int distanceY = this.getY()-zelda.getY();
		if((distanceX<=distanceAttaque && distanceX >= (-distanceAttaque)) && (distanceY<=distanceAttaque && distanceY >= (-distanceAttaque))) {
			zelda.setArgentPorteMonnaie(zelda.getArgentPorteMonnaie()-1);
		}
	}

	@Override
	public boolean seDeplacer(Zelda zelda, Terrain terrain, int paneTranslateX, int paneTranslateY) {
		if (Math.random()<tauxDeDeplacement) {
			Bfs b = new Bfs();
			int direction = b.directionBfs(zelda, this, this.getEnv(), terrain, this.getPorteeAttaque(), paneTranslateX, paneTranslateY);
			if (direction == 1 && !zelda.conditionCollision(this.getX()+8, this.getY(), this.getWidth(), this.getHeight(), this.getId())) {
				this.setX(this.getX()+8);
			}
			else if (direction == -1 && (zelda.getX()!=this.getX()-8 || zelda.getY()!=this.getY())) {
				this.setX(this.getX()-8);
			}
			else if (direction == +2 && (zelda.getX()!=this.getX() || zelda.getY()!=this.getY()+8)) {
				this.setY(this.getY()+8);
			}
			else if (direction == -2 && (zelda.getX()!=this.getX() || zelda.getY()!=this.getY()-8)) {
				this.setY(this.getY()-8);
			}
				try {
					if (Math.random()<0.1) {
						this.attaquer((Zelda)zelda);
					}
				} catch (Exception e) {
				}
			
		}
		return true;
	}


	public double getPorteeAttaque() {
		return porteeAttaque;
	}
}
