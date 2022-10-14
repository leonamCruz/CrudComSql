package br.com.crud.services.impl;

import br.com.crud.dao.FuncionarioDao;
import br.com.crud.model.Funcionario;
import br.com.crud.services.FuncionarioService;

import java.util.List;

public class FuncionarioServiceImpl implements FuncionarioService {
    private FuncionarioDao funcionarioDao;

    public FuncionarioServiceImpl() {
    this.funcionarioDao = new FuncionarioDao();
    }

    @Override
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        return this.funcionarioDao.cadastrarFuncionario(funcionario);
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return this.funcionarioDao.listarFuncionarios();
    }

    @Override
    public boolean excluirFuncionario(Funcionario funcionario) {
        return this.funcionarioDao.excluirFuncionario(funcionario);
    }

    @Override
    public boolean alterarFuncionario(Funcionario funcionario) {
        return this.funcionarioDao.alterarFuncionario(funcionario);
    }

    @Override
    public List<Funcionario> filtrarPorNomes(String nome) {
        return this.funcionarioDao.filtrarPorNomes(nome);
    }
}
