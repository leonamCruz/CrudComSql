package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Fornecedor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao {
    public static final String CADASTRA_FORNECEDOR = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String LISTAR_FORNECEDOR = "select * from tb_fornecedores";
    public static final String DELETAR_FORNECEDOR = "delete from tb_fornecedores where id";
    public static final String FILTRAR_FORNECEDOR_NOME = "select * from tb_fornecedores where nome like ?";
    public static final String ALTERAR_FORNECEDOR = "update tb_fornecedores set nome = ?," +
            "cnpj = ?,email = ?,telefone = ?,celular = ?,cep = ?,endereco = ?" +
            ",numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ? where id = ?";

    public boolean cadastrarFornecedores(Fornecedor fornecedor) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(CADASTRA_FORNECEDOR)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCpnj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setInt(8, fornecedor.getNumero());
            stmt.setString(9, fornecedor.getComplemento());
            stmt.setString(10, fornecedor.getBairro());
            stmt.setString(11, fornecedor.getCidade());
            stmt.setString(12, fornecedor.getEstado());

            stmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Fornecedor> listarFornecedor() {
        List<Fornecedor> lista = new ArrayList<>();
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(LISTAR_FORNECEDOR)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpnj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setNumero(rs.getInt("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));

                lista.add(fornecedor);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean excluirFornecedor(Fornecedor fornecedor) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(DELETAR_FORNECEDOR)) {
            stmt.setInt(1, fornecedor.getId());

            stmt.execute();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }
    public boolean alterarFornecedor(Fornecedor fornecedor) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(ALTERAR_FORNECEDOR)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCpnj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setInt(8, fornecedor.getNumero());
            stmt.setString(9, fornecedor.getComplemento());
            stmt.setString(10, fornecedor.getBairro());
            stmt.setString(11, fornecedor.getCidade());
            stmt.setString(12, fornecedor.getEstado());
            stmt.setInt(13, fornecedor.getId());

            stmt.execute();
            return true;
        } catch (Exception erro) {
            erro.printStackTrace();
            return false;
        }
    }
    public List<Fornecedor> filtrarPorNomesFornecedores(String nome) {
        List<Fornecedor> lista = new ArrayList<>();
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(FILTRAR_FORNECEDOR_NOME)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpnj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setNumero(rs.getInt("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));

                lista.add(fornecedor);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}