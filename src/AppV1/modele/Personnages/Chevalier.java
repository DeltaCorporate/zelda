package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.modele.Items.CasqueChevalier;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.vue.Utils.Terrain;

import java.util.ArrayList;

public class Chevalier extends PersoQuete {

	public Chevalier(Environnement env, int x, int y, FragmentCle f) {
		super(env, x, y, 3, f, 0.5, recupererMessageEnCours1(), recupererMessageEnCours2(), recupererMessageEnCours3());

	}

	public static ArrayList<String> recupererMessageEnCours1() {
 		String messageChevalier1 = "Chevalier : Que faites vous ici ? Allez vous en !";
 		String messageZelda1 = "Zelda : Bonjour, chevalier ...";
 		String messageChevalier2 = "Chevalier : Que me voulez vous enfin ?!";
 		String messageZelda2 = "Zelda : Je suis à la recherche de fragment de clé pour sauver la princesse";
 		String messageChevalier3 = "Chevalier : Je pense pouvoir te donner ça... mais...";
 		String messageChevalier4 = "Chevalier : J'ai perdu mon casque magique, et si je ne le récupère pas,";
 		String messageChevalier5 = "Chevalier : Je ne te donnerai pas ton fragment de clé !";
 		String messageZelda3 = "Zelda : Je vais de ce pas le chercher !";
 		String messageChevalier6 = "Chevalier : Reviens me voir uniquement si tu as ce dont j'ai besoin";
 		String messageQuete = "Allez chercher le casque magique du chevalier";
 		
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		listeMessage.add(messageChevalier1);
 		listeMessage.add(messageZelda1);
 		listeMessage.add(messageChevalier2);
 		listeMessage.add(messageZelda2);
 		listeMessage.add(messageChevalier3);
 		listeMessage.add(messageChevalier4);
 		listeMessage.add(messageChevalier5);
 		listeMessage.add(messageZelda3);
 		listeMessage.add(messageChevalier6);
 		listeMessage.add(messageQuete);
 		return listeMessage;
	}	
	
	public static ArrayList<String> recupererMessageEnCours2() {
 		String messageChevalier1 = "Chevalier : Tu as ce que je t'ai demandé ?";
 		String messageZelda1 = "Zelda : Et vous avez vous mon fragment ?";
 		String messageChevalier2 = "Chevalier : Voici ton fragment de clé, donne moi mon casque magique";
 		String messageZelda2 = "Zelda : Le voici, j'ai maintenant les 3 fragments!"; 	
 		String messageQuete = "Allez vite sauver la princesse avec votre clé";
 		
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		listeMessage.add(messageChevalier1);
 		listeMessage.add(messageZelda1);
 		listeMessage.add(messageChevalier2);
 		listeMessage.add(messageZelda2);
 		listeMessage.add(messageQuete);
 		return listeMessage;
	}
	
	public static ArrayList<String> recupererMessageEnCours3() {
 		ArrayList<String> listeMessage = new ArrayList<String>();
 		String messageChevalierFinal = "Encore merci pour mon casque, va vite sauver la princesse";
 		listeMessage.add(messageChevalierFinal);

 		return listeMessage;
	}

	public CasqueChevalier presenceObjetQuete() {
		for (Items o : this.env.getZelda().getlisteObjets()) {
			if (o instanceof CasqueChevalier) {
				return (CasqueChevalier) o;
			}
		}
		return null;
	}

	@Override
	public void creerObjetQuete(Terrain terrain) {
		int tuileLength = terrain.getTuileLength();
		this.getEnv().addObjet(new CasqueChevalier(env, 15*tuileLength , -5*tuileLength));
		this.setObjetQuetePlace(true);
	}	

}