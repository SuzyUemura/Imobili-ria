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
import br.com.Imobiliaria.modal.Negocio;
import br.com.Imobiliaria.service.Negocio_Service;

@Controller
@RequestMapping("/negocio")
public class Negocio_Controller {
	
	private static final String CADASTRO_VIEW = "CadNegocio";
	
	@Autowired
	private Negocio_Service service;

	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Negocio());
		return mv;
	}
	
	@PostMapping
	public String salvar (@Validated Negocio negocio, Errors error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			service.salvar(negocio);
			attributes.addFlashAttribute("mensagem", "Negócio salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/negocio/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar (@ModelAttribute("filtro")Filter filtro, @RequestParam (defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Negocio");
		
		Page<Negocio> todosNegocios = service.pesquisar(numPag, filtro);
		mv.addObject("paginacao", todosNegocios.getContent());
		
		int page = todosNegocios.getPageable().getPageNumber();
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo", todosNegocios.getPageable().getPageNumber() + 1);
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView pagEdição(@PathVariable("id") Negocio negocio) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(negocio);
		return mv;
	}
	
	@DeleteMapping(value = "/{id}")
	public String excluir (@PathVariable Long id, RedirectAttributes attributes) {
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Negócio excluído com sucesso!");
		return "redirect:/negocio";
	}
	

}
