/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.text.DecimalFormat;
import java.util.Vector;

/**
 *
 * @author miand
 */
public class TableauFinal extends ObjetBdd{
    int id;
    String disciplines;
    String code;
    int etat;
    double recette;
    double depense;
    double diff;
    
    public Vector<TableauFinal> getAll()throws Exception{
        return this.find("select*from TableauFinal");
    }
    public String mamadikaDifference(){
        String diff = "";
        DecimalFormat decimal = new DecimalFormat("0,000.00");
        if(getDiff()<0){
            diff="("+decimal.format(getDiff()*(-1))+")";
        }else{
            diff=decimal.format(getDiff());
        }
        return diff;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public double getRecette() {
        return recette;
    }

    public void setRecette(double recette) {
        this.recette = recette;
    }

    public double getDepense() {
        return depense;
    }

    public void setDepense(double depense) {
        this.depense = depense;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }
    
}
