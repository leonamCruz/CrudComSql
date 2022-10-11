package br.com.crud.model;

public class Fornecedor extends Cliente{
    private String cpnj;

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }
    public String getCpnj() {
        return cpnj;
    }
    @Override
    public String toString(){
        return this.getNome();
    }
}
