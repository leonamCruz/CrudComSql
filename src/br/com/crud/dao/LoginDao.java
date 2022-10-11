package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private String nomeDoLogado;
    private static final String VERIFICA_USER = "select * from tb_funcionarios where email = ? and senha = ?";

    public boolean logar(String email, String senha){
        try(var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(VERIFICA_USER)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nomeDoLogado = rs.getString("nome");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getNomeDoLogado() {
        return nomeDoLogado;
    }
}