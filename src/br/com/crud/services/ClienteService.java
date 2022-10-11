package br.com.crud.services;

import br.com.crud.model.Cliente;

import java.util.List;

public interface ClienteService {
	
	List<Cliente> listarClientes();
	
	List<Cliente> filtrarPorNomes(String nome);
	
	void cadastrarCliente(Cliente cliente);
	
	void excluirClientes(Cliente cliente);
	
	void alterarClientes(Cliente cliente);

}
