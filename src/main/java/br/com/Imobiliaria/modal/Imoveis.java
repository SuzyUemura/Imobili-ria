package br.com.Imobiliaria.modal;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Imoveis {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "Nome do imóvel é obrigatório")
	private String nomeImovel;
	
	@NotNull (message = "Preço é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor não pode ser menor que 0,01")
	@DecimalMax(value = "9999999.99", message = "O preço nao pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal preco;
	
	@NotNull(message = "Negócio é obrigatório")
	@ManyToOne 
	private Negocio negocio;
	
	@NotNull(message = "Categoria é obrigatório")
	@ManyToOne 
	private Categoria categoria;
	
	@NotNull(message = "Estado é obrigatório")
	@ManyToOne 
	private Estado estado;
	
	@NotNull(message = "Quarto é obrigatório")
	@ManyToOne 
	private Quarto quarto;
	
	@NotBlank (message = "Endereço é obrigatório")
	private String endereco;
	
	@NotNull (message = "Municipio é obirgatório")
	@ManyToOne
	private Municipio municipio;
	
	@NotNull (message = "Bairro é obrigatório")
	@ManyToOne
	private Bairro bairro;
	
	public Imoveis() {
		
	}
	public Imoveis(String nomeImovel, BigDecimal preco, Negocio negocio, Categoria categoria, Estado estado,
			Quarto quarto, String endereco, Municipio municipio, Bairro bairro) {
		super();
		this.nomeImovel = nomeImovel;
		this.preco = preco;
		this.negocio = negocio;
		this.categoria = categoria;
		this.estado = estado;
		this.quarto = quarto;
		this.endereco = endereco;
		this.municipio = municipio;
		this.bairro = bairro;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Negocio getNegocio() {
		return negocio;
	}
	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Quarto getQuarto() {
		return quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	public String getNomeImovel() {
		return nomeImovel;
	}
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imoveis other = (Imoveis) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
}
