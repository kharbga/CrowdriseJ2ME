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
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
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
public class Inscription extends Form implements CommandListener, Runnable {

    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PidevJ2ME/ajout.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    Display disp;

    TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    TextField tfPrenom = new TextField("prenom", null, 100, TextField.ANY);
    TextField tfauthentifiant = new TextField("Authentifiant", null, 100, TextField.ANY);
    TextField tfMail = new TextField("Mail", null, 100, TextField.EMAILADDR);
    TextField tfpass = new TextField("Mot de passe", null, 100, TextField.PASSWORD);
    TextField tfconfpass = new TextField("Confirmer mot de passe", null, 100, TextField.PASSWORD);
    String[] choix = {"Submitter", "Solver"};
    ChoiceGroup choice = new ChoiceGroup("Role", Choice.EXCLUSIVE, choix, null);
    Alert alerta3 = new Alert("Validée", "Félicitations , votre compte est activé!", null, AlertType.CONFIRMATION);
    Alert alerta = new Alert("Error", "Les mots de passe ne sont pas identiques", null, AlertType.ERROR);
    Alert alerta2 = new Alert("Error", "Veuillez Remplir tout les champs", null, AlertType.ERROR);

    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);

    public Inscription(String title, Display d) {
        super(title);

        append(tfNom);
        append(tfPrenom);
        append(tfauthentifiant);
        append(tfMail);
        append(tfpass);
        append(tfconfpass);
        append(choice);
        addCommand(cmdValider);
        addCommand(cmdBack);
        setCommandListener(this);

        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
            Thread th = new Thread((Runnable) this);
            th.start();
        }
        if (c == cmdBack) {
            Login form1 = new Login("Connexion", disp);
            disp.setCurrent(form1);
        }
    }

    public void run() {
        try {
            String role;
            if ("Solver".equals(choice.getString(choice.getSelectedIndex()))) {
                role = "a:1:{i:0;s:11:\"ROLE_SOLVER\";}";

            } else {
                role = "a:1:{i:0;s:14:\"ROLE_SUBMITTER\";}";
            }
            if (!tfconfpass.getString().trim().equals(tfpass.getString().trim())) {
                disp.setCurrent(alerta);
            } else if (tfMail.getString().trim().equals("") || "".equals(tfNom.getString().trim()) || "".equals(tfPrenom.getString().trim()) || "".equals(tfauthentifiant.getString().trim()) || "".equals(tfconfpass.getString().trim()) || "".equals(tfpass.getString().trim())) {

                disp.setCurrent(alerta2);
            } else {
                hc = (HttpConnection) Connector.open(url + "?nom=" + tfNom.getString().trim() + "&prenom=" + tfPrenom.getString().trim() + "&username=" + tfauthentifiant.getString().trim() + "&email=" + tfMail.getString().trim() + "&password=" + tfpass.getString().trim() + "&role=" + role);
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char) ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    Login form1 = new Login("Acceuil", disp);
                    disp.setCurrent(form1);
                     disp.setCurrent(alerta3);
                }
                sb = new StringBuffer();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
