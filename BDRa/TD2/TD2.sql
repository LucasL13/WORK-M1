CREATE TABLE Hotels ( NumHo, NomHo, RueAdrHo, VilleHo, NbEtoilesHo )
CREATE TABLE TypesChambre ( NumTy, NomTy, PrixTy ) 
CREATE TABLE Chambres ( NumCh, NumHo, NumTy ) 
CREATE TABLE Clients ( NumCl, NomCl, PrénomCl, RueAdrCl, VilleCl ) 
CREATE TABLE Réservations ( NumCL, NumHo, NumTy, DateA, NbJours, NbChambres ) 
CREATE TABLE Occupations ( NumCl, NumHo, NumCh, DateA, DateD )


Q1 )
en mysql :

CREATE TABLE Hotel{
  NumHo INT NOT NULL AUTO_INCREMENT = 100 PRIMARY KEY
}

en oracle :

CREATE SEQUENCE seq_numHo
  MINVALUE 100
  MAXVALUE 1000
  START WITH 110
  INCREMENT BY 10
  NOCACHE;


Q2 ) 

CREATE TRIGGER Hotels.forceSeq
BEFORE INSERT ON Hotels REFERENCING NEW AS N
(UPDATE N SET N.NumHo = seq_numHo.nextval)

Correction : 

create or replace trigger clef_hotels
before insert on hotels
REFRENCING NEW ROW AS newrow
for each row
begin
   newrow.NumHo := NumHo.nextval;
end;


Exercice 2 : 

C1 => "Il est impossible de passer une réservation pour un type de chambre dans un hotel qui ne possede aucune chambre de ce type"
-> "Analyser la sémantique opérationnelle de C1"
  -> "J'essaie de vérifier C1 : l'impact d'une opération (INSERT, DELETE, UPDATE) sur 1 ou plusieurs autres tables"


  CREATE TRIGGER Reservations.





