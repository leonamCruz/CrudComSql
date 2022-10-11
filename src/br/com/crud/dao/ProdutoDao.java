package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Fornecedor;
import br.com.crud.model.Produto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private Connection con;

    public void ProdutoDao() {
        this.con = new ConnectFactory().getConnection();
    }

    public void cadastrarProdutos(Produto obj) {
        ProdutoDao();
        try {
            String sql = "insert into tb_produtos (descricao,preco,qtd_estoque,for_id) values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getEstoque());
            stmt.setInt(4, obj.getFornecedores().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Sucesso Total", "Cadastrado com Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de Sql", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void alterarProdutos(Produto obj) {
        try {
            String sql = "update tb_produtos set descricao = ?, preco = ?, qtd_estoque=?, for_id =? where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getEstoque());
            stmt.setInt(4, obj.getFornecedores().getId());
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Sucesso Total", "Alterado com Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de Sql", "Erro" + e,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirProdutos(Produto obj) {
        try {
            String sql = "delete from tb_produtos where id = ?";
            ProdutoDao();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Sucesso total",
                    "Excluído com Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e,
                    "Errado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Produto> listarProdutos() {
        try {
            List<Produto> lista = new ArrayList<>();
            ProdutoDao();
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p " +
                    "inner join tb_fornecedores as f on (p.for_id=f.id)";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("qtd_estoque"));
                fornecedor.setNome(rs.getString("f.nome"));
                produto.setFornecedores(fornecedor);

                lista.add(produto);

            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro sql", "erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<Produto> filtrarPorNomesProdutos(String nome) {
        try {
            List<Produto> lista = new ArrayList<>();
            ProdutoDao();
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p " +
                    "inner join tb_fornecedores as f on (p.for_id=f.id) where p.descricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setId(rs.getInt("p.id"));
                produto.setDescricao(rs.getString("p.descricao"));
                produto.setPreco(rs.getDouble("p.preco"));
                produto.setEstoque(rs.getInt("p.qtd_estoque"));
                fornecedor.setNome(rs.getString("f.nome"));
                produto.setFornecedores(fornecedor);

                lista.add(produto);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu erro",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public Produto filtrarPorNomesCodigo(int codigo) {
        try {
            ProdutoDao();
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p " +
                    "inner join tb_fornecedores as f on (p.for_id=f.id) where p.id like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                Fornecedor fornecedor = new Fornecedor();

                produto.setId(rs.getInt("p.id"));
                produto.setDescricao(rs.getString("p.descricao"));
                produto.setPreco(rs.getDouble("p.preco"));
                produto.setEstoque(rs.getInt("p.qtd_estoque"));
                fornecedor.setNome(rs.getString("f.nome"));
                produto.setFornecedores(fornecedor);

                return produto;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu erro no sql",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void atualizaEstoque(int id, int qtdNova) {
        try {
            ProdutoDao();
            String sql = "update tb_produtos set qtd_estoque=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, qtdNova);
            stmt.setInt(2, id);

            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int estoqueAtual(int id) {
        try {
            ProdutoDao();
            String sql = "SELECT qtd_estoque from tb_produtos where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            int qtdAtual;
            if (rs.next()) {
                qtdAtual = rs.getInt("qtd_estoque");
                return qtdAtual;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}