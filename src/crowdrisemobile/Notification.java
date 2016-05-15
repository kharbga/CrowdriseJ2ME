/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrisemobile;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Sedki
 */
public class Notification extends MIDlet {
    
    Display disp = Display.getDisplay(this);
    Command cmdParse = new Command("Personnes", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Notification[] notifications;
    List lst = new List("Personnes", List.IMPLICIT);
    Form f = new Form("Notification");
    Form form = new Form("Infos Personne");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    public void startApp() {
        f.append("Click PERSONNES to get your personnes_list");
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
        disp.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Personne: \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }

    }

    public void run() {
        try {
            // this will handle our XML
            PersonneHandler personnesHandler = new PersonneHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/Parsing/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
            personnes = personnesHandler.getPersonne();

            if (personnes.length > 0) {
                for (int i = 0; i < personnes.length; i++) {
                    lst.append(personnes[i].getNom()+" "
                            +personnes[i].getPrenom(), null);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showPersonne(int i) {
        String res = "";
        if (personnes.length > 0) {
            sb.append("* ");
            sb.append(personnes[i].getId());
            sb.append("\n");
            sb.append("* ");
            sb.append(personnes[i].getPrenom());
            sb.append("\n");
            sb.append("* ");
            sb.append(personnes[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
