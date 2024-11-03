package org.educa.dao;

import org.educa.entity.DireccionEntity;

import java.sql.SQLException;
import java.util.List;

public interface DireccionDAO {
    List<DireccionEntity> findDireccionesByClienteId(int clienteId) throws SQLException;
    int create(DireccionEntity direccion) throws SQLException;
}