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
import br.com.Imobiliaria.modal.Estado;
import br.com.Imobiliaria.repository.Estado_Repository;

@Service
public class Estado_Service {
	@Autowired
	private Estado_Repository repositorio;
	
	
	public void salvar (Estado estado) {
		try {
			repositorio.save(estado);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public Page<Estado> pesquisar(int numPagina, Filter filtro) {
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Estado> paginas = repositorio.findAll(page);
		
		
		String criteriosPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		if (criteriosPesquisa == "") {
			return paginas;
		} else {
		List<Estado> estado = repositorio.findByNomeEstadoContainingIgnoreCase(criteriosPesquisa);
		List<Estado> estadosPaginados = new ArrayList<>();
		
		if (estado.size() < 5) {
			estadosPaginados = estado;
		} else {
			for (int i=0; i < page.getPageSize(); i++) {
				estadosPaginados.add(estado.get(i));
			}
		}
		Page<Estado> pages = new PageImpl<Estado>(estadosPaginados, page, estado.size());
		return pages;
		}
	}
	
	public void excluir (Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Estado> todos () {
		return repositorio.findAll();
	}
}
