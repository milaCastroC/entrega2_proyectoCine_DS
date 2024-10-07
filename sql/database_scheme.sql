CREATE DATABASE db_cine2;

USE db_cine2;

CREATE TABLE rol(
	id_rol INT AUTO_INCREMENT PRIMARY KEY,
	descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE usuario(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	correo VARCHAR(100) UNIQUE KEY NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	telefono VARCHAR(10) NOT NULL,
	id_rol INT NOT NULL,
	FOREIGN KEY(id_rol) REFERENCES rol(id_rol) ON DELETE CASCADE,
	contrasena VARCHAR(250) NOT NULL
);

CREATE TABLE pelicula(
	id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
	codigo_pelicula VARCHAR(20) UNIQUE KEY NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	fecha_estreno DATE NOT NULL,
	director VARCHAR(100) NOT NULL,
	duracion_minutos INT NOT NULL,
	genero VARCHAR(100) NOT NULL,
	sinopsis TEXT NOT NULL
);

CREATE TABLE sala(
	id_sala INT AUTO_INCREMENT PRIMARY KEY,
	codigo_sala VARCHAR(20) UNIQUE KEY NOT NULL,
	capacidad INT NOT NULL,
	nombre_sala VARCHAR(50)	
);
 
CREATE TABLE silla(
	id_silla INT AUTO_INCREMENT PRIMARY KEY,
	numero INT NOT NULL,
	id_sala INT NOT NULL,
	FOREIGN KEY(id_sala) REFERENCES sala(id_sala) ON DELETE CASCADE
);

CREATE TABLE funcion(
	id_funcion INT AUTO_INCREMENT PRIMARY KEY,
	codigo_funcion VARCHAR(20) UNIQUE KEY NOT NULL,
	id_sala INT NOT NULL,
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	id_pelicula INT NOT NULL,
	FOREIGN KEY(id_sala) REFERENCES sala(id_sala) ON DELETE CASCADE,
	FOREIGN KEY(id_pelicula) REFERENCES pelicula(id_pelicula) ON DELETE CASCADE
);
 
CREATE TABLE comentario (
   id_comentario INT AUTO_INCREMENT PRIMARY KEY,
   contenido TEXT NOT NULL,
   id_usuario INT NOT NULL,
   FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
   fecha DATETIME NOT NULL,
   id_pelicula INT NOT NULL,
   FOREIGN KEY(id_pelicula) REFERENCES pelicula(id_pelicula) ON DELETE CASCADE,
   tipo VARCHAR(20) NOT NULL 
);

-- CREATE TABLE comentario_pelicula (
-- 	id_comentario_pelicula INT AUTO_INCREMENT PRIMARY KEY,
-- 	id_comentario INT NOT NULL,
-- 	FOREIGN KEY(id_comentario) REFERENCES comentario(id_comentario) ON DELETE CASCADE,
-- 	id_pelicula INT NOT NULL,
-- 	FOREIGN KEY(id_pelicula) REFERENCES pelicula(id_pelicula) ON DELETE CASCADE
-- ); 
 	
CREATE TABLE compra (
   id_compra INT AUTO_INCREMENT PRIMARY KEY,
   fecha DATE NOT NULL,
   id_usuario INT,
   FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
   cantidad_boletas INT NOT NULL
);


CREATE TABLE boleta (
   id_boleta INT AUTO_INCREMENT PRIMARY KEY,
   id_funcion INT NOT NULL,
   id_silla INT NOT NULL,
   id_compra INT NOT NULL,
   precio DECIMAL NOT NULL,
   FOREIGN KEY(id_funcion) REFERENCES funcion(id_funcion) ON DELETE CASCADE,
   FOREIGN KEY(id_silla) REFERENCES silla(id_silla) ON DELETE CASCADE,
   FOREIGN KEY(id_compra) REFERENCES compra(id_compra) ON DELETE CASCADE    
);
 

INSERT INTO rol (descripcion) VALUES ('Administrador');
INSERT INTO rol (descripcion) VALUES ('Cliente');


-- DROP DATABASE db_cine2;

