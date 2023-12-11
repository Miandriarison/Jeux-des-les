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
 * @author miand
 */
public class Athletes extends ObjetBdd{
    int id;
    String nom;
    String prenom;
    int idgenre;
    int idpays;
    int iddisciplines;
    
    public Vector<Athletes> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from athletes where id ="+idmodifier+"");
    }
    public void modifierAthletes(int idupdate,String nom,String prenom, int idgenre,int idpays, int iddisciplines)throws Exception{
         this.updat("update athletes set nom='"+nom+"', prenom='"+prenom+"', idgenre="+idgenre+", idpays="+idpays+", iddisciplines="+iddisciplines+" where id="+idupdate+"");
    }
    
    public Vector<Athletes> getAll()throws Exception{
        return this.find("select*from athletes");
    }
    
    public void supprimerAthletes(int idsupprimer)throws Exception{
         this.updat("delete from athletes where id="+idsupprimer+"");
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

    public int getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(int idgenre) {
        this.idgenre = idgenre;
    }
    
    public int getIdpays() {
        return idpays;
    }

    public void setIdpays(int idpays) {
        this.idpays = idpays;
    }

    public int getIddisciplines() {
        return iddisciplines;
    }

    public void setIddisciplines(int iddisciplines) {
        this.iddisciplines = iddisciplines;
    }
    
}
