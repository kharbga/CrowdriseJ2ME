/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 * @author asalhi
 */
import java.io.IOException;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.*;

/**
 *
 * @author asalhi
 */
public class Contact extends Form implements CommandListener, Runnable {

    Display display;
    private TextField destinataire;
    private TextField message;
    private TextField Email;
    private Alert alert;
    private Command send, exit,Map;
    MessageConnection clientConnexion;
    private Form ContactForm;
    Image img = null;
    private ChoiceGroup Subject;
    private Ticker tk;

    public Contact(String title, Display d) {
         super(title);
        display = d;

       
        ContactForm = new Form("Contactez-Nous");
        
        send = new Command("Envoyer", Command.BACK, 1);
        exit = new Command("Exit", Command.OK, 0);
        Map = new Command("Map", Command.ITEM, 0);

        destinataire = new TextField("A", "", 20, TextField.PHONENUMBER);
        message = new TextField("Message", "", 800, TextField.ANY);
        Email = new TextField("Votre Email", "", 20, TextField.EMAILADDR);

        tk = new Ticker("Bienvenue - Nous sommes à votre écoute");

        String tab_smsTypes[] = {"Contact", "Suggestion", "Réclamation"};
        Subject = new ChoiceGroup("\n Sujet : \n ", ChoiceGroup.EXCLUSIVE, tab_smsTypes, null);

         
        try {
            img = Image.createImage("/GUI/sms.png");

        } catch (IOException ex) {
        }
        ContactForm.append(img);
        ContactForm.append(new Spacer(0, 20));
        ContactForm.append(Subject);
        ContactForm.append(new Spacer(0, 20));
        ContactForm.append(Email);
        ContactForm.append(new Spacer(0, 10));
        ContactForm.append(destinataire);
        ContactForm.append(new Spacer(0, 10));
        ContactForm.append(message);
        ContactForm.addCommand(send);
        ContactForm.addCommand(exit);
        ContactForm.addCommand(Map);
        ContactForm.setTicker(tk);
        ContactForm.setCommandListener(this);

    }

    

    public void commandAction(Command cmd, Displayable disp) {
        if (cmd == exit) {
            //destroyApp(false);
        }
        
        
        
         if (cmd == send) {

            Thread th = new Thread(this);
            th.start();
        }
    }

    public void run() {

        String num = destinataire.getString();
        String msg = message.getString();
        String mail = Email.getString();
        String sujet = Subject.getString(Subject.getSelectedIndex());
        if (num.equals("")) {
            alert = new Alert("Erreur", "Entrer le numero de votre destinataire!", null, AlertType.ERROR);
            alert.setTimeout(2000);
            display.setCurrent(alert);
        } else if (msg.equals("")) {
            alert = new Alert("Erreur", "Entrer votre Message!", null, AlertType.ERROR);
            alert.setTimeout(2000);
            display.setCurrent(alert);
        } else if (mail.equals("")) {
            alert = new Alert("Erreur", "Entrer votre Email!", null, AlertType.ERROR);
            alert.setTimeout(2000);
            display.setCurrent(alert);
        } else {
            try {
                clientConnexion = (MessageConnection) Connector.open("sms://" + num); // +"From" +member
                alert = new Alert("Info", "Message envoyé avec succés", null, AlertType.INFO);
                alert.setTimeout(2000);
                Email.setString("");
                destinataire.setString("");
                message.setString("");
                display.setCurrent(alert);
            } catch (Exception e) {
                alert = new Alert("Erreur", "Problème de connexion", null, AlertType.ERROR);
                alert.setTimeout(2000);
                display.setCurrent(alert);
            }
            try {
                TextMessage textmessage = (TextMessage) clientConnexion.newMessage(MessageConnection.TEXT_MESSAGE);
                textmessage.setAddress("sms://" + num);
                textmessage.setPayloadText(" Subject :" + " " + sujet + " \n From :" + " " + mail + " \n Message :" + " " + msg);

                clientConnexion.send(textmessage);
            } catch (Exception e) {
                Alert alert = new Alert("Erreur", "Echec d'envoie !!!", null, AlertType.ERROR);
                alert.setTimeout(Alert.FOREVER);
                alert.setString("Echec d'envoie");
                display.setCurrent(alert);
            }
        }
    }

}
