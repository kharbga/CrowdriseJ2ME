/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import crowdrisemobile.CrowdriseMidlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

/**
 *
 * @author Rayhana
 */
public class AfficherOffre extends List implements CommandListener, Runnable {

    Command cmdBack = new Command("Back", Command.EXIT, 0);

    public AfficherOffre() {
        super("Informations", List.IMPLICIT);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            CrowdriseMidlet.mMidlet.disp.setCurrent(new Solutions());
        }
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
