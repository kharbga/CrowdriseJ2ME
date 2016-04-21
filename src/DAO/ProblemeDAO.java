/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Probleme;
import entityhandlers.ProblemsHandler;
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
public class ProblemeDAO {
    public Probleme[] probTab; 
    public Probleme[] select(){
       try {
            ProblemsHandler probHandler = new ProblemsHandler();
            // get a parser object
            SAXParser SAXparser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/probList.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            SAXparser.parse(dis, probHandler);
            // display the result
            probTab = probHandler.getProbleme();
             
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

             return probTab;
   }
    
}
