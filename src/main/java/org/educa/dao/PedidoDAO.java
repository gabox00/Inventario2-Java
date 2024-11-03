package org.educa.dao;

import org.educa.entity.PedidoEntity;

import java.sql.SQLException;

public interface PedidoDAO {
    void insertarPedido(PedidoEntity pedido) throws SQLException;
}