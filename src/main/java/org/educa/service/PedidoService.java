package org.educa.service;

import org.educa.dao.PedidoDAO;
import org.educa.dao.PedidoDAOImpl;
import org.educa.entity.PedidoEntity;

import java.sql.SQLException;

public class PedidoService {

    private final PedidoDAO pedidoDAO = new PedidoDAOImpl();

    public void insertarPedido(PedidoEntity pedido) throws SQLException {
        pedidoDAO.insertarPedido(pedido);
    }
}
