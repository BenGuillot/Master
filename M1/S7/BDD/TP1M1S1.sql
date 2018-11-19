CREATE TABLE Personnel (personnelId int(10), Nom char(20), Naissance date, HeuresDeVol int(10), Type char(20));
CREATE TABLE Destination (DestinationId int(10), Ville char(20), Pays char(20), Distance int(10));
CREATE TABLE Compagnie (CompagnieId int(10), Nom char(20));
CREATE TABLE Avion (AvionId int(10), CompagnieId int(10), Nom char(20), Vitesse double, Equipage int);
CREATE TABLE Vol (VolId int(10), AvionId int(10), DestinationId int(10), Depart date);
CREATE TABLE Reservation (ReservationId int(10), VolId int(10), Nom char(20), Prix int(10), Reduction double);
CREATE TABLE Equipage (VolId int(10), personnelId int(10));

ALTER TABLE Avion MODIFY COLUMN Vitesse number(6);
ALTER TABLE Personnel MODIFY COLUMN Nom char(10);
ALTER TABLE Compagnie MODIFY COLUMN Nom char(10);
ALTER TABLE Avion MODIFY COLUMN Nom char(10);
ALTER TABLE Reservation MODIFY COLUMN Nom char(10);

ALTER TABLE Avion ADD COLUMN Capacite int;
ALTER TABLE Destination ADD COLUMN Decalage double;

ALTER TABLE Personnel MODIFY COLUMN personnelId int not null;
ALTER TABLE Destination MODIFY COLUMN DestinationId int not null;
ALTER TABLE Compagnie MODIFY COLUMN CompagnieId int not null;
ALTER TABLE Avion MODIFY COLUMN AvionId int not null;
ALTER TABLE Vol MODIFY COLUMN VolId int not null;
ALTER TABLE Reservation MODIFY COLUMN ReservationId int not null;
#ajouter auto_increment AVANT de faire les clés étrangères 

ALTER TABLE Personnel ADD PRIMARY KEY (personnelId); 
ALTER TABLE Destination ADD PRIMARY KEY (DestinationId);
ALTER TABLE Compagnie ADD PRIMARY KEY (CompagnieId);
ALTER TABLE Avion ADD PRIMARY KEY (AvionId);
ALTER TABLE Avion ADD FOREIGN KEY (CompagnieId) REFERENCES Compagnie (CompagnieId);
ALTER TABLE Vol ADD PRIMARY KEY (VolId);
ALTER TABLE Vol ADD FOREIGN KEY (AvionId) REFERENCES Avion (AvionId);
ALTER TABLE Vol ADD FOREIGN KEY (DestinationId) REFERENCES Destination (DestinationId); 
ALTER TABLE Reservation ADD PRIMARY KEY (ReservationId);
ALTER TABLE Reservation ADD FOREIGN KEY (VolId) REFERENCES Vol (VolId);
ALTER TABLE Equipage ADD PRIMARY KEY (VolId,personnelId);
ALTER TABLE Equipage ADD FOREIGN KEY (VolId) REFERENCES Vol (VolId);
ALTER TABLE Equipage ADD FOREIGN KEY (personnelId) REFERENCES Personnel (personnelId);

INSERT INTO Avion values (1, 1, 'Piper', 150, 1, 5);
INSERT INTO Compagnie values (1, 'RyanHAIR');

UPDATE Avion SET Nom = UPPER (Nom);

INSERT INTO Destination(DestinationId, Ville, Pays, Distance, Decalage) values (1, 'PARIS', 'France', 1000, 0);
INSERT INTO Destination(DestinationId, Ville, Pays, Distance, Decalage) values (4, 'Marseille', 'France', 500, 0);
INSERT INTO Destination(DestinationId, Ville, Pays, Distance, Decalage) values (2, 'BERLIN', 'ALLEMAGNE', 1500, 0), 
																				(3, 'DUBLIN', 'IRELANDE', 2000, 0);
INSERT INTO Vol(VolId,AvionId,DestinationId,Depart) values (1, 1, 1, 20181212), (2,1,3, 20180104);
INSERT INTO Avion(AvionId,CompagnieId,Nom,Vitesse,Equipage,Capacite)values(2,1,'A320',300,8,800);
INSERT INTO Personnel (personnelId,Nom,Naissance,HeuresDeVol,Type)values(1,'Laureline',19970627,0,'Pilote'), (2,'Maxime',19980326,0,'Pilote');

UPDATE Destination SET Ville = LOWER (Ville) WHERE Distance > 1000;
UPDATE Destination SET Decalage = Decalage + 1 WHERE Pays != 'France';

DELETE FROM Avion WHERE Equipage = 5;
DELETE FROM Vol WHERE DEPART < 20050101;
SELECT Vitesse FROM Avion A WHERE A.Nom = 'A320'; 
SELECT P.Nom FROM Personnel P WHERE P.Type = 'Pilote' ORDER BY P.nom ASC;
SELECT DISTINCT Pays FROM Destination;
SELECT Naissance From Personnel WHERE Personnel.Nom = 'Maxime';

SELECT D.Ville  FROM Destination D, Vol V, Equipage E, Personnel P WHERE P.personnelId = E.personnelId AND V.VolId = E.VolId AND D.DestinationId = V.DestinationId AND P.Nom = 'Michel' ORDER BY D.Ville ASC;
SELECT DISTINCT A.Nom FROM Avion A, Vol V, Destination D WHERE A.AvionId = V.AvionId AND V.DestinationId = D.DestinationId AND D.Ville = 'Londres' ORDER BY A.Vitesse ASC;