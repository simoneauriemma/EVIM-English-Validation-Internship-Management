#Inserimento utenti
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('fferrucci@unisa.it','Filomena','Ferrucci','F','Ferrucci11',2, null,'Avellino','23/08/1972','Salerno','Via Ponteromito, 12','3214567432','');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('segreteria@unisa.it','Segreteria','Segreteria','F','segreteria1',1, null,'','','Salerno','','3298767432','');


#Studenti
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('mconcetta@studenti.unisa.it','Maria Concetta','Schiavone','F','mconcetta1998',0,'triennale','Napoli','02/04/1998','Avellino','Via Monza, 29','3214567098','0512105001');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('simonagrieco@studenti.unisa.it','Simona','Grieco','F','grieco1998',0,'triennale','Avellino','24/11/1998','Avellino','Via Lampo, 11','3216767098','0512105002');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('simoneauriemma@studenti.unisa.it','Simone','Auriemma','M','simone1',0,'triennale','Napoli','26/10/1998','Napoli','Via Marlo, 34','3216767098','0512105003');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('nicolasisti@studenti.unisa.it','Nicola','Sisti','M','nicola1',0,'triennale','Potenza','12/02/1994','Matera','Via Giallo, 5','3216123098','0512105004');



#referente aziendale
INSERT INTO `referente_aziendale` (`CF`,`nome`,`cognome`,`luogo_nascita`,`data_nascita`,`ruolo`)
VALUES('001RTU67T56DIHGG','Luca','Esposito','Roma','23/09/1982','Amministratore delegato');
INSERT INTO `referente_aziendale` (`CF`,`nome`,`cognome`,`luogo_nascita`,`data_nascita`,`ruolo`)
VALUES('002RTU67T56DIHGG','Maria','Paolillo','Caserta','07/11/1989','Amministratore delegato');
INSERT INTO `referente_aziendale` (`CF`,`nome`,`cognome`,`luogo_nascita`,`data_nascita`,`ruolo`)
VALUES('003RTU67T56DIHGG','Angela','Giordano','Benevento','11/04/1989','Amministratore delegato');
INSERT INTO `referente_aziendale` (`CF`,`nome`,`cognome`,`luogo_nascita`,`data_nascita`,`ruolo`)
VALUES('004RTU67T56DIHGG','Paola','Lucca','Salerno','07/11/1989','Amministratore delegato');
INSERT INTO `referente_aziendale` (`CF`,`nome`,`cognome`,`luogo_nascita`,`data_nascita`,`ruolo`)
VALUES('005RTU67T56DIHGG','Maria','Paolillo','Caserta','07/11/1989','Amministratore delegato');

#Inserimento aziende
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`,`ID_Convenzione`) 
VALUES ('0164352056C','3214565780','Microsoft','microsoft1','microsoftofficial@tiscali.it','https://www.microsoft.com/it-it','Viale Pasubio, 21, 20154 Milano MI','I suoi prodotti principali sono il sistema operativo desktop Microsoft Windows e la suite di produttivita personale Microsoft Office, per i quali sta al primo posto nel rispettivo mercato. Altre linee di produzione comprendono: sistemi di sviluppo software (IDE e compilatori), DBMS, periferiche di input (tastiere e mouse), console di gioco (Xbox, Xbox 360 e Xbox One), periferiche di gioco (joystick e cloche per il pilotaggio di velivoli, volanti e altro).',234,'Codice','001RTU67T56DIHGG',1);
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`,`ID_Convenzione`)
VALUES ('0264352056C','3335678903','Samsung','samsung1','samsungitalia@gmail.com','https://www.samsung.com/it/',' Via Mike Bongiorno, 9, 20124 Milano MI','Ci dedichiamo costantemente allo sviluppo e all ampliamento della gamma di prodotti strategici nella nostra divisione dell elettronica di consumo.',987,'Codice','002RTU67T56DIHGG',2);
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`,`ID_Convenzione`)
VALUES ('0364352056C','3465678903','Apple','appleapple1','appleitalia@gmail.com','https://www.apple.com/it/',' Via Mele Bianche, 2, 20124 Milano MI','Ci dedichiamo a software completi per il nostro marchio, famoso in tutto il mondo, e allo sviluppo di algortimi complessi per essi.',145,'Codice','003RTU67T56DIHGG',3);
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`,`ID_Convenzione`)
VALUES ('0464352056C','3465678123','IBM','ibmitalia1','ibmitalia@gmail.com','https://www.ibm.com/it/',' Via Carosello, 27, 20124 Milano MI','Ci dedichiamo al successo di ogni cliente e alla creazione di innovazioni importanti, di soluzioni e prodotti , per lo sviluppo del tuo business.',876,'Codice','004RTU67T56DIHGG',4);
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`,`ID_Convenzione`)
VALUES ('0564352056C','3465678321','Google','google123','google@gmail.com','https://www.google.com/it/',' Via Federico Confalonieri, 4, 20124 Milano MI','Creare oppurtunità è uno dei nostri impegni, ma anche del nostro interesse: perché voi avete un grande potenziale da esprimere in digitale','457','Codice','005RTU67T56DIHGG',5);

#Inserimento tutor aziendali
INSERT INTO `evim`.`TutorAziendale` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('1', 'Ugo', 'Ughi', 'uughi@tutor.unisa.it', 'ciaociao1', '3330333000');
INSERT INTO `evim`.`TutorAziendale` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('1', 'Emilio', 'Grande', 'egrande@tutor.unisa.it', 'ciaociao1', '3330333111');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('2', 'Anna', 'Aster', 'aaster@tutor.unisa.it', 'ciaociao1', '3331333111');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('2', 'Gianni', 'Mastro', 'gmastro@tutor.unisa.it', 'ciaociao1', '3331333222');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`)
VALUES ('3', 'Ada', 'Ino', 'aino@tutor.unisa.it', 'ciaociao1', '3332333222');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`)
VALUES ('3', 'Antonio', 'Gianno', 'agianno@tutor.unisa.it', 'ciaociao1', '3332333555');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('4', 'Imma', 'Bobbo', 'ibobbo@tutor.unisa.it', 'ciaociao1', '3333333333');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('4', 'Alex', 'Martino', 'amartino@tutor.unisa.it', 'ciaociao1', '3333333999');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('5', 'Simone', 'Babbo', 'sbabbo@tutor.unisa.it', 'ciaociao1', '3333333300');
INSERT INTO `evim`.`tutorAZIENDALE` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('5', 'Rosa', 'Torre', 'rtorre@tutor.unisa.it', 'ciaociao1', '3333333987');
select * from TutorAziendale;

#Inserimento tutor accademici
INSERT INTO `TutorAccademico` (`Nome`,`Cognome`,`Password`,`sex`,`email`) 
VALUES ('Mario','Giorgio','umpalumpa2','M','mariogiorgio@unisa.it');
INSERT INTO `tutoraccademico` (`Nome`,`Cognome`,`Password`,`sex`,`email`) 
VALUES ('Antonio','Sultani','radiomaria11','M','antoniosultani@unisa.it');
INSERT INTO `tutoraccademico` (`Nome`,`Cognome`,`Password`,`sex`,`email`) 
VALUES ('Laura','Bolla','laura1','F','laurabolla@unisa.it');
INSERT INTO `tutoraccademico` (`Nome`,`Cognome`,`Password`,`sex`,`email`) 
VALUES ('Giorgia','Sacco','giorgia1','F','giorgiasacco@unisa.it');
select * from TutorAccademico;

#Inserimento proposte

#tutor aziendali
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppare nuove metodologie. Integrare metodologie esistenti.','Laboratorio di Verifica di Correttezza e Sintesi Automatica di Sistemi Digitali','Verifica automatica di correttezza dei programmi',' Per avere idea dell area di riferimento consultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software (http://www.di- srv.unisa.it/professori/latorre/didattica/TACS/)', 1 ,1);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppo di servizi di intelligenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',2,3);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Realizzazione di un ecosistema digitale di rappresentazione e gestione della conoscenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',3,5);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Realizzazione di sistemi domotici (IOT)','Reti di calcolaroei','Sistemi Informativi Geografici e applicazioni IOT', 'stringaAcasoPoiSiVede', 4, 7);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppare nuove metodologie. Integrare metodologie esistenti.','Laboratorio di Verifica di Correttezza e Sintesi Automatica di Sistemi Digitali','Verifica automatica di correttezza dei programmi',' Per avere idea dell area di riferimento consultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software ', 5 ,9);


# tutor accademici
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppare nuove metodologie. Integrare metodologie esistenti.','Laboratorio di Verifica di Correttezza e Sintesi Automatica di Sistemi Digitali','Verifica automatica di correttezza dei programmi',' Per avere idea dell area di riferimento consultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software ', null ,1);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppo di servizi di intelligenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',null,2);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Realizzazione di un ecosistema digitale di rappresentazione e gestione della conoscenza territoriale','Laboratorio di Sistemi Informativi Geografici','Sistemi Informativi Geografici e applicazioni per Mobile GIS','http://docenti.unisa.it/004827/risorse?categoria=337&risorsa=807',null ,3);
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Realizzazione di sistemi domotici (IOT)','Reti di calcolaroei','Sistemi Informativi Geografici e applicazioni IOT', 'stringaAcasoPoiSiVede', null, 4);


#Inserimenti TIROCINI ESTERNI
INSERT INTO `TirocinioEsterno` (`EMAIL`,`ID_TutorAccademico`,`ID_TutorAziendale`,`data`,`OreTotali`,`status`,`CFU`,`FirmaAzienda`,`FirmaTutorAccademico`,`FirmaTutorAziendale`,`FirmaPdCD`,`ID_Proposta`)
VALUES ('simonagrieco@studenti.unisa.it','1','1','24/11/2019',150,"in approvazione",6, false, false, false, false,1);


#Inserimenti TIROCINIO INTERNO
INSERT INTO `TirocinioInterno` (`EMAIL`,`ID_tutorAccademico`,`Data`,`OreTotali`,`status`,`numeroCFU`,`FirmaTutorAccademico`,`FirmaPdCD`,`ID_Proposta`)
VALUES ('mconcetta@studenti.unisa.it','2','02/04/2019',150,"in approvazione",6, false, false,4);
INSERT INTO `TirocinioInterno` (`EMAIL`,`ID_tutorAccademico`,`Data`,`OreTotali`,`status`,`numeroCFU`,`FirmaTutorAccademico`,`FirmaPdCD`,`ID_Proposta`)
VALUES ('simonagrieco@studenti.unisa.it','1','08/04/2019',150,"in approvazione",6, false, false,4);

#Inserimento Convezioni
INSERT INTO `convenzione` 
VALUES (1,'24/08/2019','n.2884/2018','3 Gennaio - 18 Giugno');
INSERT INTO `convenzione` 
VALUES (2,'24/08/2019','n.2884/2018','3 Febbraio - 18 Luglio');
INSERT INTO `convenzione` 
VALUES (3,'24/08/2019','n.2884/2018','6 Gennaio - 28 Giugno');
INSERT INTO `convenzione` 
VALUES (4,'24/08/2019','n.2884/2018','10 Gennaio - 18 Giugno');
INSERT INTO `convenzione` 
VALUES (5,'24/08/2019','n.2884/2018','9 Gennaio - 07 Giugno');
