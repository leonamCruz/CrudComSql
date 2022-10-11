package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Funcionario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    private static final String CADASTRA_FUNCIONARIO = "insert into tb_funcionarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero," +
            "complemento,bairro,cidade,estado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String LISTAR_FUNCIONARIO = "select * from tb_funcionarios";

    private static final String EXCLUIR_FUNCIONARIO = "delete from tb_funcionarios where id = ?";
    private static final String ALTERAR_FUNCIONARIO = "update tb_funcionarios set nome = ?,rg = ?," +
            "cpf = ?,email = ?, telefone = ?,celular = ?,cep = ?,endereco = ?" +
            ",numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ? where id = ?";
    private static final String FILTRAR_POR_NOME = "select * from tb_funcionarios where nome like ?";




    public void cadastrarFuncionario(Funcionario obj) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(CADASTRA_FUNCIONARIO)) {

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, String.valueOf(obj.getNivelDeAcesso()));
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());

            stmt.execute();

        } catch (SQLException erro) {
            System.out.println("erro" + erro);
        }
    }

    public List<Funcionario> listarFuncionarios() {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(LISTAR_FUNCIONARIO)) {
            List<Funcionario> lista = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenhaFuncionario(rs.getString("senha"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setNivelDeAcesso(rs.getBoolean("nivel_acesso"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));

                lista.add(funcionario);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void excluirFuncionario(Funcionario funcionario) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(EXCLUIR_FUNCIONARIO)) {
            stmt.setInt(1, funcionario.getId());
            stmt.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    public void alterarFuncionario(Funcionario funcionario) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(ALTERAR_FUNCIONARIO)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCelular());
            stmt.setString(7, funcionario.getCep());
            stmt.setString(8, funcionario.getEndereco());
            stmt.setInt(9, funcionario.getNumero());
            stmt.setString(10, funcionario.getComplemento());
            stmt.setString(11, funcionario.getBairro());
            stmt.setString(12, funcionario.getCidade());
            stmt.setString(13, funcionario.getEstado());
            stmt.setInt(14, funcionario.getId());

            stmt.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }

    public List<Funcionario> filtrarPorNomes(String nome) {
        try (var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(FILTRAR_POR_NOME)){
            List<Funcionario> lista = new ArrayList<>();
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenhaFuncionario(rs.getString("senha"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setNivelDeAcesso(rs.getBoolean("nivel_acesso"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));

                lista.add(funcionario);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}