package org.educa.service;

import org.educa.dao.PedidoProductoDAO;
import org.educa.dao.PedidoProductoImpl;
import org.educa.entity.PedidoProductoEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class PedidoProductoService {

    private final PedidoProductoDAO pedidoProductoDAO = new PedidoProductoImpl();

    public void insert(Connection c, PedidoProductoEntity pedidoProducto) throws SQLException {
        pedidoProductoDAO.insert(c, pedidoProducto);
    }

}
