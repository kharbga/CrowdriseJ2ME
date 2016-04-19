
package entities;



public class Administrateur {

    private int idAdministrateur;
    private String login;
    private String motDePasse;

    public Administrateur() {
    }

    public int getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(int idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public String toString() {
        return "Administrateur{" + "idAdministrateur=" + idAdministrateur + ", login=" + login + ", motDePasse=" + motDePasse + '}';
    }

   
  

  
}
