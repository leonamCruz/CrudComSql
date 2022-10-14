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
    public boolean cadastrarCliente(Cliente cliente) {
        return this.clienteDao.cadastrarCliente(cliente);
    }

    @Override
    public boolean excluirClientes(Cliente cliente) {
        return this.clienteDao.excluirClientes(cliente);
    }

    @Override
    public boolean alterarClientes(Cliente cliente) {
        return this.clienteDao.alterarClientes(cliente);
    }
}