#AZIENDA
INSERT INTO `azienda` (`CF`,`Telefono`,`Nome`,`Password`,`Email`,`SitoWeb`,`Indirizzo`,`Descizione`,`Numero_Dipendenti`,`Codice_Ateco`,`CF_Referente`,`ID_Convenzione`) 
VALUES ('0164352056C','3214565780','AsusItalia','azienda1','azienda1@email.it','https://www.asus.com/it-it','Viale Pasubio, 21, 20154 Milano MI','Entra nel mondo ASUS e scopri le novità su Smartphone, Notebook, Gaming e Accessori.',234,'Codice','001RTU67T56DIHGG',1);

#TUTOR AZIENDALE
INSERT INTO `evim`.`TutorAziendale` (`ID_Azienda`, `Nome`, `Cognome`, `Email`, `Password`, `Telefono`) 
VALUES ('1', 'Anna', 'Verdi', 'tutoraz@email.it', 'tutoraz1', '3330333000');

#TUTOR ACCADEMICO
INSERT INTO `TutorAccademico` (`Nome`,`Cognome`,`Password`,`sex`,`email`) 
VALUES ('Vittorio','Secca','tutor1','M','tutorac@unisa.it');

#STUDENTE
INSERT INTO `user` (`EMAIL`,`NAME`,`SURNAME`,`SEX`,`PASSWORD`,`USER_TYPE`,`tipoCorso`,`Luogo_Nascita`,`Data_Nascita`,`Residente`,`Via`,`Telefono`,`Matricola`) 
VALUES ('studente1@studenti.unisa.it','Maria Concetta','Schiavone','F','studente1',0,'triennale','Napoli','02/04/1998','Avellino','Via Monza, 29','3214567098','0512105001');