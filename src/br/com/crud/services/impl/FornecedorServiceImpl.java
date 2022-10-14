package br.com.crud.services.impl;

import br.com.crud.dao.FornecedorDao;
import br.com.crud.model.Fornecedor;
import br.com.crud.services.FornecedorService;

import java.util.List;

public class FornecedorServiceImpl implements FornecedorService {

    private FornecedorDao fornecedorDao = new FornecedorDao();

    @Override
    public boolean cadastrarFornecedores(Fornecedor fornecedor) {
       return this.fornecedorDao.cadastrarFornecedores(fornecedor);
    }

    @Override
    public List<Fornecedor> listarFornecedor() {
        return this.fornecedorDao.listarFornecedor();
    }

    @Override
    public boolean excluirFornecedor(Fornecedor fornecedor) {
        return this.fornecedorDao.excluirFornecedor(fornecedor);
    }

    @Override
    public boolean alterarFornecedor(Fornecedor fornecedor) {
       return this.fornecedorDao.alterarFornecedor(fornecedor);
    }

    @Override
    public List<Fornecedor> filtrarPorNomesFornecedores(String nome) {
        return this.fornecedorDao.filtrarPorNomesFornecedores(nome);
    }
}
