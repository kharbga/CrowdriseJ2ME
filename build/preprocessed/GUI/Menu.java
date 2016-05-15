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
import javax.microedition.lcdui.List;

import GUI.*;

/**
 *
 * @author kouki
 */
public class Menu extends Form implements CommandListener, Runnable {

    Display dis;
    
    Contact Contact;

    Command cmdProjet = new Command("Projet", Command.OK, 0);
    Command cmdProbleme = new Command("Probleme", Command.OK, 1);
    Command cmdSolution = new Command("Solution", Command.OK, 2);
    Command cmdContact = new Command("Contact", Command.OK, 3);

    public Menu(String title, Display d) {
        super(title);
        dis = d;

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdProjet) {
            dis.setCurrent(new ListeProjet("Liste Projet", dis));
        }

        if (c == cmdProbleme) {
            dis.setCurrent(new ListeProbleme("Liste Probleme", dis));
        }
//       if (c == cmdSolution) {
//            dis.setCurrent(new ("Liste solution", dis));
//        }
  if (c == cmdContact) {
            dis.setCurrent(new Contact("Liste solution", dis));
        }
  
//  if (c == Map && d == Contact) {   // change to midlet and test if disp=contactform
//            dis.setCurrent(new GoogleMapsZoomCanvas(this, dis));
//        }

}
 
    

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
