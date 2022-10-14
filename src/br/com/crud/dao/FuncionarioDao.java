package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Funcionario;

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


    public boolean cadastrarFuncionario(Funcionario funcionario) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(CADASTRA_FUNCIONARIO)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setString(6, funcionario.getCargo());
            stmt.setString(7, String.valueOf(funcionario.getNivelDeAcesso()));
            stmt.setString(8, funcionario.getTelefone());
            stmt.setString(9, funcionario.getCelular());
            stmt.setString(10, funcionario.getCep());
            stmt.setString(11, funcionario.getEndereco());
            stmt.setInt(12, funcionario.getNumero());
            stmt.setString(13, funcionario.getComplemento());
            stmt.setString(14, funcionario.getBairro());
            stmt.setString(15, funcionario.getCidade());
            stmt.setString(16, funcionario.getEstado());

            stmt.execute();
            return true;

        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
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

    public boolean excluirFuncionario(Funcionario funcionario) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(EXCLUIR_FUNCIONARIO)) {
            stmt.setInt(1, funcionario.getId());
            stmt.execute();
            return true;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return false;
        }
    }
    public boolean alterarFuncionario(Funcionario funcionario) {
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
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> filtrarPorNomes(String nome) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(FILTRAR_POR_NOME)) {
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