package br.com.crud.model;

public class ItemVenda {
    private int id;
    private Venda venda;
    private Produto produto;
    private int qtd;
    private double subTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda getVendas() {
        return venda;
    }

    public void setVendas(Venda venda) {
        this.venda = venda;
    }

    public Produto getProdutos() {
        return produto;
    }

    public void setProdutos(Produto produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
