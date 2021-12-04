CREATE DATABASE bd_restaurante CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_general_ci';

USE bd_restaurante;

CREATE TABLE rol (
	id_rol INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	CONSTRAINT pk_id_rol PRIMARY KEY(id_rol)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';


-- IMPORTANTE
CREATE TABLE usuario (
	id_usuario INT,
	ci INT UNIQUE,
	nombres VARCHAR(50) NOT NULL,
	apellido_paterno VARCHAR(50),
	apellido_materno VARCHAR(50),
	usuario VARCHAR(100),
	contrasenia VARCHAR(50) NOT NULL,
	id_rol INT,
	CONSTRAINT pk_id_usuario PRIMARY KEY(id_usuario),
	CONSTRAINT fk_id_rol FOREIGN KEY(id_rol) REFERENCES rol(id_rol)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';

CREATE OR REPLACE SEQUENCE seq_usuario
	START WITH 101
	INCREMENT BY 1
	MINVALUE 101;

DELIMITER //
CREATE OR REPLACE TRIGGER disp_usuario
	BEFORE INSERT ON usuario
	FOR EACH ROW
BEGIN
	SET NEW.id_usuario = NEXTVAL(seq_usuario);
	SET NEW.usuario = LOWER(CONCAT(NEW.nombres, '_', CONVERT(NEW.ci, VARCHAR(10))));
	SET NEW.contrasenia = MD5(CONVERT(NEW.ci, VARCHAR(50)));
END //
DELIMITER ;

CREATE TABLE mesas (
	id_mesa INT AUTO_INCREMENT,
	nombre VARCHAR(40),
	encargado INT,
	ocupada INT(1) CHECK(ocupada IN(0, 1)),
	CONSTRAINT pk_id_mesa PRIMARY KEY(id_mesa),
	CONSTRAINT fk_encargado FOREIGN KEY(encargado) REFERENCES usuario(id_usuario)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';


CREATE TABLE categoria (
	id_categoria INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	tipo VARCHAR(20),
	CONSTRAINT pk_id_categoria PRIMARY KEY(id_categoria)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';

CREATE TABLE comentarios (
	id_comentario INT AUTO_INCREMENT,
	descripcion VARCHAR(200),
	id_categoria INT NOT NULL,
	CONSTRAINT pk_id_comentario PRIMARY KEY(id_comentario),
	CONSTRAINT fk_id_categoria FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';

CREATE TABLE producto (
	id_producto VARCHAR(9),
	nombre VARCHAR(55),
	precio FLOAT(7, 2) CHECK(precio > 0),
	id_categoria INT,
	CONSTRAINT pk_id_producto PRIMARY KEY(id_producto),
	CONSTRAINT fk_id_categoria_prod FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';

CREATE OR REPLACE SEQUENCE seq_producto
	START WITH 1001
	INCREMENT BY 1
	MINVALUE 1001;

DELIMITER //
CREATE OR REPLACE TRIGGER disp_producto
    BEFORE INSERT ON producto
	FOR EACH ROW
BEGIN
	SET NEW.id_producto = CONCAT('COM-', LPAD(CONVERT(NEXTVAL(seq_producto), VARCHAR(5)), 5, '0'));
END //
DELIMITER ;


-- FACTURA
CREATE TABLE servicio (
	id_servicio INT AUTO_INCREMENT,
	fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
	id_mesa INT,
	id_encargado INT,
	total FLOAT(10, 2) DEFAULT 0 NOT NULL,
	cancelada INT(1) CHECK(cancelada IN(0, 1)),
	CONSTRAINT pk_id_servicio PRIMARY KEY(id_servicio),
	CONSTRAINT fk_id_mesa FOREIGN KEY(id_mesa) REFERENCES mesas(id_mesa),
	CONSTRAINT fk_id_mesero_usuario FOREIGN KEY(id_encargado) REFERENCES usuario(id_usuario)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';

CREATE TABLE detalle_servicio (
	id_detalle_servicio INT AUTO_INCREMENT,
	id_servicio INT,
	hora TIME DEFAULT CURRENT_TIME,
	id_producto VARCHAR(9),
	cantidad INT DEFAULT 0,
	subtotal FLOAT(10, 2) DEFAULT 0,
	comentario VARCHAR(100),
	estado INT(1) CHECK(estado IN(0, 1)),
	CONSTRAINT pk_id_detalle_servicio PRIMARY KEY(id_detalle_servicio),
	CONSTRAINT fk_id_producto FOREIGN KEY(id_producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_id_servicio FOREIGN KEY(id_servicio) REFERENCES servicio(id_servicio)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';

DELIMITER //
CREATE OR REPLACE TRIGGER dis_actualiza_subtotal
	BEFORE INSERT ON detalle_servicio
	FOR EACH ROW
BEGIN
	SET NEW.subtotal = NEW.cantidad * (SELECT precio FROM producto WHERE id_producto = NEW.id_producto);
END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER dis_actualiza_total 
	AFTER INSERT ON detalle_servicio
	FOR EACH ROW
BEGIN
	  UPDATE servicio SET total = total + (SELECT SUM(subtotal) FROM detalle_servicio WHERE id_detalle_servicio = NEW.id_detalle_servicio);
END //
DELIMITER ;


CREATE TABLE servicio_temporal (
	id_servicio_temporal INT AUTO_INCREMENT,
	hora TIME DEFAULT CURRENT_TIME,
	id_producto VARCHAR(9),
	cantidad INT DEFAULT 0,
	comentario VARCHAR(100),
	CONSTRAINT pk_id_detalle_servicio PRIMARY KEY(id_detalle_servicio),
	CONSTRAINT fk_id_producto FOREIGN KEY(id_producto) REFERENCES producto(id_producto)
) ENGINE=innoDB DEFAULT CHARSET='utf8mb4';