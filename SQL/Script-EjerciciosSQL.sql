
/* Ejericicio 1 - Top clientes con mas facturas */
SELECT
c.id,
c.nombre,
c.apellido,
COUNT (f.id) as total_facturas
FROM public.cliente c 
JOIN public.factura f on f.cliente_id  = c.id 
group by c.id , c.nombre , c.apellido 
order by total_facturas desc;


/*Ejercicio 2 - Top clientes que mas gastaron */
SELECT
