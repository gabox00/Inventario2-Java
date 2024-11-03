package org.educa.dao;

import org.educa.entity.PedidoEntity;
import org.educa.entity.PedidoProductoEntity;
import org.educa.entity.ProductoEntity;
import org.educa.enums.PedidoEstadoEnum;
import org.educa.pool.ConnectionPool;
import org.educa.service.HistoricoPedidoService;
import org.educa.service.PedidoProductoService;
import org.educa.service.ProductoService;

import java.sql.*;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    private final PedidoProductoService pedidoProductoService = new PedidoProductoService();
    private final HistoricoPedidoService historicoPedidoService = new HistoricoPedidoService();
    private final ProductoService productoService = new ProductoService();

    @Override
    public void insertarPedido(PedidoEntity pedido) throws SQLException {
        List<PedidoProductoEntity> pedidoProductos = pedido.getPedidoProducto();
        if (pedidoProductos == null) {
            throw new IllegalArgumentException("El pedido debe tener un producto");
        }

        for (PedidoProductoEntity pedidoProducto : pedidoProductos) {
            ProductoEntity producto = pedidoProducto.getProducto();

            Connection connection = null;
            try {
                connection = ConnectionPool.getDataSource().getConnection();
                connection.setAutoCommit(false);

                int pedidoId = insert(connection, pedido);
                pedido.setId(pedidoId);

                pedidoProductoService.insert(connection, pedidoProducto);
                historicoPedidoService.insert(connection, pedido);
                productoService.markProductAsSold(connection, producto);

                connection.commit();

            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException rollbackEx) {
                        throw new SQLException("Error al hacer rollback", rollbackEx);
                    }
                }
                throw new SQLException("Error al crear el pedido, transacción revertida.", e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException closeEx) {
                        System.err.println("Error al cerrar la conexión: " + closeEx.getMessage());
                    }
                }
            }
        }
    }

    private int insert(Connection c, PedidoEntity pedido) throws SQLException {
        String queryPedido = "INSERT INTO pedido (id_cliente, estado, direccion, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement psPedido = c.prepareStatement(queryPedido, Statement.RETURN_GENERATED_KEYS)) {
            psPedido.setInt(1, pedido.getCliente().getId());
            psPedido.setInt(2, PedidoEstadoEnum.PREPARANDO.getEstado());
            psPedido.setInt(3, pedido.getDireccion().getId());
            psPedido.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            psPedido.executeUpdate();

            // Obtener el id del pedido generado
            try (ResultSet generatedKeys = psPedido.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating pedido failed, no ID obtained.");
                }
            }
        }
    }
}