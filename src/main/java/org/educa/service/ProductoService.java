package org.educa.service;

import org.educa.dao.ProductoDAO;
import org.educa.dao.ProductoDAOImpl;
import org.educa.entity.ProductoEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductoService {

    private final ProductoDAO productoDAO = new ProductoDAOImpl();

    public List<ProductoEntity> findAllProducts() throws SQLException {
        return productoDAO.getAllProducts();
    }

    public ProductoEntity getFirstProductoByNameTallaAndColor(String nombre, String talla, String color) throws SQLException {
        return productoDAO.getFirstProductoByNameTallaAndColor(nombre, talla, color);
    }

    public List<ProductoEntity> findByName(ProductoEntity producto) throws SQLException {
        return productoDAO.findByName(producto);
    }

    public void markProductAsSold(Connection c, ProductoEntity producto) throws SQLException {
        productoDAO.markProductAsSold(c, producto);
    }
}
