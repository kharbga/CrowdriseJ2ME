/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrisemobile;
import crowdrisemobile.Inscription;
import entities.Membre;
import entityhandlers.MembreHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Login extends MIDlet implements CommandListener, Runnable {

    Display disp = Display.getDisplay(this);
    //Form 1
    Form f1 = new Form("Bienvenue sur CrowdRise!");
    TextField tfauthentifiant = new TextField("Authentifiant", null, 100, TextField.ANY);
    TextField tfpass = new TextField("Mot de passe", null, 100, TextField.PASSWORD);
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdInscription = new Command("Inscription", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);
    Form f2 = new Form("Welcome");
    Form f3 = new Form("Login ou mot de passe incorrect");
    Membre[] Membres;
    
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PidevJ2ME/login.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public void startApp() {

        f1.append(tfauthentifiant);
        f1.append(tfpass);
        f1.addCommand(cmdValider);
        f1.addCommand(cmdInscription);
        f1.setCommandListener(this);
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
         if (c == cmdInscription) {
            
            try {
                Class.forName("crowdrisemobile.Inscription");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            
        }
    }

    public void run() {
        try {
            MembreHandler MembresHandler = new MembreHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            hc = (HttpConnection) Connector.open(url + "?username=" + tfauthentifiant.getString().trim() + "&password=" + tfpass.getString().trim());
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, MembresHandler);
            Membres = MembresHandler.getMembre();
            if (Membres.length > 0) {
                disp.setCurrent(f2);
            } else {
                disp.setCurrent(f3);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }
    }
}
