/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;


/**
 *
 * @author kouki
 */
public class Acceuil extends Form implements CommandListener, Runnable {

    Display disp;
    Command cmdProjet = new Command("Projets", Command.OK, 0);
    Command cmdDeconexion = new Command("Deconexion", Command.EXIT, 0);

    public Acceuil(String title, Display d) {
        super(title);
        disp = d;
        addCommand(cmdProjet);
        addCommand(cmdDeconexion);
        setCommandListener(this);
        
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdProjet) {

             ListeProjet form1 = new ListeProjet("Liste des Porjets", disp);
            disp.setCurrent(form1);
        }
        if (c == cmdDeconexion) {

            Login form1 = new Login("Connexion", disp);
            disp.setCurrent(form1);
        }
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
