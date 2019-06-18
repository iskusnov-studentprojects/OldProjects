-- Table Klient

CREATE TABLE `Klient`
(
  `firstName` Varchar(20) NOT NULL,
  `login` Varchar(50) NOT NULL,
  `password` Varchar(50) NOT NULL,
  `lastName` Varchar(20) NOT NULL
)
;

ALTER TABLE `Klient` ADD  PRIMARY KEY (`login`)
;

-- Table Card

CREATE TABLE `Card`
(
  `idCard` Int NOT NULL,
  `login` Varchar(50) NOT NULL,
  `cardName` Varchar(50) NOT NULL,
  `validDate` Date NOT NULL,
  `securePassword` Varchar(4) NOT NULL,
  `mainCard` Bool NOT NULL,
  `number` Varchar(20) NOT NULL,
  `idCardType` Int NOT NULL
)
;

CREATE INDEX `IX_Relationship8` ON `Card` (`idCardType`)
;

ALTER TABLE `Card` ADD  PRIMARY KEY (`idCard`,`login`)
;

-- Table CardType

CREATE TABLE `CardType`
(
  `idCardType` Int NOT NULL,
  `name` Varchar(20) NOT NULL
)
;

ALTER TABLE `CardType` ADD  PRIMARY KEY (`idCardType`)
;

-- Table TransactionType

CREATE TABLE `TransactionType`
(
  `idTransactionType` Int NOT NULL,
  `typeName` Varchar(20) NOT NULL
)
;

ALTER TABLE `TransactionType` ADD  PRIMARY KEY (`idTransactionType`)
;

-- Table RealCard

CREATE TABLE `RealCard`
(
  `number` Char(20) NOT NULL,
  `firstName` Char(50) NOT NULL,
  `lastName` Char(50) NOT NULL,
  `validDate` Date NOT NULL,
  `securePassword` Char(4) NOT NULL,
  `cash` Int NOT NULL,
  `idCardType` Int NOT NULL
)
;

CREATE INDEX `IX_Relationship9` ON `RealCard` (`idCardType`)
;

ALTER TABLE `RealCard` ADD  PRIMARY KEY (`number`)
;

-- Table Transaction

CREATE TABLE `Transaction`
(
  `idTransaction` Int NOT NULL,
  `date` Date NOT NULL,
  `time` Time NOT NULL,
  `summ` Int NOT NULL,
  `idTransactionType` Int,
  `number` Char(20) NOT NULL,
  `category` Varchar(20)
)
;

CREATE INDEX `IX_Relationship10` ON `Transaction` (`idTransactionType`)
;

ALTER TABLE `Transaction` ADD  PRIMARY KEY (`idTransaction`,`number`)
;

-- Table Autocharge

CREATE TABLE `Autocharge`
(
  `idAutorecharge` Int NOT NULL,
  `idCard` Int NOT NULL,
  `login` Varchar(50) NOT NULL,
  `byTime` Bool NOT NULL,
  `timeSinceDate` Date,
  `timeToDate` Date,
  `time` Int,
  `timeSumm` Int,
  `byAmount` Bool NOT NULL,
  `amountSinceDate` Date,
  `amountToDate` Date,
  `amountSumm` Int,
  `amountRechargeSumm` Int
)
;

ALTER TABLE `Autocharge` ADD  PRIMARY KEY (`idAutorecharge`,`idCard`,`login`)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE `Card` ADD CONSTRAINT `have` FOREIGN KEY (`login`) REFERENCES `Klient` (`login`) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE `Card` ADD CONSTRAINT `Relationship8` FOREIGN KEY (`idCardType`) REFERENCES `CardType` (`idCardType`) ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE `RealCard` ADD CONSTRAINT `Relationship9` FOREIGN KEY (`idCardType`) REFERENCES `CardType` (`idCardType`) ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE `Transaction` ADD CONSTRAINT `Relationship10` FOREIGN KEY (`idTransactionType`) REFERENCES `TransactionType` (`idTransactionType`) ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE `Transaction` ADD CONSTRAINT `Relationship11` FOREIGN KEY (`number`) REFERENCES `RealCard` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
;

ALTER TABLE `Autocharge` ADD CONSTRAINT `Relationship12` FOREIGN KEY (`idCard`, `login`) REFERENCES `Card` (`idCard`, `login`) ON DELETE CASCADE ON UPDATE CASCADE
;

