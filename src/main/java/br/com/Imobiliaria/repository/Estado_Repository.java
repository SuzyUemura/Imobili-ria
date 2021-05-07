package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.Imobiliaria.modal.Estado;

public interface Estado_Repository extends JpaRepository<Estado, Long>, PagingAndSortingRepository<Estado, Long>{
	
	public List<Estado> findByNomeEstadoContainingIgnoreCase(String estados);
	
}