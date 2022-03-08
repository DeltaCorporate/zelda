package AppV1.modele.Items.Armes;

import AppV1.modele.Environnement;
import javafx.scene.layout.BorderPane;

public class Bouclier extends Armes {
    public Bouclier(Environnement env, int x, int y, int degats, int cadence, BorderPane p) {
        super(env, x, y, degats, cadence, 16, p, 20);
    }

	@Override
	public void utilisationArme(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
