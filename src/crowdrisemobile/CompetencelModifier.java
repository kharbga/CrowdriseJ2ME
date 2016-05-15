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
public class CompetencelModifier extends Form implements CommandListener, Runnable {

    Profil profils = new Profil();
    Display disp;

    Form idProfil = new Form("idProfil");
    Form formModif = new Form("Modifier Competence");
    Form f2 = new Form("Modifié");

    TextField tfNom = new TextField("Nom", null, 50, TextField.ANY);
    TextField tfDescription = new TextField("Description", null, 200, TextField.ANY);
    TextField tfIdProfil = new TextField("id profil", null,3,TextField.NUMERIC);

    Command cmdModifier = new Command("Modifier", Command.OK, 0);
    Command cmdValider = new Command("Valider", Command.OK, 0);

    DateField dfNaissance = new DateField("Date de naissance", DateField.DATE);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PidevJ2ME/Profil/ModifierCompetenc.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    int idProfilNew;

    public CompetencelModifier(String title, Display d, int IdProfil) {
        super(title);
        append(tfNom);
        append(tfDescription);
        addCommand(cmdModifier);
        setCommandListener(this);
        disp = d;
        idProfilNew = IdProfil ;
    }

    public void commandAction(Command c, Displayable d) {
        if (c==cmdValider){
            Thread th = new Thread(this);
            th.start();
        }
        if (c==cmdModifier){ 
              ProfilModifier form1 = new ProfilModifier("Modifier Profil", disp, Integer.parseInt(tfIdProfil.getString()));
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
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PidevJ2ME/Profil/AfficheCompetence.php?id=" + tfIdProfil.getString());
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
        if (profils != null) {
            tfNom.setString(p.getNom());
            tfDescription.setString(p.getPrenom());

            dfNaissance.setDate(new Date());
            formModif.append(tfNom);
            formModif.append(tfDescription);
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
