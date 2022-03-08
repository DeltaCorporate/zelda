package AppV1.controleur;

import AppV1.modele.Items.CleFinale;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MonObservateurItemZelda implements ListChangeListener<Items>{
	private Pane p;
	private static int i = 0;
	private static int posX = 0;
	private static int posXCle = 0; 
	private static int posYButton = 60;
	private static int posYCle = 300;
	private ArrayList<Button> buttons;
	public MonObservateurItemZelda(Pane p) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.buttons = new ArrayList<>();
	}
	private void creerButtonInterface(Items o) {
			BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("../vue/Images/boutonInventaire.png").toExternalForm()), BackgroundRepeat.NO_REPEAT,
		            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background background = new Background(backgroundImage);
			ImageView v = creerImageViewItems(o);
    		Button l = new Button ();
    		buttons.add(l);
    		l.setId(o.getId());
    		//this.buttons.add(l);
    		v.setFitWidth(35);
    		v.setFitHeight(35);
    		l.setBackground(background);
    		l.setGraphic(v);
    		p.getChildren().add(l);
	}
	private void creerCleInterface (Items o) {
		ImageView v = creerImageViewItems(o);
		v.setFitWidth(50);
		v.setFitHeight(50);
		v.setLayoutX(posXCle);
		v.setLayoutY(posYCle);
		p.getChildren().add(v);
		i++;
		if (i%3 == 0) {
			posXCle = 0;
		}
		else {
			posXCle += 60;
		}
		
	}
	private ImageView creerImageViewItems (Items o) {
		MonObservateurItem obs = new MonObservateurItem(p); 
		ImageView v = obs.creerObjet(o, p);
		return v;
	}
	private void attribuerPlaceBoutons() {
		int i = 0;
		for (Button e : this.buttons) {
			if (i == 0) {
				posX = 7;
			}
				posX  = 60*i+7;
	    		e.setLayoutX(posX);
	    		e.setLayoutY(posYButton);
	    		i++;
			}
		i = 0;
		posX = 0;
	}
	private void enleverButton(Items o) {
		Button b = getButton(o.getId());
		p.getChildren().remove(p.lookup("#"+o.getId()));
		this.buttons.remove(b);
	}
	private Button getButton(String id) {
		for (Button b : this.buttons) {
			if (b.getId().equals(id)) {
				return b;
			}
		}
		return null;
	}
	@Override
	public void onChanged(Change<? extends Items> c) {
		// TODO Auto-generated method stub
		while (c.next()) {
			for (Items a : c.getAddedSubList()) {
				if (!(a instanceof FragmentCle || a instanceof CleFinale)) {
					creerButtonInterface(a);
					attribuerPlaceBoutons();
				}
				else {
					creerCleInterface(a);
				}
			}
			for (Items supp : c.getRemoved()) {
				enleverButton(supp);
				attribuerPlaceBoutons();
			}
		}
	}

}