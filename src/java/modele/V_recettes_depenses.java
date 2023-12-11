/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author miand
 */
public class V_recettes_depenses extends ObjetBdd{
    int id;
    Date daty;
    String types_categories;
    String code_categories;
    Double prix;
    String code_diciplines;
    
    public String getDateFormat() throws ParseException{
        java.util.Date dat=new java.sql.Date(this.getDaty().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEE, dd MMMM yyyy");
        return dateFormat.format(dat);
    }
    public Vector<V_recettes_depenses> getAll()throws Exception{
        return this.find("select*from v_recettes_depenses");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getTypes_categories() {
        return types_categories;
    }

    public void setTypes_categories(String types_categories) {
        this.types_categories = types_categories;
    }

    public String getCode_diciplines() {
        return code_diciplines;
    }

    public void setCode_diciplines(String code_diciplines) {
        this.code_diciplines = code_diciplines;
    }


    public String getCode_categories() {
        return code_categories;
    }

    public void setCode_categories(String code_categories) {
        this.code_categories = code_categories;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

}
