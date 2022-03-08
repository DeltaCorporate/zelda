package AppV1.vue;

import AppV1.controleur.KeyBoardManager;
import AppV1.modele.Personnages.PersoQuete;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Utils.Terrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

public class DiscussionVue {

     public DiscussionVue(){
     }
     
    public void discuterAvecZelda(PersoQuete p, Label label, Zelda zelda, KeyBoardManager k, Terrain terrain) {
    	double TauxDeDeplacementInitial = p.getTauxDeDeplacement();
    	ArrayList<String> message;
    	int zeldaXInitial = zelda.getX();
    	int zeldaYInitial = zelda.getY();
    	p.setTauxDeDeplacement(0);
    	if (p.getEtatQuete()==1) {
    		p.creerObjetQuete(terrain);
	    	message = p.getListeMessage1();
		}
    	else if (p.getEtatQuete()==2 && p.presenceObjetQuete()==null) {
    		p.setEtatQuete(p.getEtatQuete()-1);
			message = p.getListeMessage1();
		}else if (p.getEtatQuete()==2 && p.presenceObjetQuete()!=null) {
			p.prendreUnObjet(p.presenceObjetQuete());
			zelda.getEnv().removeObjet(p.presenceObjetQuete());
			zelda.getlisteObjets().remove(p.presenceObjetQuete());
			zelda.setNbObjetsHorsCle(zelda.getNbObjetsHorsCle()-2);
        	zelda.setQueteEnCours(zelda.getQueteEnCours()+1);
			zelda.prendreUnObjet(p.getFragmentCle());
			p.getlisteObjets().remove(p.getFragmentCle());
			message = p.getListeMessage2();
		}else {
	    	message = p.getMessageFinal();
		}
    	
 		Timeline timeLine = new Timeline();
 		timeLine.setCycleCount(Timeline.INDEFINITE);
 		KeyFrame f = new KeyFrame(
 				Duration.seconds(3),
 				(ev ->{
 					if (p.getMessageEnCours()<message.size()-1 && zelda.getX() == zeldaXInitial && zelda.getY() == zeldaYInitial) {
 						label.setText(message.get(p.getMessageEnCours()));
 						p.setMessageEnCours(p.getMessageEnCours()+1);
 					}
 					else {
 						label.setText(message.get(message.size()-1));
 						p.setMessageEnCours(0);
 						p.setTauxDeDeplacement(TauxDeDeplacementInitial);
 						k.setPNonAppuye();
 						p.setEtatQuete(p.getEtatQuete()+1);
 						timeLine.stop();
 					}
 				}
 				));
 		timeLine.getKeyFrames().add(f);
 		timeLine.play();
    }
}
