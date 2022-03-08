package AppV1.modele.Items.Armes;

import AppV1.modele.Environnement;
import AppV1.modele.Items.Items;
import javafx.scene.layout.BorderPane;

public abstract class Armes extends Items {
    private int degats;
    private int cadence;
    int champs;

    private BorderPane p;
    
    public Armes(Environnement env, int x, int y, int degats, int cadence, int champs, BorderPane p, int cout) {
        super(env, x, y, cout);

        this.degats = degats;
        this.cadence = cadence;
        this.champs =champs;
        this.p = p;
    }
    public BorderPane getPanneau() {
    	return this.p;
    }


    public int getCadence() {
        return cadence;
    }

    public int getDegats() {
        return degats;
    }
    public abstract void utilisationArme(int x, int y);
}
