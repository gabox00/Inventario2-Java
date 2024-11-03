package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.DireccionEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    @Override
    public ClienteEntity login(String dni, String password) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE dni = ? AND pass = ?";

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dni);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                ClienteEntity cliente = new ClienteEntity();
                cliente.setId(rs.getInt("id"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setPrimerApellido(rs.getString("primer_apellido"));
                cliente.setSegundoApellido(rs.getString("segundo_apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setPass(rs.getString("pass"));
                cliente.setUsuCre(rs.getString("usu_cre"));
                cliente.setFecCre(rs.getTimestamp("fec_cre"));
                cliente.setUsuMod(rs.getString("usu_mod"));
                cliente.setFecMod(rs.getTimestamp("fec_mod"));

                // Obtener las direcciones del cliente
                String sqlDirecciones = "SELECT * FROM direccion WHERE id_cliente = ?";
                try (PreparedStatement psDirecciones = connection.prepareStatement(sqlDirecciones)) {
                    psDirecciones.setInt(1, cliente.getId());
                    ResultSet rsDirecciones = psDirecciones.executeQuery();
                    List<DireccionEntity> direcciones = new ArrayList<>();
                    while (rsDirecciones.next()) {
                        DireccionEntity direccion = new DireccionEntity();
                        direccion.setId(rsDirecciones.getInt("id"));
                        direccion.setCalle(rsDirecciones.getString("calle"));
                        direccion.setCiudad(rsDirecciones.getString("ciudad"));
                        direccion.setCp(rsDirecciones.getString("cp"));
                        direccion.setFecCre(rsDirecciones.getTimestamp("fec_cre"));
                        direccion.setFecMod(rsDirecciones.getTimestamp("fec_mod"));
                        direccion.setPais(rsDirecciones.getString("pais"));
                        direccion.setUsuCre(rsDirecciones.getString("usu_cre"));
                        direccion.setUsuMod(rsDirecciones.getString("usu_mod"));
                        direcciones.add(direccion);
                    }
                    cliente.setDirecciones(direcciones);
                }

                return cliente;
            }
        }
        return null;
    }
}
