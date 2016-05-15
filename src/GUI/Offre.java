/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ProblemeDAO;
import DAO.SolutionDAO;
import crowdrisemobile.CrowdriseMidlet;
import entities.Probleme;
import entities.Solution;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.media.control.FramePositioningControl;

/**
 *
 * @author Rayhana
 */
public class Offre extends List implements CommandListener, Runnable {

    Command cmdback = new Command("Précedent", Command.EXIT, 0);
    Command cmdExit = new Command("Précedent", Command.EXIT, 0);
    Form form = new Form("Information");
    String prob;

    public Offre(String p) {
        super("Liste des Offres", List.IMPLICIT);
        form.addCommand(cmdback);
        addCommand(cmdExit);
        setCommandListener(this);
        form.setCommandListener(this);
        Thread th = new Thread(this);
        th.start();
        prob = p;
    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdExit) {
            CrowdriseMidlet.mMidlet.disp.setCurrent(new Solutions());
        }
        if (c == cmdback) {
            CrowdriseMidlet.mMidlet.disp.setCurrent(new Solutions());
        }
        if (c == List.SELECT_COMMAND) {
            form.append("L'Offre:\n");
            form.append(showOffre(this.getSelectedIndex()));
            CrowdriseMidlet.mMidlet.disp.setCurrent(form);
        }
    }
    Solution[] solution;

    public void run() {
        solution = new SolutionDAO().selectSolution(prob);
        if (solution.length > 0) {
            for (int i = 0; i < solution.length; i++) {
//                System.out.println(Prob.length);
//                System.out.println(Prob[i].getTitre());
                this.append("Titre: " + solution[i].getTitre() + "\n------------------------------------------", null);
            }
        }
    }
    StringBuffer sb = new StringBuffer();

    private String showOffre(int i) {
        String res = "";
        if (solution.length > 0) {
            sb.append("* ");
            sb.append(solution[i].getTitre());
            sb.append("\n");

            sb.append("* ");
            sb.append(solution[i].getDescription());
            sb.append("\n");

            sb.append("* ");
            sb.append(solution[i].getSalaire());
            sb.append("\n");

            sb.append("* ");
            sb.append(solution[i].getEtat());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

}
