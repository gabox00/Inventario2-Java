package org.educa.dao;

import org.educa.entity.PedidoEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface HistoricoPedidoDAO {
    void insert(Connection c,  PedidoEntity pedido) throws SQLException;
}
