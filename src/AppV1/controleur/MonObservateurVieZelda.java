package AppV1.controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class MonObservateurVieZelda implements ChangeListener<Number>{
	private Pane pane;
	
	public MonObservateurVieZelda(Pane pane) {
		super();
		this.pane = pane;
	}

	private void creerCoeur(int nouvelleVie) {
      Image img1Coeur = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/1coeur.png")).toExternalForm(), 110, 16, true, true);
      Image img2Coeur = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/2coeur.png")).toExternalForm(), 110, 16, true, true);
      Image img3Coeur = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/3coeur.png")).toExternalForm(), 110, 16, true, true);
      Image img4Coeur = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/4coeur.png")).toExternalForm(), 110, 16, true, true);
      Image img5Coeur = new Image (Objects.requireNonNull(getClass().getResource("../vue/Images/5coeur.png")).toExternalForm(), 110, 16, true, true);
     
      ImageView coeurView = null;

      if (nouvelleVie <= 20) {
          coeurView = new ImageView(img1Coeur);
      } else if(nouvelleVie <= 40){
          coeurView = new ImageView(img2Coeur);
      }else if(nouvelleVie <= 60){
          coeurView = new ImageView(img3Coeur);
      }else if(nouvelleVie <= 80){
          coeurView = new ImageView(img4Coeur);
      }else if(nouvelleVie <= 100){
          coeurView = new ImageView(img5Coeur);
      }

      if(coeurView != null){
    	  coeurView.setX(5);
    	  coeurView.setY(5);
          coeurView.setId(""+nouvelleVie);
          this.pane.getChildren().add(coeurView);
      }
    }
	public void enleverCoeur(int ancienneVie) {
		this.pane.getChildren().remove(pane.lookup("#"+ ancienneVie));
	}


	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		creerCoeur((int)newValue);
		enleverCoeur((int)oldValue);
	}
}
