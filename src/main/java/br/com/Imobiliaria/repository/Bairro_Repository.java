package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.Imobiliaria.modal.Bairro;

public interface Bairro_Repository extends JpaRepository<Bairro, Long>, PagingAndSortingRepository<Bairro, Long>{
	
	public List<Bairro> findByNomeBairroContainingIgnoreCase(String nome);
}
