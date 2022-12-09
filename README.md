# Hotell
1. ### [ SQL](https://github.com/KarlJug/hotel/blob/main/README.md#andmebaas)
	- [Kliendid](https://github.com/KarlJug/hotel/blob/main/README.md#teha-andmepaas-nimega-hotell-ja-copy-past-ida-query-sse-j%C3%A4rgmised-k%C3%A4sud)
	- [Toad](https://github.com/KarlJug/hotel/blob/main/README.md#tekst-toad)
	- [Broneeringud](https://github.com/KarlJug/hotel/blob/main/README.md#tekst-broneeringud)
	
2. ### [Info failide kohta](https://github.com/KarlJug/hotel/blob/main/README.md#info-failide-kohta-1)

3. ### [Kuidas teha uus vaade](https://github.com/KarlJug/hotel/blob/main/README.md#kuidas-teha-uus-vaade-1)

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
CREATE SEQUENCE hotell_toad_seq;

CREATE TABLE toad (
	id bigint NOT NULL DEFAULT nextval('hotell_toad_seq'),
	toa_num integer DEFAULT NULL,
	toa_type integer DEFAULT NULL,
	voodikohtade_arv integer DEFAULT NULL,
	hind integer DEFAULT NULL,
	broneeritud boolean DEFAULT FALSE,
	broneeria_eesnimi varchar(30) DEFAULT NULL,
	broneeria_perekonnanimi varchar(30) DEFAULT NULL,
	PRIMARY KEY (id)
);

-- genereerib mõned toad andmebaasi
insert into public.toad(
	toa_num, toa_type, voodikohtade_arv, hind)
	VALUES (101, 2, 2, 114), (102, 3, 2, 105), (103, 3, 2, 105), (104, 1, 3, 230), (105, 4, 1, 87), (106, 3, 2, 105), (107, 2, 2, 114),
	(108, 3, 2, 105), (109, 1, 3, 230), (110, 3, 2, 105), (111, 3, 2, 105), (112, 3, 2, 105), (113, 1, 3, 230), (114, 4, 1, 87),
	(115, 1, 3, 230), (116, 2, 2, 114), (117, 1, 3, 230), (118, 3, 2, 105), (119, 1, 3, 230), (120, 2, 2, 114), (121, 4, 1, 87),
	(122, 1, 3, 230), (123, 1, 3, 230), (124, 3, 2, 105), (125, 1, 3, 230), (126, 1, 3, 230), (127, 1, 3, 230), (128, 1, 3, 230),
	(129, 1, 3, 230), (130, 3, 2, 105), (201, 2, 2, 114), (202, 1, 3, 230), (203, 2, 2, 114), (204, 2, 2, 114), (205, 2, 2, 114),
	(206, 3, 2, 105), (207, 3, 2, 105), (208, 3, 2, 105), (209, 3, 2, 105), (210, 3, 2, 105), (211, 4, 1, 87), (212, 1, 3, 230),
	(213, 1, 3, 230), (214, 4, 1, 87), (215, 1, 3, 230), (216, 1, 3, 230), (217, 2, 2, 114), (218, 1, 3, 230), (219, 1, 3, 230),
	(220, 4, 1, 87), (221, 3, 2, 105), (222, 3, 2, 105), (223, 4, 1, 87), (224, 2, 2, 114), (225, 1, 3, 230), (226, 4, 1, 87),
	(227, 2, 2, 114), (228, 4, 1, 87), (229, 1, 3, 230), (230, 1, 3, 230), (301, 1, 3, 230), (302, 2, 2, 114), (303, 2, 2, 114),
	(304, 1, 3, 230), (305, 3, 2, 105), (306, 3, 2, 105), (307, 1, 3, 230), (308, 3, 2, 105), (309, 3, 2, 105), (310, 3, 2, 105),
	(311, 2, 2, 114), (312, 1, 3, 230), (313, 3, 2, 105), (314, 1, 3, 230), (315, 2, 2, 114), (316, 2, 2, 114), (317, 4, 1, 87),
	(318, 4, 1, 87), (319, 4, 1, 87), (320, 1, 3, 230), (321, 2, 2, 114), (322, 3, 2, 105), (323, 2, 2, 114), (324, 4, 1, 87),
	(325, 2, 2, 114), (326, 1, 3, 230), (327, 1, 3, 230), (328, 4, 1, 87), (329, 4, 1, 87), (330, 2, 2, 114);
```

&#8595; Mis tekitab sellise tabeli &#8595;

| id	 | toa_num       | toa_type | voodikohtade_arv | hind | broneeritud | broneeria_eesnimi | broneeria_perekonnanimi |
| ------:|:-------------:|---------:|-----------------:|-----:|:-----------:|:-----------------:|:-----------------------:|
| 1      | Nimi          |         2| 3                | 500  | false       | Nimi	        | Perekonnanimi           |

***

### Tekst broneeringud

```
CREATE SEQUENCE hotell_broneeringud_seq;

CREATE TABLE broneeringud (
	id bigint NOT NULL DEFAULT nextval('hotell_broneeringud_seq'),
	kulastaja_eesnimi varchar(30) DEFAULT NULL,
	kulastaja_perekonnanimi varchar(30) DEFAULT NULL,
	tuba integer DEFAULT NULL,
	alg_aeg date DEFAULT NULL,
	lopp_aeg date DEFAULT NULL,
	PRIMARY KEY (id)
);
```
&#8595; Mis tekitab sellise tabeli &#8595;

| id 	 | kulastaja_eesnimi | kulastaja_perekonnanimi | tuba          | alg_aeg    | lõpp_aeg   |
| ------:|:-----------------:|:-----------------------:|:-------------:|:----------:|:----------:|
| 1      | Nimi              | Perekonnanimi           | perekond      | DD.MM.YYYY | DD.MM.YYYY |

***

## Info failide kohta

- HotellApplication on main ja sellega laaditakse algne vaade
- ViewFactory class-is on scene-ide laadimine
- Mode class-is kontrollib
- DB kaustas on kõik class-id millega ühendub andmebaasi
- recours kaustas on
	- CSS
	- FXML
	- Icons
	- Images

***

## Kuidas teha uus vaade

1. Teha FXML fail ja sellele controller
2. ViewFactory class-ile lisada sellele uue vaate privat Anchorpane controller ja teha getter method
3. Controllerisse saab lisada functsioone selle vaate nuppudele

## Kuidas kasutada

1. kui andmebaasid on olemas, saab broneerida hotelli ruum nime, isikukoodiga ja emailiga.
4. Admin paneeli saab läbi all vasakul oleva logi sisse nuppu.
- Kasutaiaid pole hetkel, aga kui vajutad sisene siis saad admin paneeli
- admin paneelis saad valida näha broneeringuid, registreerituid kliente ja hotelli tubade kohta
### registreeritud klientide menüü
- registreeritud klientide menüüs on võimalik topelt klõpsuga või valida klient ja vajutada "muuda" nuppu, et muuta kliendi infot
- Kliendi saab kustutada kui vajutad kliendi lahtri peale ja kustuta nuppu

- Teistel menüüdel ei ole seda funktsionaalsist veel
