package br.com.crud.services;

import br.com.crud.model.Fornecedor;

import java.util.List;

public interface FornecedorService {
    boolean cadastrarFornecedores(Fornecedor fornecedor);

    List<Fornecedor> listarFornecedor();

    boolean excluirFornecedor(Fornecedor fornecedor);

    boolean alterarFornecedor(Fornecedor fornecedor);

    List<Fornecedor> filtrarPorNomesFornecedores(String nome);
}