/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splash;

import GUI.Acceuil;
import GUI.Login;
import GUI.Solutions;
import crowdrisemobile.CrowdriseMidlet;
import crowdrisemobile.CrowdriseMidlet;
import java.io.IOException;
import javax.microedition.lcdui.Canvas; 
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Rayhana
 */
public class SplashScreen extends Canvas implements Runnable{
    
    private Image mImage;
    
    private static CrowdriseMidlet projectMIDlet;
    private Display disp;
    
    public SplashScreen(CrowdriseMidlet projectMIDlet){
        this.projectMIDlet = projectMIDlet;
        try{
        mImage = Image.createImage("Splash/img.jpg");
       
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Paints the image centered on the screen.
     */
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(0x000000);
        g.fillRect(0,0, width, height);
        g.drawImage(mImage, width / 2, height / 2,
                Graphics.HCENTER | Graphics.VCENTER);
    }
    /**
     * Dismisses the splash screen with a key press or a pointer press
     */
    public void dismiss() {
        if (isShown())
        Display.getDisplay(projectMIDlet).setCurrent(new Login("Acceuil", disp)); 

       

    }
    /**
     * Default timeout with thread
     */
    public void run() {
        try {
            Thread.sleep(4000);//set for 6 seconds
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
        dismiss();
    }
    /**
     * A key release event triggers the dismiss()
     * method to be called.
     */
}
