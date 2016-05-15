/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Projet;
import entityhandlers.ProjetHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
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
public class ListeProjet extends Form implements CommandListener, Runnable {

    Display disp;

    Command cmdSuppression = new Command("Supprimer", Command.SCREEN, 0);
    Command cmdRetour = new Command("Back", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Projet[] projets;
    List lst = new List("Projets", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Information sur le Projet");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    int id;
    Alert alerta = new Alert("");

    public ListeProjet(String title, Display d) {
        super(title);
        disp = d;
        Thread th = new Thread(this);
        th.start();
        setCommandListener(this);
        lst.addCommand(cmdRetour);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.addCommand(cmdSuppression);
        form.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Projets: \n");
            form.append(showProjet(lst.getSelectedIndex()));

            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
        if (c == cmdSuppression) {
            deleteProjet();
        }
        if (c == cmdRetour) {
            GUI.Acceuil form1 = new GUI.Acceuil("Connexion", disp);
            disp.setCurrent(form1);
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
                    lst.append(projets[i].getTitre() + " ", null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    public void deleteProjet() {
        try {
            id = projets[lst.getSelectedIndex()].getIdProjet();
            System.err.println(id);
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/suppression.php?id=" + id);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            int ch;
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equalsIgnoreCase(sb.toString().trim())) {

                alerta.setString("le Projet a ete supprimer avec succes");
                alerta.setTimeout(2000);
                alerta.setType(AlertType.INFO);
                sb = new StringBuffer("");
                disp.setCurrent(alerta);
                GUI.Acceuil form1 = new GUI.Acceuil("Connexion", disp);
                disp.setCurrent(form1);

            } else {
                alerta.setTitle("Erreur");
                System.out.println(sb.toString().trim());
                alerta.setString("désolée, suppression échoué" + sb.toString().trim());
                alerta.setType(AlertType.ERROR);
                alerta.setTimeout(2000);
                sb = new StringBuffer("");
                disp.setCurrent(alerta);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String showProjet(int i) {
        String res = "";
        if (projets.length > 0) {
            sb.append("*Titre De Projet:");
            sb.append(projets[i].getTitre());
            sb.append("\n");
            sb.append("*Description du projet: ");
            sb.append(projets[i].getDescription());
            sb.append("\n");
            sb.append("*Type Financement :");
            sb.append(projets[i].getTypeFinancement());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

}
