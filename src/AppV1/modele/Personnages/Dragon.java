package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;

public class Dragon extends PersoOffensif {
	private int cptDeplacement=0;
	private int direction = 1;
    public Dragon(Environnement env) {
        super(env, 100, 100, 200, 80);
    }

    public void deplacementSpecial(Zelda z, Terrain terrain) {
        if (cptDeplacement%4==0) {
			if (direction==4) {
				direction=1;
			}else {
				direction++;
			}
		}
        if (direction==1) {
			this.setX(this.getX());
			this.setY(this.getY()-16);
		}else if (direction==2) {
			this.setX(this.getX()+16);
			this.setY(this.getY());
		}else if (direction==3) {
			this.setX(this.getX());
			this.setY(this.getY()+16);
		}else if (direction==4) {
			this.setX(this.getX()-16);
			this.setY(this.getY());
		}
        cptDeplacement++;
        try {
			this.attaquer(z);
		} catch (Exception e) {
		}
    }

    @Override
    public void attaquer(Zelda p) throws Exception {
    	if (p.conditionCollision(this.getX(), this.getY(), this.getWidth()+48, this.getHeight()+48, this.getId())) {
    		p.setPtsVie(p.getPtsVie()-this.getPtsAttaque());
    	}
    }
}
