package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Fornecedor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao {
    private Connection con;

    public void FornecedoresDao() {
        this.con = new ConnectFactory().getConnection();
    }

    public void cadastrarFornecedores(Fornecedor obj) {
        try {
            FornecedoresDao();
            String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero," +
                    "complemento,bairro,cidade,estado) values (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpnj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso",
                    "Sucesso total", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException erro) {
            System.out.println("erro" + erro);
        }
    }

    public List<Fornecedor> listarFornecedores() {
        try {
            List<Fornecedor> lista = new ArrayList<>();
            FornecedoresDao();
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpnj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu erro",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void excluirFornecedor(Fornecedor obj) {
        try {
            FornecedoresDao();
            String sql = "delete from tb_fornecedores where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,
                    "Excluido com Sucesso", "Sucesso total", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException erro) {
            System.out.println("erro" + erro);
        }
    }

    public void alterarFornecedor(Fornecedor obj) {
        try {
            FornecedoresDao();
            String sql = "update tb_fornecedores set nome = ?," +
                    "cnpj = ?,email = ?,telefone = ?,celular = ?,cep = ?,endereco = ?" +
                    ",numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ? where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpnj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,
                    "Alterado com Sucesso", "Sucesso total", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException erro) {
            System.out.println("erro" + erro);
        }
    }

    public List<Fornecedor> filtrarPorNomesFornecedores(String nome) {
        try {
            List<Fornecedor> lista = new ArrayList<>();
            FornecedoresDao();
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpnj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu erro",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}