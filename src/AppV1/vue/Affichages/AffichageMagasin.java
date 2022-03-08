package AppV1.vue.Affichages;

import AppV1.modele.Personnages.Zelda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class AffichageMagasin {

	
	public void afficherMagasin(Zelda zelda, Pane pane, Label info) {
		Image imgMagasin = new Image (Objects.requireNonNull(getClass().getResource("../../vue/Images/magasin.png")).toExternalForm(), 244, 530,true, true);
		ImageView fondDeMagasin = new ImageView(imgMagasin);
		Image imgPotion = new Image (Objects.requireNonNull(getClass().getResource("../../vue/Images/boutonPotionRapidite.png")).toExternalForm(), 195, 70,true, true);
		ImageView potionVitesse = new ImageView(imgPotion);
		Image imgNourriture = new Image (Objects.requireNonNull(getClass().getResource("../../vue/Images/boutonNourriture.png")).toExternalForm(), 195, 70,true, true);
		ImageView nourriture = new ImageView(imgNourriture);
		Image imgGun = new Image (Objects.requireNonNull(getClass().getResource("../../vue/Images/boutonGun.png")).toExternalForm(), 195, 70,true, true);
		ImageView gun = new ImageView(imgGun);
		Image imgCleFinale = new Image (Objects.requireNonNull(getClass().getResource("../../vue/Images/boutonCle.png")).toExternalForm(), 195, 70,true, true);
		ImageView cleFinale = new ImageView(imgCleFinale);
//		Image imgFondLabel = new Image (Objects.requireNonNull(getClass().getResource("../../vue/Images/fondLabel.png")).toExternalForm(), 195, 45,true, true);
//		ImageView fondLabel  = new ImageView(imgFondLabel);
		
		
		Pane paneMagasin = new Pane(fondDeMagasin);
		paneMagasin.setId("#panneauMagasin");
        paneMagasin.setLayoutX(932);
        paneMagasin.setLayoutY(12);
        
        Label label = new Label();
        label.setPrefHeight(45);
        label.setPrefWidth(195);
        label.setFont(new javafx.scene.text.Font(20));
        label.setTextFill(Color.web("#ffffff"));
        label.setLayoutX(30);
        label.setLayoutY(310);
        
        paneMagasin.getChildren().add(label);
        
        Button b = new Button("", potionVitesse) {
		};
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	label.setText("");
            	try {
            		String retourMagasin = zelda.getMagasin().acheterPotion1(zelda);
                    label.setText(retourMagasin);
				} catch (Exception e) {
					label.setText("Pas assez d'argent");
				}
            }
        });
       
        b.setPrefWidth(195);
        b.setPrefHeight(70);
        b.setLayoutX(25);
        b.setLayoutY(80);
        b.setPadding(Insets.EMPTY);
        paneMagasin.getChildren().add(b);
        
        Button b2 = new Button("", gun);
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	label.setText("");
            	try {
            		String retourMagasin = zelda.getMagasin().acheterGun(zelda);
                    label.setText(retourMagasin);
            	} catch (Exception e) {
					label.setText("Pas assez d'argent");
				}
            }
        });
        b2.setPadding(Insets.EMPTY);

        b2.setPrefWidth(195);
        b2.setPrefHeight(70);
        b2.setLayoutX(25);
        b2.setLayoutY(160);
        paneMagasin.getChildren().add(b2);
        
        Button b3 = new Button("", nourriture);
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	label.setText("");
            	try {
                String retourMagasin = zelda.getMagasin().acheterNourriture(zelda);
                label.setText(retourMagasin);
            	} catch (Exception e) {
					label.setText("Pas assez d'argent");
				}
            }
        });
        b3.setPrefWidth(195);
        b3.setPrefHeight(70);
        b3.setLayoutX(25);
        b3.setLayoutY(240);
        b3.setPadding(Insets.EMPTY);
        paneMagasin.getChildren().add(b3);
        
        Button b4 = new Button("", cleFinale);
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String retourMagasin =  zelda.getMagasin().acheterCle(zelda, info);
                label.setText(retourMagasin);
            }
        });
        b4.setPrefWidth(195);
        b4.setPrefHeight(70);
        b4.setLayoutX(25);
        b4.setLayoutY(390);
        b4.setPadding(Insets.EMPTY);
        paneMagasin.getChildren().add(b4);

		pane.getChildren().add(paneMagasin);

	}
}
