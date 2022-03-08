package AppV1.modele.Items;

import AppV1.modele.Environnement;
import AppV1.modele.Personnages.Zelda;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PotionVie extends Items{
	private int compteurDureeEffet;

	public PotionVie(Environnement env, int x, int y) {
		super(env, x, y, 3);
		this.compteurDureeEffet = 0;
	}

	
	public void consommer(Zelda zelda) {
		int dureeMaxEffet = 3;
		Timeline timeLine = new Timeline();
 		timeLine.setCycleCount(Timeline.INDEFINITE);
 		KeyFrame f = new KeyFrame(
 				Duration.seconds(2),
 				(ev ->{
 					if (zelda.getPtsVie() > 80 || compteurDureeEffet == dureeMaxEffet ) {
 						this.compteurDureeEffet = 0;
 	 					timeLine.stop();						
					}
 					else {
						zelda.setPtsVie(zelda.getPtsVie()+20);
						this.compteurDureeEffet++;
					}
 				}
 				));
 		timeLine.getKeyFrames().add(f);
 		timeLine.play();
	}
}
