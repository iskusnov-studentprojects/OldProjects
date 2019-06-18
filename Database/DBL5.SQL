/*
Created: 29.06.2015
Modified: 29.06.2015
Model: DBL4
Database: Oracle 10g
*/


-- Create tables section -------------------------------------------------

-- Table Klient

CREATE TABLE Klient(
  PK_Klient Integer NOT NULL,
  FIO Varchar2(20 ) NOT NULL,
  Nomer_pasporta Varchar2(10 ) NOT NULL,
  Adres Varchar2(100 ),
  Nomer_telefona Varchar2(10 )
)
/

-- Add keys for table Klient

ALTER TABLE Klient ADD CONSTRAINT Key1 PRIMARY KEY (PK_Klient)
/

-- Table Dogovor

CREATE TABLE Dogovor(
  Nomer_dogovora Integer NOT NULL,
  Id_lombarda Integer NOT NULL,
  Id_klienta Integer NOT NULL,
  Id_rabotnika Integer NOT NULL,
  Id_Imushestva Integer NOT NULL,
  Razmer_ssudi Integer NOT NULL,
  Data_zakluchenia Date NOT NULL,
  Data_vozvrata Date NOT NULL,
  Data_lgotnogo_sroka Date NOT NULL,
  Procentnaa_stavka Integer NOT NULL
)
/

-- Add keys for table Dogovor

ALTER TABLE Dogovor ADD CONSTRAINT Key2 PRIMARY KEY (Nomer_dogovora,Id_rabotnika,Id_Imushestva,Id_lombarda,Id_klienta)
/

-- Table Imushestvo

CREATE TABLE Imushestvo(
  PK_Imushestvo Integer NOT NULL,
  Naimenovanie Varchar2(20 ) NOT NULL,
  Opisanie Varchar2(1000 ),
  Stoimost Integer NOT NULL,
  Status Varchar2(10 ) NOT NULL,
  Kategoria Integer
)
/

-- Add keys for table Imushestvo

ALTER TABLE Imushestvo ADD CONSTRAINT Key3 PRIMARY KEY (PK_Imushestvo)
/

-- Table Rabotnik

CREATE TABLE Rabotnik(
  PK_Rabotnik Integer NOT NULL,
  FIO Varchar2(50 ) NOT NULL,
  Dolgnost Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Rabotnik

ALTER TABLE Rabotnik ADD CONSTRAINT Key4 PRIMARY KEY (PK_Rabotnik)
/

-- Table Lombard

CREATE TABLE Lombard(
  PK_Lombard Integer NOT NULL,
  Adres Varchar2(100 ) NOT NULL,
  Naimenovanie Varchar2(20 ) NOT NULL,
  INN Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Lombard

ALTER TABLE Lombard ADD CONSTRAINT Key5 PRIMARY KEY (PK_Lombard)
/

-- Table Kategoria

CREATE TABLE Kategoria(
  PK_Kategoria Integer NOT NULL,
  Naimenovanie Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Kategoria

ALTER TABLE Kategoria ADD CONSTRAINT Key6 PRIMARY KEY (PK_Kategoria)
/

-- Create relationships section ------------------------------------------------- 

ALTER TABLE Dogovor ADD CONSTRAINT Oformlaet FOREIGN KEY (Id_rabotnika) REFERENCES Rabotnik (PK_Rabotnik)
/

ALTER TABLE Dogovor ADD CONSTRAINT Zakluchaetsa_v_otnoshenii FOREIGN KEY (Id_Imushestva) REFERENCES Imushestvo (PK_Imushestvo)
/

ALTER TABLE Dogovor ADD CONSTRAINT Ukazivaetsa_v FOREIGN KEY (Id_lombarda) REFERENCES Lombard (PK_Lombard)
/

ALTER TABLE Imushestvo ADD CONSTRAINT Harakterizuet FOREIGN KEY (Kategoria) REFERENCES Kategoria (PK_Kategoria)
/

ALTER TABLE Dogovor ADD CONSTRAINT Zakluchaet FOREIGN KEY (Id_klienta) REFERENCES Klient (PK_Klient)
/



