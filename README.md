# Hotell

### Teha andmepaas nimega hotell ja copy past-ida query-sse järgmised käsud

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

| kliendi_id | ees_nimi      | pere_nimi     | email                   |
| ----------:|:-------------:|:-------------:|:-----------------------:|
| 1          | Nimi          |perekond       | Nimi.perekond@gmail.com |
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

| kliendi_id | ees_nimi      | pere_nimi     | email                   |
| ----------:|:-------------:|:-------------:|:-----------------------:|
| 1          | Nimi          |perekond       | Nimi.perekond@gmail.com |
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

| kliendi_id | ees_nimi      | pere_nimi     | alg_aeg    | lõpp_aeg   |
| ----------:|:-------------:|:-------------:|:----------:|:----------:|
| 1          | Nimi          |perekond       | DD.MM.YYYY | DD.MM.YYYY |

***

- ViewFactory class-is on scene-ide laadimine
- Mode class-is kontrollib kas Stage on olemas ja kui ei ole siis tekitab
- HotellApplication on selleks laadida algne vaade

### Kui on vaja uus vaade teha siis selle vaate Controller-isse lisada selle vaate jaoks vajatud, funksioonid ViewFactory-sse lisada selle laadimine ja ClientController-isse lisada nuppude functsioon, mis viivad järgmisesse vaatesse

