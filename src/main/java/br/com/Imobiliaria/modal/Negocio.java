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
public class Negocio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNegocio;
	
	@NotBlank (message = "O campo négocio é obrigatório")
	@Size(max = 40, message = "O campo negócio não pode conter mais de 40 caracteres")
	private String nomeNegocio;
	
	@OneToMany (mappedBy = "negocio", cascade = CascadeType.ALL)
	private List<Imoveis> imovel;
	
	public Negocio() {
		
	}

	public Negocio (String nomeNegocio) {
		super();
		this.nomeNegocio = nomeNegocio;
	}



	public Long getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Long idNegocio) {
		this.idNegocio = idNegocio;
	}

	public String getNomeNegocio() {
		return nomeNegocio;
	}

	public void setNomeNegocio(String nomeNegocio) {
		this.nomeNegocio = nomeNegocio;
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
		result = prime * result + ((idNegocio == null) ? 0 : idNegocio.hashCode());
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
		Negocio other = (Negocio) obj;
		if (idNegocio == null) {
			if (other.idNegocio != null)
				return false;
		} else if (!idNegocio.equals(other.idNegocio))
			return false;
		return true;
	}
	
	
	
}
