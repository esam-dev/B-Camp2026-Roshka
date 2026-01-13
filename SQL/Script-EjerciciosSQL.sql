
/* Ejericicio 1 - Top clientes con mas facturas */
SELECT
c.id,
c.nombre,
c.apellido,
COUNT (f.id) AS total_facturas
FROM public.cliente c 
JOIN public.factura f ON f.cliente_id  = c.id 
GROUP BY c.id , c.nombre , c.apellido 
ORDER BY total_facturas desc
limit 10;


/*Ejercicio 2 - Top clientes que mas gastaron */
SELECT
   c.id,
   c.nombre,
   c.apellido,
   SUM(f.factura_tipo_id ) AS total_gastado
FROM cliente c  
JOIN factura f ON F.cliente_id  = c.id 
GROUP BY c.id , c.nombre , c.apellido 
ORDER BY  total_gastado  DESC
LIMIT 10;


/* Ejercicio 3 - Top monedas mas utilizadas */
SELECT 
   m.nombre  as moneda,
   COUNT(f.id) AS veces_usada
FROM factura f 
JOIN moneda m ON  m.id = f.moneda_id 
GROUP BY m.nombre
ORDER BY veces_usada desc
limit 10;


/* Ejercicio  4 - Top proveedor de productos */
SELECT 
   p.nombre AS proveedor, 
   COUNT(pr.id) AS total_productos 
FROM proveedor  p 
JOIN producto pr ON pr.proveedor_id  = p.id 
GROUP BY p.nombre 
ORDER BY total_productos DESC
LIMIT 5;


/* Ejercicio 5 - Productos mas vendidos */
select 
   p.nombre as producto,
    SUM(df.cantidad) as  total_vendido
from factura_detalle df 
join producto p on p.id = df.producto_id 
group by p.nombre 
order by total_vendido desc 
limit 5;


/* Ejercicio 6 - Productos menos vendidos */
select 
   p.nombre as producto, 
   MIN(df.cantidad) as  menos_vendido
   from factura_detalle df 
   join producto p  on p.id = df.producto_id 
   group by p.nombre 
   order by menos_vendido desc
   limit 10;
		

/* Ejercicio  7 - Consulta que muestre fecha de emisión de factura,
 * nombre y apellido del cliente, nombres de productos de esa factura, 
 * cantidades compradas, nombre de tipo de factura de una factura específica */ 
select 
 f.fecha_emision,
 c.nombre as cliente_nombre,
 c.apellido as cliente_apellido,				
 p.nombre  as producto,
 df.cantidad,
 tf.nombre as tipo_factura 
 from factura f 
 join cliente c on c.id = f.cliente_id 
 join factura_detalle df on df.factura_id = f.id 
 join producto p  on  p.id = df.producto_id
 join factura_tipo tf on tf.id  = f.factura_tipo_id 
 where f.id = 123; 


/* Ejercicio 8 - Monto de facturas ordenadas segun totales */
select 
   f.id  as factura_id,
   f.fecha_emision,
   f.cliente_id,
   f.total
   from  factura f
   order by f.total desc;



