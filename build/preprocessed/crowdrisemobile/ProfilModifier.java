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
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Sedki
 */
public class ProfilModifier extends MIDlet implements CommandListener, Runnable{
    
     Profil profils = new Profil();
     Display disp = Display.getDisplay(this);
     
     Form idProfil = new Form("idProfil");
     Form formModif = new Form("Modifier Profil");
     Form f2 = new Form("Modifié");
     
     TextField tfNom = new TextField("Nom", null, 50, TextField.ANY);
     TextField tfPrenom = new TextField("Prenom",null, 50, TextField.ANY);
     TextField tfPseudo = new TextField("Pseudo", null, 50, TextField.ANY);
     TextField tfAdresse = new TextField("Adresse", null, 200, TextField.ANY);
     TextField tfProfession = new TextField("Profession", null, 25, TextField.ANY);
     TextField tfIdProfil = new TextField("id", null, 12, TextField.NUMERIC);
     
     Command cmdModifier = new Command("Modifier", Command.OK, 0);
     Command cmdValider = new Command("Valider", Command.OK, 0);
     
     DateField dfNaissance = new DateField("Date de naissance", DateField.DATE);
    
     //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PidevJ2ME/Profil/Modifier.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    
    public void startApp() {
        
        idProfil.append(tfIdProfil);
        idProfil.addCommand(cmdModifier);
        idProfil.setCommandListener(this);
        
        disp.setCurrent(idProfil);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c==cmdModifier){
            Thread th = new Thread(this);
            th.start();
        }
        if (c==cmdValider){
            try {
                hc = (HttpConnection) Connector.open(url+"?nom="+tfNom.getString().trim()+"&prenom="
                        +tfPrenom.getString().trim()+"&adresse="
                        +tfAdresse.getString().trim()+"&pseudo="
                        +tfPseudo.getString().trim()+"&profession="
                        +tfProfession.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            // this will handle our XML
            ProfilHandler profilHandler = new ProfilHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PidevJ2ME/Profil/Affiche.php?id="+tfIdProfil.getString());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, profilHandler);
            // display the result
            profils = profilHandler.getProfil();
            showProfil(profils);
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
    }
    
    private void showProfil(Profil p) {
        if (profils != null ) {
            tfNom.setString(p.getNom());
            tfPrenom.setString(p.getPrenom());
            tfPseudo.setString(p.getPseudo());
            tfAdresse.setString(p.getAdresse());
            tfProfession.setString(p.getProfession());
            
            dfNaissance.setDate(new Date());
            formModif.append(tfNom);
            formModif.append(tfPrenom);
            formModif.append(tfPseudo);
            formModif.append(dfNaissance);
            formModif.append(tfAdresse);
            formModif.append(tfProfession);
            formModif.addCommand(cmdValider);
//            formModif.setCommandListener(new CommandListener() {
//                public void commandAction(Command c, Displayable d) {
//                    try {
//                        
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            });
            disp.setCurrent(formModif);
        }
    }
}
