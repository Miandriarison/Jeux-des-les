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
public class Sites extends ObjetBdd{
    int id;
    String nom;
    
    public Vector<Sites> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from sites where id ='"+idmodifier+"'");
    }
    public void modifierSites(int idupdate,String nom)throws Exception{
         this.updat("update sites set nom='"+nom+"' where id="+idupdate+"");
    }
    public Vector<Sites> getAll()throws Exception{
        return this.find("select*from sites");
    }
    
    public void supprimerSites(int idsupprimer)throws Exception{
         this.updat("delete from sites where id="+idsupprimer+"");
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
}
