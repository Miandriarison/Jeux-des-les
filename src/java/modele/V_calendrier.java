/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author miand
 */
public class V_calendrier extends ObjetBdd{
    int id;
    int iddisciplines;
    String disciplines;
    Timestamp daty;
    String sites;
    
    public String getDateFormat() throws ParseException{
        java.util.Date dat=new java.sql.Date(this.getDaty().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEE, dd MMMM yyyy 'a' HH:mm:ss", Locale.FRANCE);
        return dateFormat.format(dat);
    }
    public Vector<V_calendrier> getAll()throws Exception{
        return this.find("select*from v_calendrier");
    }
    /*public Date getDate(Timestamp time){
         long millesecond=time.getTime();
         Date dates=new Date(millesecond);
       return dates;
    }
    public static Timestamp getdate(String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date parsedDate = sdf.parse(date);
            Timestamp timestamp  = new Timestamp(parsedDate.getTime());
            return timestamp;
    }*/
    public static String requette(String discipline,String date) throws ParseException{
        String value="";
        if(discipline.equalsIgnoreCase("")&& date.equalsIgnoreCase("")!=true){
            value="select * from v_calendrier where ((Date(daty)='"+java.sql.Date.valueOf(date)+"' ))";
        }else if(discipline.equalsIgnoreCase("")!=true&& date.equalsIgnoreCase("")){
            value="select * from v_calendrier where disciplines ='"+discipline+"'";
        }else if(discipline.equalsIgnoreCase("")!=true&& date.equalsIgnoreCase("")!=true){
            value="select * from v_calendrier where disciplines ='"+discipline+"' and((Date(daty)='"+java.sql.Date.valueOf(date)+"' ))";
        }
        else{
            value="select * from v_calendrier";
        }
        return value;
    }
    public Vector<V_calendrier> filtre(String discip, String daty)throws Exception{
        return this.find(requette(discip, daty));
    }

    public int getIddisciplines() {
        return iddisciplines;
    }

    public void setIddisciplines(int iddisciplines) {
        this.iddisciplines = iddisciplines;
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

    
    public Timestamp getDaty() {
        return daty;
    }

    public void setDaty(Timestamp daty) {
        this.daty = daty;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    
}
