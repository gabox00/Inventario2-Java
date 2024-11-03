package org.educa.service;

import org.educa.dao.ClienteDAO;
import org.educa.dao.ClienteDAOImpl;
import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();

    public ClienteEntity login(String dni, String password) {
        try {
            return clienteDAO.login(dni, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error authenticating user", e);
        }
    }
}
