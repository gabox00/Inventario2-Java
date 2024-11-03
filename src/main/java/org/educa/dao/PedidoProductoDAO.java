package org.educa.dao;

import org.educa.entity.PedidoProductoEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface PedidoProductoDAO {
    void insert(Connection c,  PedidoProductoEntity pedidoProducto) throws SQLException;
}
