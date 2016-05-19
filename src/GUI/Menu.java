/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import crowdrisemobile.CrowdriseMidlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;


/**
 *
 * @author asalhi
 */
public class Menu extends List implements CommandListener, Runnable {
  
    Display disp ;
    List lst = new List("Menu", List.IMPLICIT);
 
    public Menu() {
        super("Menu", List.IMPLICIT);
          
         lst.append("Gestion Projet", null);
        lst.append("Gestion Probleme", null);
        lst.append("Gestion Solution", null);
        lst.append("Contact", null);
         
        lst.setCommandListener(this);
        disp.setCurrent(lst);
        
         Thread th = new Thread(this);
        th.start();
        
       // public static Display getDisplay(MIDlet midlet);
    }

    public void commandAction(Command c, Displayable d) {
    if(c==List.SELECT_COMMAND)
        {
            switch(lst.getSelectedIndex())
			{
			case 0:
				CrowdriseMidlet.mMidlet.disp.setCurrent(new ListeProjet("ListeProjet", disp)); 
				break;
			case 1:
				CrowdriseMidlet.mMidlet.disp.setCurrent(new ListeProbleme("Liste Probleme", disp));
				break;
			case 2:
				CrowdriseMidlet.mMidlet.disp.setCurrent(new Offre("offre"));
			case 3:
				CrowdriseMidlet.mMidlet.disp.setCurrent(new Contact("contact", disp));
				break;
			 
			}
        }
}
 
    

    public void run() {
         //
    }

}
