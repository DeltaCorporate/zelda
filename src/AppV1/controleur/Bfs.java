package AppV1.controleur;

import AppV1.modele.Environnement;
import AppV1.modele.Personnages.Personnage;
import AppV1.modele.Personnages.Zelda;
import AppV1.vue.Utils.Terrain;

import java.util.ArrayList;

public class Bfs {
	//BFS fonctionnant avec une arrayList contenant des arrayList de Point.
	//La première arrayList est initialisé avec la position initiale de l'ennemi comme point de départ. Ensuite le principe du BFS entre en jeu
	//Pour la dernière case de chaque liste de point, on regarder si la position |haut|bas|droite|gauche est accessible, et non déja parcourue, pour ensuite l'ajouter
    //On décide de stocker les listes de chemin afin de savoir lorsqu'un chemin nous mène vers Zelda, 
	//quelle était la direction de son premier déplacement, pour ensuite le transmettre à l'ennemi de sorte à le déplacer
	//L'objectif étant d'avoir les dernières coordonnés d'une ArrayList égale aux position de Zelda
	private ArrayList<ArrayList<Point>> liste;
	private ArrayList<Point> listePointParcourus;
	private Point arrive;
	private int DirectionFinale;
	
	public Bfs () {
		this.liste = new ArrayList<ArrayList<Point>>();
		this.listePointParcourus = new ArrayList<Point>();
		this.DirectionFinale = 0;
	}


	public int directionBfs(Zelda zelda, Personnage ennemi, Environnement env, Terrain terrain, double porteeAttaque, int paneTranslateX, int paneTranslateY) {
		ArrayList<Point> liste1 = new ArrayList<Point>();
		int nbPassage = 0;
		Point pInitial = new Point(ennemi.getX(), ennemi.getY()+ennemi.getTrompeurPositionY());
		Point pfinal = new Point(zelda.getX(), zelda.getY()+16);
		this.arrive = pfinal;
		
		liste1.add(pInitial);
		this.liste.add(liste1);
		this.listePointParcourus.add(pInitial);

		
		while (ennemiArriveZelda(this.liste)==0  && nbPassage<70) {
			ArrayList<ArrayList<Point>> listeNouveauChemin = new ArrayList<ArrayList<Point>>();
			nbPassage++;
			//On parcourt toute la liste de liste de point
			//On vérifie les 4 cases possibles en partant du dernier point de chaque liste, si la premiere case testé est accessible, on modifie directement la liste en question
			//Si on constate que il est possible d'acceder à >= 2 direction, il faudra faire une copie de notre chemin de base pour faire un chemin qui va 
			//vers chaque direction de case possible.
			for (int i = 0; i<liste.size(); i++) {
				int compteur = 0;
				ArrayList<Point> temp = (ArrayList<Point>)liste.get(i).clone();
				
				//case vers la gauche
				Point p1 = temp.get(temp.size()-1).caseObstacle(env, zelda, ennemi, terrain, -8, 0, paneTranslateX, paneTranslateY);
				if (p1!=null && !estDejaPresent(p1)) {
					liste.get(i).add(p1);
					listePointParcourus.add(p1);
					compteur++;
				}
				
				//case vers la droite
				Point p2 = temp.get(temp.size()-1).caseObstacle(env, zelda, ennemi, terrain, +8, 0, paneTranslateX, paneTranslateY);
				if (p2!=null && !estDejaPresent(p2)) {
					if (compteur==0) {
						liste.get(i).add(p2);
						compteur++;
					}
					else {
						ArrayList<Point> secondListe = (ArrayList<Point>)temp.clone();
						secondListe.add(p2);
						listeNouveauChemin.add(secondListe);
					}
					listePointParcourus.add(p2);
				}
				
				//case vers le haut
				Point p3 = temp.get(temp.size()-1).caseObstacle(env, zelda, ennemi, terrain, 0, -8, paneTranslateX, paneTranslateY);
				if (p3!=null && !estDejaPresent(p3)) {
					if (compteur==0) {
						liste.get(i).add(p3);
						compteur++;
					}
					else {
						ArrayList<Point> secondListe = (ArrayList<Point>)temp.clone();
						secondListe.add(p3);
						listeNouveauChemin.add(secondListe);
					}
					listePointParcourus.add(p3);
				}
				
				//case vers le bas
				Point p4 = temp.get(temp.size()-1).caseObstacle(env, zelda, ennemi, terrain, 0, 8, paneTranslateX, paneTranslateY);
				if (p4!=null && !estDejaPresent(p4)) {
					if (compteur==0) {
						liste.get(i).add(p4);
						compteur++;
					}
					else {
						ArrayList<Point> secondListe = (ArrayList<Point>)temp;
						secondListe.add(p4);
						listeNouveauChemin.add(secondListe);
					}
					listePointParcourus.add(p4);
				}
			}
			for (ArrayList<Point> arrayList : listeNouveauChemin) {
				this.liste.add(arrayList);
			}
		}
		return DirectionFinale;
}
	
	public int ennemiArriveZelda(ArrayList<ArrayList<Point>> liste) {
		int direction = 0;
		if (liste.size()!=1) {
			for (ArrayList<Point> listeChemin : liste) {
				Point finalTest = listeChemin.get(listeChemin.size()-1);
				if (finalTest.estIdentique(this.arrive)) {
					int xDebut = listeChemin.get(0).getCaseX();
					int xSuite = listeChemin.get(1).getCaseX();
					int yDebut = listeChemin.get(0).getCaseY();			
					int ySuite = listeChemin.get(1).getCaseY();

					if (xDebut!=xSuite){
						direction = xSuite- xDebut;
					}
					else {
						direction = ySuite - yDebut;
						direction *= 2;
					}
					this.DirectionFinale = direction;
					break;
				}
			}
		}
		return direction;
	}

	public boolean estDejaPresent(Point p) {
		boolean presence = false;
		for (Point point : listePointParcourus) {
			if (point.estIdentique(p)) {
				presence = true;
				break;
			}
		}
		return presence;
	}
}	