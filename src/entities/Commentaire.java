/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;



public class Commentaire {

    private int idCommentaire;

    private String description;

    private Date dateCommentaire;

    private int Projetid;

    private int Membreid;

    public Commentaire() {
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public int getProjetid() {
        return Projetid;
    }

    public void setProjetid(int Projetid) {
        this.Projetid = Projetid;
    }

    public int getMembreid() {
        return Membreid;
    }

    public void setMembreid(int Membreid) {
        this.Membreid = Membreid;
    }

   
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", description=" + description + ", dateCommentaire=" + dateCommentaire + ", Projetid=" + Projetid + ", Membreid=" + Membreid + '}';
    }

   
    

}
