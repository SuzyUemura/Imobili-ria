package br.com.Imobiliaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Imobiliaria.filter.Filter;
import br.com.Imobiliaria.modal.Negocio;
import br.com.Imobiliaria.repository.Negocio_Repository;

@Service
public class Negocio_Service {
	
	@Autowired
	private Negocio_Repository repositorio;
	
	public void salvar (Negocio negocio) {
		try {
			repositorio.save(negocio);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	
	}
	
	public Page<Negocio> pesquisar(int numPagina, Filter filtro){
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Negocio> paginas = repositorio.findAll(page);
		
		String criterioPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		if(criterioPesquisa == "") {
			return paginas;
		} else {
		List<Negocio> negocios = repositorio.findByNomeNegocioContainingIgnoreCase(criterioPesquisa);
		
		List<Negocio> negociosPaginados = new ArrayList<>();
		if (negocios.size() < 5) {
			negociosPaginados = negocios;
		} else {
			for (int i=0; i < page.getPageSize(); i ++) {
				negociosPaginados.add(negocios.get(i));
			}
		}
		Page<Negocio> pages = new PageImpl<Negocio>(negociosPaginados , page, negocios.size());
		return pages;
		
		}
	}
	
	public void excluir (Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Negocio> todos(){
		return repositorio.findAll();
	}
	

}
