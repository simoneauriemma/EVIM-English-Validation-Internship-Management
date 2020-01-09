#Inserimento utenti
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('fferrucci@unisa.it','Filomena','Ferrucci','F','Ferrucci11',2, null,'Avellino','23/08/1972','Salerno','Via Ponteromito, 12','3214567432','');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('segreteria@unisa.it','Segreteria','Segreteria','F','segreteria1',1, null,'','','Salerno','','3298767432','');


#Studenti
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('mconcetta@studenti.unisa.it','Maria Concetta','Schiavone','F','mconcetta1998',0,'triennale','Napoli','02/04/1998','Avellino','Via Monza, 29','3214567098','0512105001');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('simonagrieco@studenti.unisa.it','Simona','Grieco','F','grieco1998',0,'triennale','Napoli','24/11/1998','Avellino','Via Lampo, 11','3216767098','0512105002');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('paolorossi@studenti.unisa.it','Paolo','Rossi','M','paolo1',0,'triennale','Benevento','06/05/1998','Benevento','Via Lampo, 11','3216767098','0512105002');

#Inserimento aziende
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`) 
VALUES ('0764352056C','3214565780','Microsoft','Xboxthebest','microsoftofficial@tiscali.it','https://www.microsoft.com/it-it','Viale Pasubio, 21, 20154 Milano MI','I suoi prodotti principali sono il sistema operativo desktop Microsoft Windows e la suite di produttivita personale Microsoft Office, per i quali sta al primo posto nel rispettivo mercato. Altre linee di produzione comprendono: sistemi di sviluppo software (IDE e compilatori), DBMS, periferiche di input (tastiere e mouse), console di gioco (Xbox, Xbox 360 e Xbox One), periferiche di gioco (joystick e cloche per il pilotaggio di velivoli, volanti e altro).','234','Codice',1);
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`)
VALUES ('0854334056C','3335678903','Samsung','SamsungUnisa','samsungitalia@gmail.com','https://www.samsung.com/it/',' Via Mike Bongiorno, 9, 20124 Milano MI','Ci dedichiamo costantemente allo sviluppo e all ampliamento della gamma di prodotti strategici nella nostra divisione dell elettronica di consumo.','234','Codice',2);

#Inserimento tutor aziendali
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('1', 'Ugo', 'Ughi', 'uughi@tutor.unisa.it', 'ciaociao1', '3330333000');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('1', 'Anna', 'Aster', 'aaster@tutor.unisa.it', 'ciaociao1', '3331333111');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`)
VALUES ('2', 'Ada', 'Ino', 'aino@tutor.unisa.it', 'ciaociao1', '3332333222');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('2', 'Imma', 'Bobbo', 'ibobbo@tutor.unisa.it', 'ciaociao1', '3333333333');

#Inserimento tutor accademici
INSERT INTO `tutoraccademico` (`Nome`,`Cognome`,`Password`,`indirizzo`,`email`,`Telefono`) 
VALUES ('Mario','Giorgio','umpalumpa2','via dalle cicogne Salerno 22344 64','mariogiorgio@unisa.it','3334333444');
INSERT INTO `tutoraccademico` (`Nome`,`Cognome`,`Password`,`indirizzo`,`email`,`Telefono`) 
VALUES ('Antonio','Sultani','radiomaria11','via roma 82933 Salerno 81','antoniosultani@unisa.it','3335333555');
select * from tutorAccademico;

#Inserimento proposte
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppare nuove metodologie. Integrare metodologie esistenti.','Laboratorio di Verifica di Correttezza e Sintesi Automatica di Sistemi Digitali','Verifica automatica di correttezza dei programmi',' Per avere unâidea dellâarea di riferimento consultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software (http://www.di- srv.unisa.it/professori/latorre/didattica/TACS/)',1,1);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppo di servizi di intelligenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',NULL,1);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Realizzazione di un ecosistema digitale di rappresentazione e gestione della conoscenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',NULL,2);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Realizzazione di sistemi domotici (IOT)','Reti di calcolaroei','Sistemi Informativi Geografici e applicazioni IOT', 'stringaAcasoPoiSiVede', NULL, 2);

#Inserimenti TIROCINI ESTERNI
INSERT INTO `TirocinioEsterno` (`EMAIL`,`ID_TutorAccademico`,`ID_TutorAziendale`,`data`,`OreTotali`,`status`,`CFU`,`FirmaAzienda`,`FirmaTutorAccademico`,`FirmaTutorAziendale`,`FirmaPdCD`,`ID_Proposta`)
VALUES ('simonagrieco@studenti.unisa.it','1','1','24/11/2019',150,"in approvazione",6, false, false, false, false,1);


#Inserimenti TIROCINIO INTERNO
INSERT INTO `TirocinioInterno` (`EMAIL`,`ID_tutorAccademico`,`Data`,`OreTotali`,`status`,`numeroCFU`,`FirmaTutorAccademico`,`FirmaPdCD`,`ID_Proposta`)
VALUES ('mconcetta@studenti.unisa.it','2','02/04/2019',150,"in approvazione",6, false, false,4);
INSERT INTO `TirocinioInterno` (`EMAIL`,`ID_tutorAccademico`,`Data`,`OreTotali`,`status`,`numeroCFU`,`FirmaTutorAccademico`,`FirmaPdCD`,`ID_Proposta`)
VALUES ('simonagrieco@studenti.unisa.it','1','08/04/2019',150,"in approvazione",6, false, false,4);

#Inserimenti Attività


#Inserimenti Registro


select * from EVIM.TirocinioInterno where status="in approvazione";
select * from EVIM.TirocinioEsterno where status="in approvazione";


select TirocinioEsterno.ID_TirocinioEsterno, Registro.FirmaResponsabile, TirocinioEsterno.status, TirocinioEsterno.CFU,
							TirocinioEsterno.OreTotali, Registro.ID_Registro 
							from TirocinioEsterno, Registro 
							where TirocinioEsterno.ID_TirocinioEsterno = Registro.ID_Tirocinio AND 
							TirocinioEsterno.EMAIL = 'simonagrieco@studenti.unisa.it' AND TirocinioEsterno.status='in svolgimento';

select * from EVIM.TirocinioInterno where EMAIL="simonagrieco@studenti.unisa.it" AND status="in approvazione";