package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class PersoOffensif extends PersoTuable {

    private int ptsAttaque;
    private SimpleIntegerProperty ArgentPorteMonnaie;

    public PersoOffensif(Environnement env, int x, int y, int ptsVie, int ptsAttaque) {
        super(env, x, y, ptsVie);
        this.ptsAttaque = ptsAttaque;
        this.ArgentPorteMonnaie = new SimpleIntegerProperty(20);
    }


    public int getPtsAttaque() {
        return this.ptsAttaque;
    }


    public void setArgentPorteMonnaie(int argentPorteMonnaie) throws Exception {
        if (argentPorteMonnaie < 0) throw new Exception() {

        };
        this.ArgentPorteMonnaie.setValue(argentPorteMonnaie);
    }

    public SimpleIntegerProperty getArgentPorteMonnaieProperty() {
        return ArgentPorteMonnaie;
    }

    public abstract void attaquer(Zelda p) throws Exception;


    public int getArgentPorteMonnaie() {
        return ArgentPorteMonnaie.getValue();
    }


}
