package AppV1.modele.Items.Armes;

import AppV1.modele.Environnement;
import AppV1.modele.Personnages.PersoTuable;
import AppV1.modele.Personnages.Zelda;
import javafx.scene.layout.BorderPane;

public class Epee extends Armes{

    public Epee(Environnement env, int x, int y, int degat, int cadence, BorderPane p) {
        super(env, x, y, degat, cadence,16, p, 20);


    }

    @Override
	public void utilisationArme(int x, int y) {
		for (AppV1.modele.Personnages.Personnage p : this.getEnvironnement().getListePersonnage()) {
			if (this.getX() - p.getX()<=32 && this.getX() - p.getX()>=-32 && this.getY() - p.getY() <= 32 && this.getY() - p.getY()>=-32 && !(p instanceof Zelda)) {
				if ((p instanceof PersoTuable)) {
					((PersoTuable) p).perdreVie(this.getDegats());
				}
			}
		}
	}
}
