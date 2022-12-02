# Hotell
1. ### [ SQL](https://github.com/KarlJug/hotel/blob/main/README.md#andmebaas)
	- [Kliendid](https://github.com/KarlJug/hotel/blob/main/README.md#teha-andmepaas-nimega-hotell-ja-copy-past-ida-query-sse-j%C3%A4rgmised-k%C3%A4sud)
	- [Toad](https://github.com/KarlJug/hotel/blob/main/README.md#tekst-toad)
	- [Broneeringud](https://github.com/KarlJug/hotel/blob/main/README.md#tekst-broneeringud)
	
2. ### [Info failide kohta](https://github.com/KarlJug/hotel/blob/main/README.md#info-failide-kohta)

3. ### [Kuidas teha uus vaade](https://github.com/KarlJug/hotel/blob/main/README.md#kuidas-teha-uus-vaade)

## andmebaas

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

***

### Tekst toad

```
CREATE SEQUENCE hotell_kliendi_seq;

CREATE TABLE kliendid (
	id bigint NOT NULL DEFAULT nextval('hotell_kliendi_seq'),
	toa_num integer DEFAULT NULL,
	voodikohtade_arv integer DEFAULT NULL,
	hind integer DEFAULT NULL,
	broneeria_eesnimi varchar(50) DEFAULT NULL,
	broneeria_perekonnanimi varchar(50) DEFAULT NULL,
	PRIMARY KEY (kliendi_id)
);
```

&#8595; Mis tekitab sellise tabeli &#8595;

| id	 | toa_num       | voodikohtade_arv | hind | broneeria_eesnimi | broneeria_perekonnanimi |
| ------:|:-------------:|-----------------:|-----:|:-----------------:|:-----------------------:|
| 1      | Nimi          | 3                | 500  | Nimi	       | Perekonnanimi	         |

***

### Tekst broneeringud

```
CREATE SEQUENCE hotell_kliendi_seq;

CREATE TABLE kliendid (
	id bigint NOT NULL DEFAULT nextval('hotell_kliendi_seq'),
	külastaja_eesnimi varchar(50) DEFAULT NULL,
	külastaja_perekonnanimi varchar(50) DEFAULT NULL,
	tuba integer DEFAULT NULL,
	alg_aeg date DEFAULT NULL,
	lõpp_aeg date DEFAULT NULL,
	PRIMARY KEY (kliendi_id)
);
```
&#8595; Mis tekitab sellise tabeli &#8595;

| id 	 | külastaja_eesnimi | külastaja_perekonnanimi | tuba          | alg_aeg    | lõpp_aeg   |
| ------:|:-----------------:|:-----------------------:|:-------------:|:----------:|:----------:|
| 1      | Nimi              | Perekonnanimi           | perekond      | DD.MM.YYYY | DD.MM.YYYY |

***

## Info failide kohta

- HotellApplication on main ja sellega laaditakse algne vaade
- ViewFactory class-is on scene-ide laadimine
- Mode class-is kontrollib
- DB kaustas on kõik class-id millega ühendub andmebaasi

***

## Kuidas teha uus vaade

1. 
