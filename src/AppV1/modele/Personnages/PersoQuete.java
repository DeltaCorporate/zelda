package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.vue.Utils.Terrain;

import java.util.ArrayList;


public abstract class PersoQuete extends Personnage{
	private int messageEnCours = 0;
	
	private ArrayList<String> listeMessage1;
	private ArrayList<String> listeMessage2;
	private ArrayList<String> messageFinal;
	private ArrayList<Items> listeItems;
	private boolean objetQuetePlace;
	private int ordreQuete;
	private int etatQuete;
	private double tauxDeDeplacement;
	
	public PersoQuete(Environnement env, int x, int y, int ordreQuete, FragmentCle f, double taux, ArrayList<String> listeMessage1, ArrayList<String> listeMessage2, ArrayList<String> messageFinal) {
		super(env, x, y);
		this.ordreQuete = ordreQuete;
		this.listeMessage1 = listeMessage1;
		this.listeMessage2 = listeMessage2;
		this.messageFinal= messageFinal;
		this.listeItems = new ArrayList<>();
		this.listeItems.add(new FragmentCle(env, 0, 0));
		this.setEtatQuete(1);
		this.tauxDeDeplacement = taux;
	}
	
	public int getMessageEnCours(){
		return this.messageEnCours;
	}
	
	public void setMessageEnCours(int indiceMessage) {
		this.messageEnCours = indiceMessage;
	}
	
	public ArrayList<String> getListeMessage1() {
		return this.listeMessage1;
	}
	public ArrayList<String> getListeMessage2() {
		return this.listeMessage2;
	}
	public ArrayList<String> getMessageFinal() {
		return messageFinal;
	}
	
	public void prendreUnObjet (Items o) {
		this.listeItems.add(o);
	}
	public ArrayList<Items> getlisteObjets(){
		return this.listeItems;
	}
	public Items getFragmentCle() {
		for (Items o : listeItems) {
			if (o instanceof FragmentCle) {
				return o;
			}
		}
		return null;
	}

	@Override
	public boolean seDeplacer(Zelda zelda, Terrain terrain, int paneTranslateX, int paneTranslateY) {
		return true;
	}

	public double getTauxDeDeplacement() {
		return this.tauxDeDeplacement;
	}
	
	public void setTauxDeDeplacement(double taux) {
		this.tauxDeDeplacement = taux;
	}

	public int getEtatQuete() {
		return etatQuete;
	}

	public void setEtatQuete(int etatQuete) {
		this.etatQuete = etatQuete;
	}
	
	public abstract Items presenceObjetQuete();

	public int getOrdreQuete() {
		return this.ordreQuete;
	}


	public abstract void creerObjetQuete(Terrain terrain);

	public boolean isObjetQuetePlace() {
		return objetQuetePlace;
	}

	public void setObjetQuetePlace(boolean objetQuetePlace) {
		this.objetQuetePlace = objetQuetePlace;
	}

	
}
