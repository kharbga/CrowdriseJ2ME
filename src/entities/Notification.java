/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author Sedki
 */
public class Notification {
    
    private int id ;
    private String Receiver ;
    private String Producer ;
    private String Message ;

    public Notification() {
    }

    public Notification(int id, String Receiver, String Producer, String Message) {
        this.id = id;
        this.Receiver = Receiver;
        this.Producer = Producer;
        this.Message = Message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String Producer) {
        this.Producer = Producer;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notification other = (Notification) obj;
        return true;
    }

    public String toString() {
        return "Notification{" + "id=" + id + ", Receiver=" + Receiver + ", Producer=" + Producer + ", Message=" + Message + '}';
    }
    
    
}
