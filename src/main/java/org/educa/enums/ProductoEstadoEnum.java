package org.educa.enums;

import lombok.Getter;

@Getter
public enum ProductoEstadoEnum {
    ALMACEN(1),
    VENDIDO(2),
    DEVUELTO(3),
    TARADO(4),
    DESCATALOGADO(5),
    ELIMINADO(6);

    private final int estado;

    ProductoEstadoEnum(int estado) {
        this.estado = estado;
    }

}
