# Hotell

### Teha andmepaas nimega hotell ja copy past-ida query-sse järgmised käsud

```
CREATE SEQUENCE hotell_kliendi_seq;

CREATE TABLE kliendid (
	kliendi_id bigint NOT NULL DEFAULT nextval('hotell_kliendi_seq'),
	eesnimi varchar(50) DEFAULT NULL,
	pere_nimi varchar(50) DEFAULT NULL,
	isikukood varchar(11) DEFAULT NULL,
	email varchar(50) DEFAULT NULL,
	PRIMARY KEY (kliendi_id)
);
```

 &#8595; Mis tekitab sellise tabeli &#8595;

| kliendi_id | eesnimi       | pere_nimi     | isikukood   | email                   |
| ----------:|:-------------:|:-------------:|:-----------:|:-----------------------:|
| 1          | Nimi          | perekonnanimi | 55555555555 | Nimi.perekond@gmail.com |
---
### Tekst toad
```
CREATE SEQUENCE hotell_kliendi_seq;

CREATE TABLE kliendid (
	kliendi_id bigint NOT NULL DEFAULT nextval('hotell_kliendi_seq'),
	ees_nimi varchar(50) DEFAULT NULL,
	pere_nimi varchar(50) DEFAULT NULL,
	email varchar(50) DEFAULT NULL,
	PRIMARY KEY (kliendi_id)
);
```

&#8595; Mis tekitab sellise tabeli &#8595;

| kliendi_id | toa_num       | voodikohtade_arv | hind | broneeria_eesnimi | broneeria_perekonnanimi |
| ----------:|:-------------:|-----------------:|-----:|:-----------------:|:-----------------------:|
| 1          | Nimi          | 3                | 500  | Nimi		   | Perekonnanimi	     |
---
### Tekst broneeringud
```
CREATE SEQUENCE hotell_kliendi_seq;

CREATE TABLE kliendid (
	kliendi_id bigint NOT NULL DEFAULT nextval('hotell_kliendi_seq'),
	ees_nimi varchar(50) DEFAULT NULL,
	pere_nimi varchar(50) DEFAULT NULL,
	alg_aeg date DEFAULT NULL,
	lõpp_aeg date DEFAULT NULL,
	PRIMARY KEY (kliendi_id)
);
```
&#8595; Mis tekitab sellise tabeli &#8595;

| kliendi_id | külastaja_eesnimi| külastaja_perekonnanimi | tuba          | alg_aeg    | lõpp_aeg   |
| ----------:|:----------------:|:-----------------------:|:-------------:|:----------:|:----------:|
| 1          | Nimi             | Perekonnanimi		  | perekond      | DD.MM.YYYY | DD.MM.YYYY |

***

- ViewFactory class-is on scene-ide laadimine
- Mode class-is kontrollib kas Stage on olemas ja kui ei ole siis tekitab
- HotellApplication on selleks laadida algne vaade

### Kui on vaja uus vaade teha siis selle vaate Controller-isse lisada selle vaate jaoks vajatud, funksioonid ViewFactory-sse lisada selle laadimine ja ClientController-isse lisada nuppude functsioon, mis viivad järgmisesse vaatesse

