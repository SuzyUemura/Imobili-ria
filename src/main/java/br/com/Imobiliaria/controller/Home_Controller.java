package br.com.Imobiliaria.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping ("/home")
public class Home_Controller {
	
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
	
	@GetMapping
	public ModelAndView pagHome() {
		ModelAndView mv = new ModelAndView("Home");
		return mv;
	}
	
	@PostMapping
	public String popularBanco(RedirectAttributes attributes) {
		for (int i = 1; i <11; i ++ ) {
			Negocio n = new Negocio("Negócio " + i);
			Categoria c = new Categoria ("Categoria " + i);
			Quarto q = new Quarto("" + i);
			Estado e = new Estado("EE", "estado " + i);
			Municipio mu = new Municipio("municipio " + i, e);
			Bairro b = new Bairro("bairro " + i, mu, e);
			Imoveis m = new Imoveis ("Imóvel" + i, new BigDecimal("10000"), n, c, e, q,"Avenida testes okay", mu, b);
			
			salvarCampos(n, c, q, e, mu, b, m);
		}
		
		attributes.addFlashAttribute("mensagem", "Banco populado com sucesso");
		return "redirect:/home";
	}

	private void salvarCampos(Negocio n,Categoria c, Quarto q, Estado e, Municipio mu, Bairro b, Imoveis m) {
		negocioService.salvar(n);

		categoriaService.salvar(c);

		quartoService.salvar(q);

		estadoService.salvar(e);
		
		municipioService.salvar(mu);
		
		bairroService.Salvar(b);
		
		service.Salvar(m);
	}

}
