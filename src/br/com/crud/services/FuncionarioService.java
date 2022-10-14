package br.com.crud.services;

import br.com.crud.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    boolean cadastrarFuncionario(Funcionario funcionario);

    List<Funcionario> listarFuncionarios();

    boolean excluirFuncionario(Funcionario funcionario);

    boolean alterarFuncionario(Funcionario funcionario);

    List<Funcionario> filtrarPorNomes(String nome);

}
