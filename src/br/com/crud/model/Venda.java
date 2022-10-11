package br.com.crud.model;

public class Venda {
    private int id;
    private Cliente clientes;
    private String data, obs;
    private double totalDaVenda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTotalDaVenda() {
        return totalDaVenda;
    }

    public void setTotalDaVenda(double totalDaVenda) {
        this.totalDaVenda = totalDaVenda;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getObs() {
        return obs;
    }
}