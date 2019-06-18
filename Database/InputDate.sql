-- Входные данные для клиента

insert into klient (PK_Klient, FIO, Nomer_pasporta, Adres, Nomer_telefona)
  values (1, 'Chapayev', '123456789', 'Adres', '123456789');

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (2, 'Anderson', TO_DATE('15-jun-1993', 'DD-MON-YYYY'), '123456789', TO_DATE('20-jun-2007', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (3, 'Barnes', TO_DATE('30-aug-2000', 'DD-MON-YYYY'), '123456789', TO_DATE('20-aug-2014', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (4, 'Baker', TO_DATE('13-jun-1990', 'DD-MON-YYYY'), '123456789', TO_DATE('17-jun-2004', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (5, 'Brown', TO_DATE('20-jun-1991', 'DD-MON-YYYY'), '123456789', TO_DATE('20-jun-2005', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (6, 'Allen', TO_DATE('20-Oct-1994', 'DD-MON-YYYY'), '123456789', TO_DATE('30-Oct-2008', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (7, 'Collins', TO_DATE('20-Dec-1994', 'DD-MON-YYYY'), '123456789', TO_DATE('30-Dec-2008', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (8, 'Cooper', TO_DATE('17-jun-2001', 'DD-MON-YYYY'), '123456789', TO_DATE('24-jun-2015', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (9, 'Clark', TO_DATE('20-Apr-1994', 'DD-MON-YYYY'), '123456789', TO_DATE('20-Apr-2008', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

insert into klient (PK_Klient, FIO, Data_rogdenia, Nomer_pasporta, Data_vidachi, Kem_vidan, Adres, Nomer_telefona) values (10, 'Campbell', TO_DATE('1-jun-1994', 'DD-MON-YYYY'), '123456789', TO_DATE('14-jun-2008', 'DD-MON-YYYY'), 'Kem-to', 'Adres', '123456789')
/

-- Входные данные для категорий

insert into kategoria (pk_kategoria, naimenovanie) values (1, 'odegda')
/

insert into kategoria (pk_kategoria, naimenovanie) values (2, 'biguteria')
/

insert into kategoria (pk_kategoria, naimenovanie) values (3, 'tehnika')
/

insert into kategoria (pk_kategoria, naimenovanie) values (4, 'antikvariat')
/

-- Входные данные для Lombard

insert into Lombard (PK_Lombard, Adres, Naimenovanie, INN) values (1, 'Adres', 'LombardLombard', '132456789')
/

-- Входные данные для Imushestvo

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (1, 'Kolco', 'zolotoe kolco', 10000, 2, 'hranetie')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (2, 'microvolnovka', 'prosto microvolnovka' ,3000, 3, 'hranetie')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (3, 'kostum', 'horoshii' ,5000, 1, 'hranetie')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (4, 'ogerelie', 'serebro' ,7000, 2, 'hranetie')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (5, 'vasa', 'staraya' ,20000, 4, 'prodaga')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (6, 'minitor', 'gk' ,4000, 3, 'prodaga')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (7, 'kompiyuter', 'novii' ,15000, 3, 'hranetie')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (8, 'planshet', 'samsung' ,5000, 3, 'prodaga')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (9, 'botinki', 'koganie' ,3000, 1, 'hranetie')
/

insert into Imushestvo (PK_Imushestvo, Naimenovanie, Opisanie, Stoimost, Kategoria, Status) values (10, 'braslet', 's briliantom' ,7500, 2, 'prodaga')
/

-- Входные данные для Rabotnik

insert into Rabotnik (PK_Rabotnik, FIO, Dolgnost) values (1, 'Lewis', 'upravlayushii')
/

-- Входные данные для Dogovor

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (1, 1, 1, 1, 2, 2000, TO_DATE('14-jun-2020', 'DD-MON-YYYY'), TO_DATE('21-jun-2020', 'DD-MON-YYYY'), TO_DATE('10-jul-2020', 'DD-MON-YYYY'), 5)
/

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (2, 1, 2, 1, 3, 3000, TO_DATE('14-jul-2020', 'DD-MON-YYYY'), TO_DATE('21-jul-2020', 'DD-MON-YYYY'), TO_DATE('10-aug-2020', 'DD-MON-YYYY'), 5)
/

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (3, 1, 3, 1, 4, 5000,  TO_DATE('30-jun-2020', 'DD-MON-YYYY'), TO_DATE('14-jul-2020', 'DD-MON-YYYY'), TO_DATE('24-aug-2020', 'DD-MON-YYYY'), 5)
/

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (4, 1, 4, 1, 5, 18000, TO_DATE('1-jun-2020', 'DD-MON-YYYY'), TO_DATE('13-jun-2020', 'DD-MON-YYYY'), TO_DATE('31-jun-2020', 'DD-MON-YYYY'), 5)
/

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (5, 1, 5, 1, 6, 2000, TO_DATE('5-jun-2020', 'DD-MON-YYYY'), TO_DATE('20-jun-2020', 'DD-MON-YYYY'), TO_DATE('10-jul-2020', 'DD-MON-YYYY'), 5)
/

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (6, 1, 6, 1, 7, 13000, TO_DATE('14-jun-2020', 'DD-MON-YYYY'), TO_DATE('21-jun-2020', 'DD-MON-YYYY'), TO_DATE('10-jul-2020', 'DD-MON-YYYY'), 5)
/

insert into Dogovor (Nomer_dogovora, Id_lombarda, Id_klienta, Id_rabotnika,  Id_imushestva, Razmer_ssudi, Data_zakluchenia, Data_vozvrata, Data_lgotnogo_sroka, Procentnaa_stavka) values (7, 1, 7, 1, 8, 6000, TO_DATE('30-jun-2020', 'DD-MON-YYYY'), TO_DATE('10-jul-2020', 'DD-MON-YYYY'), TO_DATE('30-jul-2020', 'DD-MON-YYYY'), 5)
/