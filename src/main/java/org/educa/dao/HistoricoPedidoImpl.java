package org.educa.dao;

import org.educa.entity.PedidoEntity;
import org.educa.enums.PedidoEstadoEnum;
import org.educa.util.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoricoPedidoImpl implements HistoricoPedidoDAO {
    public void insert(Connection c, PedidoEntity pedido) throws SQLException {
        String queryHistoricoPedido = "INSERT INTO historico_pedido (id_pedido, cambios, usu_mod, fec_mod) VALUES (?, ?, ?, ?)";
        try (PreparedStatement psHistoricoPedido = c.prepareStatement(queryHistoricoPedido)) {
            psHistoricoPedido.setInt(1, pedido.getId());
            psHistoricoPedido.setString(2, PedidoEstadoEnum.PREPARANDO.name());
            psHistoricoPedido.setString(3, Usuarios.USU_APP.getName());
            psHistoricoPedido.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            psHistoricoPedido.executeUpdate();
        }
    }
}
