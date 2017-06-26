CREATE DATABASE bdd_sdzee DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';
GRANT ALL ON bdd_sdzee.* TO 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';

DROP TABLE IF EXISTS bdd_sdzee.FileUpload ;
DROP TABLE IF EXISTS bdd_sdzee.Company ;

CREATE TABLE bdd_sdzee.Company (
    id INT(11) NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(10) NOT NULL,
    company_full_name VARCHAR(100) NOT NULL,
    password_company CHAR (32 ) NOT NULL,
    responsible_1_name VARCHAR(100) NOT NULL,
    responsible_1_email VARCHAR(100) NOT NULL,
    responsible_1_phone VARCHAR(12) NOT NULL,
    responsible_2_name VARCHAR(100),
    responsible_2_email VARCHAR(100),
    responsible_2_phone VARCHAR(12) ,
    project_responsible VARCHAR(100) NOT NULL,
    submission_type VARCHAR(30) NOT NULL,
    file_type VARCHAR(10) NOT NULL,
    data_description TINYTEXT,
    sign_in_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (company_name)
)  ENGINE=INNODB;

-- Submission type : `email`, `fileshare`, `webservice`
-- filetype : `csv`, `excel`


CREATE TABLE bdd_sdzee.FileUpload (     
id INT(11) NOT NULL AUTO_INCREMENT,     
filename VARCHAR(60) NOT NULL,     
file_type VARCHAR(10) NOT NULL,     
id_company INT(11),    
dateUpload DATETIME,    
size_file INT NOT NULL,     
PRIMARY KEY (id),    
FOREIGN KEY (id_company)        
REFERENCES Company (id)        
ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=INNODB;


    
 INSERT INTO `bdd_sdzee`.`Company` (
`company_name`,
`company_full_name`,
`password_company`,
`responsible_1_name`,
`responsible_1_email`,
`responsible_1_phone`,
`responsible_2_name`,
`responsible_2_email`,
`responsible_2_phone`,
`project_responsible`,
`submission_type`,
`file_type`,
`data_description`,
`sign_in_date`)
VALUES(
	'BUS1',
    'Bus company of Floripa',
    'bus',
    'Pedro',
    'pedro@mail.fr',
    '0123456789',
	'Rodrigo',
    'rodrigo@mail.fr',
    '0123456788',
    'Rodrigo',
    'webservice',
    'csv',
    'User of the bus company of Floripa',
    NOW());


 INSERT INTO `bdd_sdzee`.`Company`
(
`company_name`,
`company_full_name`,
`password_company`,
`responsible_1_name`,
`responsible_1_email`,
`responsible_1_phone`,
`responsible_2_name`,
`responsible_2_email`,
`responsible_2_phone`,
`project_responsible`,
`submission_type`,
`file_type`,
`data_description`,
`sign_in_date`)
VALUES(
	'BUS2',
    'Second Bus company of Floripa',
    'bus',
    'Pedra',
    'pedra@mail.fr',
    '0123456789',
	'Rodriga',
    'rodriga@mail.fr',
    '0123456788',
    'Rodriga',
    'webservice',
    'csv',
    'User of the second bus company of Floripa',
    NOW());

    
INSERT INTO `bdd_sdzee`.`FileUpload`
(
`filename`,
`file_type`,
`id_company`,
`dateUpload`,
`size_file`)
VALUES
(
'bus1-1.csv',
'csv',
1,
NOW(),
50); 

INSERT INTO `bdd_sdzee`.`FileUpload`
(
`filename`,
`file_type`,
`id_company`,
`dateUpload`,
`size_file`)
VALUES
(
'bus2-1.csv',
'csv',
2,
NOW(),
100);