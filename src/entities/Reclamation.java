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
public class Reclamation {
    
    private int idReclamation;

    private String description;

    private String MembreId;

    public Reclamation() {
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembreId() {
        return MembreId;
    }

    public void setMembreId(String MembreId) {
        this.MembreId = MembreId;
    }

}
