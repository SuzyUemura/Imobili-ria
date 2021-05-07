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
import br.com.Imobiliaria.modal.Categoria;
import br.com.Imobiliaria.repository.Categoria_Repository;

@Service
public class Categoria_Service {
	
	@Autowired
	private Categoria_Repository repositorio;
	
	public void salvar (Categoria categoria) {
		try {
			repositorio.save(categoria);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("O tipo da categoria informado é inválido");  
		}
	}
	
	public Page<Categoria> pesquisar (int numPagina, Filter filtro) {
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Categoria> paginas = repositorio.findAll(page);
		
		String criterioPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		
		if (criterioPesquisa == "") {
			return paginas;
		} else {
		 List<Categoria> categoria = repositorio.findByNomeCategoriaContainingIgnoreCase(criterioPesquisa);
		 
		 List<Categoria> categoriaPaginada = new ArrayList<>();
		 
		 if (categoria.size() < 5) {
			 categoriaPaginada = categoria;
		 } else {
			 for (int i=0; i < page.getPageSize(); i++) {
				 categoriaPaginada.add(categoria.get(i));
			 }
			 
		 }
		 
		 Page<Categoria> pages = new PageImpl<Categoria>(categoriaPaginada, page, categoria.size());
		 return pages; 
		}
	}
	
	public void excluir (Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Categoria> todos() {
		return repositorio.findAll();
	}
}
