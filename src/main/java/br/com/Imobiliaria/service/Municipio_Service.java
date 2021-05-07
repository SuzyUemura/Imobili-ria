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
import br.com.Imobiliaria.modal.Municipio;
import br.com.Imobiliaria.repository.Municipio_Repository;

@Service
public class Municipio_Service {
	
	@Autowired
	Municipio_Repository repositorio;
	
	public void salvar (Municipio municipio) {
		repositorio.save(municipio);
	}
	
	public void excluir(Long id) {
		repositorio.deleteById(id);
	}
	
	public Page<Municipio> pesquisar (int numPagina, Filter filtro) {
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Municipio> paginas = repositorio.findAll(page);
		
		String criterioPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		if (criterioPesquisa == "") {
			return paginas;
		} else {
		List<Municipio> municipio = repositorio.findByNomeMunicipioIgnoreCaseContaining(criterioPesquisa);
		
		List<Municipio> municipiosPaginados = new ArrayList<>();
		
		if (municipio.size() < 5) {
			municipiosPaginados = municipio; 
		} else {
		for (int i=0; i < page.getPageSize(); i++) {
			municipiosPaginados.add(municipio.get(i));
		
			}
		}
		
		Page<Municipio> pages = new PageImpl<Municipio>(municipiosPaginados , page, municipio.size());
		return pages;
		}
	}
	
	public List<Municipio> todos(){
		return repositorio.findAll();
	}
}
