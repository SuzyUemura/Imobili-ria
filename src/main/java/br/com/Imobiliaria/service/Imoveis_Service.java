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
import br.com.Imobiliaria.modal.Imoveis;
import br.com.Imobiliaria.repository.ImoveisRepository;

@Service
public class Imoveis_Service {
	
	@Autowired
	private ImoveisRepository repositorio;
	
	public void Salvar(Imoveis imovel) {
		try {
			repositorio.save(imovel);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void excluir (Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Imoveis> todos() {
		return repositorio.findAll();
	}

	private List<Imoveis> filtrarPesquisa(List<Imoveis> resultadoCategoria, List<Imoveis> resultadoNegocio,
			List<Imoveis> resultadoEstado, List<Imoveis> resultadoQuarto) {
		if(!resultadoCategoria.isEmpty()) {
			return resultadoCategoria;
			
		} else if (!resultadoNegocio.isEmpty()) {
			return resultadoNegocio;
			
		} else if (!resultadoEstado.isEmpty()) {
			return resultadoEstado;
			
		} else if (!resultadoQuarto.isEmpty()) {
			return resultadoQuarto;
		}
		
		return new ArrayList<>();
	}
	
	public Page<Imoveis> paginar (int numPagina, Filter filtro) {
		Pageable page = PageRequest.of(numPagina, 5);
		Page<Imoveis> paginas = repositorio.findAll(page);
		
		String criterioPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		
		if(criterioPesquisa == "") {
			return paginas;
		} else {
		List<Imoveis> resultadoCategoria = repositorio.findByImovelFromCategoria(criterioPesquisa);
		List<Imoveis> resultadoNegocio = repositorio.findByImovelFromNegocio(criterioPesquisa);
		List<Imoveis> resultadoEstado = repositorio.findByImoveisFromEstado(criterioPesquisa);
		List<Imoveis> resultadoQuarto = repositorio.findByImoveisFromQuarto(criterioPesquisa);
		List<Imoveis> imoveis = filtrarPesquisa(resultadoCategoria, resultadoNegocio, resultadoEstado, resultadoQuarto);
		
		List<Imoveis> imoveisPaginados = new ArrayList<>();
		
		if (imoveis.size() < 5) {
			imoveisPaginados = imoveis; 
		} else {
		for (int i=0; i < page.getPageSize(); i++) {
			imoveisPaginados.add(imoveis.get(i));
		
			}
		}
		
		Page<Imoveis> pages = new PageImpl<Imoveis>(imoveisPaginados , page, imoveis.size());
		return pages;
		}
	
	}
	
}
