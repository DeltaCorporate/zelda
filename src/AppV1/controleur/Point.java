package AppV1.controleur;

import AppV1.modele.Environnement;


public class Point {
	private int caseX;
	private int caseY;
	
	public Point (int x, int y) {
		this.caseX = (x / 8);
		this.caseY = (y / 8);
	}
	
	//verifie si obstacle dans la case et retourne un point aux bonnes coordonnés si aucun obstacle
	public Point caseObstacle(Environnement env, AppV1.modele.Personnages.Personnage zelda, AppV1.modele.Personnages.Personnage ennemi, AppV1.vue.Utils.Terrain terrain, int xChange, int yChange, int paneTranslateX, int paneTranslateY) {
		//verifie si obstacle dans la case et retourne faux si aucun obstacle
		// si aucun obstacle
		
		if (!env.presenceCollisionPerso((int)(getCaseX()*8-paneTranslateX+xChange), (int)(getCaseY()*8-paneTranslateY+yChange)) && !env.conditionsCollisionTerrain((int)(getCaseX()*8-paneTranslateX+xChange), (int)(getCaseY()*8-paneTranslateY+yChange), terrain)) {
			Point p = new Point(this.getCaseX()*8+xChange, this.getCaseY()*8+yChange);
			return p;
		}
		else {
			return null;
		}
	}
	
	public boolean estIdentique(Point p) {
		return (this.getCaseX() == p.getCaseX() && this.getCaseY() == p.getCaseY());
	}
	
	public int getCaseX() {
		return caseX;
	}
	

	public int getCaseY() {
		return this.caseY;
	}
	


}


