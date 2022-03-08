package AppV1.modele.Items;

import AppV1.modele.Environnement;
import AppV1.modele.Personnages.Zelda;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public abstract class Items {
    private Environnement env;
    private String id;
    private static int cpt = 0;
    private SimpleIntegerProperty x,y;
    private int cout;

    public Items(Environnement env, int x, int y, int cout){
        this.id = "#obj"+cpt;
        cpt++;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.cout = cout;
    }
    public String getId() {
        return this.id;
    }
    public final int getX() {
        return this.x.getValue();
    }

    public final int getY() {
        return this.y.getValue();
    }

    public final SimpleIntegerProperty getXProperty() {
        return this.x;
    }

    public final SimpleIntegerProperty getYProperty() {
        return this.y;
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public void setY(int y) {
        this.y.setValue(y);
    }
    public Environnement getEnvironnement() {
    	return this.env;
    }
    public void setRandomPosition(Zelda zelda) {
        Random random = new Random();
        int low = 10;
        int high = 500;
        int randomX,randomY;
        randomX = random.nextInt(high-low) + low;
        randomY = random.nextInt(high-low) + low;
        this.setX(randomX);
        this.setY(randomY);
    }
	public int getCout() {
		return cout;
	}
}
