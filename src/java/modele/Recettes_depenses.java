/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author miand
 */
public class Recettes_depenses extends ObjetBdd{
    int id;
    int iddisciplines;
    int idcategorie;
    double prix;
    Date daty;
    
    public Vector<Recettes_depenses> getAllbyid(int idmodifier)throws Exception{
        return this.find("select*from recettes_depenses where id ='"+idmodifier+"'");
    }
    public void modifierSites(int idupdate,String nom)throws Exception{
         this.updat("update sites set nom='"+nom+"' where id="+idupdate+"");
    }
    public Vector<Recettes_depenses> getAll()throws Exception{
        return this.find("select*from recettes_depenses");
    }
    
    public void supprimerRecettes_depenses(int idsupprimer)throws Exception{
         this.updat("delete from recettes_depenses where id="+idsupprimer+"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddisciplines() {
        return iddisciplines;
    }

    public void setIddisciplines(int iddisciplines) {
        this.iddisciplines = iddisciplines;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
    
    public static Vector<String> importationcsv(String chemin) throws Exception {
        Vector<String> lines = new Vector<String>();
        try {
            try (BufferedReader bf = new BufferedReader(new FileReader(chemin))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    lines.add(line);
                }
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.elementAt(i));
        }
        return lines;
    }
    public static String convertToSQLDate(String inputDate) {
        DateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date date = inputDateFormat.parse(inputDate);
            String sqlDate = outputDateFormat.format(date);
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Recettes_depenses createObject(String [] line,Vector<Disciplines> discipline,Vector<Categories> categorie){
        Recettes_depenses recette=new Recettes_depenses();
        recette.setDaty(Date.valueOf(convertToSQLDate(line[0])));
        recette.setPrix(Double.parseDouble(line[3]));
        for (int i = 0; i < discipline.size(); i++) {
            System.out.println(discipline.get(i).getCode() +" id "+discipline.get(i).getId());
            if(line[4].equalsIgnoreCase(discipline.get(i).getCode())){
                recette.setIddisciplines(discipline.get(i).getId());
            }
        }
        for (int i = 0; i < categorie.size(); i++) {
            if(line[1].equalsIgnoreCase(categorie.get(i).getTypes()) && line[2].equalsIgnoreCase(categorie.get(i).getCode())){
                recette.setIdcategorie(categorie.get(i).getId());
            }
        }
        return recette;
    }
    
    public static void genererObjetAuto(String chemin) throws Exception {
        try {
            Vector<String> lines = importationcsv(chemin);
            Vector<Disciplines> discipline= new Disciplines().getAll();
            Vector<Categories> categorie= new Categories().getAll();
            for (int i = 0; i < lines.size(); i++) {
                String [] line=lines.get(i).split(";");
                Recettes_depenses res=createObject(line, discipline, categorie);
                System.out.println(res);
                res.create();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Recettes_depenses{" + "id=" + id + ", iddisciplines=" + iddisciplines + ", idcategorie=" + idcategorie + ", prix=" + prix + ", daty=" + daty + '}';
    }
    public static void importcsv(String csv) throws Exception{
        String chemin="C:\\Users\\miand\\Documents\\NetBeansProjects\\afaka\\";
        chemin=chemin+csv;
        System.out.println(chemin);
        genererObjetAuto(chemin);
    }
    public static void main(String [] args) throws Exception{
        Recettes_depenses fon=new Recettes_depenses();
        fon.genererObjetAuto("C:\\Users\\miand\\Documents\\NetBeansProjects\\afaka\\coucou.csv");
    }
}
