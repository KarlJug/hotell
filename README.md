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
	broneeria_eesnimi varchar(50) DEFAULT NULL,
	broneeria_perekonnanimi varchar(50) DEFAULT NULL,
	PRIMARY KEY (id)
);


insert into public.toad(
	toa_num, toa_type, voodikohtade_arv, hind)
	VALUES (101, 3, 2, 300), (102, 3, 2, 300), (103, 2, 2, 500), (104, 3, 2, 300), (105, 3, 2, 300), (106, 2, 2, 500), (107, 4, 1, 200),
	    (108, 2, 2, 500), (109, 1, 3, 700), (110, 2, 2, 500), (111, 2, 2, 500), (112, 3, 2, 300), (113, 3, 2, 300), (114, 1, 3, 700),
	    (115, 1, 3, 700), (116, 4, 1, 200), (117, 1, 3, 700), (118, 3, 2, 300), (119, 1, 3, 700), (120, 3, 2, 300), (121, 3, 2, 300),
	    (122, 2, 2, 500), (123, 2, 2, 500), (124, 1, 3, 700), (125, 4, 1, 200), (126, 2, 2, 500), (127, 3, 2, 300), (128, 1, 3, 700),
	    (129, 3, 2, 300), (130, 1, 3, 700), (201, 1, 3, 700), (202, 4, 1, 200), (203, 2, 2, 500), (204, 4, 1, 200), (205, 3, 2, 300),
	    (206, 2, 2, 500), (207, 2, 2, 500), (208, 4, 1, 200), (209, 4, 1, 200), (210, 4, 1, 200), (211, 1, 3, 700), (212, 3, 2, 300),
	    (213, 1, 3, 700), (214, 3, 2, 300), (215, 3, 2, 300), (216, 3, 2, 300), (217, 1, 3, 700), (218, 4, 1, 200), (219, 2, 2, 500),
	    (220, 4, 1, 200), (221, 1, 3, 700), (222, 3, 2, 300), (223, 4, 1, 200), (224, 2, 2, 500), (225, 2, 2, 500), (226, 2, 2, 500),
	    (227, 3, 2, 300), (228, 4, 1, 200), (229, 2, 2, 500), (230, 4, 1, 200), (301, 3, 2, 300), (302, 1, 3, 700), (303, 1, 3, 700),
	    (304, 2, 2, 500), (305, 1, 3, 700), (306, 3, 2, 300), (307, 2, 2, 500), (308, 1, 3, 700), (309, 2, 2, 500), (310, 3, 2, 300),
	    (311, 1, 3, 700), (312, 2, 2, 500), (313, 1, 3, 700), (314, 4, 1, 200), (315, 4, 1, 200), (316, 3, 2, 300), (317, 2, 2, 500),
	    (318, 3, 2, 300), (319, 1, 3, 700), (320, 4, 1, 200), (321, 3, 2, 300), (322, 1, 3, 700), (323, 4, 1, 200), (324, 1, 3, 700),
	    (325, 3, 2, 300), (326, 3, 2, 300), (327, 1, 3, 700), (328, 2, 2, 500), (329, 2, 2, 500), (330, 1, 3, 700);
```

&#8595; Mis tekitab sellise tabeli &#8595;

| id	 | toa_num       | voodikohtade_arv | hind | broneeria_eesnimi | broneeria_perekonnanimi |
| ------:|:-------------:|-----------------:|-----:|:-----------------:|:-----------------------:|
| 1      | Nimi          | 3                | 500  | Nimi	       | Perekonnanimi	         |

***

### Tekst broneeringud

```
CREATE SEQUENCE hotell_broneeringud_seq;

CREATE TABLE broneeringud (
	id bigint NOT NULL DEFAULT nextval('hotell_broneeringud_seq'),
	kÃ¼lastaja_eesnimi varchar(50) DEFAULT NULL,
	kÃ¼lastaja_perekonnanimi varchar(50) DEFAULT NULL,
	tuba integer DEFAULT NULL,
	alg_aeg date DEFAULT NULL,
	lÃµpp_aeg date DEFAULT NULL,
	PRIMARY KEY (id)
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

