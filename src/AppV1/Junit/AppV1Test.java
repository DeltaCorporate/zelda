package AppV1.Junit;

import AppV1.modele.Environnement;
import AppV1.modele.Items.Armes.Gun;
import AppV1.modele.Magasin;
import AppV1.modele.Personnages.Loup;
import AppV1.modele.Personnages.Sanglier;
import AppV1.modele.Personnages.Voleur;
import AppV1.modele.Personnages.Zelda;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppV1Test {
	BorderPane p = new BorderPane();
	Environnement env = new Environnement(512,512);
	Magasin m = new Magasin(env, p, p);
	Zelda z = new Zelda(env, 168, 56, 100, 0, m);
	Voleur v = new Voleur(env, 160, 50, 100, 50, 0.5, 30);
	@Test
	void testAchatMagasin() {
		try {
			m.acheterGun(z);
			m.acheterPotion1(z);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(z.getArgentPorteMonnaie(), 12);
	}
	
	@Test
	void testAttaqueVoleur() {
		try {
			v.attaquer(z);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals (z.getArgentPorteMonnaie(), 19);
	}
	@Test
	void testInventaireZelda() {
		Gun g = new Gun (env, 168, 56,1, 100, p);
		z.prendreUnObjet(g);
		assertTrue (z.existeArmeListe());
		z.prendreEnMain(g);
		assertFalse(z.rienEnMain());
		z.deposseder(g);
		assertTrue(z.rienEnMain());
	}
	@Test
	void testAttaquePredateur() {
		z.setX(280);
		z.setY(136);
		
		Sanglier s = new Sanglier(env);
		Loup l = new Loup (env);
		l.setX(275);
		l.setY(130);
		s.setX(275);
		s.setY(130);
		s.porterAttaque(z);
		assertEquals(z.getPtsVie(), 85);
		l.porterAttaque(z);
		assertEquals(z.getPtsVie(), 73);
	}
}
