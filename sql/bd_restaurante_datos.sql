USE bd_restaurante;

-- Inserción en rol
INSERT INTO rol(id_rol, nombre) VALUES(1, 'Administrador');
INSERT INTO rol(id_rol, nombre) VALUES(2, 'Cajero');
INSERT INTO rol(id_rol, nombre) VALUES(3, 'Cocinero');
INSERT INTO rol(id_rol, nombre) VALUES(4, 'Invitado');

-- Inserción en Usuarios
INSERT INTO usuario(id_usuario, ci, nombres, apellido_paterno, apellido_materno, id_rol) VALUES(101, 34564, 'Alan', 'Brito', 'Delgado', 1);
INSERT INTO usuario(id_usuario, ci, nombres, apellido_paterno, apellido_materno, id_rol) VALUES(102, 34536, 'Armando', 'Paredes', 'Pinto', 2);
INSERT INTO usuario(id_usuario, ci, nombres, apellido_paterno, apellido_materno, id_rol) VALUES(103, 78348, 'Lola', 'Mento', 'Ramirez', 3);
INSERT INTO usuario(id_usuario, ci, nombres, apellido_paterno, apellido_materno, id_rol) VALUES(104, 89123, 'Dolores', 'Fuertes', 'de Barriga', 3);
INSERT INTO usuario(id_usuario, ci, nombres, apellido_paterno, apellido_materno, id_rol) VALUES(105, 24324, 'Román', 'Calavera', 'Calva', 4);

-- Inserción en mesas
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(1, 'Mesa 1', 102, 0);
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(2, 'Mesa 2', 102, 0);
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(3, 'Mesa 3', 102, 0);
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(4, 'Mesa 4', 102, 0);
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(5, 'Mesa 5', 102, 0);
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(6, 'Mesa 6', 102, 0);
INSERT INTO mesas(id_mesa, nombre, encargado, ocupada) VALUES(7, 'Mesa 7', 102, 0);

-- Inserción en categoria 
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(1, 'Aguas', 'Barra');
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(2, 'Refrescos', 'Barra');
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(3, 'Desayunos', 'Cocina');
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(4, 'Comidas', 'Cocina');
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(5, 'Cenas', 'Cocina');
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(6, 'A la carta', 'Cocina');
INSERT INTO categoria(id_categoria, nombre, tipo) VALUES(7, 'Postres', 'Atención');

-- Inserción en comentarios
INSERT INTO comentarios(id_comentario, descripcion, id_categoria) VALUES(1, 'En vaso', 2);
INSERT INTO comentarios(id_comentario, descripcion, id_categoria) VALUES(2, 'En hielo', 2);
INSERT INTO comentarios(id_comentario, descripcion, id_categoria) VALUES(3, 'En vaso', 3);
INSERT INTO comentarios(id_comentario, descripcion, id_categoria) VALUES(4, 'Con hielo', 3);

-- Inserción en producto
INSERT INTO producto(id_producto, nombre, precio, id_categoria) VALUES('COM-01001', 'Pollo a la Broaster', 10, 4);
INSERT INTO producto(id_producto, nombre, precio, id_categoria) VALUES('COM-01002', 'Pollo Spiedo', 12, 4);
INSERT INTO producto(id_producto, nombre, precio, id_categoria) VALUES('COM-01003', 'Papas Fritas', 5, 7);
INSERT INTO producto(id_producto, nombre, precio, id_categoria) VALUES('COM-01004', 'Coca Cola NR 3 Litros', 13, 2);
INSERT INTO producto(id_producto, nombre, precio, id_categoria) VALUES('COM-01005', 'Coca Cola NR 2 Litros', 10, 2);
INSERT INTO producto(id_producto, nombre, precio, id_categoria) VALUES('COM-01006', 'Cuellitos', 5, 7);

-- una venta de prueba
INSERT INTO servicio(id_servicio, fecha_hora, id_mesa, id_encargado, cancelada) VALUES(1, '2021-08-03 10:14:13', 2, 102, 0);

INSERT INTO detalle_servicio(id_detalle_servicio, id_servicio, hora, id_producto, cantidad) VALUES(1, 1, '10:15:12', 'COM-01002', 3);
INSERT INTO detalle_servicio(id_detalle_servicio, id_servicio, hora, id_producto, cantidad) VALUES(2, 1, '10:16:00', 'COM-01001', 2);
INSERT INTO detalle_servicio(id_detalle_servicio, id_servicio, hora, id_producto, cantidad) VALUES(3, 1, '10:16:05', 'COM-01003', 5);
COMMIT;

