package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.modele.Items.Nourriture;
import AppV1.vue.Utils.Terrain;

import java.util.ArrayList;

public class Princesse extends PersoQuete{

	public Princesse(Environnement env, int x, int y, FragmentCle f) {
		super(env, x, y, 1, f, 0.00, recupererMessageEnCours1(), recupererMessageEnCours2(), recupererMessageEnCours3());
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<String> recupererMessageEnCours1() {
 		String messagePrincesse1 = "Princesse : Salut à toi jeune entrepreneur";
 		String messageZelda1 = "Zelda : Bonjour princesse";
 		String messagePrincesse2 = "Princesse : J'ai besoin de votre aide !";
 		String messageZelda2 = "Zelda : Comment puis-je vous aider ?";
 		String messagePrincesse3 = "Princesse : Il faut chercher les morceaux de clé pour m'ouvrir";
 		String messageZelda3 = "Zelda : Je vais de ce pas les chercher !";
 		String messagePrincesse4 = "Princesse : Amenez moi à manger d'abord, j'ai très faim !";
 		String messageQuete = "Allez chercher de la nourriture pour la princesse";
 		
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		listeMessage.add(messagePrincesse1);
 		listeMessage.add(messageZelda1);
 		listeMessage.add(messagePrincesse2);
 		listeMessage.add(messageZelda2);
 		listeMessage.add(messagePrincesse3);
 		listeMessage.add(messageZelda3);
 		listeMessage.add(messagePrincesse4);
 		listeMessage.add(messageQuete);
 		return listeMessage;
	}	
	
	public static ArrayList<String> recupererMessageEnCours2() {
 		String messagePrincesse1 = "Princesse : Je vois que vous n'avez pas les mains vides";
 		String messageZelda1 = "Zelda : Effectivement, j'ai ce qu'il vous faut";
 		String messagePrincesse2 = "Princesse : Merci Zelda";
 		String messageZelda2 = "Zelda : Mais comment vous vous êtes retrouvé là ?";
 		String messagePrincesse3 = "Princesse : C'est une longue histoire que je vous expliquerai une fois sortie";
 		String messagePrincesse4 = "Princesse : J'ai déja réussi à voler un fragment de clé au garde, tenez !";
 		String messagePrincesse5 = "Princesse : Allez maintenant voir le magicien !";
 		String messageZelda3 = "Zelda : Je vais de ce pas le chercher !";
 		String messageQuete = "Allez voir le magicien";
 		
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		listeMessage.add(messagePrincesse1);
 		listeMessage.add(messageZelda1);
 		listeMessage.add(messagePrincesse2);
 		listeMessage.add(messageZelda2);
 		listeMessage.add(messagePrincesse3);
 		listeMessage.add(messagePrincesse4);
 		listeMessage.add(messagePrincesse5);
 		listeMessage.add(messageZelda3);
 		listeMessage.add(messageQuete);
 		return listeMessage;
	}
	
	public static ArrayList<String> recupererMessageEnCours3() {
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		String messagePrincesseFinal = "Princesse : Merci de votre aide, continuez vos recherches.";
 		listeMessage.add(messagePrincesseFinal);

 		return listeMessage;
	}

	@Override
	public Nourriture presenceObjetQuete() {
		for (Items o : this.env.getZelda().getlisteObjets()) {
			if (o instanceof Nourriture) {
				return (Nourriture) o;
			}
		}
		return null;
	}	
	
	public void creerObjetQuete(Terrain terrain) {
		int tuileLength = terrain.getTuileLength();
		if (!this.isObjetQuetePlace()) {
			this.env.addObjet(new Nourriture(env,21*tuileLength , -2*tuileLength));
			this.setObjetQuetePlace(true);
		}
	}
}
