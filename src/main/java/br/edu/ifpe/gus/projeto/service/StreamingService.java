package br.edu.ifpe.gus.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.projeto.entity.Streaming;
import br.edu.ifpe.gus.projeto.repository.StreamingRepository;

@Service
public class StreamingService {

	@Autowired
	private StreamingRepository streamingRepository;
	
	public List<Streaming> listarStreaming() {
		return streamingRepository.findAll();
	}
	
	public Streaming listarStreamingsPorId(Long id) {
		return streamingRepository.findById(id).get();
	}

	public void salvar(Streaming streaming) {
		streamingRepository.save(streaming);
		
	}
	
	public void removerStreamingPorId(Long id) {
		streamingRepository.deleteById(id);
	}
	
}
