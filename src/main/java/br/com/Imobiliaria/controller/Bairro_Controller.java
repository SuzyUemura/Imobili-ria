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
import br.com.Imobiliaria.modal.Bairro;
import br.com.Imobiliaria.modal.Estado;
import br.com.Imobiliaria.modal.Municipio;
import br.com.Imobiliaria.service.Bairro_Service;
import br.com.Imobiliaria.service.Estado_Service;
import br.com.Imobiliaria.service.Municipio_Service;

@Controller
@RequestMapping("/bairro")
public class Bairro_Controller {
	
	private static final String CADASTRO_VIEW = "CadBairro";
	
	@Autowired
	private Bairro_Service bairroService;
	
	@Autowired
	private Municipio_Service municipioService;
	
	@Autowired
	private Estado_Service estadoService;
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Bairro());
		return mv;
	}
	
	@PostMapping
	public String salvar (@Validated Bairro bairro, Errors error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			bairroService.Salvar(bairro);
			attributes.addFlashAttribute("mensagem", "Bairro salvo com sucesso");
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/bairro/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar (@ModelAttribute("filtro") Filter filtro, @RequestParam(defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Bairro");
		Page<Bairro> todosBairros = bairroService.pesquisar(numPag, filtro);
		mv.addObject("paginacao", todosBairros);
		
		int page = todosBairros.getPageable().getPageNumber();
		
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo", todosBairros.getPageable().getPageNumber() + 1);
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView telaEdicao (@PathVariable("id") Bairro bairro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(bairro);
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		bairroService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Bairro excluído com sucesso");
		return "redirect:/bairro";
	}
	
	@ModelAttribute("todosBairros")
	public List<Bairro> todosBairros() {
		return bairroService.todos();
	}
	
	@ModelAttribute("todosMunicipios")
	public List<Municipio> todosMunicipios () {
		return municipioService.todos();
	}

	
	@ModelAttribute("todosEstados")
	public List<Estado> todosEstados() {
		List<Estado> estados = estadoService.todos();
		return estados;
	}
}
