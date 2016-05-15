/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityhandlers;

import entities.Probleme;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Rayhana
 */
public class ProblemsHandler extends DefaultHandler {

    private Vector ProblemeVector;
    String titreTag = "close";

    public ProblemsHandler() {
        ProblemeVector = new Vector();
    }

    public Probleme[] getProbleme() {
        Probleme[] probTab = new Probleme[ProblemeVector.size()];
        ProblemeVector.copyInto(probTab);
        return probTab;
    }

    String selectedBalise = "";

    Probleme seclectedProbleme;

    public void startElement(String string, String string1, String qName, Attributes atrbts) throws SAXException {
        if (qName.equals("Titre")) {
            seclectedProbleme = new Probleme();
            seclectedProbleme.setTitre(atrbts.getValue("titre"));
        } else if (qName.equals("titre")) {
            titreTag = "open";
        }
    }

    public void endElement(String string, String string1, String qName) throws SAXException {
        if (qName.equals("Titre")) {

            ProblemeVector.addElement(seclectedProbleme);
            seclectedProbleme = null;
        } else if (qName.equals("titre")) {
            titreTag = "close";
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (seclectedProbleme != null) {
            if (titreTag.equals("titre")) {
                String titre = new String(ch, start, length).trim();
                seclectedProbleme.setTitre(titre);
            }
        }
    }
}
