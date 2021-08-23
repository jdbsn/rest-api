package br.edu.ifpe.gus.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.projeto.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	

}