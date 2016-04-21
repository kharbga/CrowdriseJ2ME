/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author kouki
 */
public class Inscription  extends Form implements CommandListener,Runnable {
   
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PidevJ2ME/ajout.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    Display disp ;

    TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    TextField tfPrenom = new TextField("prenom", null, 100, TextField.ANY);
    TextField tfauthentifiant = new TextField("Authentifiant", null, 100, TextField.ANY);
    TextField tfMail = new TextField("Mail", null, 100, TextField.ANY);
    TextField tfpass = new TextField("Mot de passe", null, 100, TextField.ANY);
    TextField tfconfpass = new TextField("Confirmer mot de passe", null, 100, TextField.ANY);
    String[] choix = {"Submitter", "Solver"};
    ChoiceGroup choice = new ChoiceGroup("Role", Choice.EXCLUSIVE, choix, null);
  
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);
    
    public Inscription(String title,Display d) {
        super(title);
        
  append(tfNom);
  append(tfPrenom);
  append(tfauthentifiant);
  append(tfMail);
  append(tfpass);
  append(tfconfpass);
  append(choice);
  addCommand(cmdValider);
  setCommandListener(this);

        disp=d;
    }

   
        public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
            Thread th = new Thread((Runnable) this);
            th.start();
        }
        if (c == cmdBack) {

            
        }
    }
    public void run() {
        try {
            String role;
            if ("Solver".equals(choice.getString(choice.getSelectedIndex()))) {
                role = "a:1:{i:0;s:11:\"ROLE_SOLVER\";}";

            } else {
                role = "a:1:{i:0;s:11:\"ROLE_SUBMITTER\";}";
            }

            hc = (HttpConnection) Connector.open(url + "?nom=" + tfNom.getString().trim() + "&prenom=" + tfPrenom.getString().trim() + "&username=" + tfauthentifiant.getString().trim() + "&email=" + tfMail.getString().trim() + "&password=" + tfpass.getString().trim() + "&role=" + role);
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                append("ok");
            } else {
                append("pas ok");
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
