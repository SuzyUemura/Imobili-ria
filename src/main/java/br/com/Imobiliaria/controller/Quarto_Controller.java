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
import br.com.Imobiliaria.modal.Quarto;
import br.com.Imobiliaria.service.Quarto_Service;

@Controller
@RequestMapping("/quarto")
public class Quarto_Controller {
	
	private static final String CADASTRO_VIEW = "CadQuartos";
	
	@Autowired
	private Quarto_Service service;
	
	@GetMapping("/novo")
	public ModelAndView novo () {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Quarto());
		return mv;
	}
	
	@PostMapping
	public String salvar (@ Validated Quarto quarto, Errors error, RedirectAttributes attributes) {
		if(error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			attributes.addFlashAttribute("mensagem", "Quarto salvo com sucesso");
			service.salvar(quarto);
		} catch (Exception e) {
			e.getMessage();
		}
		
		return "redirect:/quarto/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar (@ModelAttribute("filtro") Filter filtro, @RequestParam(defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Quarto");
		
		Page<Quarto> quartos = service.pesquisar(numPag, filtro);
		mv.addObject("paginacao", quartos);
		
		int page = quartos.getPageable().getPageNumber();
		
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo", quartos.getPageable().getPageNumber() + 1);
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView pagEdicao(@PathVariable ("id") Quarto quarto) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(quarto);		
		return mv;
	}
	
	@DeleteMapping(value = "/{id}")
	public String excluir (@PathVariable Long id, RedirectAttributes attributes) {
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Quarto excluído com sucesso");
		return"redirect:/quarto";
	}
}
