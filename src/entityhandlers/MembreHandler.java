package entityhandlers;

import java.util.Vector;
import entities.Membre;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MembreHandler extends DefaultHandler {

    private Vector membres;
    String idTag = "close";
    String nomTag = "close";
    String prenTag = "close";
    String userTag = "close";
    String mailTag = "close";
    String passTag = "close";

    public MembreHandler() {
        membres = new Vector();
    }

    public Membre[] getMembre() {
        Membre[] membress;
        membress = new Membre[membres.size()];
        membres.copyInto(membress);
        return membress;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Membre currentMembre;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("membre")) {
            currentMembre = new Membre();
            //2ème methode pour parser les attributs
            currentMembre.setId(Integer.parseInt(attributes.getValue("id")));
            currentMembre.setNom(attributes.getValue("nom"));
            currentMembre.setPrenom(attributes.getValue("prenom"));
            /**
             * *
             */

        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            prenTag = "open";
        } else if (qName.equals("username")) {
            userTag = "open";
        } else if (qName.equals("email")) {
            mailTag = "open";
        } else if (qName.equals("password")) {
            passTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            membres.addElement(currentMembre);
            currentMembre = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenTag = "close";
        } else if (qName.equals("username")) {
            userTag = "close";
        } else if (qName.equals("email")) {
            mailTag = "close";
        } else if (qName.equals("password")) {
            passTag = "close";
        }
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentMembre != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentMembre.setId(Integer.parseInt(id));
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentMembre.setNom(nom);
            } else if (prenTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentMembre.setPrenom(pren);
            } else if (userTag.equals("open")) {
                String user = new String(ch, start, length).trim();
                currentMembre.setUsername(user);
            } else if (mailTag.equals("open")) {
                String mail = new String(ch, start, length).trim();
                currentMembre.setEmail(mail);
            } else if (passTag.equals("open")) {
                String pass = new String(ch, start, length).trim();
                currentMembre.setPassword(pass);
            }
        }
    }

}
