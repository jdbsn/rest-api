package br.edu.ifpe.gus.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.projeto.entity.Cliente;
import br.edu.ifpe.gus.projeto.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarCliente() {
		return clienteRepository.findAll();
	}
	
	public Cliente listarClientesPorId(Long id) {
		return clienteRepository.findById(id).get();
	}

	public void salvar(Cliente cliente) {
		clienteRepository.save(cliente);
		
	}
	
	public void removerClientePorId(Long id) {
		clienteRepository.deleteById(id);
	}
	
}
