CREATE DATABASE bdd_sdzee DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';
GRANT ALL ON bdd_sdzee.* TO 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';

CREATE TABLE  bdd_sdzee.Utilisateur (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 email VARCHAR( 60 ) NOT NULL ,
 mot_de_passe VARCHAR( 32 ) NOT NULL ,
 nom VARCHAR( 20 ) NOT NULL ,
 date_inscription DATETIME NOT NULL ,
 PRIMARY KEY ( id ),
 UNIQUE ( email )
) ENGINE = INNODB;

ALTER TABLE Utilisateur CHANGE mot_de_passe mot_de_passe CHAR(56) NOT NULL;

INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('coyote@mail.acme', MD5('bipbip'), 'Coyote', NOW());
INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('jadorejquery@unefois.be', MD5('avecdesfrites'), 'Thunderseb', NOW());