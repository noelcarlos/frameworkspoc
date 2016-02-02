CREATE TABLE usuarios (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(30),
  mail VARCHAR(50)
);

CREATE TABLE lk_tipos_usos_viviendas (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_provincias (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_localizaciones_viviendas (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_tipos_construcciones (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_calidades_construcciones (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_tipologias_viviendas (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_opciones_personalizacion_seguro_vivienda (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);

CREATE TABLE lk_periodos_contrataciones (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(64)
);