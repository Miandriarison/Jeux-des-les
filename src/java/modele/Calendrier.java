/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import genreiquedao.ObjetBdd;
import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author miand
 */
public class Calendrier extends ObjetBdd{
    int id;
    int iddisciplines;
    Timestamp daty;
    int idsites;
    
    public Vector<Calendrier> getAll()throws Exception{
        return this.find("select*from calendrier");
    }
    public void supprimerCalendrier(int idsupprimer)throws Exception{
         this.updat("delete from calendrier where id="+idsupprimer+"");
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

    public Timestamp getDaty() {
        return daty;
    }

    public void setDaty(Timestamp daty) {
        this.daty = daty;
    }

    public int getIdsites() {
        return idsites;
    }

    public void setIdsites(int idsites) {
        this.idsites = idsites;
    }

    
}
