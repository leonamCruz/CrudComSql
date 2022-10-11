package br.com.crud.services;

public interface LoginService {
    boolean logar(String email, String senha);

    String getNomeDoLogado();
}
