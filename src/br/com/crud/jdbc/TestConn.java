package br.com.crud.jdbc;

import java.sql.SQLException;

public class TestConn {
    public static void main(String[] args) throws SQLException {
        var conn = ConnectFactory.getConnection();
        if (conn!=null){
            System.out.println("Conectado");
            conn.close();
        }
    }
}
