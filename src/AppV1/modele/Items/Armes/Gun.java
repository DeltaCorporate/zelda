package AppV1.modele.Items.Armes;

import AppV1.modele.Environnement;
import javafx.scene.layout.BorderPane;

public class Gun extends ArmesDistance{

    public Gun(Environnement env, int x, int y, int degats, int cadence, BorderPane p) {
        super(env, x, y, degats, cadence,128, p, 5);

    }
}
