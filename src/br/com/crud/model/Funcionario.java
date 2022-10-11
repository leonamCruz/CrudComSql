package br.com.crud.model;

public class Funcionario extends Cliente{
    String senha, cargo;
    Boolean nivelDeAcesso; //True caso seja ADM;

    public void setSenhaFuncionario(String senhaFuncionario){
        this.senha = senhaFuncionario;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setNivelDeAcesso(Boolean nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getNivelDeAcesso() {
        return nivelDeAcesso;
    }
    public String getCargo() {
        return cargo;
    }
}