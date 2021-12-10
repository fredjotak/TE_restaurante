-- seleccionar un usuario
SELECT id_usuario, ci, nombres, apellido_paterno, apellido_materno, usuario, u.id_rol, r.nombre nombre_rol
FROM usuario u LEFT JOIN rol r ON(u.id_rol=r.id_rol)

-- 
SELECT id_servicio, DATE_FORMAT(fecha_hora, '%Y-%m-%d') fecha, DATE_FORMAT(fecha_hora, '%h:%i:%s') hora, id_mesa, id_encargado, cancelada, total
FROM servicio WHERE id_servicio = 1;

SELECT id_servicio, DATE_FORMAT(fecha_hora, '%Y-%m-%d') fecha, DATE_FORMAT(fecha_hora, '%h:%i:%s') hora, s.id_mesa, m.nombre, id_encargado, CONCAT(nombres, ' ', apellido_paterno, ' ', apellido_materno) encargado, cancelada, total
FROM servicio s LEFT JOIN mesas m ON(s.id_mesa=m.id_mesa) LEFT JOIN usuario ON(id_usuario=id_encargado);

-- detalle servicio
SELECT id_detalle_servicio, id_servicio, hora, d.id_producto, nombre, cantidad, precio, subtotal, comentario, estado
FROM detalle_servicio d JOIN producto p ON(d.id_producto = p.id_producto) WHERE id_servicio = 1;


-- buscar por nombre 
SELECT id_usuario, ci, nombres, apellido_paterno, apellido_materno, usuario, u.id_rol, r.nombre nombre_rol
FROM usuario u LEFT JOIN rol r ON(u.id_rol=r.id_rol)
WHERE ci LIKE '%45%';

SELECT id_usuario, ci, nombres, apellido_paterno, apellido_materno, usuario, u.id_rol, r.nombre nombre_rol
FROM usuario u LEFT JOIN rol r ON(u.id_rol=r.id_rol)
WHERE ci LIKE '%45%' OR nombres LIKE '%45%';