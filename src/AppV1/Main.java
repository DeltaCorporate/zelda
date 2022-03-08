package AppV1;

import AppV1.controleur.Controleur;
import AppV1.controleur.KeyBoardManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vue/vue.fxml"));
            KeyBoardManager keyBoardManager = new KeyBoardManager();
            Controleur controleur = new Controleur(keyBoardManager);

            loader.setController(controleur);
            BorderPane root = loader.load();

            Scene scene = new Scene(root,1200,655);


            primaryStage.setScene(scene);
            scene.addEventFilter(KeyEvent.ANY,keyBoardManager);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Zelda-triade");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
   

    public static void main(String[] args) {
        launch(args);
    }
}