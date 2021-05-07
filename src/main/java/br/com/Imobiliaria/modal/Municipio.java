package br.com.Imobiliaria.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Municipio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMunicipio;
	
	@NotBlank (message = "Municipio é obrigatório")
	private String nomeMunicipio;
	
	@NotNull(message = "Estado é obrigatório")
	@ManyToOne
	private Estado estado;
	
	@OneToMany (mappedBy = "municipio", cascade = CascadeType.ALL)
	private List<Bairro> bairro;
	
	@OneToMany (mappedBy = "municipio", cascade = CascadeType.ALL)
	private List<Imoveis> imovel;
	
	public Municipio() {
	
	}

	public Municipio(@NotBlank(message = "Municipio é obrigatório") String nomeMunicipio,
			@NotNull(message = "Estado é obrigatório") Estado estado) {
		super();
		this.nomeMunicipio = nomeMunicipio;
		this.estado = estado;
	}



	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public List<Bairro> getBairro() {
		return bairro;
	}

	public void setBairro(List<Bairro> bairro) {
		this.bairro = bairro;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		result = prime * result + ((idMunicipio == null) ? 0 : idMunicipio.hashCode());
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
		Municipio other = (Municipio) obj;
		if (idMunicipio == null) {
			if (other.idMunicipio != null)
				return false;
		} else if (!idMunicipio.equals(other.idMunicipio))
			return false;
		return true;
	}
	
}
