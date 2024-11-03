create table if not exists CLIENTE ( 
    id serial not null primary key,
    email varchar(200) not null,
    pass varchar(50) not null,
    dni varchar(9) not null,
    nombre varchar(100) not null,
    primer_apellido varchar(100) NOT null,
    segundo_apellido varchar(100),
    telefono varchar(15) not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp
);

create table if not exists DIRECCION ( 
    id serial not null primary key,
    id_cliente int not null,
    calle varchar(200) not null,
    ciudad varchar(50) not null,
    cp varchar(9) not null,
    pais varchar(20) not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp,
    CONSTRAINT FK_ID_CLIENTE_DIRECCION
    FOREIGN KEY (id_cliente)
    REFERENCES CLIENTE(id)
);

create table if not exists ESTADO_PEDIDO ( 
    id serial not null primary key,
    nombre varchar(100) not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp
);

create table if not exists PEDIDO ( 
    id serial not null primary key,
    id_cliente int not null,
    estado int not null,
    direccion int not null,
    fecha timestamp not null,
    CONSTRAINT FK_ID_CLIENTE_PEDIDO
    FOREIGN KEY (id_cliente)
    REFERENCES CLIENTE(id),
    CONSTRAINT FK_ESTADO_PEDIDO
    FOREIGN KEY (estado)
    REFERENCES ESTADO_PEDIDO(id),
    CONSTRAINT FK_DIRECCION_PEDIDO
    FOREIGN KEY (direccion)
    REFERENCES DIRECCION(id)
);

create table if not exists HISTORICO_PEDIDO ( 
    id serial not null primary key,
    id_pedido int not null,
    cambios text not null,
    usu_mod varchar(50) not null,
    fec_mod timestamp not null,
    CONSTRAINT FK_ID_PEDIDO_HISTORICO_PEDIDO
    FOREIGN KEY (id_pedido)
    REFERENCES PEDIDO(id)
);

create table if not exists ESTADO_PRODUCTO ( 
    id serial not null primary key,
    nombre varchar(100) not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp
);

create table if not exists COLECCION ( 
    id serial not null primary key,
    nombre varchar(100) not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp
);

create table if not exists TIPO_PRODUCTO ( 
    id serial not null primary key,
    nombre varchar(100) not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp
);

create table if not exists PRODUCTO (
    id serial not null primary key,
    codigo_barra varchar(13) not null,
    nombre varchar(100) not null,
    coleccion int not null,
    tipo int not null,
    talla varchar(3) not null,
    color varchar(20) not null,
    precio numeric(12,3) not null,
    descuento numeric(4,2),
    estado int not null,
    usu_cre varchar(50) not null,
    fec_cre timestamp not null,
    usu_mod varchar(50),
    fec_mod timestamp,
    CONSTRAINT FK_COLECCION_PRODUCTO
    FOREIGN KEY (coleccion)
    REFERENCES COLECCION(id),
    CONSTRAINT FK_ESTADO_PRODUCTO
    FOREIGN KEY (estado)
    REFERENCES ESTADO_PRODUCTO(id),
    CONSTRAINT FK_TIPO_PRODUCTO
    FOREIGN KEY (tipo)
    REFERENCES TIPO_PRODUCTO(id)
);


create table if not exists PEDIDO_PRODUCTO ( 
    id serial not null primary key,
    id_pedido int not null,
    id_producto int not null,
    CONSTRAINT FK_ID_PEDIDO_PEDIDO_PRODUCTO
    FOREIGN KEY (id_pedido)
    REFERENCES PEDIDO(id),
    CONSTRAINT FK_ID_PRODUCTO_PEDIDO_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO(id)
);



