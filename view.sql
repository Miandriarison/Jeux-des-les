insert into Resultat(iddisciplines,idpays,rang) values(1,1,2);

create or replace view v_athletes as select (athletes.id)id,(athletes.nom)Nom,prenom,(genre.nom)genre,(disciplines.nom)disciplines, disciplines.id as iddisciplines ,(pays.nom)pays,pays.id as idpays from athletes 
join genre on athletes.idgenre = genre.id
join disciplines on athletes.iddisciplines = disciplines.id
join pays on athletes.idpays = pays.id;

create or replace view v_calendrier as select (calendrier.id)id, disciplines.id as iddisciplines, (disciplines.nom)disciplines, daty, (sites.nom)sites from calendrier 
join disciplines on calendrier.iddisciplines = disciplines.id
join sites on calendrier.idsites = sites.id;

create or replace view v_resultat as select (resultat.id)id, (disciplines.id)iddisciplines,(pays.id)idpays , (disciplines.nom)disciplines, (pays.nom)pays ,rang from resultat 
join disciplines on resultat.iddisciplines = disciplines.id
join pays on resultat.idpays = pays.id;

select count (nom) from athletes;

select * from v_calendrier where iddisciplines = '5' and daty = '2023-08-28';


-----------------------------medaille-------------------------------


create or replace view V_or as
SELECT idpays,count(*) as ors from resultat where rang=1 group by idpays;


create or replace view V_argent as
SELECT idpays,count(*) as argent from resultat where rang=2 group by idpays;


create or replace view V_bronze as
SELECT idpays,count(*) as argent from resultat where rang=3 group by idpays;


create or replace view medailles_avant as 
select p.id,p.nom as pays,coalesce (o.ors,0) as ors,coalesce(a.argent,0) as argent,coalesce(b.argent,0) as bronze,(coalesce (o.ors,0)+coalesce (a.argent,0)+coalesce (b.argent,0)) as total from pays as p 
left join v_or as o on o.idpays=p.id
left join v_argent as a on a.idpays=p.id
left join v_bronze as b on b.idpays=p.id;

create or replace view v_medailles as 
select * from medailles_avant order by ors DESC,argent DESC,bronze DESC , total DESC;


MANAO VIEW v_recettes_depenses

    create or replace view v_recettes_depenses as select recettes_depenses.id as id, daty,categories.types as types_categories, categories.code as code_categories, prix, disciplines.code as code_diciplines from recettes_depenses 
    join disciplines on recettes_depenses.iddisciplines = disciplines.id
    join categories on recettes_depenses.idcategorie = categories.id;


create view categorieArgent as
SELECT recettes_depenses.*, categories.nom, categories.code,categories.types
FROM recettes_depenses
JOIN categories ON recettes_depenses.idCategorie = categories.id;

create view Tableau as
SELECT categorieArgent.*, disciplines.nom AS discipline_nom, disciplines.etat, disciplines.code AS discipline_code
FROM categorieArgent 
JOIN disciplines ON categorieArgent.iddisciplines = disciplines.id;

create or replace view TableauFinal as
SELECT 
    disciplines.*,
    SUM(CASE WHEN tableau.types = 'recette' THEN tableau.prix ELSE 0 END) AS recette,
    SUM(CASE WHEN tableau.types = 'depense' THEN tableau.prix ELSE 0 END) AS depense,
    SUM(CASE WHEN tableau.types = 'recette' THEN tableau.prix ELSE -tableau.prix END) AS diff
FROM
    tableau
JOIN
    disciplines ON tableau.iddisciplines = disciplines.id
GROUP BY
    disciplines.id,disciplines.nom;
    

    ________________________________________________________________________________________

    ------tableau hafa mintsy -----
create view avant as
SELECT
    r.iddisciplines,
    SUM(CASE WHEN c.types = 'recette' THEN r.prix ELSE 0 END) AS recette,
    SUM(CASE WHEN c.types = 'depense' THEN r.prix ELSE 0 END) AS depense,
    SUM(CASE WHEN c.types = 'recette' THEN r.prix ELSE -r.prix END) AS diff
FROM
    recettes_depenses as r
join categories as c on c.id=r.idcategorie
group by r.iddisciplines;
----------------
create or replace view TableauFinal as 
select db.nom as disciplines,coalesce(a.iddisciplines,db.id) as iddisciplines ,coalesce (a.recette,0) as recette,coalesce (a.depense,0) as depense,
coalesce (a.diff,0) as diff from disciplines as db
left join avant as a on a.iddisciplines=db.id;  


________________________________________________________________________________________
public String traitementResultatCollectif() throws Exception{
        String misy = "";
        V_resultat v_resultat = new V_resultat();
        Vector<V_resultat> resultat=v_resultat.find("select*from v_resultat where (iddisciplines='"+iddisciplines+"' and idpays='"+idpays+"') or (idpays!='"+idpays+"' and rang='"+rang+"' and iddisciplines='"+iddisciplines+"')");
        if(resultat.size()>0){
            for (int i = 0; i < resultat.size(); i++) {
                misy=misy+"Tsy mety izany satria amin'ny ("+resultat.get(i).getDisciplines()+") i ("+resultat.get(i).getPays()+") dia efa nahazo laharana (" +resultat.get(i).getRang() + ")";
            }
        }
        return misy;
    }