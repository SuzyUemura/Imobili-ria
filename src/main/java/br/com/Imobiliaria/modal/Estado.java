package br.com.Imobiliaria.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstado;
	
	@NotBlank (message = "A unidade federativa é obrigatória")
	@Size (max = 2, min = 2, message = "A unidade federativa contém apenas duas letras")
	private String uf;
	
	@NotBlank(message = "Estado é obrigatório")
	private String nomeEstado;
	
	@OneToMany (mappedBy = "estado", cascade = CascadeType.ALL)
	private List<Municipio> municipio;
	
	@OneToMany (mappedBy = "estado", cascade = CascadeType.ALL)
	private List<Bairro> bairro;
	
	@OneToMany (mappedBy = "estado", cascade = CascadeType.ALL)
	private List<Imoveis> imovel;
	
	public Estado() {
	
	}
	
	public Estado(
			@NotBlank(message = "A unidade federativa é obrigatória") @Size(max = 2, min = 2, message = "A unidade federativa contém apenas duas letras") String uf,
			@NotBlank(message = "Estado é obrigatório") String nomeEstado) {
		super();
		this.uf = uf;
		this.nomeEstado = nomeEstado;
	}


	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public List<Municipio> getMunicipio() {
		return municipio;
	}

	public void setMunicipio(List<Municipio> municipio) {
		this.municipio = municipio;
	}

	public List<Bairro> getBairro() {
		return bairro;
	}

	public void setBairro(List<Bairro> bairro) {
		this.bairro = bairro;
	}

	public List<Imoveis> getImovel() {
		return imovel;
	}

	public void setImovel(List<Imoveis> imovel) {
		this.imovel = imovel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstado == null) ? 0 : idEstado.hashCode());
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
		Estado other = (Estado) obj;
		if (idEstado == null) {
			if (other.idEstado != null)
				return false;
		} else if (!idEstado.equals(other.idEstado))
			return false;
		return true;
	}

	
	
}
