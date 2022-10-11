package br.com.crud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectFactory {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/BDVENDAS", "Cabuloso", "Leonam1!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}