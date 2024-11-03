package org.educa.dao;

import org.educa.entity.ColeccionEntity;
import org.educa.entity.EstadoProductoEntity;
import org.educa.entity.ProductoEntity;
import org.educa.entity.TipoProductoEntity;
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
        String query =
                "select p.id           as producto_id,\n" +
                        "       p.nombre       as producto_nombre,\n" +
                        "       p.codigo_barra as producto_codigo_barra,\n" +
                        "       p.talla        as producto_talla,\n" +
                        "       p.color        as producto_color,\n" +
                        "       p.precio       as producto_precio,\n" +
                        "       p.descuento    as producto_descuento,\n" +
                        "       p.usu_cre      as producto_usu_cre,\n" +
                        "       p.fec_cre      as producto_fec_cre,\n" +
                        "       p.usu_mod      as producto_usu_mod,\n" +
                        "       p.fec_mod      as producto_fec_mod,\n" +
                        "       c.id           as coleccion_id,\n" +
                        "       c.nombre       as coleccion_nombre,\n" +
                        "       c.usu_cre      as coleccion_usu_cre,\n" +
                        "       c.fec_cre      as coleccion_fec_cre,\n" +
                        "       c.usu_mod      as coleccion_usu_mod,\n" +
                        "       c.fec_mod      as coleccion_fec_mod,\n" +
                        "       ep.id          as estado_producto_id,\n" +
                        "       ep.nombre      as estado_producto_nombre,\n" +
                        "       ep.usu_cre     as estado_producto_usu_cre,\n" +
                        "       ep.fec_cre     as estado_producto_fec_cre,\n" +
                        "       ep.usu_mod     as estado_producto_usu_mod,\n" +
                        "       ep.fec_mod     as estado_producto_fec_mod,\n" +
                        "       tp.id          as tipo_producto_id,\n" +
                        "       tp.nombre      as tipo_producto_nombre,\n" +
                        "       tp.usu_cre     as tipo_producto_usu_cre,\n" +
                        "       tp.fec_cre     as tipo_producto_fec_cre,\n" +
                        "       tp.usu_mod     as tipo_producto_usu_mod,\n" +
                        "       tp.fec_mod     as tipo_producto_fec_mod\n" +
                        "from producto p\n" +
                        "         INNER JOIN coleccion c on c.id = p.coleccion\n" +
                        "         INNER JOIN estado_producto ep on ep.id = p.estado\n" +
                        "         INNER JOIN tipo_producto tp on p.tipo = tp.id\n" +
                        "WHERE p.estado = ?";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, ProductoEstadoEnum.ALMACEN.getEstado());
            try (ResultSet rs = ps.executeQuery()) {
                return getProducts(rs);
            }
        }
    }

    @Override
    public List<ProductoEntity> findByName(ProductoEntity producto) throws SQLException {
        String query =
                "select p.id           as producto_id,\n" +
                        "       p.nombre       as producto_nombre,\n" +
                        "       p.codigo_barra as producto_codigo_barra,\n" +
                        "       p.talla        as producto_talla,\n" +
                        "       p.color        as producto_color,\n" +
                        "       p.precio       as producto_precio,\n" +
                        "       p.descuento    as producto_descuento,\n" +
                        "       p.usu_cre      as producto_usu_cre,\n" +
                        "       p.fec_cre      as producto_fec_cre,\n" +
                        "       p.usu_mod      as producto_usu_mod,\n" +
                        "       p.fec_mod      as producto_fec_mod,\n" +
                        "       c.id           as coleccion_id,\n" +
                        "       c.nombre       as coleccion_nombre,\n" +
                        "       c.usu_cre      as coleccion_usu_cre,\n" +
                        "       c.fec_cre      as coleccion_fec_cre,\n" +
                        "       c.usu_mod      as coleccion_usu_mod,\n" +
                        "       c.fec_mod      as coleccion_fec_mod,\n" +
                        "       ep.id          as estado_producto_id,\n" +
                        "       ep.nombre      as estado_producto_nombre,\n" +
                        "       ep.usu_cre     as estado_producto_usu_cre,\n" +
                        "       ep.fec_cre     as estado_producto_fec_cre,\n" +
                        "       ep.usu_mod     as estado_producto_usu_mod,\n" +
                        "       ep.fec_mod     as estado_producto_fec_mod,\n" +
                        "       tp.id          as tipo_producto_id,\n" +
                        "       tp.nombre      as tipo_producto_nombre,\n" +
                        "       tp.usu_cre     as tipo_producto_usu_cre,\n" +
                        "       tp.fec_cre     as tipo_producto_fec_cre,\n" +
                        "       tp.usu_mod     as tipo_producto_usu_mod,\n" +
                        "       tp.fec_mod     as tipo_producto_fec_mod\n" +
                        "FROM producto p\n" +
                        "         INNER JOIN coleccion c on c.id = p.coleccion\n" +
                        "         INNER JOIN estado_producto ep on ep.id = p.estado\n" +
                        "         INNER JOIN tipo_producto tp on p.tipo = tp.id\n" +
                        "WHERE p.nombre = ?";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            try (ResultSet rs = ps.executeQuery()) {
                return getProducts(rs);
            }
        }
    }

    public ProductoEntity getFirstProductoByNameTallaAndColor(String nombre, String talla, String color) throws SQLException {
        String query =
                "select p.id           as producto_id,\n" +
                        "       p.nombre       as producto_nombre,\n" +
                        "       p.codigo_barra as producto_codigo_barra,\n" +
                        "       p.talla        as producto_talla,\n" +
                        "       p.color        as producto_color,\n" +
                        "       p.precio       as producto_precio,\n" +
                        "       p.descuento    as producto_descuento,\n" +
                        "       p.usu_cre      as producto_usu_cre,\n" +
                        "       p.fec_cre      as producto_fec_cre,\n" +
                        "       p.usu_mod      as producto_usu_mod,\n" +
                        "       p.fec_mod      as producto_fec_mod,\n" +
                        "       c.id           as coleccion_id,\n" +
                        "       c.nombre       as coleccion_nombre,\n" +
                        "       c.usu_cre      as coleccion_usu_cre,\n" +
                        "       c.fec_cre      as coleccion_fec_cre,\n" +
                        "       c.usu_mod      as coleccion_usu_mod,\n" +
                        "       c.fec_mod      as coleccion_fec_mod,\n" +
                        "       ep.id          as estado_producto_id,\n" +
                        "       ep.nombre      as estado_producto_nombre,\n" +
                        "       ep.usu_cre     as estado_producto_usu_cre,\n" +
                        "       ep.fec_cre     as estado_producto_fec_cre,\n" +
                        "       ep.usu_mod     as estado_producto_usu_mod,\n" +
                        "       ep.fec_mod     as estado_producto_fec_mod,\n" +
                        "       tp.id          as tipo_producto_id,\n" +
                        "       tp.nombre      as tipo_producto_nombre,\n" +
                        "       tp.usu_cre     as tipo_producto_usu_cre,\n" +
                        "       tp.fec_cre     as tipo_producto_fec_cre,\n" +
                        "       tp.usu_mod     as tipo_producto_usu_mod,\n" +
                        "       tp.fec_mod     as tipo_producto_fec_mod\n" +
                        "FROM producto p\n" +
                        "         INNER JOIN coleccion c on c.id = p.coleccion\n" +
                        "         INNER JOIN estado_producto ep on ep.id = p.estado\n" +
                        "         INNER JOIN tipo_producto tp on p.tipo = tp.id\n" +
                        "WHERE p.nombre = ? AND p.talla = ? AND p.color = ? LIMIT 1";

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombre);
            ps.setString(2, talla);
            ps.setString(3, color);

            try (ResultSet rs = ps.executeQuery()) {
                List<ProductoEntity> productos = getProducts(rs);
                return productos.isEmpty() ? null : productos.getFirst();
            }
        }
    }

    private List<ProductoEntity> getProducts(ResultSet rs) throws SQLException {
        List<ProductoEntity> products = new ArrayList<>();
        while (rs.next()) {
            ProductoEntity producto = new ProductoEntity();
            producto.setId(rs.getInt("producto_id"));
            producto.setNombre(rs.getString("producto_nombre"));
            producto.setCodigoBarra(rs.getString("producto_codigo_barra"));
            producto.setTalla(rs.getString("producto_talla"));
            producto.setColor(rs.getString("producto_color"));

            BigDecimal precio = rs.getBigDecimal("producto_precio") != null ? rs.getBigDecimal("producto_precio") : BigDecimal.ZERO;
            producto.setPrecio(precio);

            BigDecimal descuento = rs.getBigDecimal("producto_descuento") != null ? rs.getBigDecimal("producto_descuento") : BigDecimal.ZERO;
            producto.setDescuento(descuento);

            if(descuento.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal precioFinal = precio.subtract(precio.multiply(descuento.divide(BigDecimal.valueOf(100))));
                producto.setPrecioFinal(precioFinal);
            } else {
                producto.setPrecioFinal(precio);
            }

            producto.setUsuCre(rs.getString("producto_usu_cre"));
            producto.setFecCre(rs.getTimestamp("producto_fec_cre"));
            producto.setUsuMod(rs.getString("producto_usu_mod"));
            producto.setFecMod(rs.getTimestamp("producto_fec_mod"));

            EstadoProductoEntity estadoProducto = new EstadoProductoEntity();
            estadoProducto.setId(rs.getInt("estado_producto_id"));
            estadoProducto.setNombre(rs.getString("estado_producto_nombre"));
            estadoProducto.setUsuCre(rs.getString("estado_producto_usu_cre"));
            Timestamp estadoFecCre = rs.getTimestamp("estado_producto_fec_cre");
            estadoProducto.setFecCre(estadoFecCre != null ? estadoFecCre.toLocalDateTime().toLocalDate() : null);
            estadoProducto.setUsuMod(rs.getString("estado_producto_usu_mod"));
            Timestamp estadoFecMod = rs.getTimestamp("estado_producto_fec_mod");
            estadoProducto.setFecMod(estadoFecMod != null ? estadoFecMod.toLocalDateTime().toLocalDate() : null);
            producto.setEstadoProducto(estadoProducto);

            ColeccionEntity coleccion = new ColeccionEntity();
            coleccion.setId(rs.getInt("coleccion_id"));
            coleccion.setNombre(rs.getString("coleccion_nombre"));
            coleccion.setUsuCre(rs.getString("coleccion_usu_cre"));
            Timestamp coleccionFecCre = rs.getTimestamp("coleccion_fec_cre");
            coleccion.setFecCre(coleccionFecCre != null ? coleccionFecCre.toLocalDateTime().toLocalDate() : null);
            coleccion.setUsuMod(rs.getString("coleccion_usu_mod"));
            Timestamp coleccionFecMod = rs.getTimestamp("coleccion_fec_mod");
            coleccion.setFecMod(coleccionFecMod != null ? coleccionFecMod.toLocalDateTime().toLocalDate() : null);
            producto.setColeccionBean(coleccion);

            TipoProductoEntity tipoProducto = new TipoProductoEntity();
            tipoProducto.setId(rs.getInt("tipo_producto_id"));
            tipoProducto.setNombre(rs.getString("tipo_producto_nombre"));
            tipoProducto.setUsuCre(rs.getString("tipo_producto_usu_cre"));
            Timestamp tipoFecCre = rs.getTimestamp("tipo_producto_fec_cre");
            tipoProducto.setFecCre(tipoFecCre != null ? tipoFecCre.toLocalDateTime().toLocalDate() : null);
            tipoProducto.setUsuMod(rs.getString("tipo_producto_usu_mod"));
            Timestamp tipoFecMod = rs.getTimestamp("tipo_producto_fec_mod");
            tipoProducto.setFecMod(tipoFecMod != null ? tipoFecMod.toLocalDateTime().toLocalDate() : null);
            producto.setTipoProducto(tipoProducto);

            products.add(producto);}
        return products;
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
