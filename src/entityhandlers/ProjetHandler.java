package entityhandlers;

import java.util.Vector;
import entities.Projet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProjetHandler extends DefaultHandler {

    private Vector Projets;
    String idProjetTag = "close";
    String titreTag = "close";
    String idcat_idTag = "close";
    String type_financementTag = "close";
    String imageProjetTag = "close";
    String descriptionTag = "close";
    String deadlineTag = "close";

    public ProjetHandler() {
        Projets = new Vector();
    }

    public Projet[] getProjet() {
        Projet[] projetss;
        projetss = new Projet[Projets.size()];
        Projets.copyInto(projetss);
        return projetss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Projet currentProjet;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("projet")) {
            currentProjet = new Projet();
            //2ème methode pour parser les attributs
            currentProjet.setIdProjet(Integer.parseInt(attributes.getValue("id_projet")));
            currentProjet.setIdcat(Integer.parseInt(attributes.getValue("idcat_id")));
            currentProjet.setDescription(attributes.getValue("description"));
            currentProjet.setTitre(attributes.getValue("titre"));
            
            currentProjet.setTypeFinancement(attributes.getValue("type_financement"));
           
            /**
             * *
             */

        } else if (qName.equals("id_projet")) {
            idProjetTag = "open";
        } else if (qName.equals("idcat_id")) {
            idcat_idTag = "open";
        } else if (qName.equals("titre")) {
            titreTag = "open";
        } else if (qName.equals("type_financement")) {
            type_financementTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
//        } else if (qName.equals("deadline_projet")) {
//            deadlineTag = "open";
//        } 
    }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("projet")) {
            // we are no longer processing a <reg.../> tag
            Projets.addElement(currentProjet);
            currentProjet = null;
        } else if (qName.equals("id_projet")) {
            idProjetTag = "close";
        } else if (qName.equals("idcat_id")) {
            idcat_idTag = "close";
        } else if (qName.equals("titre")) {
            titreTag = "close";
        } else if (qName.equals("type_financement")) {
            type_financementTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
//        } else if (qName.equals("deadline_projet")) {
//            deadlineTag = "close";
       } 
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentProjet != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idProjetTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentProjet.setIdProjet(Integer.parseInt(id));
            } else if (idcat_idTag.equals("open")) {
                String idcat = new String(ch, start, length).trim();
                currentProjet.setIdcat(Integer.parseInt(idcat));
            } else if (titreTag.equals("open")) {
                String titre = new String(ch, start, length).trim();
                currentProjet.setTitre(titre);
            } else if (type_financementTag.equals("open")) {
                String tf = new String(ch, start, length).trim();
                currentProjet.setTypeFinancement(tf);
            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentProjet.setDescription(description);
//            } else if (deadlineTag.equals("open")) {
//                String dl = new String(ch, start, length).trim();
//                currentProjet.setDeadlineProjet();
//            }
        }
    }

}
}
