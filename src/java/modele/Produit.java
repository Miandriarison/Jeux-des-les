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
public class Produit extends ObjetBdd{
    int id;
    String nom;
    int nombre;
    
    public Vector<Produit> getAll()throws Exception{
        return this.find("select*from produit");
    }
    public Vector<Produit> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from produit where id ='"+idmodifier+"'");
    }
    public void modifierProduit(int idupdate,String nom, int nombre)throws Exception{
         this.updat("update produit set nom='"+nom+"', nombre='"+nombre+"' where id="+idupdate+"");
    }
    public void supprimerProduit(int idupdate)throws Exception{
         this.updat("delete from produit where id="+idupdate+"");
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

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
