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
public class Disciplines extends ObjetBdd{
    int id;
    String nom;
    String code;
    int etat;
    
    public Vector<Disciplines> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from disciplines where id ='"+idmodifier+"'");
    }
    public void modifierDisciplines(int idupdate,String nom,int etat)throws Exception{
         this.updat("update disciplines set nom='"+nom+"', etat="+etat+" where id="+idupdate+"");
    }
    public Vector<Disciplines> getAll()throws Exception{
        return this.find("select*from disciplines");
    }
    public Vector<Disciplines> getAllIndivudiel()throws Exception{
        return this.find("select*from disciplines where etat = 1");
    }
    public Vector<Disciplines> getAllCollectif()throws Exception{
        return this.find("select*from disciplines where etat = 2");
    }
    
    public void supprimerDisciplines(int idsupprimer)throws Exception{
         this.updat("delete from disciplines where id="+idsupprimer+"");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}
