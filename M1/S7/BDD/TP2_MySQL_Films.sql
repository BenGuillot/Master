-- phpMyAdmin SQL Dump
-- version 3.2.2
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 16 Octobre 2009 à 17:14
-- Version du serveur: 5.0.75
-- Version de PHP: 5.2.6-3ubuntu4.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: 'VOYAGE-TD_BD_M1_Info-2009'
--

-- --------------------------------------------------------

--
-- Structure de la table 'ACTEUR'
--

DROP TABLE IF EXISTS ACTEUR;
CREATE TABLE ACTEUR (
  nom varchar(20) NOT NULL,
  adresse text,
  genre enum('M','F') default 'M',
  dateNaissance date default NULL,
  PRIMARY KEY (nom)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'ACTEUR'
--

INSERT INTO ACTEUR (nom, adresse, genre, dateNaissance) VALUES
('Acteur inconnu', 'xwc', 'M', NULL),
('Acteur inconnu 2', 'xwc', 'M', NULL),
('Cage', 'Long Beach', 'M', '1964-01-07'),
('Chaplin', 'Londres', 'M', '1889-04-16'),
('Dern', 'Los Angeles', 'F', '1967-02-10'),
('Fisher', 'Sacramento', 'F', '1955-10-21'),
('Fletcher', 'Birmingham', 'F', '1934-07-22'),
('Guiness', 'Londres', 'M', '1914-04-02'),
('Hamill', 'Denver', 'M', '1951-09-25'),
('Hopkins', 'Margam', 'M', '1937-12-31'),
('McGregor', 'Crieff', 'M', '1971-03-31'),
('Neeson', 'Ballymena', 'M', '1952-06-07'),
('Neil', 'Omagh', 'M', '1947-09-14'),
('Nicholson', 'New York', 'M', '1937-04-22'),
('Oakie', 'Sedalia', 'M', '1903-11-12'),
('Portmann', 'Jerusalem', 'F', '1981-06-09');
-- --------------------------------------------------------

--
-- Structure de la table 'REALISATEUR'
--

DROP TABLE IF EXISTS REALISATEUR;
CREATE TABLE REALISATEUR (
  numcert int(11) NOT NULL auto_increment,
  nom varchar(20) NOT NULL,
  adresse text,
  salaire double NOT NULL default '-1',
  PRIMARY KEY (numcert)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table 'REALISATEUR'
--

INSERT INTO REALISATEUR (numcert, nom, adresse, salaire) VALUES
(1, 'Spielberg', 'Beverly Hills', 300000),
(2, 'Forman', 'New York', 200000),
(3, 'Lucas', 'Modesto', 500000),
(4, 'Lynch', 'Pittsburgh', 200000),
(5, 'Chaplin', 'Londres', 20000);


-- --------------------------------------------------------

--
-- Structure de la table 'FILM'
--

DROP TABLE IF EXISTS FILM;
CREATE TABLE FILM (
  titre varchar(50) NOT NULL,
  annee int(11) NOT NULL,
  duree int(11) NOT NULL default '-1',
  enCouleur tinyint(1) default '1',
  certrea int(11) NOT NULL,
  cout bigint(20) unsigned NOT NULL default '0',
  PRIMARY KEY  (titre, annee),
  FOREIGN KEY (certrea) REFERENCES REALISATEUR(numcert)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'FILM'
--

INSERT INTO FILM (titre, annee, duree, enCouleur, certrea, cout) VALUES
('Elephant Man', 1980, 124, 0, 4, 5000000),
('Jurassic Park', 1993, 127, 1, 1, 95000000),
('Le Dictateur', 1940, 130, 0, 5, 150000),
('Les Temps Modernes', 1936, 123, 0, 5, 100000),
('Sailor et Lula', 1990, 124, 1, 4, 10000000),
('Star Wars: La Menace Fantôme', 1999, 133, 1, 3, 115000000),
('Star Wars: Un Nouvel Espoir', 1977, 121, 1, 3, 11000000),
('Vol au dessus d''un nid de coucou', 1975, 129, 1, 2, 4400000);

-- --------------------------------------------------------

--
-- Structure de la table 'JOUEDANS'
--

DROP TABLE IF EXISTS JOUEDANS;
CREATE TABLE JOUEDANS (
  titrefilm varchar(50) NOT NULL,
  anneefilm int(11) NOT NULL,
  nomActeur varchar(20) NOT NULL,
  PRIMARY KEY  (titrefilm, anneefilm, nomActeur),
  FOREIGN KEY (nomActeur) REFERENCES ACTEUR (nom),
  FOREIGN KEY (titrefilm, anneefilm) REFERENCES FILM (titre, annee)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table 'JOUEDANS'
--

INSERT INTO JOUEDANS (titrefilm, anneefilm, nomActeur) VALUES
('Sailor et Lula', 1990, 'Cage'),
('Le Dictateur', 1940, 'Chaplin'),
('Les Temps Modernes', 1936, 'Chaplin'),
('Jurassic Park', 1993, 'Dern'),
('Sailor et Lula', 1990, 'Dern'),
('Star Wars: Un Nouvel Espoir', 1977, 'Fisher'),
('Vol au dessus d''un nid de coucou', 1975, 'Fletcher'),
('Star Wars: Un Nouvel Espoir', 1977, 'Guiness'),
('Star Wars: Un Nouvel Espoir', 1977, 'Hamill'),
('Elephant Man', 1980, 'Hopkins'),
('Star Wars: La Menace Fantôme', 1999, 'McGregor'),
('Jurassic Park', 1993, 'Neeson'),
('Star Wars: La Menace Fantôme', 1999, 'Neeson'),
('Jurassic Park', 1993, 'Neil'),
('Vol au dessus d''un nid de coucou', 1975, 'Nicholson'),
('Le Dictateur', 1940, 'Oakie'),
('Star Wars: La Menace Fantôme', 1999, 'Portmann');

-- QUESTION 1
DELETE FROM ACTEUR 
	WHERE ACTEUR.nom 
		NOT IN (SELECT j.nomActeur 
					FROM JOUEDANS j);

--QUESTION 2
UPDATE REALISATEUR 
	SET salaire = salaire*1.1 
	WHERE salaire < 250000;

--QUESTION 3
--R1
SELECT A.nom, A.dateNaissance 
	FROM ACTEUR A 
	WHERE A.genre 
		LIKE "F" 
		ORDER BY A.dateNaissance ASC;

--R2
SELECT A.nom 
	FROM ACTEUR A 
		WHERE A.dateNaissance < "1950-01-01" 
		OR A.nom LIKE "_a%";

--R3
SELECT DISTINCT FILM.titre, FILM.annee 
	FROM FILM, REALISATEUR 
	WHERE FILM.certrea = REALISATEUR.numcert 
		AND REALISATEUR.nom LIKE "%Chaplin%";

--R4
SELECT DISTINCT A.nom 
	FROM ACTEUR A, JOUEDANS J, FILM F, REALISATEUR R 
	WHERE A.nom = J.nomActeur 
		AND J.titrefilm = F.titre 
		AND F.certrea = R.numcert 
		AND R.nom LIKE "%Lynch%" 
		AND A.nom IN (SELECT DISTINCT a.nom 
						FROM ACTEUR a, JOUEDANS j, FILM f, REALISATEUR r 
						WHERE a.nom = j.nomActeur 
						AND j.titrefilm = f.titre 
						AND f.certrea = r.numcert 
						AND r.nom LIKE "%Spielberg%"); 

--R5
SELECT R.nom 
	FROM REALISATEUR R 
		WHERE R.nom IN (SELECT NOM FROM ACTEUR);

--R6
SELECT R.nom
	FROM REALISATEUR R
	WHERE R.salaire > (SELECT MIN(F.cout) FROM FILM F);

--R7
SELECT DISTINCT A.nom
	FROM ACTEUR A, JOUEDANS J, FILM F, REALISATEUR R
	WHERE A.nom = J.nomActeur
		AND J.titrefilm = F.titre
		AND F.certrea = R.numcert
		AND R.nom IN (SELECT R.nom
						FROM REALISATEUR R
						WHERE R.salaire > (SELECT MIN(F.cout) FROM FILM F));

--R8
SELECT DISTINCT R.nom FROM REALISATEUR R, FILM F WHERE F.certrea = R.numcert AND F.cout > 10000000 AND R.nom NOT IN (SELECT R.nom FROM REALISATEUR R, FILM F WHERE F.certrea = R.numcert AND F.cout < 10000000);

--R9
SELECT DISTINCT R.nom FROM REALISATEUR R, FILM F WHERE F.certrea = R.numcert AND F.cout < 1000000 AND R.nom NOT IN (SELECT DISTINCT R.nom FROM REALISATEUR R, FILM F WHERE F.certrea = R.numcert AND F.cout > 1000000 AND R.nom);

--R10
SELECT R.nom, AVG(F.cout) AS COUT_MOYEN, MAX(F.cout) AS COUT_MAX
	FROM REALISATEUR R, FILM F
	WHERE F.certrea = R.numcert
	GROUP BY R.nom;

--R11
SELECT R.nom, AVG(F.cout) AS COUT_MOYEN, MAX(F.cout) AS COUT_MAX
	FROM REALISATEUR R, FILM F
	WHERE F.certrea = R.numcert
		AND R.nom NOT IN (SELECT R.nom FROM REALISATEUR R, FILM F WHERE F.certrea = R.numcert AND F.cout < 1000000)
	GROUP BY R.nom;

--R12
SELECT F.titre, F.annee FROM FILM F 
	WHERE F.duree >
 		(SELECT MAX(F.duree) FROM FILM F, REALISATEUR R WHERE F.certrea = R.numcert AND R.nom LIKE "%Chaplin%" );

--R13
SELECT DISTINCT F.titre, F.annee, A.nom FROM FILM F, ACTEUR A, JOUEDANS J WHERE F.duree > 125 AND J.titrefilm = F.titre AND J.anneefilm = F.annee AND J.nomActeur = A.nom  ORDER BY F.titre, A.adresse DESC;

--R14
SELECT F.titre,A.nom, COUNT(A.nom)
	FROM FILM F, ACTEUR A, JOUEDANS J
	WHERE F.titre = J.titrefilm
		AND J.nomActeur = A.nom
		AND F.cout > 1000000
		GROUP BY F.titre, F.annee
		HAVING COUNT(A.nom) > 2;
--R15
SELECT F.titre, F.cout, F.cout*0.67 AS EURO FROM FILM F;

