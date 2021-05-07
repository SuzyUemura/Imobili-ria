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
import br.com.Imobiliaria.modal.Estado;
import br.com.Imobiliaria.service.Estado_Service;

@Controller
@RequestMapping("/estado")
public class Estado_Controller  {
	
	private static final String CADASTRO_VIEW = "CadEstado";
	
	@Autowired
	private Estado_Service service;
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Estado());
		return mv;
	}
	
	@PostMapping
	public String salvar (@Validated Estado estado, Errors error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			service.salvar(estado);
			attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso");
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/estado/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") Filter filtro, @RequestParam(defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Estado");
		
		Page<Estado> todosEstados = service.pesquisar(numPag, filtro);
		mv.addObject("paginacao", todosEstados);
		
		int page = todosEstados.getPageable().getPageNumber();
		
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo", todosEstados.getPageable().getPageNumber() + 1);
		
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView telaEdicao (@PathVariable ("id") Estado estado) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(estado);
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String excluir (@PathVariable Long id, RedirectAttributes attributes) {
		
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Estado excluído com sucesso");
		return "redirect:/estado";
	}
}
