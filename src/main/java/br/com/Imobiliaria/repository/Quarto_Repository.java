package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.Imobiliaria.modal.Quarto;

public interface Quarto_Repository extends JpaRepository<Quarto, Long>, PagingAndSortingRepository<Quarto, Long> {
	
	public List<Quarto> findByQuantidadeContainingIgnoreCase(String nome);
}
