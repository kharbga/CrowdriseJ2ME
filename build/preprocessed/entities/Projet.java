/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;


/**
 *
 * @author kouki
 */
public class Projet {

    private int idProjet;

    private String titre;

    private String description;

    private String typeFinancement;

    private Date dateProjet;

    private Date deadlineProjet;

    private String fichierProjet;

    private int MembreId;

    private String imageProjet;

    private String videoProjet;

    private int idcat;

    public Projet() {
    }

    public Projet(int idProjet, String titre, String description, String typeFinancement, Date dateProjet, Date deadlineProjet, String fichierProjet, int MembreId, String imageProjet, String videoProjet, int idcat) {
        this.idProjet = idProjet;
        this.titre = titre;
        this.description = description;
        this.typeFinancement = typeFinancement;
        this.dateProjet = dateProjet;
        this.deadlineProjet = deadlineProjet;
        this.fichierProjet = fichierProjet;
        this.MembreId = MembreId;
        this.imageProjet = imageProjet;
        this.videoProjet = videoProjet;
        this.idcat = idcat;
    }

   

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeFinancement() {
        return typeFinancement;
    }

    public void setTypeFinancement(String typeFinancement) {
        this.typeFinancement = typeFinancement;
    }

    public Date getDateProjet() {
        return dateProjet;
    }

    public void setDateProjet(Date dateProjet) {
        this.dateProjet = dateProjet;
    }

    public Date getDeadlineProjet() {
        return deadlineProjet;
    }

    public void setDeadlineProjet(Date deadlineProjet) {
        this.deadlineProjet = deadlineProjet;
    }

    public String getFichierProjet() {
        return fichierProjet;
    }

    public void setFichierProjet(String fichierProjet) {
        this.fichierProjet = fichierProjet;
    }

    public int getMembreId() {
        return MembreId;
    }

    public void setMembreId(int MembreId) {
        this.MembreId = MembreId;
    }

    public String getImageProjet() {
        return imageProjet;
    }

    public void setImageProjet(String imageProjet) {
        this.imageProjet = imageProjet;
    }

    public String getVideoProjet() {
        return videoProjet;
    }

    public void setVideoProjet(String videoProjet) {
        this.videoProjet = videoProjet;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

   
}
