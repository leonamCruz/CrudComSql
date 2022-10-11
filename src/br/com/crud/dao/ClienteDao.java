package br.com.crud.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Cliente;

public class ClienteDao {
  
	private static final String SELECT_ALL = "select * from tb_clientes";
    private static final String SELECT_POR_NOME = "select * from tb_clientes where nome like ?";
    private static final String SELECT_POR_CPF = "select * from tb_clientes where cpf like ?";
	private static final String DELETE_POR_ID = "delete from tb_clientes where id = ?";
	private static final String INSERT_CLIENTE = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero," +
			"complemento,bairro,cidade,estado) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_CLIENTE = "update tb_clientes set nome = ?,rg = ?," +
			"cpf = ?,email = ?,telefone = ?,celular = ?,cep = ?,endereco = ?" +
			",numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ? where id = ?";

	public Cliente filtrarPorCpf(String cpf) {
    	
        try(var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(SELECT_POR_CPF);) {
            stmt.setString(1,cpf);

            var rs = stmt.executeQuery();

            while (rs.next()) {
                var cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCep(rs.getString("cep"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                return cliente;
            }
        }
        catch (SQLException e) {
        	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Deu erro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void cadastrarCliente(Cliente cliente) {
        try (var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(INSERT_CLIENTE);){
        	
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getEndereco());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getComplemento());
            stmt.setString(11, cliente.getBairro());
            stmt.setString(12, cliente.getCidade());
            stmt.setString(13, cliente.getEstado());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso", "Sucesso total", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException erro) {
        	erro.printStackTrace();
        }
    }

    public List<Cliente> listarClientes() {
        try (var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(SELECT_ALL);){
            var lista = new ArrayList<Cliente>();
            var rs = stmt.executeQuery();

            while (rs.next()) {
                var cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCep(rs.getString("cep"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

                lista.add(cliente);
            }
            return lista;
        }
        catch (SQLException e) {
        	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Deu erro",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return new ArrayList<>();
    }

    public void excluirClientes(Cliente cliente) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(DELETE_POR_ID);){
            stmt.setInt(1, cliente.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso", "Sucesso total",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException erro) {
        	erro.printStackTrace();
        }
    }

    public void alterarClientes(Cliente cliente) {
    	
        try (var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(UPDATE_CLIENTE);) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getEndereco());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getComplemento());
            stmt.setString(11, cliente.getBairro());
            stmt.setString(12, cliente.getCidade());
            stmt.setString(13, cliente.getEstado());
            stmt.setInt(14, cliente.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null,
                    "Alterado com Sucesso", "Sucesso total", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException erro) {
        	erro.printStackTrace();
        }
    }

    public List<Cliente> filtrarPorNomes(String nome) {
        try (var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(SELECT_POR_NOME);){
            var lista = new ArrayList<Cliente>();
            
            stmt.setString(1,nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCep(rs.getString("cep"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

                lista.add(cliente);
            }
            return lista;
        }
        catch (SQLException e) {
        	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Deu erro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return new ArrayList<>();
    }
    
}