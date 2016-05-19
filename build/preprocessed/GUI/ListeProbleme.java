/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Probleme;
import entities.Projet;
import entityhandlers.ProblemeHandler;
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
 * @author asalhi
 *
 */
public class ListeProbleme extends Form implements CommandListener, Runnable {

    Display disp;

    Command cmdBack = new Command("Back", Command.BACK, 0);

    Command cmdDelete = new Command("Supprimer", Command.OK, 0);

    Probleme[] problem;
    List lst = new List("Liste des Problèmes", List.IMPLICIT);

    Form form = new Form("Détails Problème");

    StringBuffer sb = new StringBuffer();

    Alert alerta;

    public ListeProbleme(String title, Display d) {
        super(title);
        disp = d;

        form.addCommand(cmdBack);
        form.addCommand(cmdDelete);
        form.setCommandListener(this);
        
        lst.addCommand(cmdBack);
        lst.setCommandListener(this);
        Thread th = new Thread(this);
        th.start();
      //  disp.setCurrent(lst);

    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdDelete && d == form) {

            deleteProb();
            disp.setCurrent(lst);

        }

        if (c == cmdBack && d == form) {
            
            form.deleteAll();
            disp.setCurrent(lst);
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Demandes: \n");
            form.append(showProbs(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }
        if (c == cmdBack && d == lst) {
             
            disp.setCurrent(new Menu());
        }

    }

    public void run() {
        try {
            // this will handle our XML
            ProblemeHandler problemesHandler = new ProblemeHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/probList.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, problemesHandler);
            // display the result
            problem = problemesHandler.getProbleme();

            if (problem.length > 0) {
                for (int i = 0; i < problem.length; i++) {
                    lst.append(problem[i].getTitre() + " ", null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showProbs(int i) {
        String res = "";
        if (problem.length > 0) {
            sb.append("* ID : ");
            sb.append(problem[i].getIdProbleme());
            sb.append("\n");
            sb.append("* Catégorie : ");
            sb.append(problem[i].getCategorie());
            sb.append("\n");
            sb.append("* Description : ");
            sb.append(problem[i].getDescription());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public void deleteProb() {
        try {
            int id = problem[lst.getSelectedIndex()].getIdProbleme();
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/delete.php?id=" + id);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            int ch;
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equalsIgnoreCase(sb.toString().trim())) {

                alerta.setTitle("Info");
                alerta.setString("Demande supprimé avec succès");
                alerta.setTimeout(3000);
                alerta.setType(AlertType.INFO);
                sb = new StringBuffer("");
                disp.setCurrent(alerta);
            } else {
                alerta.setTitle("Erreur");
                System.out.println(sb.toString().trim());
                alerta.setString("désolée, suppression achoué" + sb.toString().trim());
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
}
