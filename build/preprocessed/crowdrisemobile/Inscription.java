/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrisemobile;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author kouki
 */
public class Inscription extends MIDlet implements CommandListener, Runnable{
 Display disp = Display.getDisplay(this);
    //Form 1
    Form f1 = new Form("Inscription");
    TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    TextField tfPrenom = new TextField("prenom", null, 100, TextField.ANY);
    TextField tfauthentifiant = new TextField("Authentifiant", null, 100, TextField.ANY);
    TextField tfMail = new TextField("Mail", null, 100, TextField.ANY);
    TextField tfpass = new TextField("Mot de passe", null, 100, TextField.ANY);
    TextField tfconfpass = new TextField("Confirmer mot de passe", null, 100, TextField.ANY);
    String[] choix = {"Submitter","Solver"};
    ChoiceGroup choice = new ChoiceGroup("Role", Choice.EXCLUSIVE, choix, null);
    
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);

    Form f2 = new Form("Welcome");
    Form f3 = new Form("Login ou mot de passe incorrect");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PidevJ2ME/ajout.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public void startApp() {
        f1.append(tfNom);
        f1.append(tfPrenom);
        f1.append(tfauthentifiant);
        f1.append(tfMail);
        f1.append(tfpass);
        f1.append(tfconfpass);
        f1.append(choice);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        f2.addCommand(cmdBack);
        f3.addCommand(cmdBack);
        f2.setCommandListener(this);
        disp.setCurrent(f1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
            Thread th = new Thread(this);
            th.start();
        }
        if (c == cmdBack) {
            
            disp.setCurrent(f1);
        }
    }

    public void run() {
        try {
                
                
                hc = (HttpConnection) Connector.open(url+"?nom="+tfNom.getString().trim()+"&prenom="+tfPrenom.getString().trim()+"&username="+tfauthentifiant.getString().trim()+"&email="+tfMail.getString().trim()+"&password="+tfpass.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
                }catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}


