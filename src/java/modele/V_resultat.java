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
public class V_resultat extends ObjetBdd{
    int id;
    int iddisciplines;
    int idpays;
    String disciplines;
    String pays;
    int rang;
    
    public Vector<V_resultat> getAll()throws Exception{
        return this.find("select*from v_resultat");
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

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }
    
}
