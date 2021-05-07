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
import br.com.Imobiliaria.modal.Quarto;
import br.com.Imobiliaria.repository.Quarto_Repository;

@Service
public class Quarto_Service {
	
	@Autowired
	private Quarto_Repository repositorio;
	
	public void salvar (Quarto quarto) {
		try {
			repositorio.save(quarto);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public Page<Quarto> pesquisar(int numPagina, Filter filtro) {
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Quarto> paginas = repositorio.findAll(page);
		
		String criterioPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		if(criterioPesquisa == "") {
			return paginas;
		} else {
		List<Quarto> quarto =  repositorio.findByQuantidadeContainingIgnoreCase(criterioPesquisa);	
		
		List<Quarto> quartosPaginados = new ArrayList<>();
		
		if (quarto.size() < 5) {
			quartosPaginados = quarto; 
		} else {
		for (int i=0; i < page.getPageSize(); i++) {
			quartosPaginados.add(quarto.get(i));
		
			}
		}
		
		Page<Quarto> pages = new PageImpl<Quarto>(quartosPaginados , page, quarto.size());
		return pages;
		}
	}
	
	public void excluir (Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Quarto> todos () {
		return repositorio.findAll();
	}
}
