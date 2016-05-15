/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrisemobile;

import GUI.Login;
import GUI.Solutions;
import Splash.SplashScreen;
import java.io.DataInputStream;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;

/**
 * @author kouki
 */
public class CrowdriseMidlet extends MIDlet {

    public Display disp;
    public static CrowdriseMidlet mMidlet;

    public CrowdriseMidlet() {

        disp = Display.getDisplay(this);
    }

    public void startApp() {

       
        disp.setCurrent(new Login("login",disp));
       

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

    }

}
