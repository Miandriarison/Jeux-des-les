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
public class V_athletes extends ObjetBdd{
    int id;
    String nom;
    String prenom;
    String genre;
    int iddisciplines;
    String disciplines;
    int idpays;
    String pays;
    
    public Vector<V_athletes> getAll()throws Exception{
        return this.find("select*from v_athletes");
    }

    public int getIddisciplines() {
        return iddisciplines;
    }

    public void setIddisciplines(int iddisciplines) {
        this.iddisciplines = iddisciplines;
    }

    public int getIdpays() {
        return idpays;
    }

    public void setIdpays(int idpays) {
        this.idpays = idpays;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
}
