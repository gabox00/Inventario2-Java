package org.educa.dao;


import org.educa.entity.DireccionEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DireccionDAOImpl implements DireccionDAO {
    @Override
    public List<DireccionEntity> findDireccionesByClienteId(int clienteId) throws SQLException {
        String query = "SELECT * FROM direccion WHERE id_cliente = ?";
        List<DireccionEntity> direcciones = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    direcciones.add(new DireccionEntity(
                            rs.getInt("id"),
                            rs.getInt("id_cliente"),
                            rs.getString("calle"),
                            rs.getString("ciudad"),
                            rs.getString("cp"),
                            rs.getString("pais"),
                            rs.getString("usu_cre"),
                            rs.getTimestamp("fec_cre"),
                            rs.getString("usu_mod"),
                            rs.getTimestamp("fec_mod")
                    ));
                }
            }
        }
        return direcciones;
    }

    @Override
    public int create(DireccionEntity direccion) throws SQLException {
        String query = "INSERT INTO direccion (id_cliente, calle, ciudad, cp, pais, usu_cre, fec_cre) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, direccion.getId());
            ps.setString(2, direccion.getCalle());
            ps.setString(3, direccion.getCiudad());
            ps.setString(4, direccion.getCp());
            ps.setString(5, direccion.getPais());
            ps.setString(6, direccion.getUsuCre());
            ps.setTimestamp(7, direccion.getFecCre());
            return ps.executeUpdate();
        }
    }
}