package AppV1.vue.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Terrain {
    private int width;
    private int height;
    private int tuileLength;
    private ArrayList<int[][]> calques2D;


    public Terrain(String filename) {
        this.calques2D = chargerCalques(filename);
    }

    private ArrayList<int[]> getCartesFromXML(String filename) {
        ArrayList<int[]> calques = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document image = builder.parse(new File(filename));

            //Pour reformater le code correctement

            image.getDocumentElement().normalize();
            NodeList terrains = image.getElementsByTagName("data");
            NodeList parentInfo = image.getElementsByTagName("map");
            for (int i = 0; i < parentInfo.getLength(); i++) {
                Node node = parentInfo.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    setWidth(Integer.parseInt(element.getAttribute("width")));
                    setHeight(Integer.parseInt(element.getAttribute("height")));
                    setTuileLength(Integer.parseInt(element.getAttribute("tilewidth")));
                }
            }
            for (int i = 0; i < terrains.getLength(); i++) {
                Node node = terrains.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String[] carte = element.getTextContent().split(",");
                    int taille = carte.length;
                    for (int carteIndex = 0; carteIndex < taille; carteIndex++) {
                        carte[carteIndex] = carte[carteIndex].replaceAll("\n", "");
                        carte[carteIndex] = carte[carteIndex].replaceAll(" *","");

                    }

                    calques.add(parseStringToint(carte));
                }
            }
            return calques;


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] parseStringToint(String[] carteToInt) {
        int[] carte = new int[carteToInt.length];
        for (int i = 0; i < carteToInt.length; i++) {
            carte[i] = Integer.parseInt(carteToInt[i]);
        }
        return carte;
    }

    public ArrayList<int[][]> chargerCalques(String filename) {
        ArrayList<int[]> calquesToConvert = getCartesFromXML(filename);
        ArrayList<int[][]> calques = new ArrayList<>();
        assert calquesToConvert != null;
        calquesToConvert.forEach(tab -> {
            int[][] tab2D = new int[height][width];
            for (int colonne = 0; colonne < height; colonne++) {
                for (int ligne = 0; ligne < width; ligne++) {
                    int row = colonne * width + ligne;

                    if(tab[row] == 0){
                        tab2D[colonne][ligne] = 20028;
                    } else{
                        if(tab[row] == 3649){
                            tab2D[colonne][ligne] = 1;
                        } else{
                            tab2D[colonne][ligne] = tab[row];
                        }
                    }

                }
            }
            calques.add(tab2D);
        });
        return calques;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTuileLength(int tuileLength) {
        this.tuileLength = tuileLength;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTuileLength() {
        return tuileLength;
    }


    public int getCodeTuiles2DParIndex(int idCalque, int colonne, int ligne) {
        return calques2D.get(idCalque)[colonne][ligne];
    }
    public boolean estUnObstacle(int colonne, int ligne){
        return getCodeTuiles2DParIndex(this.calques2D.size()-1,colonne,ligne) == 1;
    }

    public ArrayList<int[][]> getListeCalques() {
        return calques2D;
    }

}
