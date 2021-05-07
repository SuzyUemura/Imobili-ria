package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.Imobiliaria.modal.Municipio;

public interface Municipio_Repository extends JpaRepository<Municipio, Long>, PagingAndSortingRepository<Municipio, Long> {
	
	public List<Municipio> findByNomeMunicipioIgnoreCaseContaining(String nome);
}
