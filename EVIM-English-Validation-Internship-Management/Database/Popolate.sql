#Inserimento utenti
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`) 
VALUES ('fferrucci@unisa.it','Filomena','Ferrucci','F','Ferrucci11',1, null);
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`) 
VALUES ('mconcetta@studenti.unisa.it','Maria Concetta','Schiavone','F','mconcetta1998',0,'triennale');
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`) 
VALUES ('simonagrieco@studenti.unisa.it','Simona','Grieco','F','grieco1998',0,'triennale');

#Inserimento aziende
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`) 
VALUES ('0764352056C','3214565780','Microsoft','Xboxthebest','microsoftofficial@tiscali.it','https://www.microsoft.com/it-it','Viale Pasubio, 21, 20154 Milano MI','I suoi prodotti principali sono il sistema operativo desktop Microsoft Windows e la suite di produttivit� personale Microsoft Office, per i quali � al primo posto nel rispettivo mercato. Altre linee di produzione comprendono: sistemi di sviluppo software (IDE e compilatori), DBMS, periferiche di input (tastiere e mouse), console di gioco (Xbox, Xbox 360 e Xbox One), periferiche di gioco (joystick e cloche per il pilotaggio di velivoli, volanti e altro).');
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`)
VALUES ('0854334056C','3335678903','Samsung','SamsungUnisa','samsungitalia@gmail.com','https://www.samsung.com/it/',' Via Mike Bongiorno, 9, 20124 Milano MI','Ci dedichiamo costantemente allo sviluppo e all�ampliamento della gamma di prodotti strategici nella nostra divisione dell�elettronica di consumo.');

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

#Inserimento proposteee
INSERT INTO `proposta` (`Obiettivi`,`Competenze`,`Attivita`,`Modalita`,`ID_Azienda`,`ID_Tutor`) 
VALUES ('Sviluppare nuove metodologie. Integrare metodologie esistenti.','Laboratorio di Verifica di Correttezza e Sintesi Automatica di Sistemi Digitali','Verifica automatica di correttezza dei programmi',' Per avere un’idea dell’area di riferimento consultare il materiale del corso di Tecniche Automatiche per La Correttezza del Software (http://www.di- srv.unisa.it/professori/latorre/didattica/TACS/)',1,1);
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

select * from EVIM.TirocinioInterno where status="in approvazione";
select * from EVIM.TirocinioEsterno where status="in approvazione";