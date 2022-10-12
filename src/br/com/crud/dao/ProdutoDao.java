package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Fornecedor;
import br.com.crud.model.Produto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private static final String LISTAR_PRODUTOS = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p " +
            "inner join tb_fornecedores as f on (p.for_id=f.id)";
    private static final String CADASTRA_PRODUTOS = "insert into tb_produtos (descricao,preco,qtd_estoque,for_id) values (?,?,?,?)";
    private static final String ALTERA_PRODUTOS = "update tb_produtos set descricao = ?, preco = ?, qtd_estoque=?, for_id =? where id = ?";
    private static final String DELETA_PRODUTOS = "delete from tb_produtos where id = ?";
    private static final String FILTRAR_PRODUTO_NOME = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p " +
            "inner join tb_fornecedores as f on (p.for_id=f.id) where p.descricao like ?";
    private static final String FILTRAR_NOME_CODIGO = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p " +
            "inner join tb_fornecedores as f on (p.for_id=f.id) where p.id like ?";
    private static final String ATUALIZA_ESTOQUE = "update tb_produtos set qtd_estoque=? where id=?";
    private static final String ESTOQUE_ATUAL = "SELECT qtd_estoque from tb_produtos where id = ?";

    public void cadastrarProdutos(Produto produto) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(CADASTRA_PRODUTOS)) {
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setInt(4, produto.getFornecedores().getId());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarProdutos(Produto produto) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(ALTERA_PRODUTOS)) {
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setInt(4, produto.getFornecedores().getId());
            stmt.setInt(5, produto.getId());
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirProdutos(Produto produto) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(DELETA_PRODUTOS)) {
            stmt.setInt(1, produto.getId());
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(LISTAR_PRODUTOS)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Produto> filtrarPorNomesProdutos(String nome) {
        List<Produto> lista = new ArrayList<>();
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(FILTRAR_PRODUTO_NOME)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Produto filtrarPorNomesCodigo(int codigo) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(FILTRAR_NOME_CODIGO)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizaEstoque(int id, int qtdNova) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(ATUALIZA_ESTOQUE)) {
            stmt.setInt(1, qtdNova);
            stmt.setInt(2, id);
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int estoqueAtual(int id) {
        try(var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(ESTOQUE_ATUAL)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("qtd_estoque");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}