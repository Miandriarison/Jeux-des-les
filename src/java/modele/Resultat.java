/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author miand
 */
public class Resultat extends ObjetBdd{
    int id;
    int iddisciplines;
    int idpays;
    int idathlete;
    int rang;
    
    public String traitementResultatCollectif() throws Exception{
        String misy = "";
        V_resultat v_resultat = new V_resultat();
        Vector<V_resultat> resultat=v_resultat.find("select*from v_resultat where (iddisciplines='"+iddisciplines+"' and idpays!='"+idpays+"') or (idpays!='"+idpays+"' and iddisciplines ='"+iddisciplines+"' and rang<4)");
        if(resultat.size()==4){
                misy=misy+"Tsy mety izany";
        }
        return misy;
    }
    public String traitementResultatIndividuel() throws Exception{
        String misy = "";
        Resultat resultats = new Resultat();
        Vector<Resultat> resultat=resultats.find("select*from resultat where iddisciplines='"+iddisciplines+"' and idathlete='"+idathlete+"' and rang<4");
        if(resultat.size()==4){
                misy=misy+"Tsy mety izany";
        }
        return misy;
    }
    public void insertResultatCollectif(Resultat resultat) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            ObjetBdd connect = new ObjetBdd();
            connection = connect.getConnexion();
            String query = "insert into Resultat(iddisciplines,idpays,rang) values(?,?,?)";
            statement = connection.prepareStatement(query);
            System.out.println("queryyyy ==" + query);
            statement.setInt(1, resultat.getIddisciplines());
            statement.setInt(2, resultat.getIdpays());
            statement.setDouble(3, resultat.getRang());

            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public Vector<Resultat> getAll()throws Exception{
        return this.find("select*from resultat");
    }
    public void supprimerResultat(int idsupprimer)throws Exception{
         this.updat("delete from resultat where id="+idsupprimer+"");
    }

    public int getIdathlete() {
        return idathlete;
    }

    public void setIdathlete(int idathlete) {
        this.idathlete = idathlete;
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

    public int getIdpays() {
        return idpays;
    }

    public void setIdpays(int idpays) {
        this.idpays = idpays;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

}
