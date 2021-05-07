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
public class Bairro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBairro;
	
	@NotBlank (message = "Bairro é obrigatório")
	private String nomeBairro;
	
	@NotNull(message = "Municipio é obrigatório")
	@ManyToOne
	private Municipio municipio;
	
	@NotNull(message = "Estado é obrigatório")
	@ManyToOne 
	private Estado estado;
	
	@OneToMany (mappedBy = "bairro", cascade = CascadeType.ALL)
	private List<Imoveis> imovel;
	
	public Bairro() {
	
	}

	public Bairro(@NotBlank(message = "Bairro é obrigatório") String nomeBairro,
			@NotNull(message = "Municipio é obrigatório") Municipio municipio,
			@NotNull(message = "Estado é obrigatório") Estado estado) {
		super();
		this.nomeBairro = nomeBairro;
		this.municipio = municipio;
		this.estado = estado;
	}


	public Long getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Long idBairro) {
		this.idBairro = idBairro;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
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
		result = prime * result + ((idBairro == null) ? 0 : idBairro.hashCode());
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
		Bairro other = (Bairro) obj;
		if (idBairro == null) {
			if (other.idBairro != null)
				return false;
		} else if (!idBairro.equals(other.idBairro))
			return false;
		return true;
	}

}
