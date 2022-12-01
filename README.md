# Hotell

### Teha andmepaas nimega hotell ja copy past-ida query-sse järgmised käsud

'''
CREATE SEQUENCE hotell_kliendi_seq;

CREATE TABLE kliendid (
kliendi_id bigint NOT NULL DEFAULT nextval('hotell_kliendi_seq'),
ees_nimi varchar(50) DEFAULT NULL,
pere_nimi varchar(50) DEFAULT NULL,
email varchar(50) DEFAULT NULL,
PRIMARY KEY (kliendi_id)
);
'''

### ViewFactory class-is on scene-ide laadimine
### Mode class-is kontrollib kas Stage on olemas ja kui ei ole siis tekitab
### HotellApplication on selleks laadida algne vaade
