/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityhandlers;

import entities.Solution;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Rayhana
 */
public class SolutionHandler extends DefaultHandler {

    private Vector SolutionVector;
    String titreTag = "close";
    String descTag = "close";
    String salaireTag = "close";
    String etatTag = "close";

    public SolutionHandler() {
        SolutionVector = new Vector();
    }

    public Solution[] getSolution() {
        Solution[] probTab = new Solution[SolutionVector.size()];
        SolutionVector.copyInto(probTab);
        return probTab;
    }

    String selectedBalise = "";

    Solution seclectedSolution;

    public void startElement(String string, String string1, String qName, Attributes atrbts) throws SAXException {
        if (qName.equals("Solution")) {
            seclectedSolution = new Solution();
            seclectedSolution.setTitre(atrbts.getValue("titre"));
            seclectedSolution.setDescription(atrbts.getValue("description"));
            seclectedSolution.setSalaire(Double.parseDouble(atrbts.getValue("salaire")));
            seclectedSolution.setEtat(atrbts.getValue("etat"));
        } else if (qName.equals("titre")) {
            titreTag = "open";
        }else if (qName.equals("description")) {
            descTag = "open";
        }else if (qName.equals("salaire")) {
            salaireTag = "open";
        }else if (qName.equals("etat")) {
            etatTag = "open";
        }
    }

    public void endElement(String string, String string1, String qName) throws SAXException {
        if (qName.equals("Solution")) {

            SolutionVector.addElement(seclectedSolution);
            seclectedSolution = null;
        } else if (qName.equals("titre")) {
            titreTag = "close";
        }else if (qName.equals("description")) {
            descTag = "close";
        }else if (qName.equals("salaire")) {
            salaireTag = "close";
        }else if (qName.equals("etat")) {
            etatTag = "close";
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (seclectedSolution != null) {
            if (titreTag.equals("titre")) {
                String titre = new String(ch, start, length).trim();
                seclectedSolution.setTitre(titre);
            }
            if (descTag.equals("description")) {
                String desc = new String(ch, start, length).trim();
                seclectedSolution.setDescription(desc);
            }
            if (salaireTag.equals("salaire")) {
                String salaire = new String(ch, start, length).trim();
                seclectedSolution.setTitre(salaire);
            }
            if (etatTag.equals("etat")) {
                String etat = new String(ch, start, length).trim();
                seclectedSolution.setTitre(etat);
            }
        }
    }
}
