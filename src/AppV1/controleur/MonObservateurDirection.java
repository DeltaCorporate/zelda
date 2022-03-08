package AppV1.controleur;

import AppV1.modele.Personnages.Zelda;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class MonObservateurDirection implements ChangeListener<Number> {

		private Pane pane;
		private Zelda zelda;
		
		public MonObservateurDirection(Pane p, Zelda z) {
			super();
			this.pane = p;
			this.zelda = z;
		}
		
		
		public ImageView creerSprite(Pane pane, int direction) {
	        Image imgZeldaGauche = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/zelda4.png")).toExternalForm(), 16, 32,true, true);
	        Image imgZeldaDroite = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/zelda2.png")).toExternalForm(), 16, 32,true, true);
	        Image imgZeldaHaut = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/zelda1.png")).toExternalForm(), 16, 32,true, true);
	        Image imgZeldaBas = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/Personnages/zelda3.png")).toExternalForm(), 16, 32,true, true);
	        ImageView persoView = null;
	        
	        if (direction == 1) {
	            persoView = new ImageView(imgZeldaHaut);
	        }
	        else if (direction == 2) {
	            persoView = new ImageView(imgZeldaDroite);
	        }
	        else if (direction == 3) {
	            persoView = new ImageView (imgZeldaBas);
	        }
	        else if(direction == 4){
	            persoView = new ImageView(imgZeldaGauche);
	        }

	        if(persoView != null){
	            persoView.setId(this.zelda.getId());
	            persoView.translateXProperty().bind(this.zelda.getXProperty());
	            persoView.translateYProperty().bind(this.zelda.getYProperty());
	            pane.getChildren().add(persoView);
	        }
	        return persoView;
	    }
		
		public void enleverSprite(Pane pane) {
			pane.getChildren().remove(pane.lookup("#"+zelda.getId()));

		}

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			enleverSprite(pane);
			creerSprite(pane, (int)newValue);
		}
}
