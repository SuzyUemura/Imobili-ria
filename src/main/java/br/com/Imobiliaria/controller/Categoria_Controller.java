package br.com.Imobiliaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.Imobiliaria.filter.Filter;
import br.com.Imobiliaria.modal.Categoria;
import br.com.Imobiliaria.service.Categoria_Service;

@Controller 
@RequestMapping("/categoria")
public class Categoria_Controller {
	
	private static final String CADASTRO_VIEW = "CadCategoria";
	
	@Autowired
	private Categoria_Service service;
	
	@RequestMapping("/novo")
	public ModelAndView novaCategoria () {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Categoria());
		return mv;
	}
	
	@PostMapping()
	public String salvar (@Validated Categoria categoria, Errors error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			return CADASTRO_VIEW;
		}
			try {
				service.salvar(categoria);
				attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "redirect:/categoria/novo";
	}
	
	@DeleteMapping(value = "/{id}")
	public String excluir (@PathVariable Long id, RedirectAttributes attributes) {
		service.excluir(id); 
		attributes.addFlashAttribute("mensagem", "Categoria excluída com sucesso!");
		return "redirect:/categoria";
	}
	
	@GetMapping("{id}")
	public ModelAndView telaEdicao(@PathVariable("id") Categoria categoria) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(categoria);
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar (@ModelAttribute("filtro")Filter filtro, @RequestParam(defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Categoria");
		
		Page<Categoria> todasCategorias = service.pesquisar(numPag, filtro);
		mv.addObject("paginacao", todasCategorias.getContent());
		
		int page = todasCategorias.getPageable().getPageNumber();
		
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo", todasCategorias.getPageable().getPageNumber() + 1);
		return mv;
	}
}
