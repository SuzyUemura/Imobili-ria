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
import br.com.Imobiliaria.modal.Categoria;
import br.com.Imobiliaria.modal.Estado;
import br.com.Imobiliaria.modal.Imoveis;
import br.com.Imobiliaria.modal.Municipio;
import br.com.Imobiliaria.modal.Negocio;
import br.com.Imobiliaria.modal.Quarto;
import br.com.Imobiliaria.service.Bairro_Service;
import br.com.Imobiliaria.service.Categoria_Service;
import br.com.Imobiliaria.service.Estado_Service;
import br.com.Imobiliaria.service.Imoveis_Service;
import br.com.Imobiliaria.service.Municipio_Service;
import br.com.Imobiliaria.service.Negocio_Service;
import br.com.Imobiliaria.service.Quarto_Service;

@Controller
@RequestMapping("/imoveis")
public class Imoveis_Controller {
	
	private String CADASTRO_VIEW = "CadImoveis";
	
	@Autowired
	private Imoveis_Service service;
	
	@Autowired
	private Estado_Service estadoService;
	
	@Autowired
	private Municipio_Service municipioService;
	
	@Autowired
	private Bairro_Service bairroService;
	
	@Autowired
	private Quarto_Service quartoService;
	
	@Autowired
	private Categoria_Service categoriaService;
	
	@Autowired
	private Negocio_Service negocioService;
	
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Imoveis());
		return mv;
	}
	
	@PostMapping 
	public String salvar (@Validated Imoveis imovel, Errors error, RedirectAttributes attributes ) {
		
		if(error.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			service.Salvar(imovel);
			attributes.addAttribute("mensagem", "Imóvel salvo com sucesso");
		} catch (Exception e) {
			e.getMessage();
		}
		
		return "redirect:/imoveis/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") Filter filtro, @RequestParam(defaultValue = "0") String numPage) {
		int numPag = Integer.parseInt(numPage);
		
		ModelAndView mv = new ModelAndView("Imoveis");
		
		Page<Imoveis> imoveis = service.paginar(numPag, filtro);
		mv.addObject("paginacao", imoveis.getContent());
		
		int page = imoveis.getPageable().getPageNumber();
		
		mv.addObject("pageAnterior", page <= 0 ? 0 : page - 1);
		mv.addObject("pageProximo",  imoveis.getPageable().getPageNumber() + 1);
		return mv;
	}
	
	@DeleteMapping ("/{id}")
	public String excluir (@PathVariable Long id, RedirectAttributes attributes) {
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Imóvel excluído com sucesso");
		
		return "redirect:/imoveis";
	}
	
	
	@GetMapping("{id}")
	public ModelAndView telaEdicao (@PathVariable ("id") Imoveis imovel) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(imovel);
		return mv;
	}
	
	@ModelAttribute("todosImoveis")
	public List<Imoveis> todosImoveis(){
		List<Imoveis> imoveis = service.todos();
		return imoveis;
	}
	
	@ModelAttribute("todosNegocios") 
	public List<Negocio> todosNegocios() {
		List<Negocio> negocios = negocioService.todos();
		return negocios;
	}
	
	@ModelAttribute("todasCategorias")
	public List<Categoria> todasCategorias() {
		List<Categoria> categorias = categoriaService.todos();
		return categorias;
	}
	
	@ModelAttribute("todosEstados")
	public List<Estado> todosEstados() {
		List<Estado> estados = estadoService.todos();
		return estados;
	}
	
	@ModelAttribute("todosMunicipios")
	public List<Municipio> todosMunicipio(){
		List<Municipio> municipio = municipioService.todos();
		return municipio;
	}
	
	@ModelAttribute("todosBairros")
	public List<Bairro> todosBairros(){
		List<Bairro> bairros = bairroService.todos();
		return bairros;
	}
	
	@ModelAttribute("todosQuartos")
	public List<Quarto> todosQuartos() {
		List<Quarto> quartos = quartoService.todos();
		return quartos;
	}

}
