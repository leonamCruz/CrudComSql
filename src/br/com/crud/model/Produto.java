package br.com.crud.model;

public class Produto {
    private int id;
    private String descricao;
    private double preco;
    private int estoque;
    private Fornecedor fornecedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Fornecedor getFornecedores() {
        return fornecedor;
    }

    public void setFornecedores(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}