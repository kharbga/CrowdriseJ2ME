/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;


/**
 *
 * @author kouki
 */
public class Acceuil extends Form implements CommandListener, Runnable {

    Display disp;
    Command cmdProjet = new Command("Projets", Command.OK, 0);
    Command cmdDeconexion = new Command("Deconexion", Command.EXIT, 0);
    private Object items;
    private Object display;

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
        try{
         Alert alert = new Alert("Loading. Please wait ....");
            alert.setTimeout(Alert.FOREVER);
            disp.setCurrent(alert);

            Player player = Manager.createPlayer("http://localhost/PidevJ2ME/d.mp3");

            // a listener to handle player events like starting, closing etc
           

            player.setLoopCount(-1); // play indefinitely
            player.prefetch(); // prefetch
            player.realize(); // realize

            player.start(); // and start
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        
    }
   

}
