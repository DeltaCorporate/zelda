package AppV1.modele.Personnages;

import AppV1.modele.Environnement;
import AppV1.modele.Items.Armes.Armes;
import AppV1.modele.Items.CleFinale;
import AppV1.modele.Items.FragmentCle;
import AppV1.modele.Items.Items;
import AppV1.modele.Items.PotionVie;
import AppV1.modele.Magasin;
import AppV1.vue.Utils.Terrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Zelda extends PersoOffensif{
	private ObservableList<Items> listeObjets;
	private Armes armeEnMain;
	private int queteEnCours;
	private Magasin magasin;
	private Items objetEnMain;
	private static int nbObjetsHorsCle = 0;

	
	public Zelda(Environnement env, int x, int y, int ptsVie, int ptsAttaque, Magasin magasin) {
		super(env, x, y, ptsVie, ptsAttaque);

		this.armeEnMain = null; 
		this.listeObjets =  FXCollections.observableArrayList();
		this.queteEnCours = 1;
		this.magasin = magasin;
		this.objetEnMain = null;
	}
	
	public int getNbObjetsHorsCle() {
		return nbObjetsHorsCle;
	}
	public void setNbObjetsHorsCle(int nbObjetsHorsCle) {
		Zelda.nbObjetsHorsCle = nbObjetsHorsCle;

	}
	@Override
	public void attaquer(Zelda z) {
		armeEnMain.utilisationArme(this.getX(), this.getY());
	}
	public boolean seDeplacer(Zelda zelda, Terrain terrain) {
		return true;
	}
	public void prendreUnObjet (Items o) {
		if (!(listeObjets.contains(o))) {
			if (o instanceof Armes && !this.existeArmeListe() && nbObjetsHorsCle<3) {// On ne peut pas prendre 2 armes dans son inventaire mais on peut prendre 2 fois le meme objet
				this.listeObjets.add(o);
				nbObjetsHorsCle++;
				this.getEnv().getItems().remove(o);
			}
			else if (! (o instanceof Armes)) {
				if (!(o instanceof CleFinale && o instanceof FragmentCle) && nbObjetsHorsCle<3) {
					this.listeObjets.add(o);
					nbObjetsHorsCle++;
					this.getEnv().getItems().remove(o);
				}
				else if ((o instanceof CleFinale || o instanceof FragmentCle)){
					this.listeObjets.add(o);
					this.getEnv().getItems().remove(o);
				}
			}
		}
	}
	public void supprimerObjet (Items a) {
		if (this.listeObjets.contains(a)) {
			deposseder(a);
			this.listeObjets.remove(a);
			a.getXProperty().unbind();
			a.getYProperty().unbind();
			nbObjetsHorsCle--;
		}
	}
	public void deposseder(Items a) {
		if (a instanceof Armes) {
			this.armeEnMain = null;
		}
		else {
			this.objetEnMain = null;
		}
	}
	public Armes getArmes() {
		return this.armeEnMain;
	}
	public Items getItem(String id) {
		for (Items i : this.listeObjets) {
			if (i.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}
	public ObservableList<Items> getlisteObjets(){
		return this.listeObjets;
	}
	public void prendreEnMain(Items a) {
		if (a instanceof Armes) {
			this.armeEnMain = (Armes) a;
		}
		else {
			objetEnMain = a;
		}
	}
	public boolean rienEnMain() {
		if (this.objetEnMain == null && this.armeEnMain == null) {
			return true;
		}
		return false;
	}
	public void viderMains() {
		this.getEnv().removeObjet(objetEnMain);
		this.getEnv().removeObjet(armeEnMain);
		this.objetEnMain = null; // On vide tout
		this.armeEnMain = null;
	}
	public Boolean existeArmeListe () {
		for (Items a : this.listeObjets) {
			if (a instanceof Armes) {
				return true;
			}
		}
		return false;
	}
	public void objetEnMain(Items o) {
		if (this.listeObjets.contains(o) && ! this.getEnv().getItems().contains(o) && rienEnMain()) {
				this.getEnv().addObjet(o);
				o.getXProperty().bind(this.getXProperty().add(10));
				o.getYProperty().bind(this.getYProperty().add(10));
				prendreEnMain(o);
			}
			else {
				// Si il a deja quelque chose en main et qu'il veut prendre une deuxieme chose on vide tout car
				// il peut seulement avoir un objet en main il devra donc rappuyer sur le bouton pour prendre un objet
				viderMains();
			}
		}
	public boolean getObjetProche() {

		for (Items o : this.getEnv().getItems()) {
				int distanceX = o.getX() - this.getX();
				int distanceY = o.getY() - this.getY();
				if ((distanceX <= 32 && distanceX >= -32) && (distanceY <= 16 && distanceY >=-16)) {
					this.prendreUnObjet(o);
					return true;
				}
			} 
		return false;
	}
	public Items ItemEnMain() {
		if (this.armeEnMain != null) {
			return this.armeEnMain;
		}
		else if (this.objetEnMain != null) {
			return this.objetEnMain;
		}
		return null;
	}
	
	public boolean conditionProximite(PersoQuete p) {
 		if (this.getX()<=p.getX()+18 &&
 			this.getX()>=p.getX()-18 &&
 			this.getY()<=p.getY()+18 &&
 			this.getY()>=p.getY()-18) {
 			return true;
 		}
 		return false;
 	}

	public int getQueteEnCours() {
		return queteEnCours;
	}

	public void setQueteEnCours(int queteEnCours) {
		this.queteEnCours = queteEnCours;
	}

	public PersoQuete getPersoQueteProximite() {
		for (PersoQuete p : this.env.getPersoQuete()) {
 			if (this.conditionProximite(p)) {
 				return p;
 			}
 		}
		return null;
	}

	public Magasin getMagasin() {
		return magasin;
	}
	
	public boolean verifieTroisFragment() {
		boolean presenceTroisFragment = false;
		int compteur = 0;
		for (Items item : listeObjets) {
			if (item instanceof FragmentCle) {
				compteur++;
			}
		}
		
		if (compteur>=3) {
			listeObjets.removeIf(item -> item instanceof FragmentCle);
			presenceTroisFragment = true;
		}
		return presenceTroisFragment;
	}
	
	public int getNbFragment() {
		int nbFragments = 0;
		for (Items items : listeObjets) {
			if (items instanceof FragmentCle) {
				nbFragments++;
			}
		}
		return nbFragments;
	}
	
	public void utiliserPotion() {
		if (this.objetEnMain instanceof PotionVie) {
			PotionVie potion = (PotionVie)this.objetEnMain;
			potion.consommer(this);
			this.deposseder(potion);
			this.listeObjets.remove(potion);
			this.getEnv().removeObjet(potion);
		}
	}

	public int getQuêteEnCours() {
		return queteEnCours;
	}
}
