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
import br.com.Imobiliaria.modal.Bairro;
import br.com.Imobiliaria.repository.Bairro_Repository;

@Service
public class Bairro_Service {
	
	@Autowired
	private Bairro_Repository repositorio;
	
	public void Salvar(Bairro bairro) {
		try {
			repositorio.save(bairro);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void excluir(Long id) {
		repositorio.deleteById(id);
	}
	
	public Page<Bairro> pesquisar(int numPagina, Filter filtro) {
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Bairro> paginas = repositorio.findAll(page);
		
		String criterioPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		
		if(criterioPesquisa == "") {
			return paginas;
		} else {
		List<Bairro> bairro = repositorio.findByNomeBairroContainingIgnoreCase(criterioPesquisa);
		List<Bairro> bairrosPaginados = new ArrayList<>();
		
		if (bairro.size() < 5) {
			bairrosPaginados = bairro; 
		} else {
		for (int i=0; i < page.getPageSize(); i++) {
			bairrosPaginados.add(bairro.get(i));
		
			}
		}
		
		Page<Bairro> pages = new PageImpl<Bairro>(bairrosPaginados , page, bairro.size());
		return pages;
		}
	}
	
	public List<Bairro> todos() {
		return repositorio.findAll();
	}
	
	
}
