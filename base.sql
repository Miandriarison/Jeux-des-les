create database afaka;
\c afaka
create table administrateur(
    id serial primary key,
    nom varchar(100),
    email varchar(100),
    motdepasse varchar(100)
);
insert into administrateur(nom,email,motdepasse) values ('Nomena','nomena@gmail.com','Nomenaihany');

create table utilisateur(
    id serial primary key,
    nom varchar(100),
    prenom varchar(100),
    email varchar(100),
    motdepasse varchar(100)
);
insert into utilisateur(nom,prenom,email,motdepasse) values ('Leonce','miandry','miandry@gmail.com','miandryihany');
 create table produit(
    id serial primary key,
    nom varchar(100),
    nombre int
 );
 insert into produit(nom,nombre) values ('montre',4);
 insert into produit(nom,nombre) values ('puce',6);

create table genre(
    id serial primary key,
    nom varchar(50)
);
insert into genre (nom) values ('Homme'),('Femme');

create table pays(
    id serial primary key,
    nom varchar(100)
);

create table disciplines(
    id serial primary key,
    nom varchar(100),
    code varchar(3),
    etat int
);

create table athletes(
    id serial primary key,
    nom varchar(100),
    prenom varchar(100),
    idgenre int references genre(id),
    idpays int references pays(id),
    iddisciplines int references disciplines(id)
);

create table sites(
    id serial primary key,
    nom varchar(100)
);

create table calendrier(
    id serial primary key,
    iddisciplines int references disciplines(id),
    daty TIMESTAMP,
    idsites int references sites(id)
);

create table resultat(
    id serial primary key,
    iddisciplines int references disciplines(id),
    idpays int references pays(id),
    idathlete int references athletes(id),
    rang int
);  


create table categories(
    id serial primary key,
    nom varchar(100),
    code varchar(3),
    types varchar(50)
);
create table recettes_depenses(
    id serial primary key,
    iddisciplines int references disciplines(id),
    idcategorie int references categories(id),
    prix decimal(10,2),
    daty date 
);

