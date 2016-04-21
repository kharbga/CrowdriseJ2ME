/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.SolutionDAO;
import crowdrisemobile.Crowdrise;
import entities.Probleme;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author Rayhana
 */
public class Demande extends Form implements CommandListener, Runnable {
    
    TextField tf_title = new TextField("Title", "", 50, TextField.ANY);
    TextField tf_salaire = new TextField("Renumeration", "", 50, TextField.DECIMAL);
    TextField tf_desc = new TextField("Description", "", 50, TextField.ANY);
    

    Command cmdAdd = new Command("Confirm", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.EXIT, 0);

    public Demande() {
        super("Ajout Demande");

        append(tf_title);
        append(tf_salaire);
        append(tf_desc);
        addCommand(cmdAdd);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            Crowdrise.Instance.disp.setCurrent(new Solutions());
        }
        if (c == cmdAdd) {
            Thread th = new Thread(this);
            th.start();
        }

    }

    public void run() {
        String title = tf_title.getString();
        double renum = Double.parseDouble(tf_salaire.getString());
        String desc = tf_desc.getString();
        
        boolean result = new SolutionDAO().insert(title, renum, desc);
        Alert alert = new Alert("Result");
        if (result) {
            alert.setType(AlertType.CONFIRMATION);
            alert.setString("Demande ajoutée");
            Crowdrise.Instance.disp.setCurrent(alert,new Solutions());
        } else {
            alert.setType(AlertType.ERROR);
            alert.setString("ERREUR!");
            Crowdrise.Instance.disp.setCurrent(alert);
        }
    }
    
    
}
