package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.modele.Items.ChapeauMagique;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.vue.Utils.Terrain;

import java.util.ArrayList;

public class Magicien extends PersoQuete{

	public Magicien(Environnement env, int x, int y, FragmentCle f) {
		super(env, x, y, 2, f, 0.35, recupererMessageEnCours1(), recupererMessageEnCours2(), recupererMessageEnCours3());
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<String> recupererMessageEnCours1() {
 		String messageMagicien1 = "Magicien : Salut à toi jeune entrepreneur";
 		String messageZelda1 = "Zelda : Bonjour monsieur le magicien, j'ai besoin de vous";
 		String messageZelda2 = "Zelda : Je recherche un morceau de clé, en avez vous un ?";
 		String messageMagicien2 = "Magicien : Oui absolument, mais il faudrat me rendre service pour l'avoir";
 		String messageMagicien3 = "Magicien : J'ai besoin d'une chapeau magique très rare";
 		String messageZelda3 = "Zelda : Je vais de ce pas le chercher !";
 		String messageMagicien4 = "Magicien : Reviens me voir uniquement si tu as ce dont j'ai besoin";
 		String messageQuete = "Allez chercher le chapeau magique";
 		
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		listeMessage.add(messageMagicien1);
 		listeMessage.add(messageZelda1);
 		listeMessage.add(messageZelda2);
 		listeMessage.add(messageMagicien2);
 		listeMessage.add(messageMagicien3);
 		listeMessage.add(messageZelda3);
 		listeMessage.add(messageMagicien4);
 		listeMessage.add(messageQuete);
 		return listeMessage;
	}	
	
	public static ArrayList<String> recupererMessageEnCours2() {
 		String messageMagicien1 = "Magicien : Alors tu est de retour ?";
 		String messageZelda1 = "Zelda : Oui et j'ai trouvé votre chapeau magique";
 		String messageMagicien2 = "Magicien : Très bien donne le moi !";
 		String messageZelda2 = "Zelda : Et vous avez bien mon fragment de clé ?";
 		String messageMagicien3 = "Magicien : Oui bien sûr !";
 		String messageZelda3 = "Zelda : Tenez votre chapeau";
 		String messageMagicien4 = "Magicien : Et voici ton fragment de clé Zelda !";
 		String messageZelda4 = "Zelda : Savez vous où je peux trouver les autres fragments de clé ?";
 		String messageMagicien5 = "Magicien : Je pense que le chevalier peut t'être d'une grande aide";
 		String messageQuete = "Allez voir le chevalier";
 		
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		listeMessage.add(messageMagicien1);
 		listeMessage.add(messageZelda1);
 		listeMessage.add(messageMagicien2);
 		listeMessage.add(messageZelda2);
 		listeMessage.add(messageMagicien3);
 		listeMessage.add(messageZelda3);
 		listeMessage.add(messageMagicien4);
 		listeMessage.add(messageZelda4);
 		listeMessage.add(messageMagicien5);
 		listeMessage.add(messageQuete);
 		return listeMessage;
	}	
	
	public static ArrayList<String> recupererMessageEnCours3() {
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		String messageMagicienFinal = "Magicien : Encore merci pour mon chapeau magique, va sauver la princesse!";

 		listeMessage.add(messageMagicienFinal); 		
 		return listeMessage;
	}	
	
	public ChapeauMagique presenceObjetQuete() {
		for (Items o : this.env.getZelda().getlisteObjets()) {
			if (o instanceof ChapeauMagique) {
				return (ChapeauMagique) o;
			}
		}
		return null;
	}

	@Override
	public void creerObjetQuete(Terrain terrain) {
		int tuileLength = terrain.getTuileLength();
		this.env.addObjet(new ChapeauMagique(env, 15*tuileLength , tuileLength));
		this.setObjetQuetePlace(true);
	}
}

