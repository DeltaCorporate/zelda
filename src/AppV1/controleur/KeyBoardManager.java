package AppV1.controleur;

import AppV1.modele.Personnages.PersoQuete;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Affichages.AffichageMagasin;
import AppV1.vue.DiscussionVue;
import AppV1.vue.Utils.Terrain;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.EnumSet;
import java.util.Set;

public class KeyBoardManager implements EventHandler<KeyEvent> {
    private final Set<KeyCode> pressedKeys;
    private long tempsAction = 0;
    boolean pAppuye = false;
    boolean yAppuye = false;
    String magasinId = "#panneauMagasin";
    
    public KeyBoardManager(){
      this.pressedKeys = EnumSet.noneOf(KeyCode.class);
    }

    public boolean isKeyPressed(KeyCode keyCode){
        return this.pressedKeys.contains(keyCode);
    }

    public void mecaniqueZelda(Zelda zelda, Terrain terrain, DiscussionVue discussionVue, AffichageMagasin affichageMagasin, Label label, Pane mapPane, BorderPane pane, BorderPane stage) {
		//On rajoute +16 au y envoyé en paramètre pour fausser sa vrai position et ne pas faire collisionner le haut du personnage(sa tête)
//    	int zelda.getTrompeurPositionY=16;
    	int distanceDeplacement = 8;
    	
    	if (isKeyPressed(KeyCode.L)) {
    		zelda.utiliserPotion();
		}
    	
    	if (isKeyPressed(KeyCode.Y)) {
    		if (!this.getYAppuye()) {
    			this.setYAppuye();
    			affichageMagasin.afficherMagasin(zelda,stage,label);
    		}
    		else {
    			stage.getChildren().remove(stage.lookup("#" + magasinId));
    			this.setYNonAppuye();
    		}
		}

    
        if(isKeyPressed(KeyCode.Z) && !zelda.getEnv().presenceCollisionPerso(0, -8) &&!zelda.getEnv().presenceCollisionTerrain((int) ((zelda.getX())-(mapPane.getTranslateX())),(int)((zelda.getY()+zelda.getTrompeurPositionY()-distanceDeplacement*2)-mapPane.getTranslateY()),  zelda.getWidth(), zelda.getHeight(),zelda.getId(), zelda, terrain)) {
            zelda.setY(zelda.getY()-distanceDeplacement);
            zelda.setFauxY(zelda.getY()+zelda.getTrompeurPositionY());
            zelda.setDirection(1);
            zelda.getEnv().bougeEnvEnfonctionDeMap(KeyCode.Z);

        }
        if(isKeyPressed(KeyCode.D) && !zelda.getEnv().presenceCollisionPerso(+16, 0) && !zelda.getEnv().presenceCollisionTerrain((int) ((zelda.getX()+distanceDeplacement*2)-(mapPane.getTranslateX())), (int)((zelda.getY()+zelda.getTrompeurPositionY())-mapPane.getTranslateY()), zelda.getWidth(), zelda.getHeight(),zelda.getId(), zelda, terrain)) {
            zelda.setX(zelda.getX()+distanceDeplacement);
            zelda.setDirection(2);
            zelda.getEnv().bougeEnvEnfonctionDeMap(KeyCode.D);



        }
        if(isKeyPressed(KeyCode.S) && !zelda.getEnv().presenceCollisionPerso(0, 16) &&!zelda.getEnv().presenceCollisionTerrain((int) ((zelda.getX())-(mapPane.getTranslateX())),  (int)((zelda.getY()+zelda.getTrompeurPositionY()+distanceDeplacement*2)-mapPane.getTranslateY()), zelda.getWidth(), zelda.getHeight(),zelda.getId(), zelda, terrain)){
            zelda.setY(zelda.getY()+distanceDeplacement);
            zelda.setDirection(3);
            zelda.getEnv().bougeEnvEnfonctionDeMap(KeyCode.S);


        }
        if(isKeyPressed(KeyCode.Q) && !zelda.getEnv().presenceCollisionPerso(-16, 0) &&!zelda.getEnv().presenceCollisionTerrain((int) ((zelda.getX()-distanceDeplacement*2)-(mapPane.getTranslateX())),  (int)((zelda.getY()+zelda.getTrompeurPositionY())-mapPane.getTranslateY()), zelda.getWidth(), zelda.getHeight(),zelda.getId(), zelda, terrain)){
            zelda.setX(zelda.getX()-distanceDeplacement);
            zelda.setDirection(4);

            zelda.getEnv().bougeEnvEnfonctionDeMap(KeyCode.Q);


        }
        if (isKeyPressed(KeyCode.C)) {
        	zelda.getObjetProche();

        }
        if (isKeyPressed(KeyCode.X)) {
        	zelda.supprimerObjet(zelda.ItemEnMain());
        }
        if (isKeyPressed(KeyCode.SPACE) && zelda.getArmes() != null) {
        	if (System.currentTimeMillis() - tempsAction > zelda.getArmes().getCadence()* 1000) {
        		zelda.attaquer(zelda);
        		tempsAction = System.currentTimeMillis();
        	}
        }
//        else if (isKeyPressed(KeyCode.P)) {
//        	zelda.parlerPersoProximite(label, zelda);
//        	return;
//		}
        //zelda.setPremierPlanJoueur(terrainVue, pane, zelda);
        if (isKeyPressed(KeyCode.P) && !this.pAppuye) {
        	setPAppuye();
        	PersoQuete p = zelda.getPersoQueteProximite();
        	if (p!=null && (zelda.getQuêteEnCours() >= p.getOrdreQuete())) {
            	discussionVue.discuterAvecZelda(p, label, zelda, this, terrain);
			}else 
				setPNonAppuye();
		}
    }
    
    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            pressedKeys.add(event.getCode());
        } else if(event.getEventType() == KeyEvent.KEY_RELEASED){
            pressedKeys.remove(event.getCode());
        }
    }
    
    public void setPAppuye() {
    	this.pAppuye = true;
    }
    
    public void setPNonAppuye() {
    	this.pAppuye = false;
    }
    
    public void setYAppuye() {
    	this.yAppuye = true;
    }
    
    public void setYNonAppuye() {
    	this.yAppuye = false;
    }
    
    public boolean getYAppuye() {
    	return this.yAppuye;
    }
}
