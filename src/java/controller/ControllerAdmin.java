/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CSVReader;
import modele.Administrateur;
import modele.Athletes;
import modele.Calendrier;
import modele.Categories;
import modele.Disciplines;
import modele.Genre;
import modele.Pays;
import modele.Produit;
import modele.Recettes_depenses;
import modele.Resultat;
import modele.Sites;
import modele.TableauFinal;
import modele.Utilisateur;
import modele.V_athletes;
import modele.V_calendrier;
import modele.V_medailles;
import modele.V_recettes_depenses;
import modele.V_resultat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author miand
 */
@Controller
public class ControllerAdmin {
    @RequestMapping(value="/")
    public String test(Model model){
        return "index";
    }
    
    @RequestMapping(value="/loginadmin")
    public String loginadmin(Model model){
        return "loginadmin";
    }
    
    @RequestMapping(value="/loginutilisateur")
    public String loginutilisateur(Model model){
        return "loginutilisateur";
    }
    
    @RequestMapping(value="/traitementLoginAdmin")
    public String traitementLoginAdmin(Model model,HttpServletRequest req) throws Exception{
        Administrateur admi = new Administrateur();
        admi.setEmail(req.getParameter("email"));
        admi.setMotdepasse(req.getParameter("motdepasse"));
        admi=admi.traitementLogin();
        if(admi==null){
            model.addAttribute("message","mot de passe ou utilisateur fausse");
            return "loginadmin";
        }
            else{
                HttpSession sess=req.getSession(true);
                sess.setAttribute("idadministrateur",admi.getId());
                model.addAttribute("nomuti", admi.getNom());
                Produit produit = new Produit();
                model.addAttribute("produit",produit.getAll());
                return "accueiladmin";
            }
    }
    @RequestMapping(value="/traitementLoginUtilisateur")
    public String traitementLoginUtilisateur(Model model,HttpServletRequest req) throws Exception{
        Utilisateur admi = new Utilisateur();
        admi.setEmail(req.getParameter("email"));
        admi.setMotdepasse(req.getParameter("motdepasse"));
        admi=admi.traitementLogin();
        int id =admi.returnid(req.getParameter("email"), req.getParameter("motdepasse"));
        if(admi==null){
            model.addAttribute("message","mot de passe ou utilisateur fausse");
            return "loginutilisateur";
        }
            else{
                HttpSession sess=req.getSession(true);
                sess.setAttribute("id", id);
                //V_facture_acte facture_acte = new V_facture_acte();
                Utilisateur utilisateur = new Utilisateur();
                Disciplines disciplines = new Disciplines();
                V_calendrier v_calendrier = new V_calendrier();
                Sites sites = new Sites();
                //Patient patient = new Patient();
                int idu = (int) sess.getAttribute("id");
                utilisateur.setId(idu);
                //model.addAttribute("factures", facture_acte.find("select*from v_facture_acte where idutilisateur="+idu));
                model.addAttribute("utilisateur", utilisateur.getById());
                model.addAttribute("disciplines", disciplines.getAll());
                model.addAttribute("v_calendrier", v_calendrier.getAll());
                model.addAttribute("sites", sites.getAll());
                //model.addAttribute("patient", patient.getAll());
                return "saisieCalendrier";
            }
    }
    //kokokoko
    @RequestMapping(value="/inscriptionutilisateur")
    public String inscriptionutilisateur(Model model){
        return "inscriptionutilisateur";
    }
    
    @RequestMapping(value="/modifierproduit")
    public String modifierproduit(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Produit produit = new Produit();
        model.addAttribute("produitmodifier", produit.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        return "modifierproduit";
    }
    @RequestMapping(value="/supprimerProduit")
    public String supprimerProduit(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Produit produit = new Produit();
        model.addAttribute("produitsupprimer", produit.getAllbyid(idsupprimer));
        model.addAttribute("idsupprimer", idsupprimer);
        return "supprimerProduit";
    }
    @RequestMapping(value="/test")
    public String retourModification(Model model){
        return "test";
    }
    @RequestMapping(value="/lienAjoutProduit")
    public String lienAjoutProduit(Model model){
        return "ajouterProduit";
    }
    @RequestMapping(value="/retourModifier")
    public String retourModifier(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Produit produit = new Produit();
        model.addAttribute("produit", produit.getAll());
        return "accueiladmin";
    }
    @RequestMapping(value="/acceptmodification")
    public String acceptmodification(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        int nombre = Integer.parseInt(req.getParameter("nombre"));
        Produit produit = new Produit();
        produit.modifierProduit(idmodifier, nom, nombre);
        model.addAttribute("produit", produit.getAll());
        return "accueiladmin";
    }
    @RequestMapping(value="/okSupprimerProduit")
    public String okSupprimerProduit(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Produit produit = new Produit();
        produit.supprimerProduit    (idsupprimer);
        model.addAttribute("produit", produit.getAll());
        return "accueiladmin";
    }
    @RequestMapping(value="/insertionProduit")
    public String insertionProduit(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Produit produit = new Produit();
        String nom = req.getParameter("nom");
        int nombre = Integer.parseInt(req.getParameter("nombre"));
        produit.setNom(nom);
        produit.setNombre(nombre);
        produit.create();
        model.addAttribute("produit", produit.getAll());
        return "accueiladmin";
    }
    @RequestMapping(value="/insertionUtilisateur")
    public String insertionUtilisateur(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Utilisateur utilisateur = new Utilisateur();
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String motdepasse = req.getParameter("motdepasse");
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setMotdepasse(motdepasse);
        utilisateur.create();
        return "loginutilisateur";
    }
    @RequestMapping(value="/image")
    public String image(Model model){
        return "image";
    }
    
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value="/modifierpays")
    public String modifierpays(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Pays pays = new Pays();
        model.addAttribute("produitmodifier", pays.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        return "modifierpays";
    }
    
    @RequestMapping(value="/acceptmodificationpays")
    public String acceptmodificationpays(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        Pays pays = new Pays();
        pays.modifierPays(idmodifier, nom);
        return crudPays(model, response, req);
    }
    @RequestMapping(value="/crudPays")
    public String crudPays(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Pays pays = new Pays();
        model.addAttribute("pays", pays.getAll());
        return "pays";
    }
    
    @RequestMapping(value="/retourindex")
    public String retourindex(Model model){
        return "index";
    }
    
    @RequestMapping(value="/insertionPays")
    public String insertionPays(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Pays pays = new Pays();
        String nom = req.getParameter("nom");
        pays.setNom(nom);
        pays.create();
        model.addAttribute("pays", pays.getAll());
        return "pays";
    }
    
    @RequestMapping(value="/SupprimerPays")
    public String SupprimerArtiste(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Pays pays = new Pays();
        pays.supprimerPays(idsupprimer);
        model.addAttribute("pays", pays.getAll());
        return "pays";
    }
    
    @RequestMapping(value="/modifierDisciplines")
    public String modifieDisciplines(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Disciplines disciplines = new Disciplines();
        model.addAttribute("produitmodifier", disciplines.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        return "modifierdisciplines";
    }
    
    @RequestMapping(value="/acceptmodificationDisciplines")
    public String acceptmodificationDisciplines(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        int etat = Integer.parseInt(req.getParameter("etat"));
        Disciplines disciplines = new Disciplines();
        disciplines.modifierDisciplines(idmodifier, nom, etat);
        return crudDisciplines(model, response, req);
    }
    
    @RequestMapping(value="/crudDisciplines")
    public String crudDisciplines(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Disciplines disciplines = new Disciplines();
        model.addAttribute("disciplines", disciplines.getAll());
        return "disciplines";
    }
    
    @RequestMapping(value="/insertionDisciplines")
    public String insertionDisciplines(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Disciplines disciplines = new Disciplines();
        String nom = req.getParameter("nom");
        String code = req.getParameter("code");
        int etat = Integer.parseInt(req.getParameter("etat"));
        disciplines.setNom(nom);
        disciplines.setCode(code);
        disciplines.setEtat(etat);
        disciplines.create();
        model.addAttribute("disciplines", disciplines.getAll());
        return "disciplines";
    }
    
    @RequestMapping(value="/SupprimerDisciplines")
    public String SupprimerDisciplines(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Athletes athletes = new Athletes();
        athletes.supprimerAthletes(idsupprimer);
        model.addAttribute("disciplines", athletes.getAll());
        return "disciplines";
    }
    
    @RequestMapping(value="/modifierathletes")
    public String modifierathletes(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Athletes athletes = new Athletes();
        Genre genre = new Genre();
        Disciplines disciplines = new Disciplines();
        Pays pays = new Pays();
        model.addAttribute("produitmodifier", athletes.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        model.addAttribute("genre", genre.getAll());
        model.addAttribute("disciplines", disciplines.getAllIndivudiel());
        model.addAttribute("pays", pays.getAll());
        return "modifierathletes";
    }
    
    @RequestMapping(value="/acceptmodificationathletes")
    public String acceptmodificationathletes(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        int idgenre = Integer.parseInt(req.getParameter("idgenre"));
        int idpays = Integer.parseInt(req.getParameter("idpays"));
        int iddisciplines = Integer.parseInt(req.getParameter("iddisciplines"));
        Athletes athletes = new Athletes();
        athletes.modifierAthletes(idmodifier, nom,prenom,idgenre,idpays,iddisciplines);
        return crudAthletes(model, response, req);
    }
    
    @RequestMapping(value="/crudAthletes")
    public String crudAthletes(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Athletes athletes = new Athletes();
        Genre genre = new Genre();
        Disciplines disciplines = new Disciplines();
        Pays pays = new Pays();
        V_athletes v_athletes = new V_athletes();
        model.addAttribute("v_athletes", v_athletes.getAll());
        model.addAttribute("athletes", athletes.getAll());
        model.addAttribute("genre", genre.getAll());
        model.addAttribute("disciplines", disciplines.getAllIndivudiel());
        model.addAttribute("pays", pays.getAll());
        return "athletes";
    }
    
    @RequestMapping(value="/insertionAthletes")
    public String insertionAthletes(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Athletes athletes = new Athletes();
        Genre genre = new Genre();
        Disciplines disciplines = new Disciplines();
        Pays pays = new Pays();
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        int idgenre = Integer.parseInt(req.getParameter("idgenre"));
        int iddisciplines = Integer.parseInt(req.getParameter("iddisciplines"));
        int idpays = Integer.parseInt(req.getParameter("idpays"));
        athletes.setNom(nom);
        athletes.setPrenom(prenom);
        athletes.setIdgenre(idgenre);
        athletes.setIddisciplines(iddisciplines);
        athletes.setIdpays(idpays);
        athletes.create();
        V_athletes v_athletes = new V_athletes();
        model.addAttribute("v_athletes", v_athletes.getAll());
        model.addAttribute("athletes", athletes.getAll());
        model.addAttribute("genre", genre.getAll());
        model.addAttribute("disciplines", disciplines.getAllIndivudiel());
        model.addAttribute("pays", pays.getAll());
        return "athletes";
    }
    
    @RequestMapping(value="/SupprimerAthletes")
    public String SupprimerAthletes(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Athletes athletes = new Athletes();
        Genre genre = new Genre();
        Disciplines disciplines = new Disciplines();
        Pays pays = new Pays();
        athletes.supprimerAthletes(idsupprimer);
        V_athletes v_athletes = new V_athletes();
        model.addAttribute("v_athletes", v_athletes.getAll());
        model.addAttribute("athletes", athletes.getAll());
        model.addAttribute("genre", genre.getAll());
        model.addAttribute("disciplines", disciplines.getAllIndivudiel());
        model.addAttribute("pays", pays.getAll());
        return "athletes";
    }
    
    @RequestMapping(value="/modifiersites")
    public String modifiersites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Sites sites = new Sites();
        model.addAttribute("produitmodifier", sites.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        return "modifiersites";
    }
    
    @RequestMapping(value="/acceptmodificationsites")
    public String acceptmodificationsites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        Sites sites = new Sites();
        sites.modifierSites(idmodifier, nom);
        return crudSites(model, response, req);
    }
    
    @RequestMapping(value="/crudSites")
    public String crudSites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Sites sites = new Sites();
        model.addAttribute("sites", sites.getAll());
        return "sites";
    }
    
    @RequestMapping(value="/insertionSites")
    public String insertionSites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Sites sites = new Sites();
        String nom = req.getParameter("nom");
        sites.setNom(nom);
        sites.create();
        model.addAttribute("sites", sites.getAll());
        return "sites";
    }
    
    @RequestMapping(value="/SupprimerSites")
    public String SupprimerSites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Sites sites = new Sites();
        sites.supprimerSites(idsupprimer);
        model.addAttribute("sites", sites.getAll());
        return "sites";
    }
    
    @RequestMapping(value="/publicVoir")
    public String publicVoir(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Sites sites = new Sites();
        Disciplines disciplines = new Disciplines();
        V_calendrier v_calendrier = new V_calendrier();
        model.addAttribute("disciplines", disciplines.getAll());
        model.addAttribute("v_calendrier", v_calendrier.getAll());
        return "voirCalendrier";        
    }
    @RequestMapping(value="/filtreCalendrier")
    public String filtreCalendrier(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Sites sites = new Sites();
        String discip = req.getParameter("iddisciplines");
        String daty = req.getParameter("daty");
        Disciplines disciplines = new Disciplines();
        V_calendrier v_calendrier = new V_calendrier();
        model.addAttribute("disciplines", disciplines.getAll());
        model.addAttribute("v_calendrier", v_calendrier.filtre(discip, daty));
        return "voirCalendrier";        
    }
    @RequestMapping(value="/crudCalendrier")
    public String crudCalendrier(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Sites sites = new Sites();
        Disciplines disciplines = new Disciplines();
        V_calendrier v_calendrier = new V_calendrier();
        model.addAttribute("disciplines", disciplines.getAll());
        model.addAttribute("sites", sites.getAll());
        model.addAttribute("v_calendrier", v_calendrier.getAll());
        return "saisieCalendrier";
    }
    
    @RequestMapping(value="/insertionCalendrier")
    public String insertionCalendrier(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Calendrier calendrier = new Calendrier();
        int iddisciplines = Integer.parseInt(req.getParameter("iddisciplines"));
        String daty = req.getParameter("daty");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parseDate = sdf.parse(daty);
        Timestamp timestamp = new Timestamp(parseDate.getTime());
        int idsites = Integer.parseInt(req.getParameter("idsites"));
        calendrier.setIddisciplines(iddisciplines);
        calendrier.setDaty(timestamp);
        calendrier.setIdsites(idsites);
        calendrier.create();
        return crudCalendrier(model, response, req);
    }
    
    @RequestMapping(value="/SupprimeCalendrier")
    public String SupprimeCalendrier(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Calendrier calendrier = new Calendrier();
        calendrier.supprimerCalendrier(idsupprimer);
        return crudCalendrier(model, response, req);
    }
    
    @RequestMapping(value="/crudResultat")
    public String crudResultat(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Pays pays = new Pays();
        Disciplines disciplines = new Disciplines();
        V_athletes v_athletes = new V_athletes();
        V_resultat v_resultat = new V_resultat();
        model.addAttribute("disciplinesIndivudiel", disciplines.getAllIndivudiel());
        model.addAttribute("disciplinesCollectif", disciplines.getAllCollectif());
        model.addAttribute("pays", pays.getAll());
        model.addAttribute("v_athletes", v_athletes.getAll());
        model.addAttribute("v_resultat", v_resultat.getAll());
        return "saisieResultat";
    }
    
   /* @RequestMapping(value="/insertionResultat")
    public String insertionResultat(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Resultat resultat = new Resultat();
        int iddisciplines = Integer.parseInt(req.getParameter("iddisciplines"));
        int idpays = Integer.parseInt(req.getParameter("idpays"));
        int rang = Integer.parseInt(req.getParameter("rang"));
        resultat.setIddisciplines(iddisciplines);
        resultat.setIdpays(idpays);
        resultat.setRang(rang);
        if(resultat.traitementResultatCollectif().equalsIgnoreCase("")){
            resultat.create();
        }else{
            model.addAttribute("misyio", resultat.traitementResultatCollectif());
        }
        return crudResultat(model, response, req);
    }*/
    @RequestMapping(value="/insertionResultat")
    public String insertionResultat(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Resultat resultat = new Resultat();
        int iddisciplines = Integer.parseInt(req.getParameter("iddisciplines"));
        int idpays = Integer.parseInt(req.getParameter("idpays"));
        int rang = Integer.parseInt(req.getParameter("rang"));
        resultat.setIddisciplines(iddisciplines);
        resultat.setIdpays(idpays);
        resultat.setRang(rang);
        if(resultat.traitementResultatCollectif().equalsIgnoreCase("")){
            resultat.insertResultatCollectif(resultat);
        }else{
            model.addAttribute("misyio", resultat.traitementResultatCollectif());
        }
        return crudResultat(model, response, req);
    }
    @RequestMapping(value="/insertionResultatIndivudiel")
    public String insertionResultatIndivudiel(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Resultat resultat = new Resultat();
        String idathletes = req.getParameter("idathletes");
        String [] zaraina = idathletes.split("/");
        int iddisciplines = Integer.parseInt((zaraina[2]));
        int idpays = Integer.parseInt((zaraina[1]));
        int idathlete = Integer.parseInt((zaraina[0]));
        int rang = Integer.parseInt(req.getParameter("rang"));
        resultat.setIddisciplines(iddisciplines);
        resultat.setIdpays(idpays);
        resultat.setIdathlete(idathlete);
        resultat.setRang(rang);
        if(resultat.traitementResultatIndividuel().equalsIgnoreCase("")){
            resultat.create();
        }else{
            model.addAttribute("misyioIndividuel", resultat.traitementResultatIndividuel());
        }
        return crudResultat(model, response, req);
    }
    
    @RequestMapping(value="/SupprimeResultat")
    public String SupprimeResultat(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Resultat resultat = new Resultat();
        resultat.supprimerResultat(idsupprimer);
        model.addAttribute("resultat", resultat.getAll());
        return crudResultat(model, response, req);
    }
    @RequestMapping(value="/publicVoirmedaille")
    public String publicVoirmedaille(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        V_medailles v_medailles = new V_medailles();
        model.addAttribute("v_medailles", v_medailles.getAll());
        return "voirMedailles";        
    }
    
    /////////////////////////////////categorie/////////////////////////////////////////
    @RequestMapping(value="/modifierCategorie")
    public String modifierCategorie(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Categories categories = new Categories();
        model.addAttribute("produitmodifier", categories.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        return "modifierCategorie";
    }
    
    @RequestMapping(value="/acceptmodificationCategorie")
    public String acceptmodificationCategorie(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        String code = req.getParameter("code");
        String types = req.getParameter("types");
        Categories categories = new Categories();
        categories.modifierCategories(idmodifier, nom, code, types);
        return crudCategorie(model, response, req);
    }
    
    @RequestMapping(value="/crudCategorie")
    public String crudCategorie(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Categories categories = new Categories();
        model.addAttribute("categories", categories.getAll());
        return "categories";
    }
    
    @RequestMapping(value="/insertionCategorie")
    public String insertionCategorie(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Categories categories = new Categories();
        String nom = req.getParameter("nom");
        String code = req.getParameter("code");
        String types = req.getParameter("types");
        categories.setNom(nom);
        categories.setCode(code);
        categories.setTypes(types);
        categories.create();
        return crudCategorie(model, response, req);
    }
    
    @RequestMapping(value="/SupprimerCategorie")
    public String SupprimerCategorie(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Categories categories = new Categories();
        categories.supprimerCategories(idsupprimer);
        model.addAttribute("sites", categories.getAll());
        return crudCategorie(model, response, req);
    }
    
    ///////////////////////////////recette_depense///////////////////////////////////
    
    /*@RequestMapping(value="/modifiersites")
    public String modifiersites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        Sites sites = new Sites();
        model.addAttribute("produitmodifier", sites.getAllbyid(idmodifier));
        model.addAttribute("idmodifier", idmodifier);
        return "modifiersites";
    }
    
    @RequestMapping(value="/acceptmodificationsites")
    public String acceptmodificationsites(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idmodifier = Integer.parseInt(req.getParameter("idmodifier"));
        String nom = req.getParameter("nom");
        Sites sites = new Sites();
        sites.modifierSites(idmodifier, nom);
        return crudSites(model, response, req);
    }
    */
    @RequestMapping(value="/crudImportRD")
    public String crudImportRD(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Sites sites = new Sites();
        Disciplines disciplines = new Disciplines();
        Categories categories = new Categories();
        V_recettes_depenses v_recettes_depenses = new V_recettes_depenses();
        model.addAttribute("sites", sites.getAll());
        model.addAttribute("disciplines", disciplines.getAll());
        model.addAttribute("categories", categories.getAll());
        model.addAttribute("v_recettes_depenses", v_recettes_depenses.getAll());
        return "inportCsv";
    }
    
    @RequestMapping(value="/insertionImportRD")
    public String insertionImport(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        Recettes_depenses recettes_depenses = new Recettes_depenses();
        int iddisciplines = Integer.parseInt(req.getParameter("iddisciplines"));
        int idcategorie = Integer.parseInt(req.getParameter("idcategorie"));
        double prix = Double.parseDouble(req.getParameter("prix"));
        String daty = req.getParameter("daty");
        java.sql.Date datyy = java.sql.Date.valueOf(daty);
        recettes_depenses.setIddisciplines(iddisciplines);
        recettes_depenses.setIdcategorie(idcategorie);
        recettes_depenses.setPrix(prix);
        recettes_depenses.setDaty(datyy);
        recettes_depenses.create();
        return crudImportRD(model, response, req);
    }
    
    @RequestMapping(value="/SupprimerImportRD")
    public String SupprimerImport(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        int idsupprimer = Integer.parseInt(req.getParameter("idsupprimer"));
        Recettes_depenses recettes_depenses = new Recettes_depenses();
        recettes_depenses.supprimerRecettes_depenses(idsupprimer);
        return crudImportRD(model, response, req);
    }
    
    @RequestMapping(value="/fichierCsv")
    public String fichierCsv(Model model, HttpServletResponse response,HttpServletRequest req) throws Exception{
        CSVReader csvR=new CSVReader();
        String test=req.getParameter("fichier");
        //HttpSession sess=req.getSession(true);
        Recettes_depenses.importcsv(test);
        return crudImportRD(model,response,req);
    }
    
    @RequestMapping(value="/crudRDD")
    public String crudRDD(Model model, HttpServletResponse response, HttpServletRequest req) throws Exception{
        TableauFinal tableauFinal = new TableauFinal();
        model.addAttribute("tableauFinal", tableauFinal.getAll());
        return "voirRecetteDepense";
    }
}
