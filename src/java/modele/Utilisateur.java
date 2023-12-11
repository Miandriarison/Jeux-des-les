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
public class Utilisateur extends ObjetBdd{
    int id;
    String nom;
    String prenom;
    String email;
    String motdepasse;
    
    public Vector<Utilisateur> getById() throws Exception{
        return this.find("select*from utilisateur where id="+id);
    }
    public int returnid(String email, String mdp) throws Exception{
        int id = 0;
        Utilisateur uti = new Utilisateur();
        Vector<Utilisateur> coco= uti.find("select*from Utilisateur where email='"+email+"' and motdepasse='"+motdepasse+"'");
        id = coco.elementAt(0).getId();
        return id;
    }
    public Utilisateur traitementLogin() throws Exception{
        Vector<Utilisateur> utilisateurs=this.find("select*from utilisateur where email='"+email+"' and motdepasse='"+motdepasse+"'");
        if(utilisateurs.size()>0){
            return utilisateurs.elementAt(0);
        }
        else{
            return null;
        }
    }
    public Vector<Utilisateur> getAll()throws Exception{
        return this.find("select*from utilisateur");
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
