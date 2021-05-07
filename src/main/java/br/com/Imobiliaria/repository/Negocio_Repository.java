package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.Imobiliaria.modal.Negocio;

public interface Negocio_Repository extends JpaRepository<Negocio, Long>, PagingAndSortingRepository<Negocio, Long> {
	
	public List<Negocio> findByNomeNegocioContainingIgnoreCase(String nome);
}
