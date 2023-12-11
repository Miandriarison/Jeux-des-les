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
public class Pays extends ObjetBdd{
    int id;
    String nom;
    
    public Vector<Pays> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from pays where id ="+idmodifier+"");
    }
    public void modifierPays(int idupdate,String nom)throws Exception{
         this.updat("update pays set nom='"+nom+"' where id="+idupdate+"");
    }
    
    public Vector<Produit> getAll()throws Exception{
        return this.find("select*from pays");
    }
    
    public void supprimerPays(int idsupprimer)throws Exception{
         this.updat("delete from pays where id="+idsupprimer+"");
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
