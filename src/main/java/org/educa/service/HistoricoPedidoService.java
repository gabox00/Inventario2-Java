package org.educa.service;

import org.educa.dao.HistoricoPedidoDAO;
import org.educa.dao.HistoricoPedidoImpl;
import org.educa.entity.PedidoEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class HistoricoPedidoService {
    private final HistoricoPedidoDAO historicoPedidoDAO = new HistoricoPedidoImpl();

    public void insert(Connection c, PedidoEntity pedido) throws SQLException {
        historicoPedidoDAO.insert(c, pedido);
    }
}
