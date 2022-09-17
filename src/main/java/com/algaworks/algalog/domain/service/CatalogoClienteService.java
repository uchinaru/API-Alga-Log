package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.negocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienterepository;
	
	public Cliente buscar(Long clienteId) {
		
		return clienterepository.findById(clienteId)
				.orElseThrow(() -> new negocioException("Cliente não encontrado"));
	}
	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienterepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new negocioException("Email duplicado");
		}
		
		return clienterepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienterepository.deleteById(clienteId);
	}
	
	

}
