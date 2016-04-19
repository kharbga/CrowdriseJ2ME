/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author kouki
 */
public class Solution {

    private int idSolution ;

    private String titre ;

    private double salaire ;

    private String description ;

    private String fichierSolution ;

    private String etat ;

    private int MembreId ;

    private int ProblemeId ;

    public int getIdSolution() {
        return idSolution;
    }

    public void setIdSolution(int idSolution) {
        this.idSolution = idSolution;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFichierSolution() {
        return fichierSolution;
    }

    public void setFichierSolution(String fichierSolution) {
        this.fichierSolution = fichierSolution;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getMembreId() {
        return MembreId;
    }

    public void setMembreId(int MembreId) {
        this.MembreId = MembreId;
    }

    public int getProblemeId() {
        return ProblemeId;
    }

    public void setProblemeId(int ProblemeId) {
        this.ProblemeId = ProblemeId;
    }
   
}
