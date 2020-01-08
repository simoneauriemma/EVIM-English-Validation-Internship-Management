-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: evim
-- ------------------------------------------------------
-- Server version	8.0.18
use evim;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attached`
--

DROP TABLE IF EXISTS `attached`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attached` (
  `ID_ATTACHED` int(20) NOT NULL AUTO_INCREMENT,
  `FILENAME` varchar(50) NOT NULL,
  `FK_REQUEST` int(20) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_ATTACHED`),
  KEY `FK_REQUEST` (`FK_REQUEST`),
  KEY `FK_USER` (`FK_USER`),
  CONSTRAINT `attached_ibfk_1` FOREIGN KEY (`FK_REQUEST`) REFERENCES `request` (`ID_REQUEST`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attached_ibfk_2` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attached`
--

LOCK TABLES `attached` WRITE;
/*!40000 ALTER TABLE `attached` DISABLE KEYS */;
/*!40000 ALTER TABLE `attached` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attivita`
--

DROP TABLE IF EXISTS `attivita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attivita` (
  `ID_Attivita` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Registro` int(11) NOT NULL,
  `Descrizione` varchar(200) NOT NULL,
  `OrarioIngresso` int(11) NOT NULL,
  `OrarioUscita` int(11) NOT NULL,
  `FirmaResponsabile` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Attivita`),
  KEY `ID_Registro` (`ID_Registro`),
  CONSTRAINT `attivita_ibfk_1` FOREIGN KEY (`ID_Registro`) REFERENCES `registro` (`ID_Registro`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attivita`
--

LOCK TABLES `attivita` WRITE;
/*!40000 ALTER TABLE `attivita` DISABLE KEYS */;
INSERT INTO `attivita` VALUES (1,1,'Descrizione attività',15,19,0);
/*!40000 ALTER TABLE `attivita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `azienda`
--

DROP TABLE IF EXISTS `azienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `azienda` (
  `ID_Azienda` int(11) NOT NULL AUTO_INCREMENT,
  `CF` char(11) NOT NULL,
  `Telefono` varchar(13) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `SitoWeb` varchar(40) NOT NULL,
  `Indirizzo` varchar(100) NOT NULL,
  `Descizione` varchar(500) NOT NULL,
  `numero_dipendenti` varchar(27) NOT NULL,
  `codice_ateco` varchar(10) NOT NULL,
  `ID_Referente` varchar(17) DEFAULT NULL,
  `ID_Convenzione` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Azienda`,`CF`),
  KEY `ID_Referente_idx` (`ID_Referente`),
  KEY `ID_Convenzione_idx` (`ID_Convenzione`),
  CONSTRAINT `ID_Convenzione` FOREIGN KEY (`ID_Convenzione`) REFERENCES `convenzione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ID_Referente` FOREIGN KEY (`ID_Referente`) REFERENCES `referente_aziendale` (`CF`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `azienda`
--

LOCK TABLES `azienda` WRITE;
/*!40000 ALTER TABLE `azienda` DISABLE KEYS */;
INSERT INTO `azienda` VALUES (1,'0764352056C','3214565780','Microsoft','Xboxthebest','microsoftofficial@tiscali.it','https://www.microsoft.com/it-it','Viale Pasubio, 21, 20154 Milano MI','I suoi prodotti principali sono il sistema operativo desktop Microsoft Windows e la suite di produttivitï¿½ personale Microsoft Office, per i quali ï¿½ al primo posto nel rispettivo mercato. Altre linee di produzione comprendono: sistemi di sviluppo software (IDE e compilatori), DBMS, periferiche di input (tastiere e mouse), console di gioco (Xbox, Xbox 360 e Xbox One), periferiche di gioco (joystick e cloche per il pilotaggio di velivoli, volanti e altro).','da 10 a 40 dipendenti','70.22.01','ASDRFTASDFERTASDE',1),(2,'0854334056C','3335678903','Samsung','SamsungUnisa','samsungitalia@gmail.com','https://www.samsung.com/it/',' Via Mike Bongiorno, 9, 20124 Milano MI','Ci dedichiamo costantemente allo sviluppo e allï¿½ampliamento della gamma di prodotti strategici nella nostra divisione dellï¿½elettronica di consumo.','','',NULL,NULL);
/*!40000 ALTER TABLE `azienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `convenzione`
--

DROP TABLE IF EXISTS `convenzione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `convenzione` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DataConvenzione` varchar(40) NOT NULL,
  `Repertorio` varchar(20) NOT NULL,
  `DurataConvenzione` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convenzione`
--

LOCK TABLES `convenzione` WRITE;
/*!40000 ALTER TABLE `convenzione` DISABLE KEYS */;
INSERT INTO `convenzione` VALUES (1,'24/08/2019','n.2884/2018','3 Gennaio - 18 Giugno');
/*!40000 ALTER TABLE `convenzione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ente`
--

DROP TABLE IF EXISTS `ente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ente` (
  `ID_ENTE` int(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `SITE` varchar(50) NOT NULL,
  `STATO` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_ENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ente`
--

LOCK TABLES `ente` WRITE;
/*!40000 ALTER TABLE `ente` DISABLE KEYS */;
/*!40000 ALTER TABLE `ente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proposta`
--

DROP TABLE IF EXISTS `proposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proposta` (
  `ID_Proposta` int(11) NOT NULL AUTO_INCREMENT,
  `Obiettivi` varchar(200) NOT NULL,
  `Competenze` varchar(200) NOT NULL,
  `Attivita` varchar(200) NOT NULL,
  `Modalita` varchar(400) NOT NULL,
  `ID_Azienda` int(11) DEFAULT NULL,
  `ID_Tutor` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Proposta`),
  KEY `ID_Azienda` (`ID_Azienda`),
  CONSTRAINT `proposta_ibfk_1` FOREIGN KEY (`ID_Azienda`) REFERENCES `azienda` (`ID_Azienda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposta`
--

LOCK TABLES `proposta` WRITE;
/*!40000 ALTER TABLE `proposta` DISABLE KEYS */;
INSERT INTO `proposta` VALUES (1,'Sviluppare nuove metodologie. Integrare metodologie esistenti.','Laboratorio di Verifica di Correttezza e Sintesi Automatica di Sistemi Digitali','Verifica automatica di correttezza dei programmi',' Per avere una idea dell area di riferimento consultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software',1,1),(2,'Sviluppo di servizi di intelligenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',NULL,1),(3,'Realizzazione di un ecosistema digitale di rappresentazione e gestione della conoscenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',NULL,2),(4,'Realizzazione di sistemi domotici (IOT)','Reti di calcolaroei','Sistemi Informativi Geografici e applicazioni IOT','stringaAcasoPoiSiVede',NULL,2);
/*!40000 ALTER TABLE `proposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionario_s`
--

DROP TABLE IF EXISTS `questionario_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionario_s` (
  `ID_Questionario` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `AssistenzaDisp` tinyint(4) NOT NULL,
  `Informazioni` tinyint(4) NOT NULL,
  `Servizi` tinyint(4) NOT NULL,
  `Assistenza` tinyint(4) NOT NULL,
  `Logistica` tinyint(4) NOT NULL,
  `Ambiente` tinyint(4) NOT NULL,
  `Durata` tinyint(4) NOT NULL,
  `Mansioni` tinyint(4) NOT NULL,
  `Attivita` tinyint(4) NOT NULL,
  `Formazione` tinyint(4) NOT NULL,
  `Possibilita` tinyint(4) NOT NULL,
  `Valutazione` tinyint(4) NOT NULL,
  `Competenze` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID_Questionario`),
  KEY `Email` (`Email`),
  CONSTRAINT `questionario_s_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionario_s`
--

LOCK TABLES `questionario_s` WRITE;
/*!40000 ALTER TABLE `questionario_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionario_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionario_t`
--

DROP TABLE IF EXISTS `questionario_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionario_t` (
  `ID_Questionario` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `ID_TutorAziendale` int(11) NOT NULL,
  `ComptenzeIngresso` tinyint(4) NOT NULL,
  `CompetenzeAcquisite` tinyint(4) NOT NULL,
  `Utilita` tinyint(4) NOT NULL,
  `Motivazione` tinyint(4) NOT NULL,
  `Capacita` tinyint(4) NOT NULL,
  `Informazioni` tinyint(4) NOT NULL,
  `Obiettivi` tinyint(4) NOT NULL,
  `Servizi` tinyint(4) NOT NULL,
  `Assistenza` tinyint(4) NOT NULL,
  `Collaborazione` tinyint(4) NOT NULL,
  `Durata` tinyint(4)  NOT NULL,
  PRIMARY KEY (`ID_Questionario`),
  KEY `Email` (`Email`),
  KEY `ID_TutorAziendale` (`ID_TutorAziendale`),
  CONSTRAINT `questionario_t_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `questionario_t_ibfk_2` FOREIGN KEY (`ID_TutorAziendale`) REFERENCES `tutoraziendale` (`ID_TutorAziendale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionario_t`
--

LOCK TABLES `questionario_t` WRITE;
/*!40000 ALTER TABLE `questionario_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionario_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referente_aziendale`
--

DROP TABLE IF EXISTS `referente_aziendale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referente_aziendale` (
  `CF` varchar(17) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `luogo_nascita` varchar(50) NOT NULL,
  `data_nascita` varchar(10) NOT NULL,
  `ruolo` varchar(50) NOT NULL,
  PRIMARY KEY (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referente_aziendale`
--

LOCK TABLES `referente_aziendale` WRITE;
/*!40000 ALTER TABLE `referente_aziendale` DISABLE KEYS */;
INSERT INTO `referente_aziendale` VALUES ('ASDRFTASDFERTASDE','Mario','Rossi','Avellino (AV)','24/05/1979','Amministratore Delegato');
/*!40000 ALTER TABLE `referente_aziendale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `registro`(
ID_Registro int not null auto_increment,
ID_Tirocinio int not null,
Status varchar(20) not null,
FirmaResponsabile boolean not null DEFAULT false,
FirmaTutorAccamico boolean not null DEFAULT false,
Tipo varchar(20) not null,
OreRaggiunte int not null default 0,
primary key(ID_Registro)
)
 ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES (1,1,'in approvazione',0,0,'esterno');
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;

UNLOCK TABLES;

--
-- Table structure for table `relazione`
--

DROP TABLE IF EXISTS `relazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relazione` (
  `ID_Relazione` int(11) NOT NULL AUTO_INCREMENT,
  `Descrizione` varchar(200) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Non compilato',
  `ID_TutorAziendale` int(11) NOT NULL,
  PRIMARY KEY (`ID_Relazione`),
  KEY `ID_TutorAziendale` (`ID_TutorAziendale`),
  KEY `Email` (`Email`),
  CONSTRAINT `relazione_ibfk_1` FOREIGN KEY (`ID_TutorAziendale`) REFERENCES `tutoraziendale` (`ID_TutorAziendale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relazione_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relazione`
--

LOCK TABLES `relazione` WRITE;
/*!40000 ALTER TABLE `relazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `relazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `ID_REQUEST` int(20) NOT NULL AUTO_INCREMENT,
  `CERTIFICATE_SERIAL` varchar(50) NOT NULL,
  `LEVEL` varchar(7) NOT NULL,
  `RELEASE_DATE` date NOT NULL,
  `EXPIRY_DATE` date NOT NULL,
  `YEAR` year(4) NOT NULL,
  `REQUESTED_CFU` tinyint(2) NOT NULL,
  `SERIAL` int(10) NOT NULL,
  `VALIDATED_CFU` tinyint(2) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  `FK_CERTIFIER` int(20) NOT NULL,
  `FK_STATE` int(20) NOT NULL,
  PRIMARY KEY (`ID_REQUEST`),
  KEY `FK_USER` (`FK_USER`),
  KEY `FK_CERTIFIER` (`FK_CERTIFIER`),
  KEY `FK_STATE` (`FK_STATE`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`FK_CERTIFIER`) REFERENCES `ente` (`ID_ENTE`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `request_ibfk_3` FOREIGN KEY (`FK_STATE`) REFERENCES `state` (`ID_STATE`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `riconoscimento`
--
select * from riconoscimento;
DROP TABLE IF EXISTS `riconoscimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `riconoscimento` (
  `ID_Riconoscimento` int(11) NOT NULL AUTO_INCREMENT,
  `Email_User` varchar(50) NOT NULL,
  `Ente_Azienda` varchar(200) NOT NULL,
  `Profilo` varchar(200) NOT NULL,
  `Indirizzo_Sede` varchar(200) NOT NULL,
  `Tipo_Contratto` varchar(200) NOT NULL,
  `Periodo` varchar(200) NOT NULL,
  `Ore_Svolte` int(11) NOT NULL,
  `CFU_TirocinioObbligatorio` int(11) NOT NULL,
  `CFU_TirocinioEsterno` int(11) NOT NULL,
  `CFU_AccompagnamentoLavoro` int(11) NOT NULL,
  `Stato` char NOT NULL,
  PRIMARY KEY (`ID_Riconoscimento`),
  KEY `Email_User` (`Email_User`),
  CONSTRAINT `riconoscimento_ibfk_1` FOREIGN KEY (`Email_User`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riconoscimento`
--

LOCK TABLES `riconoscimento` WRITE;
/*!40000 ALTER TABLE `riconoscimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `riconoscimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state` (
  `ID_STATE` int(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_attribute`
--

DROP TABLE IF EXISTS `system_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_attribute` (
  `SLUG` varchar(50) NOT NULL,
  `VALUE` varchar(100) NOT NULL,
  `FK_USER` varchar(50) NOT NULL,
  PRIMARY KEY (`SLUG`),
  KEY `FK_USER` (`FK_USER`),
  CONSTRAINT `system_attribute_ibfk_1` FOREIGN KEY (`FK_USER`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_attribute`
--

LOCK TABLES `system_attribute` WRITE;
/*!40000 ALTER TABLE `system_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tirocinioesterno`
--

DROP TABLE IF EXISTS `tirocinioesterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tirocinioesterno` (
  `ID_TirocinioEsterno` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `ID_TutorAccademico` int(11) NOT NULL,
  `ID_TutorAziendale` int(11) NOT NULL,
  `Data` varchar(10) DEFAULT NULL,
  `OreTotali` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `CFU` int(11) NOT NULL,
  `FirmaAzienda` tinyint(1) NOT NULL DEFAULT '0',
  `FirmaTutorAziendale` tinyint(1) NOT NULL DEFAULT '0',
  `FirmaTutorAccademico` tinyint(1) NOT NULL DEFAULT '0',
  `FirmaPdCD` tinyint(1) NOT NULL DEFAULT '0',
  `ID_Proposta` int(11) NOT NULL,
  PRIMARY KEY (`ID_TirocinioEsterno`),
  KEY `EMAIL` (`EMAIL`),
  KEY `ID_TutorAccademico` (`ID_TutorAccademico`),
  KEY `ID_TutorAziendale` (`ID_TutorAziendale`),
  KEY `ID_Proposta` (`ID_Proposta`),
  CONSTRAINT `tirocinioesterno_ibfk_1` FOREIGN KEY (`EMAIL`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tirocinioesterno_ibfk_2` FOREIGN KEY (`ID_TutorAccademico`) REFERENCES `tutoraccademico` (`ID_TutorAccademico`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tirocinioesterno_ibfk_3` FOREIGN KEY (`ID_TutorAziendale`) REFERENCES `tutoraziendale` (`ID_TutorAziendale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tirocinioesterno_ibfk_4` FOREIGN KEY (`ID_Proposta`) REFERENCES `proposta` (`ID_Proposta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tirocinioesterno`
--

LOCK TABLES `tirocinioesterno` WRITE;
/*!40000 ALTER TABLE `tirocinioesterno` DISABLE KEYS */;
INSERT INTO `tirocinioesterno` VALUES (1,'simonagrieco@studenti.unisa.it',1,1,'24/11/2019',150,'in approvazione',6,0,0,0,0,1),(2,'mconcetta@studenti.unisa.it',1,1,'29/12/2019',275,'in approvazione',11,0,0,0,0,1);
/*!40000 ALTER TABLE `tirocinioesterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tirociniointerno`
--

DROP TABLE IF EXISTS `tirociniointerno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tirociniointerno` (
  `ID_TirocinioInterno` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `ID_tutorAccademico` int(11) NOT NULL,
  `Data` varchar(10) DEFAULT NULL,
  `OreTotali` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `NumeroCFU` int(11) NOT NULL,
  `FirmaPdCD` tinyint(1) NOT NULL DEFAULT '0',
  `FirmaTutorAccademico` tinyint(1) NOT NULL DEFAULT '0',
  `ID_Proposta` int(11) NOT NULL,
  PRIMARY KEY (`ID_TirocinioInterno`),
  KEY `EMAIL` (`EMAIL`),
  KEY `ID_tutorAccademico` (`ID_tutorAccademico`),
  KEY `ID_Proposta` (`ID_Proposta`),
  CONSTRAINT `tirociniointerno_ibfk_1` FOREIGN KEY (`EMAIL`) REFERENCES `user` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tirociniointerno_ibfk_2` FOREIGN KEY (`ID_tutorAccademico`) REFERENCES `tutoraccademico` (`ID_TutorAccademico`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tirociniointerno_ibfk_3` FOREIGN KEY (`ID_Proposta`) REFERENCES `proposta` (`ID_Proposta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tirociniointerno`
--

LOCK TABLES `tirociniointerno` WRITE;
/*!40000 ALTER TABLE `tirociniointerno` DISABLE KEYS */;
INSERT INTO `tirociniointerno` VALUES (1,'mconcetta@studenti.unisa.it',2,'02/04/2019',150,'in approvazione',6,0,0,4);
/*!40000 ALTER TABLE `tirociniointerno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutoraccademico`
--

DROP TABLE IF EXISTS `tutoraccademico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutoraccademico` (
  `ID_TutorAccademico` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `sex` char(1) NOT NULL,
  `email` varchar(50) NOT NULL,

  PRIMARY KEY (`ID_TutorAccademico`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutoraccademico`
--

LOCK TABLES `tutoraccademico` WRITE;
/*!40000 ALTER TABLE `tutoraccademico` DISABLE KEYS */;
INSERT INTO `tutoraccademico` VALUES (1,'Mario','Giorgio','umpalumpa2','M','mariogiorgio@unisa.it'),(2,'Antonio','Sultani','radiomaria11','M','antoniosultani@unisa.it'),(3,'Edoardo','Carpentiero','edoardo93@','M','edoardo93av@unisa.it');
/*!40000 ALTER TABLE `tutoraccademico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutoraziendale`
--

DROP TABLE IF EXISTS `tutoraziendale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutoraziendale` (
  `ID_TutorAziendale` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Azienda` int(11) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Telefono` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID_TutorAziendale`),
  KEY `ID_Azienda` (`ID_Azienda`),
  CONSTRAINT `tutoraziendale_ibfk_1` FOREIGN KEY (`ID_Azienda`) REFERENCES `azienda` (`ID_Azienda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutoraziendale`
--

LOCK TABLES `tutoraziendale` WRITE;
/*!40000 ALTER TABLE `tutoraziendale` DISABLE KEYS */;
INSERT INTO `tutoraziendale` VALUES (1,1,'Ugo','Ughi','uughi@tutor.unisa.it','ciaociao1','3330333000'),(2,1,'Anna','Aster','aaster@tutor.unisa.it','ciaociao1','3331333111'),(3,2,'Ada','Ino','aino@tutor.unisa.it','ciaociao1','3332333222'),(4,2,'Imma','Bobbo','ibobbo@tutor.unisa.it','ciaociao1','3333333333');
/*!40000 ALTER TABLE `tutoraziendale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `EMAIL` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  `SEX` char(1) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `USER_TYPE` tinyint(1) NOT NULL,
  `tipoCorso` varchar(20) DEFAULT NULL,
  `Luogo_Nascita` varchar(50) NOT NULL,
  `Data_Nascita` varchar(11) NOT NULL,
  `Residente` varchar(50) NOT NULL,
  `Via` varchar(100) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `Matricola` varchar(11) NOT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('edoardo93av@studenti.unisa.it','Edoardo','Carpentiero','o','edoardo93@',0,'triennale','ba','ab','ab','ba','ab','ab'),('fferrucci@unisa.it','Filomena','Ferrucci','F','Ferrucci11',2,NULL,'Salerno','12/02/1998','Salerno','Antonio Gramsci','85937','5100001118'),('mconcetta@studenti.unisa.it','Maria Concetta','Schiavone','F','mconcetta1998',0,'triennale','Milano','09/08/2000','Salerno','Montebello','81237','0147852369'),('simonagrieco@studenti.unisa.it','Simona','Grieco','F','grieco1998',0,'triennale','Avellino','06/05/1999','Fisciano','roma','1812324','510075');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-30  0:39:41
