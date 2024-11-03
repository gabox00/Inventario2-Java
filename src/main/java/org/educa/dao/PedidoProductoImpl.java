package org.educa.dao;

import org.educa.entity.PedidoProductoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoProductoImpl implements PedidoProductoDAO {
    @Override
    public void insert(Connection c, PedidoProductoEntity pedidoProducto) throws SQLException {
        String queryPedidoProducto = "INSERT INTO pedido_producto (id_pedido, id_producto) VALUES (?, ?)";
        try(PreparedStatement psPedidoProducto = c.prepareStatement(queryPedidoProducto)){
            psPedidoProducto.setInt(1, pedidoProducto.getPedido().getId());
            psPedidoProducto.setInt(2, pedidoProducto.getProducto().getId());
            psPedidoProducto.executeUpdate();
        }
    }
}
