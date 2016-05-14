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
import javax.microedition.lcdui.Image;

/**
 *
 * @author kouki
 */
public class Acceuil extends Form implements CommandListener,Runnable {
Display dis;
Command cmdParse = new Command("Projets", Command.SCREEN, 0);
    public Acceuil(String title,Display d) {
        super(title);
        dis=d;
        addCommand(cmdParse);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdParse) {
            
            Thread th = new Thread(this);
            th.start();
        }    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
