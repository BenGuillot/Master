#après correction du schéma :
#suppressions des tables Editeur et Brouillon.
#Ajout de l'attribut niveauGrammaire d'Editeur à Redacteur
#Ajout de l'attribut nbFilm à Acteur
#Ajout de l'attribut Contenue à Critique
#Supression de la table categorie (potentiellemnt provisoire)
#Supression des attributs ID pour Reacteur et Acteur parce qu'inutiles 
#modification de l'attribut école par nationalité dans la table Acteur 
#Ajout des tables Producteur et Produit

drop table if exists Personne, Redacteur, Acteur, Film, JoueDans, Critique, Producteur, Produit;

CREATE TABLE Personne (
	ID SMALLINT PRIMARY KEY,
	nom char (40),
	prenom char (40),
	date_de_naissance varchar (10)
);

INSERT INTO Personne VALUES 
	(0001,'Murphy','Cillian','25/05/1976'), #28 jours plus tard
	(0002,'Harris','Naomie','06/09/1976'), 
	(0003,'Carlyle','Robbert','14/04/1961'),#28 semaines plus tard
	(0004,'Byrne','Rose','24/08/1979'),
	(0005,'Affleck','Ben','15/08/1972'), #BVS
	(0006,'Cavill','Henry','05/05/1983'),
	(0007,'Gadot','Gal','30/04/1985'),
	(0008,'Pitt','Brad','18/12/1963'), #Benjamin Button
	(0009,'Blanchett','Cate','14/05/1969'),
	(0010,'Rylance','Mark','18/01/1960'), #BGG
	(0011,'Barnhill','Ruby','16/07/2004'),
	(0012,'Estevez','Emilio','12/05/1962'), #Breakfast club
	(0013,'Neslon','Judd','28/11/1954'),
	(0014,'Ringwald','Molly','18/02/1968'),
	(0015,'Evans','Chris','13/06/1981'), #Captain America
	(0016,'Weaving','Hugo','04/04/1916'),
	(0017,'Jackson','Samuel L.','21/12/1948'),
	(0018,'Willis','Bruce','19/03/1955'),#5 element
	(0019,'Jovovich','Milla','17/12/1975'),
	(0020,'Hanks','Tom','09/07/1956'),#cloud Atlas
	(0021,'Berry','Halle','14/08/1966'),
	(0022,'Depp','Johnny','09/06/1963'), #corpse bride
	(0023,'Bonham Carter','Helena','26/05/1966'),
	(0024,'Pfieffer','Michelle','29/04/1958'),#dark shadow
	(0025,'Green','Eva','06/06/1980'),
	(0026,'Gosling','Ryan','12/11/1980'),#drive
	(0027,'Thomas','Henry','09/09/1971'),#E.T
	(0038,'Broderick','Matthew','21/03/62'),#Ferris Bueller
	(0039,'Norton','Edward','18/08/1969'),#Fight Club
	(0040,'Baldwin','Alec','03/04/1958'),#Final Fantasy 
	(0041,'Radcliff','Daniel','23/07/1989'),#HP
	(0042,'Grint','Rupert','24/08/1988'),
	(0043,'Watson','Emma','15/04/1990'),
	(0044,'Damon','Matt','08/10/1970'),#soldat ryan
	(0045,'DiCaprio','Leonardo','11/11/1974'),#inception
	(0046,'Smith','Will','25/09/1968'),#independance day
	(0047,'Goldblum','Jeff','22/10/1952'),
	(0048,'Waltz','Christoph','04/10/1956'),#inglorious basterds
	(0049,'Moynahan','Briget','28/04/1971'),#I, Robot
	(0050,'McConaughey','Matthew','04/11/1969'),#interstellar
	(0051,'Hathaway','Anne','12/11/1982'),
	(0052,'Takeshi','Beat','18/0/1947'),#Kikujiro
	(0053,'Clarks Duncan','Michael','10/12/1957'),#green mile
	(0054,'Chapman','Graham','08/01/1911'),#life of Brian
	(0055,'Robbie','Margot','02/06/1990'),#wolf of wall street
	(0056,'McCatface','Catty','04/06/1969'),#redac 
	(0057,'Mcdogface','Doug','29/11/1982'),#redac
	(0058,'Randomu','Randy','15/01/1975'),#redac
	(0059,'Randomy','Luc','09/08/1989');#redac





CREATE TABLE Redacteur (
	IDred SMALLINT PRIMARY KEY,
	langue enum ('Français',
				'Anglais',
				'Allemand',
				'Espagnol',
				'Chinois',
				'Russe') default 'Français' not null,
	niveauGrammaire enum ('standard','bon','excellent') default 'standard' not null,
	FOREIGN KEY (IDred) REFERENCES Personne (ID)
);

INSERT INTO Redacteur VALUES
	(0056,'Anglais','bon'),#redac 
	(0057,'Espagnol','standard'),#redac
	(0058,'Français','standard'),#redac
	(0059,'Russe','excellent');#redac

CREATE TABLE Acteur(
	IDA SMALLINT PRIMARY KEY,
	nationalité varchar (40),#anciennement école 
	nbFilm int (4),#par raport à la base de donnée 
	FOREIGN KEY (IDA) REFERENCES Personne (ID)
);

INSERT INTO Acteur VALUES
	(0001,'Irlandais',2), #28 jours plus tard et Inception
	(0002,'Anglaise',1),  #28 jours plus tard 
	(0003,'Ecossais',1),#28 semaines plus tard
	(0004,'Australienne',1),#28 semaines plus tard
	(0005,'Americain',1), #BVS
	(0006,'Anglais',1),#BVS
	(0007,'Israëlienne ',1),#BVS
	(0008,'Americain',3), #Benjamin Button Fight Club  Inglorious Basterd
	(0009,'Australienne',1),#Benjamin Button
	(0010,'Anglais',1), #BGG
	(0011,'Anglais',1),#BGG
	(0012,'Americain',1), #Breakfast club
	(0013,'Americain',1), #Breakfast club
	(0014,'Americain',1), #Breakfast club
	(0015,'Americain',1), #Captain America
	(0016,'Nigerien',2),#Captain America
	(0017,'Americain',1),#Captain America
	(0018,'Allemand',1),#5 element
	(0019,'Russe',1),#5 element
	(0020,'Americain',3),#cloud Atlas soldat ryan green mile
	(0021,'Americaine',1), #cloud Atlas
	(0022,'Americain',2), #corpse bride dark shadow
	(0023,'Anglaise',2), #corpse bride dark shadow
	(0024,'Americaine',1),#dark shadow
	(0025,'Française',1), #dark shadow
	(0026,'Canadien',1),#drive
	(0027,'Americain',1),#E.T
	(0038,'Americain',1), #Ferris Bueller
	(0039,'Americain',1),#Fight Club
	(0040,'Americain',1),#Final Fantasy 
	(0041,'Anglais',8),#HP
	(0042,'Anglais',8),#HP
	(0043,'Anglaise',8),#HP
	(0044,'Americain',1),#soldat ryan
	(0045,'Americain',2),#inception wolf of wall street
	(0046,'Americain',2),#independance day I, Robot
	(0047,'Americain',1), #independance day
	(0048,'Autrichien',1),#inglorious basterds
	(0049,'Americaine',1),#I, Robot
	(0050,'Americain',1),#interstellar
	(0051,'Americaine',1),#interstellar
	(0052,'Japonais',1),#Kikujiro
	(0053,'Americain',1),#green mile
	(0054,'Anglais',1),#life of Brian
	(0055,'Australienne',1);#wolf of wall street

CREATE TABLE Film(
	IDF SMALLINT PRIMARY KEY,
	titre varchar (60),
	release_date varchar (10), #date semble être un mot clef d'ou le changement par rapport au schéma
	durée varchar (10),
	realisateur varchar (40),
	nomCat enum ('Divertissement',
				'Comedie',
				'Drame',
				'Horreur',
				'Science Fiction',
				'Fantasy',
				'Aventure',
				'Action',
				'Guerre',
				'Animation') default 'Divertissement' not null #defini etrangère plus bas dans le fichier
	
);

CREATE TABLE JoueDans(
	IDA SMALLINT not null,
	IDF SMALLINT not null,
	PRIMARY KEY (IDA, IDF),
	FOREIGN KEY (IDA) REFERENCES Acteur (IDA),
	FOREIGN KEY (IDF) REFERENCES Film (IDF)
);

INSERT INTO JoueDans VALUES
	(0001,033), #28 jours plus tard et Inception
	(0001,009),
	(0002,033),  #28 jours plus tard 
	(0003,032),#28 semaines plus tard
	(0004,032),#28 semaines plus tard
	(0005,031), #BVS
	(0006,031),#BVS
	(0007,031),#BVS
	(0008,030), #Benjamin Button Fight Club Inglorious Basterds
	(0008,019),
	(0008,007),
	(0009,030),#Benjamin Button
	(0010,029), #BGG
	(0011,029),#BGG
	(0012,028), #Breakfast club
	(0013,028), #Breakfast club
	(0014,028), #Breakfast club
	(0015,027), #Captain America
	(0016,027),#Captain America Cloud Atlas
	(0016,025),
	(0017,027),#Captain America
	(0018,026),#5 element
	(0019,026),#5 element
	(0020,025),#cloud Atlas soldat ryan green mile
	(0020,034),
	(0020,003),
	(0021,025), #cloud Atlas
	(0022,024), #corpse bride dark shadow
	(0022,023),
	(0023,024), #corpse bride dark shadow
	(0023,023),
	(0024,023),#dark shadow
	(0025,023), #dark shadow
	(0026,022),#drive
	(0027,021),#E.T
	(0038,020), #Ferris Bueller
	(0039,019),#Fight Club
	(0040,018),#Final Fantasy 
	(0041,017),#HP
	(0041,016),#HP
	(0041,015),#HP
	(0041,014),#HP
	(0041,013),#HP
	(0041,012),#HP
	(0041,011),#HP
	(0041,010),#HP
	(0042,017),#HP
	(0042,016),#HP
	(0042,015),#HP
	(0042,014),#HP
	(0042,013),#HP
	(0042,012),#HP
	(0042,011),#HP
	(0042,010),#HP
	(0043,017),#HP
	(0043,016),#HP
	(0043,015),#HP
	(0043,014),#HP
	(0043,013),#HP
	(0043,012),#HP
	(0043,011),#HP
	(0043,010),#HP
	(0044,034),#soldat ryan
	(0045,009),#inception wolf of wall street
	(0045,001),
	(0046,008),#independance day I, Robot
	(0046,006),
	(0047,008), #independance day
	(0048,007),#inglorious basterds
	(0049,006),#I, Robot
	(0050,005),#interstellar
	(0051,005),#interstellar
	(0052,004),#Kikujiro
	(0053,003),#green mile
	(0054,002),#life of Brian
	(0055,001);#wolf of wall street
	
	

CREATE TABLE Critique(
	IDc SMALLINT PRIMARY KEY,
	titre varchar (10),
	date_sortie varchar (10),
	IDred SMALLINT not null,
	IDF SMALLINT not null,
	Contenue varchar (500),
	FOREIGN KEY (IDred) REFERENCES Redacteur (IDred),
	FOREIGN KEY (IDF) REFERENCES Film (IDF)
);

INSERT INTO Critique VALUES
	(0001,'Critique sur Le loup de wall street','30/12/2013', 0056, 001,' '),
	(0002,'Critique sur La vie de Brian','08/05/1980',0059, 002, ' '),
	(0003,'Critique sur La ligne verte','25/03/2000',0057,003,' '),
	(0004,'Critique sur Kikujiro','25/10/1999',0056,004,' '),
	(0005,'Critique sur Interstellar','09/11/14',0058,005,' '),
	(0006,'Critique sur I, Robot','04/08/2004',0059,006,' '),
	(0007,'Critique sur Inglourious Basterds','29/08/2009',0057,007,' '),
	(0008,'Critique sur Independence Day','22/10/1996',0058,008,' '),
	(0009,'Critique sur Inception','26/07/2010',0056,009,' '),
	(0010,'Critique sur Harry Potter à l\'école des sorciers','15/12/2001',0059,010,' '),
	(0011,'Critique sur Harry Potter et la chambre des secrets','14/12/2002',0059,0011,' '),
	(0012,'Critique sur Harry Potter et le prisonnier d\'Azkaban','12/06/2004',0059,012,' '),
	(0013,'Critique sur Harry Potter et la coupe de feu','05/12/2005',0059,013,' '),
	(0014,'Critique sur Harry Potter et l\'ordre du phenix','21/07/2007',0059,014,' '),
	(0015,'Critique sur Harry Potter et le prince de Sang-Mele','25/07/2009',0059,015,' '),
	(0016,'Critique sur Harry Potter et les reliques de la mort (1)','04/12/2010',0059,016,' '),
	(0017,'Critique sur Harry Potter et les reliques de la mort (2)','23/07/2011',0059,017,' '),
	(0018,'Critique sur Final Fantasy les créatures de l\'esprit','25/08/2001',0057,018,' '),
	(0019,'Critique sur Fight Club','20/11/1999',0058,019,' '),
	(0020,'Critique sur La folle journée de Ferris Bueller','27/12/1986',0056,020,' '),
	(0021,'Critique sur E.T l\'Extra-Terrestre','11/12/1982',0059,021,' '),
	(0022,'Critique sur Drive','15/10/2011',0058,022,' '),
	(0023,'Critique sur Dark Shadows','19/05/2012',0057,023,' '),
	(0024,'Critique sur Les noces funèbres','29/10/2005',0058,024,' '),
	(0025,'Critique sur Cloud Atlas','23/03/2013',0056,025,' '),
	(0026,'Critique sur Le cinquième element','17/05/1997',0059,026,' '),
	(0027,'Critique sur Captain America -First Avanger-','27/08/2011',0057,027,' '),
	(0028,'Critique sur Breakfast Club','21/09/1985',0058,028,' '),
	(0029,'Critique sur BGG Le Bon Gros Géant','30/07/2016',0058,029,' '),
	(0030,'Critique sur L\'Etrange Histoire de Benjamin Button','14/02/2009',0056,030,' '),
	(0031,'Critique sur Batman v Superman l\'aube de la justice','29/03/2016',0056,031,' '),
	(0032,'Critique sur 28 semaines plus tard','29/09/2007',0057,032,' '),
	(0033,'Critique sur 28 jours plus tard','03/06/2003',0056,033,' '),
	(0034,'Critique sur Il faut sauver le soldat Ryan','10/10/1998',0058,034,' ');


CREATE TABLE Producteur (
    nom varchar (40) PRIMARY KEY,
    budget int (3)  #en million
);

INSERT INTO Producteur VALUES
	('A',100),
	('B',200),
	('C',300),
	('D',400);

CREATE TABLE Produit (
	nomProducteur varchar (40), 
	IDF SMALLINT not null,
	PRIMARY KEY (nomProducteur, IDF),
	FOREIGN KEY (IDF) REFERENCES Film (IDF)
);

INSERT INTO Produit VALUES
	('C',001),
	('A',002),
	('C',003),
	('A',004),
	('D',005),
	('B',006),
	('B',007),
	('B',008),
	('C',009),
	('A',010),
	('A',011),
	('A',012),
	('B',013),
	('B',014),
	('B',015),
	('C',016),
	('C',017),
	('C',018),
	('B',019),
	('A',020),
	('B',021),
	('B',022),
	('A',023),
	('A',024),
	('C',025),
	('B',026),
	('C',027),
	('A',028),
	('C',029),
	('B',030),
	('D',031),
	('A',032),
	('A',033),
	('C',034);
	
#############################################################################################################################################
#REQUETES, MODIFICATION, VUE, USER #


#MODIFICATION#
UPDATE Film
SET titre = UPPER (titre);

UPDATE Critique
SET titre = UPPER (titre);

UPDATE Personne
SET nom = UPPER (nom);

ALTER TABLE Personne
ADD CONSTRAINT unique key (nom, prenom);

ALTER TABLE Film
ADD COLUMN release_year int (4);

INSERT INTO Film VALUES 
	(001,'Le loup de wall street','25/12/2013','2h59','Martin Scorsese','Comedie',2013),
	(002,'La vie de Brian','08/04/1980','1h34','Terry Jones','Comedie',1980),
	(003,'La ligne verte','01/03/2000','3h09','Frank Darabont','Drame',2000),
	(004,'Kikujiro','20/10/1999','2h02','Takeshi Kitano','Comedie',1999),
	(005,'Interstellar','05/11/2014','2h49','Christopher Nolan','Science Fiction',2014),
	(006,'I, Robot','28/07/2004','1h55','Alex Proyas','Action',2004),
	(007,'Inglourious Basterds','19/08/2009','2h33','Quentin Tarantino','Action',2009),
	(008,'Independence Day','02/10/1996','2h25','Roland Emmerich','Science Fiction',1996),
	(009,'Inception','21/07/2010','2h28','Christopher Nolan','Aventure',2010),
	(010,'Harry Potter à l\'école des sorciers','05/12/2001','2h32','Chris Columbus','Fantasy',2001),
	(011,'Harry Potter et la chambre des secrets','04/12/2002','2h41','Chris Columbus','Fantasy',2002),
	(012,'Harry Potter et le prisonnier d\'Azkaban','02/06/2004','2h22','Alfonso Cuaron','Fantasy',2004),
	(013,'Harry Potter et la coupe de feu','30/11/2005','2h37','Mike Newell','Fantasy',2005),
	(014,'Harry Potter et l\'ordre du phenix','11/07/2007','2h18','David Yates','Fantasy',2007),
	(015,'Harry Potter et le prince de Sang-Mele','15/07/2009','2h33','David Yates','Fantasy',2009),
	(016,'Harry Potter et les reliques de la mort (1)','24/11/2010','2h26','David Yates','Fantasy',2010),
	(017,'Harry Potter et les reliques de la mort (2)','13/07/2011','2h10','David Yates','Fantasy',2011),
	(018,'Final Fantasy les créatures de l\'esprit','15/08/2001','1h46','Hironobu Sakaguchi','Animation',2001),
	(019,'Fight Club','10/11/1999','2h19','David Fincher','Drame',1999),
	(020,'La folle journée de Ferris Bueller','17/12/1986','1h43','John Huges','Comedie',1986),
	(021,'E.T l\'Extra-Terrestre','01/12/1982','1h55','Steven Spielberg','Science Fiction',1982),
	(022,'Drive','05/10/2011','1h40','Nicolas Winding Refn','Drame',2011),
	(023,'Dark Shadows','09/05/2012','1h53','Tim Burton','Comedie',2012),
	(024,'Les noces funèbres','19/10/2005','1h17','Tim Burton','Animation',2005),
	(025,'Cloud Atlas','13/03/2013','2h52','Lana & Lilly Wachowski','Drame',2013),
	(026,'Le cinquième element','07/05/1997','2h06','Luc Besson','Science Fiction',1997),
	(027,'Captain America -First Avanger-','17/08/2011','2h04','Joe Johnston','Action',2011),
	(028,'Breakfast Club','11/09/1985','1h37','John Huges','Comedie',1985),
	(029,'BGG Le Bon Gros Géant','20/07/2016','1h57','Steven Spielberg','Fantasy',2016),
	(030,'L\'Etrange Histoire de Benjamin Button','04/02/2009','2h46','David fincher','Fantasy',2009),
	(031,'Batman v Superman l\'aube de la justice','23/03/2016','2h31','Zack Snyder','Action',2016),
	(032,'28 semaines plus tard','19/09/2007','1h40','Juan Carlos Fresnadillo','Horreur',2007),
	(033,'28 jours plus tard','28/05/2003','1h53','Danny Boyle','Horreur',2003),
	(034,'Il faut sauver le soldat Ryan','30/09/1998','2h49','Steven Spielberg','Guerre',1998);


#REQUETES# 

#voir tout les films sortis pendant les 90's
SELECT titre , release_date
FROM Film
WHERE release_year < 2000 AND release_year > 1990; 

#voir tout les films sortis pendant les 80's
SELECT titre, release_date
FROM Film
WHERE release_year < 1990 AND release_year > 1980; 

#voir tout les films sortis a partir de 2000
SELECT titre , release_date 
FROM Film
WHERE release_year >= 2000; 

#voir les acteur ayant joué dans au moins 2 films et afficher les films + dates de sortie
SELECT P.prenom, P.nom, F.titre, F.release_date
FROM Personne P, Acteur A, Film F, JoueDans JD
WHERE A.IDA = P.ID 
	AND A.nbFilm >= 2
	AND A.IDA = JD.IDA
	AND F.IDF = JD.IDF;


#voir toutes les critiques sur les films de Nolan avec Cillian Murphy et afficher leur producteur
SELECT C.titre, F.titre, F.realisateur, Prod.nom, Prod.budget
FROM Critique C, Film F, Personne P, Producteur Prod
WHERE C.IDF = F.IDF 
	AND F.realisateur LIKE "%Nolan%"
    AND P.nom LIKE (SELECT DISTINCT P2.nom
         			FROM Acteur A, JoueDans JD, Personne P2
         			WHERE A.IDA = P2.ID
         				AND JD.IDA = A.IDA 
         				AND JD.IDF =F.IDF
         				AND P2.nom LIKE "%Murphy%")
    AND Prod.nom LIKE (SELECT DISTINCT Prod2.nom
                       FROM Producteur Prod2, Produit
                       WHERE Produit.IDF = F.IDF
                       	AND Prod2.nom = Produit.nomProducteur);


#VUES#

#voir tout les films #
CREATE VIEW liste_films (titre) AS
SELECT F.titre
FROM Film F;

#voir toutes les critiques #
CREATE VIEW liste_critique (titre, film) AS
SELECT C.titre, F.titre
FROM Critique C, Film F
WHERE C.IDF = F.IDF;

#voir les comedies 
CREATE VIEW Categorie_Comedie (titre, date_de_sortie, categorie) AS
SELECT F.titre, F.release_date, F.nomCat
FROM Film F
WHERE F.nomCat = "Comedie";


#voir les films avec des acteurs selon leur nationnalité (en rapport avec la BDD) 
CREATE VIEW Film_with_Australian (titre, date_de_sortie, acteur) AS
SELECT F.titre, F.release_date, P.nom
FROM Film F, Personne P, Acteur A, JoueDans
WHERE P.ID = A.IDA
	AND A.nationalité LIKE "%Australien%"
	AND JoueDans.IDA = A.IDA
	AND JoueDans.IDF = F.IDF;


				
#USER#

#CREATE USER 'Admin'@'localhoste' identified by 'Admin'; 
#GRANT ALL ON Cinemathèque TO 'Admin'@'localhoste'; 
#FLUSH PRIVILEGES;

#CREATE USER 'Viewer'@'localhoste' identified by 'Viewer'; 
#GRANT SELECT on Cinemathèque TO 'Viewer'@'localhoste';
#FLUSH PRIVILEGES;