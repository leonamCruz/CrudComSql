package br.com.crud.services;

import br.com.crud.model.Fornecedor;

import java.util.List;

public interface FornecedorService {
    void cadastrarFornecedores(Fornecedor fornecedor);

    List<Fornecedor> listarFornecedor();

    void excluirFornecedor(Fornecedor fornecedor);

    void alterarFornecedor(Fornecedor fornecedor);

    List<Fornecedor> filtrarPorNomesFornecedores(String nome);
}