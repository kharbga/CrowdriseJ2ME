package entityhandlers;

import entities.Probleme;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProblemeHandler extends DefaultHandler {

    private Vector Probleme;
    String idProblemeTag = "close";
    String titreTag = "close";
    
    String categorieTag = "close";
 
    String descriptionTag = "close";
 

    public ProblemeHandler() {
        Probleme = new Vector();
    }

    public Probleme[] getProbleme() {
        Probleme[] prob;
        prob = new Probleme[Probleme.size()];
        Probleme.copyInto(prob);
        return prob;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Probleme currentProb;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("probleme")) {
            currentProb = new Probleme();
            //2ème methode pour parser les attributs
            currentProb.setIdProbleme(Integer.parseInt(attributes.getValue("id_probleme")));
          
            currentProb.setDescription(attributes.getValue("description"));
            currentProb.setTitre(attributes.getValue("titre"));
            currentProb.setCategorie(attributes.getValue("categorie"));
       
        } else if (qName.equals("id_probleme")) {
            idProblemeTag = "open";
        }   else if (qName.equals("titre")) {
            titreTag = "open";
        } else if (qName.equals("categorie")) {
            categorieTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
  
    }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("probleme")) {
            // we are no longer processing a <reg.../> tag
            Probleme.addElement(currentProb);
            currentProb = null;
        } else if (qName.equals("id_probleme")) {
            idProblemeTag = "close";
        } else if (qName.equals("titre")) {
            titreTag = "close";
        } else if (qName.equals("categorie")) {
           categorieTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        }   
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentProb != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idProblemeTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentProb.setIdProbleme(Integer.parseInt(id));
            }  
            } else if (titreTag.equals("open")) {
                String titre = new String(ch, start, length).trim();
                currentProb.setTitre(titre);
            } else if (categorieTag.equals("open")) {
                String Categorie = new String(ch, start, length).trim();
                currentProb.setCategorie(Categorie);
            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentProb.setDescription(description);

        }
    }

}
 
