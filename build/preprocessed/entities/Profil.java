/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Sedki
 */
public class Profil {
    
    private int id ;
    private String Nom ;
    private String Prenom ;
    private String Date_de_naissance ;
    private String Pseudo ;
    private String Image ;
    private String Adresse ;
    private String Profession ;

    public Profil() {
    }

    public Profil(int id, String Nom, String Prenom, String Date_de_naissance, String Pseudo, String Image, String Adresse, String Profession) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Date_de_naissance = Date_de_naissance;
        this.Pseudo = Pseudo;
        this.Image = Image;
        this.Adresse = Adresse;
        this.Profession = Profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getDate_de_naissance() {
        return Date_de_naissance;
    }

    public void setDate_de_naissance(String Date_de_naissance) {
        this.Date_de_naissance = Date_de_naissance;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public void setPseudo(String Pseudo) {
        this.Pseudo = Pseudo;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String Profession) {
        this.Profession = Profession;
    }

    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profil other = (Profil) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Profil{" + "id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Date_de_naissance=" + Date_de_naissance + ", Pseudo=" + Pseudo + ", Image=" + Image + ", Adresse=" + Adresse + ", Profession=" + Profession + '}';
    }
    
    Profil getProgil(){
        return null ;
    }
}
