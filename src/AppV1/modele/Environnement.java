package AppV1.modele;

import AppV1.modele.Items.Armes.Arc;
import AppV1.modele.Items.Armes.Armes;
import AppV1.modele.Items.Armes.Epee;
import AppV1.modele.Items.Armes.Gun;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.modele.Personnages.Animal;
import AppV1.modele.Personnages.Chevalier;
import AppV1.modele.Personnages.Loup;
import AppV1.modele.Personnages.Magicien;
import AppV1.modele.Personnages.Mouton;
import AppV1.modele.Personnages.PersoQuete;
import AppV1.modele.Personnages.PersoTuable;
import AppV1.modele.Personnages.Personnage;
import AppV1.modele.Personnages.Poule;
import AppV1.modele.Personnages.Predateur;
import AppV1.modele.Personnages.Princesse;
import AppV1.modele.Personnages.Sanglier;
import AppV1.modele.Personnages.Voleur;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Utils.Terrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class Environnement {
    private int x, y;
    private ObservableList<Personnage> listePers;
    private ObservableList<Items> items;

    public Environnement(int x, int y) {
        this.x = x;
        this.y = y;
        this.listePers = FXCollections.observableArrayList();
        this.items = FXCollections.observableArrayList();
    }

    public ObservableList<Animal> getAnimaux() {
        ObservableList<Animal> animaux = FXCollections.observableArrayList();
        for (Personnage p : this.listePers) {
            if (p instanceof Animal) {
                animaux.add((Animal) p);
            }
        }
        return animaux;
    }

    public ArrayList<PersoQuete> getPersoQuete() {
        ArrayList<PersoQuete> testeur = new ArrayList<>();

        for (Personnage p : this.listePers) {
            if (p instanceof Magicien || p instanceof Chevalier || p instanceof Princesse) {
                testeur.add((PersoQuete) p);
            }
        }
        return testeur;
    }

    public void addPersonnage(Personnage p) {
        this.listePers.add(p);
    }


    public ObservableList<Personnage> getListePersonnage() {
        return this.listePers;
    }
    
    public void deplacerEnnemis(Zelda z, Terrain t, int paneTranslateX, int paneTranslateY){
    	for (Personnage personnage : listePers) {
			if (personnage instanceof Predateur || personnage instanceof Voleur) {
				personnage.seDeplacer(z, t, paneTranslateX, paneTranslateY);
			}
		}
    }



    public Zelda getZelda() {
        for (Personnage p : this.listePers) {
            if (p instanceof Zelda) {
                return (Zelda) p;
            }
        }
        return null;
    }

    public void addObjet(Items o) {
        this.items.add(o);
    }

    public boolean verifSiArmeEnPlace() {
        for (Items o : items) {
            if (o instanceof Armes) {
                return true;
            }
        }
        return false;
    }

    public void removeObjet(Items o) {
        this.items.remove(o);
    }

    public ObservableList<Items> getItems() {
        return items;
    }


    //Verifie si le x et y passé en paramètre sont pas trop près des x et y des personnages de l'environnement et de zelda
    public boolean presenceCollisionTerrain(int xTesting, int yTesting, int width, int height, String idUtilise, Personnage zelda, Terrain terrain) {
        boolean collision = false;
        //En définissant 10 comme distance minimal, il faudrait que la différence de coordonnées entre 2 personnages
        //Ne soit pas comprise entre la distance de collision inf et sup


        for (Items o : this.getItems()) {
            if ((!o.getId().equals(idUtilise) && o.getX() + 30 > xTesting && o.getX() < xTesting + width && yTesting < (o.getY())) && (yTesting) > o.getY()) {
                collision = true;
            }
        }


        if (zelda.conditionCollision(xTesting, yTesting, width, height, idUtilise)) {
            collision = true;
        }
        //Verifie si perso qui est vérifié est en collision avec chaque personne de l'environnement
        for (int i = 0; i < width; i += terrain.getTuileLength()) {
            for (int j = 0; j < height; j += terrain.getTuileLength()) {
                if (collisionTerrain(xTesting + i, yTesting + j, terrain)) {
                    collision = true;
                }
            }
        }
        return collision;
    }

    public boolean presenceCollisionPerso(int x, int y){
        boolean collision = false;
        Zelda zelda = this.getZelda();
        int xZelda = zelda.getX() + x;
        int yZelda = zelda.getY() + y +16;

        for (Personnage p : this.listePers) {
            if (p.conditionCollision(xZelda, yZelda, zelda.getWidth(), zelda.getHeight()-8, zelda.getId())) {
                collision = true;
            }
        }
        return collision;
    }


    public boolean collisionTerrain(int x, int y, Terrain terrain) {
        boolean collisionTerrain = false;

        if (conditionsCollisionTerrain(x, y, terrain)) {
            collisionTerrain = true;
        }
        return collisionTerrain;

    }

    public boolean conditionsCollisionTerrain(int x, int y, Terrain terrain) {
        boolean collision = false;
        //int tabTerrain[][] = terrain.getListeCalques(0);

        //505
        if (y >= 1024 || y <= 0 || x < 0 || x >= 1024 || terrain.estUnObstacle(y / terrain.getTuileLength(), x / terrain.getTuileLength())) {
            collision = true;
        }



        return collision;
    }

    public Personnage getPersonnage(String id) {
        for (Personnage p : this.listePers) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void bougeEnvEnfonctionDeMap(KeyCode keyCode){
        switch (keyCode){
            case Z:
                for(int i = 0; i<listePers.size();i++){
                    Personnage p = listePers.get(i);
                    if(!(p instanceof Zelda)){
                        p.setY(p.getY()+8);
                    }
                }
                for(int i = 0; i<items.size();i++){
                    Items o = items.get(i);
                    if(!getZelda().getlisteObjets().contains(o)){
                        o.setY(o.getY()+8);
                    }
                }
                break;
                case S:
                for(int i = 0; i<listePers.size();i++){
                    Personnage p = listePers.get(i);
                    if(!(p instanceof Zelda)){
                        p.setY(p.getY()-8);
                    }
                }
                    for(int i = 0; i<items.size();i++){
                        Items o = items.get(i);
                        if(!getZelda().getlisteObjets().contains(o)){
                            o.setY(o.getY()-8);
                        }
                    }
                break;
                case Q:
                for(int i = 0; i<listePers.size();i++){
                    Personnage p = listePers.get(i);
                    if(!(p instanceof Zelda)){
                        p.setX(p.getX()+8);
                    }
                }
                    for(int i = 0; i<items.size();i++){
                        Items o = items.get(i);
                        if(!getZelda().getlisteObjets().contains(o)){
                            o.setX(o.getX()+8);
                        }
                    }
                break;
                case D:
                for(int i = 0; i<listePers.size();i++){
                    Personnage p = listePers.get(i);
                    if(!(p instanceof Zelda)){
                        p.setX(p.getX()-8);
                    }
                }
                    for(int i = 0; i<items.size();i++){
                        Items o = items.get(i);
                        if(!getZelda().getlisteObjets().contains(o)){
                            o.setX(o.getX()-8);
                        }
                    }
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void suppMort() {
        for (int i = 0; i < this.listePers.size(); i++) {
            Personnage p = this.listePers.get(i);
            if (p instanceof PersoTuable && !((PersoTuable) p).estEnVie()) {
                this.getListePersonnage().remove(p);
            }
        }
    }
    public void creerObjet(BorderPane p) {
        this.addObjet(new Gun(this,350,230,75,1,p));
        this.addObjet(new Epee(this, 300, 200, 50, 1, p));
        this.addObjet(new Epee(this, 300, 200, 50, 1, p));
        this.addObjet(new Epee(this, 300, 200, 50, 1, p));
        this.addObjet(new Arc(this, 300,200, 20, 1, p));
        this.addObjet(new Arc(this, 300,200, 20, 1, p));
    }

    public void creePersos() {
        FragmentCle f = new FragmentCle(this, 0, 0);
        this.addPersonnage(new Chevalier(this, 200, 100, f));
        this.addPersonnage(new Magicien(this, 100, 100, f));
        this.addPersonnage(new Princesse(this, 300, 100, f));
        this.addPersonnage(new Voleur(this, 400, 100, 230, 50,0.6,25));
        this.addPersonnage(new Poule(this));
		this.addPersonnage(new Sanglier(this));
		this.addPersonnage(new Loup(this));
		this.addPersonnage(new Mouton(this));
    }

}
