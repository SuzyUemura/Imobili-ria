package br.com.Imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.Imobiliaria.modal.Imoveis;

public interface ImoveisRepository extends JpaRepository<Imoveis, Long>, PagingAndSortingRepository<Imoveis, Long>{

	@Query("Select i from Imoveis as i where i.categoria.nomeCategoria like %:nomeCategoria%")
	public List<Imoveis> findByImovelFromCategoria(@Param("nomeCategoria") String nome);
	
	
	@Query("Select i from Imoveis as i where i.negocio.nomeNegocio like %:nomeNegocio%")
	public List<Imoveis> findByImovelFromNegocio(@Param("nomeNegocio") String nome);
	
	
	@Query("Select i from Imoveis as i where i.estado.nomeEstado like %:nomeEstado%")
	public List<Imoveis> findByImoveisFromEstado(@Param("nomeEstado") String nome);
	
	
	@Query("Select i from Imoveis as i where i.quarto.quantidade like %:quantidade%")
	public List<Imoveis> findByImoveisFromQuarto(@Param("quantidade") String nome);
			
	
}
