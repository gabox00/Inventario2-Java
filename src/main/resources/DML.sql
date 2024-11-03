insert into estado_producto(nombre, usu_cre, fec_cre) values
('Almacen', 'AUTO', current_timestamp),
('Vendido', 'AUTO', current_timestamp),
('Devuelto', 'AUTO', current_timestamp),
('Tarado', 'AUTO', current_timestamp),
('Descatalogado', 'AUTO', current_timestamp),
('Eliminado', 'AUTO', current_timestamp);

insert into estado_pedido(nombre, usu_cre, fec_cre) values
('Preparando', 'AUTO', current_timestamp),
('Listo', 'AUTO', current_timestamp),
('Enviado', 'AUTO', current_timestamp),
('Reparto', 'AUTO', current_timestamp),
('Entregado', 'AUTO', current_timestamp);

insert into tipo_producto(nombre, usu_cre, fec_cre) values
('Zapatos', 'AUTO', current_timestamp),
('Pantalones', 'AUTO', current_timestamp),
('Camisetas', 'AUTO', current_timestamp),
('Faldas', 'AUTO', current_timestamp),
('Vestidos', 'AUTO', current_timestamp),
('Medias', 'AUTO', current_timestamp),
('Bolsos', 'AUTO', current_timestamp);

insert into coleccion(nombre, usu_cre, fec_cre) values
('Snoopy', 'AUTO', current_timestamp),
('Friends', 'AUTO', current_timestamp),
('Scottish', 'AUTO', current_timestamp),
('Glam', 'AUTO', current_timestamp);

--Camiseta Snoopy

do $$
begin
    for r in 1..100 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400132', 'Camiseta Snoopy', 1, 3, 'M', 'Blanca', 8.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..100 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400131', 'Camiseta Snoopy', 1, 3, 'S', 'Blanca', 8.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..100 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400133', 'Camiseta Snoopy', 1, 3, 'L', 'Blanca', 8.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

--Camiseta Friends

do $$
begin
    for r in 1..100 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400232', 'Camiseta Friends', 2, 3, 'M', 'Negra', 8.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..100 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400231', 'Camiseta Friends', 2, 3, 'S', 'Negra', 8.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..100 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400233', 'Camiseta Friends', 2, 3, 'L', 'Negra', 8.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

--Falda Scottish

do $$
begin
    for r in 1..80 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400342', 'Falda Scottish', 3, 4, 'M', 'Roja', 10.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..80 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400341', 'Falda Scottish', 3, 4, 'S', 'Roja', 10.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..80 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400343', 'Falda Scottish', 3, 4, 'L', 'Roja', 10.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

--Vestido Fiesta Glam

do $$
begin
    for r in 1..80 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400452', 'Vestido Fiesta Glam', 4, 5, 'M', 'Negro', 70.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..80 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400451', 'Vestido Fiesta Glam', 4, 5, 'S', 'Negro', 70.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;

do $$
begin
    for r in 1..80 loop
        insert into producto(codigo_barra, nombre, coleccion, tipo, talla, color, precio, descuento, estado, usu_cre, fec_cre) values
        ('840123400453', 'Vestido Fiesta Glam', 4, 5, 'L', 'Negro', 70.00, null, 1, 'AUTO', current_timestamp);
    end loop;
end;
$$;
