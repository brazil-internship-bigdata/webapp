CREATE DATABASE bdd_sdzee DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';
GRANT ALL ON bdd_sdzee.* TO 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';

CREATE TABLE bdd_sdzee.Company (
    id INT(11) NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(100) NOT NULL,
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
    sign_in_date DATE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (company_name)
)  ENGINE=INNODB;

-- Submission type : 'email', 'fileshare', 'webservice'
-- filetype : 'csv', 'excel'


CREATE TABLE bdd_sdzee.File (
    id INT(11) NOT NULL AUTO_INCREMENT,
    filename VARCHAR(60) NOT NULL,
    type VARCHAR(10) NOT NULL,
    id_company INT(11),
    dateUpload DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (id_company)
        REFERENCES Company (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;



INSERT INTO Company (   company_name, 
	password_company,
    responsible_1_name ,
    responsible_1_email ,
    responsible_1_phone ,
    responsible_2_name,
    responsible_2_email,
    responsible_2_phone ,
    project_responsible ,
    submission_type ,
    file_type ,
    data_description,
    sign_in_date) VALUES (
    "BUS",
    "bus",
    "Pedro",
    "pedro@mail.fr",
    "0123456789",
	"Rodrigo",
    "rodrigo@mail.fr",
    "0123456788",
    "Rodrigo",
    "webservice",
    "csv",
    "Company de BUS floripa",
    NOW()
    );