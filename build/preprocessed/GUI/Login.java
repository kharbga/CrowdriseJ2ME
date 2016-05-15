/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Login extends Form implements CommandListener, Runnable {

    Display disp;

    TextField tfauthentifiant = new TextField("Authentifiant", null, 100, TextField.ANY);
    TextField tfpass = new TextField("Mot de passe", null, 100, TextField.PASSWORD);
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdInscription = new Command("Inscription", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);
    Alert alerta = new Alert("Error", "Login ou mot de passe incorrect", null, AlertType.ERROR);

    Membre[] Membres;

    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parsing/login.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public Login(String title, Display d) {
        super(title);
        append(tfauthentifiant);
        append(tfpass);
        addCommand(cmdValider);
        addCommand(cmdInscription);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
   
            run();
        }
        if (c == cmdBack) {

            disp.setCurrent(this);
        }
        if (c == cmdInscription) {

             
            disp.setCurrent(new GUI.Inscription("Inscription", disp));

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
                disp.setCurrent( new GUI.Menu("Menu", disp));
                
            } else {
                disp.setCurrent(alerta);
                      
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
