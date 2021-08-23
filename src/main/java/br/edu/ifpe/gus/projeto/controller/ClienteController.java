package br.edu.ifpe.gus.projeto.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifpe.gus.projeto.entity.Cliente;
import br.edu.ifpe.gus.projeto.entity.Streaming;
import br.edu.ifpe.gus.projeto.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/api/cliente", method = RequestMethod.GET)
	public @ResponseBody List<Cliente> listarClientes() {
		return clienteService.listarCliente();
	}
	
	@RequestMapping(value = "/api/cliente/{id}", method = RequestMethod.GET)
	public @ResponseBody Cliente listarClientesPorId(@PathVariable Long id) {
		return clienteService.listarClientesPorId(id);
	}
	@RequestMapping(value = "/api/cliente", method = RequestMethod.POST)
	public ResponseEntity<Cliente> criarStreaming (@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {
		clienteService.salvar(cliente);
		URI uri = uriBuilder.path("/api/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@RequestMapping(value = "/api/cliente/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Cliente> editarCliente (@PathVariable Long id, @RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {
		var novoCliente = clienteService.listarClientesPorId(id);
		novoCliente.setNome(cliente.getNome());
		novoCliente.setCpf(cliente.getCpf());
		novoCliente.setNumeroCartao(cliente.getNumeroCartao());
		clienteService.salvar(novoCliente);
		URI uri = uriBuilder.path("/api/categoria/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(novoCliente);
	}
	
	@RequestMapping(value = "/api/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Streaming> deletarCliente (@PathVariable Long id) {
		clienteService.removerClientePorId(id);
		return ResponseEntity.ok(null);
	}
	
}
