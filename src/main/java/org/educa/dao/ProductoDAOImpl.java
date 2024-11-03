package org.educa.dao;

import org.educa.entity.ProductoEntity;
import org.educa.enums.ProductoEstadoEnum;
import org.educa.pool.ConnectionPool;
import org.educa.util.Usuarios;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    @Override
    public List<ProductoEntity> getAllProducts() throws SQLException {
        String query = "SELECT nombre, precio, descuento FROM producto GROUP BY nombre, precio, descuento";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                List<ProductoEntity> products = new ArrayList<>();
                while (rs.next()) {
                    ProductoEntity producto = new ProductoEntity();
                    producto.setNombre(rs.getString("nombre"));

                    BigDecimal precio = rs.getBigDecimal("precio") != null ? rs.getBigDecimal("precio") : BigDecimal.ZERO;
                    producto.setPrecio(precio);

                    BigDecimal descuento = rs.getBigDecimal("descuento") != null ? rs.getBigDecimal("descuento") : BigDecimal.ZERO;
                    producto.setDescuento(descuento);

                    if(descuento.compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal precioFinal = precio.subtract(precio.multiply(descuento.divide(BigDecimal.valueOf(100))));
                        producto.setPrecioFinal(precioFinal);
                    } else {
                        producto.setPrecioFinal(precio);
                    }

                    products.add(producto);
                }
                return products;
            }
        }
    }

    @Override
    public List<ProductoEntity> findByName(ProductoEntity producto) throws SQLException {
        String query = "SELECT nombre, talla, color FROM producto WHERE nombre = ? GROUP BY nombre, talla, color";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            try (ResultSet rs = ps.executeQuery()) {
                List<ProductoEntity> products = new ArrayList<>();
                while (rs.next()) {
                    ProductoEntity p = new ProductoEntity();
                    p.setNombre(rs.getString("nombre"));
                    p.setTalla(rs.getString("talla"));
                    p.setColor(rs.getString("color"));
                    products.add(p);
                }
                return products;
            }
        }
    }

    public ProductoEntity getFirstProductoByNameTallaAndColor(String nombre, String talla, String color) throws SQLException {
        String query = "SELECT id FROM producto WHERE nombre = ? AND talla = ? AND color = ? AND estado = ? LIMIT 1";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombre);
            ps.setString(2, talla);
            ps.setString(3, color);
            ps.setInt(4, ProductoEstadoEnum.ALMACEN.getEstado());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductoEntity producto = new ProductoEntity();
                    producto.setId(rs.getInt("id"));
                    return producto;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void markProductAsSold(Connection c, ProductoEntity producto) throws SQLException {
        String query = "UPDATE producto SET estado = ?, usu_mod = ?, fec_mod = NOW() WHERE id = ?";
        try (PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, ProductoEstadoEnum.VENDIDO.getEstado());
            ps.setString(2, Usuarios.USU_APP.getName());
            ps.setInt(3, producto.getId());
            ps.executeUpdate();
        }
    }
}
