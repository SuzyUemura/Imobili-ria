package br.com.Imobiliaria.controller;

import java.util.List;

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
import br.com.Imobiliaria.modal.Municipio;
import br.com.Imobiliaria.service.Estado_Service;
import br.com.Imobiliaria.service.Municipio_Service;

@Controller
@RequestMapping("/municipio")
public class Municipio_Controller {
	
	private static final String CADASTRO_VIEW = "CadMunicipio";
	
	@Autowired
	private Estado_Service estadoService;
	
	@Autowired
	private Municipio_Service municipioService;
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Municipio());
		return mv;
	}
	
	@PostMapping
	public String salvar(@Validated Municipio municipio, Errors error, RedirectAttributes attributes) {
		if(error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			municipioService.salvar(municipio);
			attributes.addFlashAttribute("mensagem", "Municipio salvo com sucesso");
		} catch (Exception e) {
			e.getMessage();
		}
		
		return"redirect:/municipio/novo";
	}
	
	@GetMapping("{id}")
	public ModelAndView telaEdicao(@PathVariable("id") Municipio municipio) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(municipio);
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") Filter filtro, @RequestParam(defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Municipio");
		
		Page<Municipio> todosMunicipios = municipioService.pesquisar(numPag, filtro);
		mv.addObject("paginacao", todosMunicipios);	
		
		int page = todosMunicipios.getPageable().getPageNumber();
		
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo", todosMunicipios.getPageable().getPageNumber() + 1);
		
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		municipioService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Municipio excluído com sucesso");
		return "redirect:/municipio";
	}
	
	@ModelAttribute("todosEstados")
	public List<Estado> todosEstados() {
		return estadoService.todos();
	}
}
