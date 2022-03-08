package AppV1.vue.Affichages;

import AppV1.vue.Utils.Terrain;
import AppV1.vue.Utils.TuilesLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Objects;

public class AffichageTerrain {
    private final String tileUrl;

    public AffichageTerrain(String tileUrl){
        this.tileUrl= tileUrl;
    }


    public Image loadImage(String image){
        try{
            return new Image(Objects.requireNonNull(getClass().getResource(image)).toExternalForm());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void afficherTerrain(Pane mapPane, Terrain terrain){
        Image tiles  = loadImage(tileUrl);
        //int[] codeTuiles = terrain.chargerCartes();
        TuilesLoader tuilesLoader = new TuilesLoader(tileUrl,terrain.getTuileLength());
        ArrayList<int[][]> listCalques = terrain.getListeCalques();
        listCalques.forEach(codeTuiles ->{
            TilePane tilePane = new TilePane();
            mapPane.getChildren().add(tilePane);
            tilePane.setPrefWidth(1024);
            tilePane.setPrefHeight(1024);
            for(int colonne = 0;colonne<terrain.getHeight();colonne++){
                for(int ligne = 0 ;ligne<terrain.getWidth();ligne++){
                    ImageView iv = new ImageView(tiles);

                    iv.setViewport(tuilesLoader.getRectangle2D(codeTuiles[colonne][ligne]));
                    if(tuilesLoader.getRectangle2D(codeTuiles[colonne][ligne]) == null){
                    }
                    tilePane.getChildren().add(iv);
                }

            }

        });
        /*TilePane tilePane = new TilePane();
        mapPane.getChildren().add(tilePane);
        tilePane.setPrefWidth(512);
        tilePane.setPrefHeight(512);

        ImageView iv = new ImageView(tiles);

        iv.setViewport(tuilesLoader.getRectangle2D(1439));
        tilePane.getChildren().add(iv);*/



    }
    
}
