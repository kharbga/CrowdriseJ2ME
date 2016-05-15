/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityhandlers;

import entities.Profil;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Date ;

/**
 *
 * @author Sedki
 */
public class ProfilHandler extends DefaultHandler {
    
    String idTag = "close";
    String nomTag = "close";
    String prenTag = "close";
    String naissTag = "close";
    String imgTag = "close" ;
    String pseudTag = "close";
    String adressTag = "close";
    String profTag = "close";
    Profil p1 = new Profil();

    public ProfilHandler() {
    }

    public Profil getProfil() {
        return p1;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Profil currentProfil;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentProfil = new Profil();
            //2ème methode pour parser les attributs
            currentProfil.setId(Integer.parseInt(attributes.getValue("id")));
            currentProfil.setNom(attributes.getValue("Nom"));
            currentProfil.setPrenom(attributes.getValue("Prenom"));
            String naissance = attributes.getValue("Date_de_naissance");
            currentProfil.setDate_de_naissance(naissance);
            currentProfil.setPseudo(attributes.getValue("Pseudo"));
            currentProfil.setImage(attributes.getValue("Image"));
            currentProfil.setAdresse(attributes.getValue("Adresse"));
            currentProfil.setProfession(attributes.getValue("Profession"));
            /****/
            
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("Nom")) {
            nomTag = "open";
        } else if (qName.equals("Prenom")) {
            prenTag = "open";
        }else if (qName.equals("Date_de_naissance")) {
            naissTag = "open";
        }else if (qName.equals("Pseudo")) {
            pseudTag = "open";
        }else if (qName.equals("Image")) {
            imgTag = "open";
        }else if (qName.equals("Adresse")) {
            adressTag = "open";
        }else if (qName.equals("Profession")) {
            profTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            p1 = currentProfil ;
            currentProfil = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("Nom")) {
            nomTag = "close";
        } else if (qName.equals("Prenom")) {
            prenTag = "close";
        }else if (qName.equals("Date_de_naissance")) {
            naissTag = "close";
        }else if (qName.equals("Pseudo")) {
            pseudTag = "close";
        }else if (qName.equals("Image")) {
            imgTag = "close";
        }else if (qName.equals("Adresse")) {
            adressTag = "close";
        }else if (qName.equals("Profession")) {
            profTag = "close";
        }
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentProfil != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                int id = Integer.parseInt(new String(ch, start, length).trim());
                currentProfil.setId(id);
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentProfil.setNom(nom);
            } else
                if (prenTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentProfil.setPrenom(pren);
            } else
                if (naissTag.equals("open")) {
                String naissance = new String(ch, start, length).trim() ;
                currentProfil.setDate_de_naissance(naissance);
            } else
                if (pseudTag.equals("open")) {
                String pseudo = new String(ch, start, length).trim();
                currentProfil.setPseudo(pseudo);
            } else
                if (imgTag.equals("open")) {
                String image = new String(ch, start, length).trim();
                currentProfil.setImage(image);
            } else
                if (adressTag.equals("open")) {
                String adresse = new String(ch, start, length).trim();
                currentProfil.setAdresse(adresse);
            } else
                if (profTag.equals("open")) {
                String profession = new String(ch, start, length).trim();
                currentProfil.setProfession(profession);
            }
        }
    }
}
