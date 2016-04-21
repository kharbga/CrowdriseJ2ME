/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ProblemeDAO;
import DAO.SolutionDAO;
import crowdrisemobile.Crowdrise;
import entities.Probleme;
import entities.Solution;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

/**
 *
 * @author Rayhana
 */
public class Offre extends List implements CommandListener, Runnable {

    Command cmdAdd = new Command("Suivant", Command.SCREEN, 0);
    Command cmdExit = new Command("Précedent", Command.EXIT, 0);

    public Offre() {
        super("Liste des Offres", List.IMPLICIT);
        addCommand(cmdAdd);
        addCommand(cmdExit);
        setCommandListener(this);
        Thread th = new Thread(this);
        th.start();

    }

    public void commandAction(Command c, Displayable d) {
      
        if (c == cmdExit) {
            Crowdrise.Instance.disp.setCurrent(new Solutions());
        }
    }

    public void run() {
        Solution[] solution = new SolutionDAO().select();
        if (solution.length > 0) {
            for (int i = 0; i < solution.length; i++) {
//                System.out.println(Prob.length);
//                System.out.println(Prob[i].getTitre());
                this.append("Titre: " + solution[i].getTitre()+ "\n------------------------------------------", null);
            }
        }
    }

}

