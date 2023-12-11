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
public class Categories extends ObjetBdd{
    int id;
    String nom;
    String code;
    String types;
    
    public Vector<Categories> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from categories where id ='"+idmodifier+"'");
    }
    public void modifierCategories(int idupdate,String nom, String code, String types)throws Exception{
         this.updat("update categories set nom='"+nom+"', code='"+code+", types='"+types+" where id="+idupdate+"");
    }
    public Vector<Categories> getAll()throws Exception{
        return this.find("select*from categories");
    }
    
    public void supprimerCategories(int idsupprimer)throws Exception{
         this.updat("delete from categories where id="+idsupprimer+"");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
    
}
