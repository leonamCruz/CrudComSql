package br.com.crud.services;

import br.com.crud.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    void cadastrarFuncionario(Funcionario funcionario);

    List<Funcionario> listarFuncionarios();

    void excluirFuncionario(Funcionario funcionario);

    void alterarFuncionario(Funcionario funcionario);

    List<Funcionario> filtrarPorNomes(String nome);

}
