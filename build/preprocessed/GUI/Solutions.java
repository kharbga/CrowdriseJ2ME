/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ProblemeDAO;
import crowdrisemobile.Crowdrise;
import entities.Probleme;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author Rayhana
 */
public class Solutions extends List implements CommandListener, Runnable {

    Probleme[] Prob;
    Command cmdAdd = new Command("Solution", Command.SCREEN, 0);
    Command cmdOffre = new Command("Offre", Command.SCREEN, 0);
    Command cmdExit = new Command("Sortir", Command.EXIT, 0);
    StringBuffer sb = new StringBuffer();

    public Solutions() {
        super("Liste Problème", List.IMPLICIT);
        addCommand(cmdAdd);
        addCommand(cmdExit);
        addCommand(cmdOffre);
        setCommandListener(this);
        Thread th = new Thread(this);
        th.start();

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdAdd) {
            Crowdrise.Instance.disp.setCurrent(new Demande(Prob[this.getSelectedIndex()].getTitre()));
        }
        if (c == cmdOffre) {
            
            Crowdrise.Instance.disp.setCurrent(new Offre(Prob[this.getSelectedIndex()].getTitre()));
            
        }
        if (c == cmdExit) {
            Crowdrise.Instance.notifyDestroyed();
        }
    }

    public void run() {
        Prob = new ProblemeDAO().select();
        if (Prob.length > 0) {
            for (int i = 0; i < Prob.length; i++) {
//                System.out.println(Prob.length);
//                System.out.println(Prob[i].getTitre());
                this.append("Titre: " + Prob[i].getTitre() + "\n------------------------------------------", null);
            }
        }
    }
}
