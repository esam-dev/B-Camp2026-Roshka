/*Ejercicio 1 - Colombian Systems */

/*Creamos el primer esquema */
create schema colombian_systems_ej1;

/* Le marcamos el nombre del primer esquema donde vamos a crear las tablas */
set search_path to colombian_systems_ej1;

	
CREATE TABLE 	cliente(
RIF_CLIENTE varchar(20) PRIMARY KEY,
NOM_CLIENTE varchar (100) NOT NULL,
DIR_CLIENTE varchar (150),
CIUDAD_CLIENTE varchar (50),
TELEF_CLIENTE varchar(20)
);	


CREATE TABLE factura (
num_fac SERIAL PRIMARY KEY,
fecha_fac DATE NOT NULL,
rif_cliente varchar(20) NOT NULL,
CONSTRAIT fk_factura_cliente 
FOREIGN KEY (rif_cliente)
REFERENCES cliente (rif_cliente)
);

 CREATE TABLE colombian_systems_ej1.detalle_factura(
 num_fact int NOT NULL,
 cod_prod varchar(20) NOT NULL ,
 cant_prod int NOT NULL,
 PRIMARY KEY  (num_fact, cod_prod),
 CONSTRAINT fk_detalle_factura
     FOREIGN KEY (num_fact)
     REFERENCES colombian_systems_ej1.factura(num_fac),
 CONSTRAINT fk_detalle_producto 
     FOREIGN KEY (cod_prod)
     REFERENCES colombian_systems_ej1.producto(cod_prod)
 );
 
 
CREATE TABLE producto (
COD_PROD varchar(20) PRIMARY KEY,
DESP_PROD varchar(100) NOT NULL,
CATEGORIA varchar(50),
VALOR_UNIT numeric (10,2)
);

/* Prueba de inserciones en las tablas */

-- cliente
INSERT INTO cliente VALUES
('e123456','Elias','Cnel Sivio Garay','Fernando de la mora','0976424534'),
('m654321','Maria','Av. España','Luque','0981122334');

-- producto
INSERT INTO producto VALUES
('P001','Mouse Logitech','Perifericos',120000),
('P002','Teclado Redragon','Perifericos',180000);


-- factura
INSERT INTO factura (fecha_fac, rif_cliente)
VALUES ('2026-01-14', 'j123456');

-- detalle_factura
INSERT INTO detalle_factura (num_fact, cod_prod, cant_prod)
VALUES (1, 'P001', 2);


/* Ejercicio 2 - Empresa de envios (paquetes)*/

/*Creamos el segundo esquema */
create schema emp_envios_ej2;

/* Le marcamos el nombre del segundo esquema donde vamos a crear las tablas */
set search_path to emp_envios_ej2;

create table empresa_origen(
orgn_rif varchar(20) primary key,
orgn_nom varchar(100) not null,
orgn_act varchar(100),
orgn_ciudad varchar(60),
orgn_dir varchar(150),
orgn_tel varchar(20),
orgn_cel varchar(20)
);


create table destinatario(
dest_id   varchar(20) primary key,
dest_nom  varchar(100) not null,
dest_cod_ciudad varchar(10),
dest_ciudad  varchar(60),
dest_dir  varchar (150),
dest_tel varchar (60),
dest_km INTEGER 
);


create table paquete (
codigo  varchar(20) primary key,
tipo    varchar(50),
nombre  varchar(100),
descripcion  TEXT,
valr_flete  numeric(10,2) not null 
);


create table guia(
guia_no varchar(20) primary key ,
guia_fecha date not null ,
guia_hora time  not null,
orgn_rif varchar(20),
dest_id varchar(20),

constraint  fk_guia_origen 
   foreign key (orgn_rif)
   references empresa_origen(orgn_rif),
   
constraint fk_guia_destinatario
   foreign key (dest_id)
   references destinatario (dest_id)
);


create table detalle_guia(
guia_no varchar (20),
codigo varchar (20),

primary key (guia_no , codigo),

constraint  fk_detalle_guia 
   foreign key (guia_no)
   references guia (guia_no),
   
constraint  fk_detalle_paquete 
   foreign key (codigo)
   references paquete (codigo)
);

/* Prueba de inserciones en las tablas */


-- empresa origen 
INSERT INTO empresa_origen VALUES
('OR01','FastShip','Envios','Asuncion','Av Central','021123456','0981111111'),
('OR02','RapidEx','Logistica','Luque','Ruta 2','021654321','0982222222');

-- destinatario
INSERT INTO destinatario VALUES
('D01','Elias','47','Fernando de la Mora','Cnel Silvio Garay','0976424534',74),
('D02','Juan','49','Fernando de la Mora','Mbuyapey','0976567897',77);

-- paquete
INSERT INTO paquete VALUES
('PK01','Caja','Documentos','Sobres y papeles',50000),
('PK02','Caja','Electronica','Notebook',120000);

-- Guia
INSERT INTO guia VALUES
('G01','2026-01-15','10:30','OR01','D01'),
('G02','2026-01-16','14:00','OR02','D02');


-- detalle_guia
INSERT INTO detalle_guia VALUES
('G01','PK01'),
('G02','PK02');


/* Ejercicio 3 - Video Club  (alquiler de casssets)*/

/*Creamos el tercer esquema */
create schema video_club_ej3;

/* Le marcamos el nombre del tecer esquema donde vamos a crear las tablas */
set search_path to video_club_ej3;

create table cliente (
 cod_cliente SERIAL primary key,
 num_membresia varchar(20) not null, 
 nom_cliente varchar(100) not null,
 dir_cliente varchar (150),
 telef_cliente varchar (20)
);


create table pelicula (
cod_pelicula SERIAL primary key ,
titulo varchar(150) not null,
categoria varchar(50),
cod_tipo varchar (20)	
);


create table actor (
cod_actor SERIAL primary key,
nom_actor varchar(200)not null,
fechanac_actor DATE 

);

create table pelicula_actor (
cod_pelicula INT not null,
cod_actor INT not null,
primary key (cod_pelicula ,cod_actor),
foreign key (cod_pelicula) references  pelicula(cod_pelicula),
foreign key (cod_actor ) references actor(cod_actor)
);	

create table cassette(
cod_cassette SERIAL primary key,
formato varchar (30),
num_copias INT,
cod_pelicula INT not null,
foreign key (cod_pelicula) references pelicula(cod_pelicula)
);

create table alquiler(
cod_alquiler SERIAL primary key,
cod_cliente INT not null,
fecha_alquiler DATE not null,
fecha_dev  DATE,
valor_alquiler numeric(10,2),
foreign key (cod_cliente) references cliente(cod_cliente)
);


create table detalle_alquiler (
cod_alquiler INT not null,
cod_cassette INT not null,
cod_cant INT not null,
primary key (cod_alquiler, cod_cassette),
foreign key (cod_alquiler) references alquiler(cod_alquiler),
foreign key (cod_cassette) references cassette(cod_cassette)
);

/* Prueba de inserciones en las tablas */

INSERT INTO cliente (num_membresia, nom_cliente, dir_cliente, telef_cliente) VALUES
('M001','Elias Ortiz','Fernando de la Mora','0976424534'),
('M002','Juan Perez','San Lorenzo','0981122334');

INSERT INTO pelicula (titulo, categoria, cod_tipo) VALUES
('Matrix','Accion','DVD'),
('Titanic','Drama','VHS');

INSERT INTO actor (nom_actor, fechanac_actor) VALUES
('Keanu Reeves','1964-09-02'),
('Leonardo DiCaprio','1974-11-11');

INSERT INTO pelicula_actor VALUES
(1,1),
(2,2);

INSERT INTO cassette (formato, num_copias, cod_pelicula) VALUES
('DVD',5,1),
('VHS',3,2);

INSERT INTO alquiler (cod_cliente, fecha_alquiler, fecha_dev, valor_alquiler) VALUES
(1,'2026-01-10','2026-01-12',20000),
(2,'2026-01-11',NULL,20000);

INSERT INTO detalle_alquiler VALUES
(1,1,1),
(2,2,1);

select * from alquiler;


/* Ejercicio 4 - Prestamos de libros */

/*Creamos el cuarto esquema */
create schema prestamo_libro_ej4;

/* Le marcamos el nombre del cuarto  esquema donde vamos a crear las tablas */
set search_path to prestamo_libro_ej4;


create table colegio (
 cod_colegio SERIAL primary key,
 nom_colegio varchar (150) not null
);

create table profesor (
 cod_profesor SERIAL primary key,
 nom_profesor varchar(150) not null,
 cod_colegio INT not null, 
 foreign key (cod_colegio) references colegio(cod_colegio)
);


create table asignatura (
 cod_asignatura  SERIAL primary key ,
 nom_asignatura varchar (100) not null
);

create table curso (
cod_curso SERIAL primary key,
nom_curso varchar(50) not null,
aula varchar (20)
);


create table libro (
cod_libro SERIAL primary key ,
titulo varchar (50) not null,
editorial  varchar (100)
);

create table prestamo (
cod_prestamo SERIAL primary key, 
cod_profesor INT not null,	
cod_libro  INT not null,
cod_asignatura  INT not null, 
cod_curso INT not null,
fecha_prestamo DATE not null, 
foreign key (cod_profesor) references profesor (cod_profesor),
foreign key (cod_libro) references libro (cod_libro),
foreign key (cod_asignatura) references asignatura (cod_asignatura),
foreign key (cod_curso) references curso (cod_curso)
);

/* Prueba de inserciones en las tablas */

INSERT INTO profesor (cod_profesor, nom_profesor, cod_colegio)
VALUES (1, 'Juan Perez', 1 );

 
INSERT INTO libro (cod_libro, titulo, editorial)
VALUES (1, 'Programación en Java', 'McGraw-Hill');

INSERT INTO curso (cod_curso, nom_curso, aula)
VALUES (1, 'Licenciatura en Informática', 374 );

INSERT INTO asignatura (cod_asignatura, nom_asignatura)
VALUES (1, 'Programación I');

insert into prestamo (cod_prestamo)



/* Consultas de tablas */
	
SELECT * FROM prestamo;
SELECT * FROM profesor;
SELECT * FROM libro;
SELECT * FROM asignatura;
SELECT * FROM curso;




/* Ejercicio 5 - Reporte de matricula  */

/*Creamos el quinto esquema */
create schema reporte_matricula_ej5;

/* Le marcamos el nombre del quinto esquema donde vamos a crear las tablas */	
set search_path to reporte_matricula_ej5;


create table alumno( 
 cod_alumno SERIAL primary key,
 nom_alumno varchar(100) not null,
 especialidad varchar (100) not null 	
 );


create table curso (
cod_curso varchar(20) primary key ,
nom_curso varchar (100) not null,
seccion varchar(50) not null
);


create table docente (
cod_docente SERIAL primary key ,
nom_docente varchar(100) not null,
oficina varchar(50)
);

create table matricula (
cod_alumno INT not null,
cod_curso varchar (20) not null,
cod_docente INT not null,
primary key (cod_alumno, cod_curso),
foreign key (cod_alumno) references alumno(cod_alumno),
foreign key (cod_curso) references curso(cod_curso),
foreign key (cod_docente) references docente(cod_docente)
);

/* Prueba de inserciones en las tablas */




/* Ejercicio 6 - Biblioteca */

/*Creamos el sexto esquema */
create schema biblioteca_ej6;

/* Le marcamos el nombre del sexto esquema donde vamos a crear las tablas */
set search_path to biblioteca_ej6;


create table editorial (
id_editorial SERIAL primary  key,
nombre varchar(100) not null
);


create table autor(
id_autor SERIAL primary key,
nombre varchar (100) not null
);

create table libro (
cod_libro INT primary key , 
titulo varchar(50) not null, 
id_editorial INT not null,
id_autor INT not null, 

foreign key (id_autor) references autor (id_autor),
foreign key (id_editorial) references editorial (id_editorial)
);


create table lector (
id_lector  SERIAL primary key ,
nombre varchar (100) not null
);


create table prestamo (
id_prestamo SERIAL primary key ,
cod_libro INT not null, 
id_lector INT not null,
fecha_devolucion DATE not null ,
foreign key (cod_libro) references libro (cod_libro),
foreign key (id_lector) references lector (id_lector)
);

/* Prueba de inserciones en las tablas */



