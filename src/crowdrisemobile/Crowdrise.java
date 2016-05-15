/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crowdrisemobile;

import GUI.Login;

import java.util.Hashtable;
import java.util.Enumeration;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Alert;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.CommandListener;

import javax.microedition.media.Player;
import javax.microedition.media.Control;
import javax.microedition.media.Manager;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VideoControl;

/**
 * @author kouki
 */
public class Crowdrise extends MIDlet {
   Display disp = Display.getDisplay(this);
  

   

    public void startApp() {

        Login form1 = new Login("Connexion", disp);

        disp.setCurrent(form1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

    }

}
