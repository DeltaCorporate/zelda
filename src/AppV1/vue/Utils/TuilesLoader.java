package AppV1.vue.Utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TuilesLoader {
    private Map<Integer, Rectangle2D> tuiles;
    private String tileURL;
    public TuilesLoader(String tileURL,int tuileLength){
        this.tileURL = tileURL;
        this.tuiles = tuileLoad(tuileLength);
    }

    public Rectangle2D getRectangle2D(int key){
        return tuiles.get(key);
    }

    public Image loadImage(String image){
        try{
            return new Image(Objects.requireNonNull(getClass().getResource(image)).toExternalForm());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Map<Integer,Rectangle2D> tuileLoad(int tuileLength){
        Map<Integer,Rectangle2D> tuiles = new HashMap<>();
        Image tiles  = loadImage(tileURL);
        int cpt = 1;
        for(int colonne = 0;colonne<tiles.getHeight()/tuileLength; colonne++){
            for(int ligne = 0; ligne<tiles.getWidth()/tuileLength; ligne++){
                tuiles.put(cpt,new Rectangle2D(ligne*tuileLength,colonne*tuileLength,tuileLength,tuileLength));
                cpt++;
            }
        }

        return tuiles;
    }

    public Image getImage(){
        return loadImage(tileURL);
    }


}
