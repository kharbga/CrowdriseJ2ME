/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Projet;
import entityhandlers.ProjetHandler;
import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 *
 * @author kouki
 */
public class ListeProjet extends Form implements CommandListener,Runnable {

    Display disp ;
    Command cmdParse = new Command("Projets", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Projet[] projets;
    List lst = new List("Projets", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos Projet");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    public ListeProjet(String title,Display d) {
        super(title);
        disp=d;
        append("Cliquer pour afficher la liste des projets");
        addCommand(cmdParse);
        setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
    }

   
    public void commandAction(Command c, Displayable d) {

        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Projets: \n");
            form.append(showProjet(lst.getSelectedIndex()));
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
            ProjetHandler projetsHandler = new ProjetHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/PidevJ2ME/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, projetsHandler);
            // display the result
            projets = projetsHandler.getProjet();

            if (projets.length > 0) {
                for (int i = 0; i < projets.length; i++) {
                    lst.append(projets[i].getTitre()+" "
                            , null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showProjet(int i) {
        String res = "";
        if (projets.length > 0) {
            sb.append("* ");
            sb.append(projets[i].getDescription());
            sb.append("\n");
            sb.append("* ");
            sb.append(projets[i].getTitre());
            sb.append("\n");
            sb.append("* ");
            sb.append(projets[i].getImageProjet());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
