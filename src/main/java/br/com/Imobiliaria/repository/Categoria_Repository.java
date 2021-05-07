package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.Imobiliaria.modal.Categoria;

public interface Categoria_Repository extends JpaRepository<Categoria, Long>, PagingAndSortingRepository<Categoria, Long> {
	
	public List<Categoria> findByNomeCategoriaContainingIgnoreCase(String nome);
}
