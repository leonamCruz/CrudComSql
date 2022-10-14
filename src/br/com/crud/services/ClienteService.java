package br.com.crud.services;

import br.com.crud.model.Cliente;

import java.util.List;

public interface ClienteService {
	
	List<Cliente> listarClientes();
	
	List<Cliente> filtrarPorNomes(String nome);
	
	boolean cadastrarCliente(Cliente cliente);
	
	boolean excluirClientes(Cliente cliente);
	
	boolean alterarClientes(Cliente cliente);

}
