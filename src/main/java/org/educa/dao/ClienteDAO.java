package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

public interface ClienteDAO {
    ClienteEntity login(String dni, String password) throws SQLException;
}
