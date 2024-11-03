package org.educa.dao;

import org.educa.entity.ProductoEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {

    List<ProductoEntity> getAllProducts() throws SQLException;
    List<ProductoEntity> findByName(ProductoEntity producto) throws SQLException;
    ProductoEntity getFirstProductoByNameTallaAndColor(String nombre, String talla, String color) throws SQLException;
    void markProductAsSold(Connection c, ProductoEntity producto) throws SQLException;
}
