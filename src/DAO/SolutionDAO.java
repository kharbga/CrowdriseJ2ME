/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.Solutions;
import entities.Probleme;
import entities.Solution;
import entityhandlers.ProblemsHandler;
import entityhandlers.SolutionHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Rayhana
 */
public class SolutionDAO {

    public boolean insert(String titre, double renum, String desc) {
        try {
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/solutionAdd.php?titre=" + titre + "&renum=" + renum + "&desc=" + desc);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if (sb.toString().trim().equals("OK")) {
                return true;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Solution[] SolutionTab;

    public Solution[] select() {
        try {
            SolutionHandler solHandler = new SolutionHandler();
            // get a parser object
            SAXParser SAXparser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/SolutionList.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            SAXparser.parse(dis, solHandler);
            // display the result
            SolutionTab = solHandler.getSolution();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return SolutionTab;
    }
}
