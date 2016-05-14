/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crowdrisemobile;

import GUI.Login;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;

/**
 * @author kouki
 */
public class Crowdrise extends MIDlet {
   Display disp = Display.getDisplay(this);
  

   

    public void startApp() {

        Login form1 = new Login("Acceuil", disp);

        disp.setCurrent(form1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

    }

}
