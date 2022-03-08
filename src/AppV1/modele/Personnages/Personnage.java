package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.vue.Utils.Terrain;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Personnage implements Comparable<Personnage> {
    protected Environnement env;
    String id;
    static int cpt = 0;
    SimpleIntegerProperty x, y;
    IntegerProperty direction;
    int vitesse;
    String message;
    int width;
    int height;
    int trompeurPositionY;
    int fauxY;


    public Personnage(Environnement env, int x, int y) {
        this.id = "#p" + this.cpt;
        this.cpt++;
        this.direction = new SimpleIntegerProperty(1);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);

        this.vitesse = 1;
        this.message = "truc";
        this.env = env;

    }

    public int getFauxY() {
        return this.fauxY;
    }

    public void setFauxY(int fauxY) {
        this.fauxY = fauxY;
    }

    public int getTrompeurPositionY() {
        return this.trompeurPositionY;
    }

    public void setTrompeurPositionY(int trompeur) {
        this.trompeurPositionY = trompeur;
    }

    public int compareTo(Personnage p) {
        return (this.getFauxY() - p.getFauxY());
    }

    public void setwidth(int w) {
        this.width = w;
    }

    public void setheight(int h) {
        this.height = h;
    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public final int getDirection() {
        return this.direction.getValue();
    }

    public final IntegerProperty getDirectionProperty() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction.setValue(direction);
    }

    public final int getX() {
        return this.x.getValue();
    }

    public final SimpleIntegerProperty getXProperty() {
        return this.x;
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public final int getY() {
        return this.y.getValue();
    }

    public final SimpleIntegerProperty getYProperty() {
        return this.y;
    }

    public void setY(int y) {
        this.y.setValue(y);
    }


    public String getId() {
        return this.id;
    }


    public abstract boolean seDeplacer(Zelda zelda, Terrain terrain, int paneTranslateX, int paneTranslateY);

    //issue de héritage
    public void donnerObjet(Personnage p) {};

    public Environnement getEnv() {
        return this.env;
    }


    //Donne un x et y random tout en verifiant les collisions lors de la premiere apparition
    public void setPersonnagePosition(Zelda zelda, Terrain terrain) {
        if (this instanceof Zelda) {
            this.setX(16 * terrain.getTuileLength());
            this.setY(17 * terrain.getTuileLength());
        } else if (this instanceof Princesse) {
            this.setX(17 * terrain.getTuileLength());
            this.setY(30 * terrain.getTuileLength());
        } else if (this instanceof Magicien) {
            this.setX(-4 * terrain.getTuileLength());
            this.setY(0);
        } else if (this instanceof Chevalier) {
            this.setX(0);
            this.setY(12 * terrain.getTuileLength());
        } else if (this instanceof Voleur) {
            this.setX(30 * terrain.getTuileLength());
            this.setY(29 * terrain.getTuileLength());
        } else if (this instanceof Loup) {
            this.setX(-8 * terrain.getTuileLength());
            this.setY(-3 * terrain.getTuileLength());
        } else if (this instanceof Sanglier) {
            this.setX(18 * terrain.getTuileLength());
            this.setY(0);
        } else if (this instanceof Poule) {
            this.setX(7 * terrain.getTuileLength());
            this.setY(6 * terrain.getTuileLength());
        } else if (this instanceof Mouton) {
            this.setX(13 * terrain.getTuileLength());
            this.setY(29 * terrain.getTuileLength());
        } else if (this instanceof Dragon) {
            this.setX(32 * terrain.getTuileLength());
            this.setY(-10 * terrain.getTuileLength());
        }

    }

    public boolean conditionCollision(int xToTest, int yToTest, int width, int height, String idUtilise) {
        if ((!this.getId().equals(idUtilise) &&
                this.getX() + this.width > xToTest &&
                this.getX() < xToTest + width &&
                yToTest < (this.getY() + this.getTrompeurPositionY() + this.height)
                && (height + yToTest) > this.getY() + this.getTrompeurPositionY())) {

            return true;

        }
        return false;
    }
}
