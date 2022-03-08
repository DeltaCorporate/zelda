package AppV1.modele.Items.Armes;

import AppV1.modele.Environnement;
import AppV1.modele.Personnages.PersoTuable;
import AppV1.modele.Personnages.Personnage;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Affichages.AffichagePersonnage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class ArmesDistance extends Armes{

	public ArmesDistance(Environnement env, int x, int y, int degats, int cadence, int champs, BorderPane p, int cout) {
		super(env, x, y, degats, cadence, champs, p, cout);
		// TODO Auto-generated constructor stub
	}

		@Override
		public void utilisationArme(int x, int y) {
			final int direction; // la direction de la balle ne peut pas changer le temps de la methode
			Timeline gameLoop = new Timeline();
			gameLoop.setCycleCount(Timeline.INDEFINITE);
			Zelda z = this.getEnvironnement().getZelda();
			direction = z.getDirection();
			
			AffichagePersonnage munition = new AffichagePersonnage();
			ImageView balle = munition.afficherBalle(z, this.getPanneau(),direction,x,y);
			balle.setId("m1");
			KeyFrame f = new KeyFrame(
					Duration.millis(10),
					(ev ->{
						if (distanceMax(champs, x, y, balle) && ! getPersonnageProche(balle) && !balle.getId().equals("null")) {
								this.avancerBalle(direction, balle);
						}
					 	else {
							balle.setId("null");
							this.getPanneau().getChildren().remove(balle);

						}
						}));
			gameLoop.getKeyFrames().add(f);
			gameLoop.play();
		}
		
		private Boolean getPersonnageProche(ImageView c) { // si il y'a un personnage proche de l'image view ou non 
			for (Personnage p : this.getEnvironnement().getListePersonnage()) {
				if ((c.getY()-p.getY()>=-16 && c.getY()-p.getY()<=16) && (c.getX()-p.getX()>=-32 && c.getX()-p.getX()<=32 ) && ! (p instanceof Zelda) && !c.getId().equals("null")) {
					if ((p instanceof PersoTuable)) {
						((PersoTuable) p).perdreVie(this.getDegats());
					}
					return true;

				}
			}
			return false;
		}
		public void avancerBalle(int direction , ImageView balle) {

			if (direction == 1) {
				balle.setY(balle.getY()-2);
			}
			if (direction == 2) {
				balle.setX(balle.getX()+2);
			}
			if (direction == 3) {
				balle.setY(balle.getY()+2);
			}
			if (direction == 4) {
				balle.setX(balle.getX()-2);
			}
		}
		public boolean distanceMax(int champs, int x, int y, ImageView balle) {
			if ((balle.getY()>= y - this.champs -16 && balle.getY()<= y + this.champs+16) && (balle.getX()<= x + this.champs +8 && balle.getX()>= x - this.champs - 8)) {
				return true;
			}
			return false;
		}
}