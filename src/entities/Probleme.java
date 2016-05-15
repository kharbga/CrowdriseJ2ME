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
public class Probleme {

    private int idProbleme;

    private String titre;

    private String description;

    private Date dateProbleme;

    private Date deadlineProbleme;

    private String fichierProbleme;
    private String Categorie;

    private int MembreId;

    public Probleme(String titre) {
        this.titre = titre;
    }

    public Probleme() {
    }

    public int getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(int idProbleme) {
        this.idProbleme = idProbleme;
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

    public Date getDateProbleme() {
        return dateProbleme;
    }

    public void setDateProbleme(Date dateProbleme) {
        this.dateProbleme = dateProbleme;
    }

    public Date getDeadlineProbleme() {
        return deadlineProbleme;
    }

    public void setDeadlineProbleme(Date deadlineProbleme) {
        this.deadlineProbleme = deadlineProbleme;
    }

    public String getFichierProbleme() {
        return fichierProbleme;
    }

    public void setFichierProbleme(String fichierProbleme) {
        this.fichierProbleme = fichierProbleme;
    }

    public int getMembreId() {
        return MembreId;
    }

    public void setMembreId(int MembreId) {
        this.MembreId = MembreId;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

}
