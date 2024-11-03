package org.educa.enums;

import lombok.Getter;

@Getter
public enum PedidoEstadoEnum {
    PREPARANDO(1),
    LISTO(2),
    ENVIADO(3),
    REPARTO(4),
    ENTREGADO(5);

    private final int estado;

    PedidoEstadoEnum(int estado) {
        this.estado = estado;
    }

}
