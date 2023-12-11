/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.util.Vector;

/**
 *
 * @author Nomena
 */
public class Administrateur extends ObjetBdd{
    int id;
    String nom;
    String email;
    String motdepasse;
    
    public int returnid(String email, String mdp) throws Exception{
        int id = 0;
        Administrateur uti = new Administrateur();
        Vector<Administrateur> coco= uti.find("select*from administrateur where email='"+email+"' and motdepasse='"+mdp+"'");
        id = coco.elementAt(0).getId();
        return id;
    }
    public Administrateur traitementLogin() throws Exception{
        Vector<Administrateur> administrateur=this.find("select*from administrateur where email='"+email+"' and motdepasse='"+motdepasse+"'");
        if(administrateur.size()>0){
            return administrateur.elementAt(0);
        }
        else{   
            return null;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
