package br.com.crud.services.impl;

import java.util.List;

import br.com.crud.dao.ClienteDao;
import br.com.crud.model.Cliente;
import br.com.crud.services.ClienteService;

public class ClienteServiceImpl implements ClienteService {

    private ClienteDao clienteDao;

    public ClienteServiceImpl() {
        this.clienteDao = new ClienteDao();
    }

    @Override
    public List<Cliente> listarClientes() {
        return this.clienteDao.listarClientes();
    }

    @Override
    public List<Cliente> filtrarPorNomes(String nome) {
        return this.clienteDao.filtrarPorNomes(nome);
    }

    @Override
    public void cadastrarCliente(Cliente cliente) {
        this.clienteDao.cadastrarCliente(cliente);
    }

    @Override
    public void excluirClientes(Cliente cliente) {
        this.clienteDao.excluirClientes(cliente);
    }

    @Override
    public void alterarClientes(Cliente cliente) {
        this.clienteDao.alterarClientes(cliente);
    }
}