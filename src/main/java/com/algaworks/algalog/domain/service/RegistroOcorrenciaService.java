package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService buscar;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao){
		
		Entrega entrega = buscar.buscar(entregaId);
				
		return entrega.adicionarOcorrencia(descricao);
	}

}
