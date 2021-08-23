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

import br.edu.ifpe.gus.projeto.entity.Streaming;
import br.edu.ifpe.gus.projeto.service.StreamingService;

@RestController
public class StreamingController {
	
	@Autowired
	private StreamingService streamingService;

	@RequestMapping(value = "/api/streaming", method = RequestMethod.GET)
	public @ResponseBody List<Streaming> listarStreamings() {
		return streamingService.listarStreaming();
	}
	
	@RequestMapping(value = "/api/streaming/{id}", method = RequestMethod.GET)
	public @ResponseBody Streaming listarStreamingsPorId(@PathVariable Long id) {
		return streamingService.listarStreamingsPorId(id);
	}
	
	@RequestMapping(value = "/api/streaming", method = RequestMethod.POST)
	public ResponseEntity<Streaming> criarStreaming (@RequestBody Streaming streaming, UriComponentsBuilder uriBuilder) {
		streamingService.salvar(streaming);
		URI uri = uriBuilder.path("/api/categoria/{id}").buildAndExpand(streaming.getId()).toUri();
		return ResponseEntity.created(uri).body(streaming);
	}
	
	@RequestMapping(value = "/api/streaming/{id}", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Streaming> editarStreaming (@PathVariable Long id, @RequestBody Streaming streaming, UriComponentsBuilder uriBuilder) {
		var novoStreaming = streamingService.listarStreamingsPorId(id);
		novoStreaming.setNome(streaming.getNome());
		novoStreaming.setPlano(streaming.getPlano());
		novoStreaming.setValor(streaming.getValor());
		streamingService.salvar(novoStreaming);
		URI uri = uriBuilder.path("/api/categoria/{id}").buildAndExpand(streaming.getId()).toUri();
		return ResponseEntity.created(uri).body(novoStreaming);
	}
	
	@RequestMapping(value = "/api/streaming/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Streaming> deletarStreaming (@PathVariable Long id) {
		streamingService.removerStreamingPorId(id);
		return ResponseEntity.ok(null);
	}
	
}
