/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crowdrisemobile;

import entities.Profil;
import entityhandlers.ProfilHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Sedki
 */
public class Affiche extends Form implements CommandListener, Runnable{
    
        Display disp ;
        
        Form form1 = new Form("Id Profil");
        Form f2 = new Form("Profil");
        
        Profil profils ;
        
        Command cmdModifier = new Command("Modifier", Command.OK, 0);
        Command cmdSupprimer = new Command("Supprimer", Command.OK, 0);
        
        StringItem StItNom = new StringItem("Nom :", null);
        StringItem StItPrenom = new StringItem("Prénom :", null);
        StringItem StItNaissance = new StringItem("Date de naissance :", null);
        StringItem StItPseudo = new StringItem("Pseudo :", null);
        StringItem StItAdresse = new StringItem("Adresse", null);
        StringItem StItProfession =  new StringItem("Profession", null);
        
//        Image photoProfil = null ;
        TextField TfId = new TextField("Id profil",null,3,TextField.NUMERIC);
        
        Command cmdValider = new Command("valider", Command.SCREEN, 0);

    public Affiche(String title, Display d) {
        super(title);
        append(TfId);
        addCommand(cmdValider);
        setCommandListener(this);
        f2.addCommand(cmdModifier);
        f2.setCommandListener(this);
        disp = d;
    }

    

    public void commandAction(Command c, Displayable d) {
        if (c==cmdValider){
            Thread th = new Thread(this);
            th.start();
        }
        if (c==cmdModifier){ 
              ProfilModifier form1 = new ProfilModifier("Modifier Profil", disp);
              disp.setCurrent(form1);
        }
    }

    public void run() {
        try {
            // this will handle our XML
            ProfilHandler profilHandler = new ProfilHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PidevJ2ME/Profil/Affiche.php?id="+TfId.getString());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, profilHandler);
            // display the result
            profils = profilHandler.getProfil();

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        showProfil(profils);
//        f2.append(photoProfil);
        f2.append(StItNom);
        f2.append(StItPrenom);
        f2.append(StItNaissance);
        f2.append(StItPseudo);
        f2.append(StItAdresse);
        f2.append(StItProfession);
        disp.setCurrent(f2);
    }
    
     private void showProfil(Profil p) {
        if (profils != null ) {
            StItNom.setText(p.getNom());
            StItPrenom.setText(p.getPrenom());
            StItNaissance.setText(p.getDate_de_naissance());
            StItPseudo.setText(p.getPseudo());
            StItAdresse.setText(p.getAdresse());
            StItProfession.setText(p.getProfession());
//            try {
//                String img = p.getImage() ;
//                img = img.replaceAll("\\/", "/");
//            photoProfil = Image.createImage(img);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
        }
    }
}
