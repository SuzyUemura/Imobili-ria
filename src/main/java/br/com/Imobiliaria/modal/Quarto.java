package br.com.Imobiliaria.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Quarto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idQuarto;
	
	@NotNull(message = "Quantidade é obrigatória")
	@PositiveOrZero (message = "Quantidade de quartos tem que ser igual ou maior que 0")
	private String quantidade;
	
	@OneToMany (mappedBy = "quarto", cascade = CascadeType.ALL)
	private List<Imoveis> imovel;
	
	public Quarto() {
	
	}

	public Quarto(
			@NotNull(message = "Quantidade é obrigatória") @PositiveOrZero(message = "Quantidade de quartos tem que ser igual ou maior que 0") String quantidade) {
		super();
		this.quantidade = quantidade;
	}



	public Long getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(Long idQuarto) {
		this.idQuarto = idQuarto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
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
		result = prime * result + ((idQuarto == null) ? 0 : idQuarto.hashCode());
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
		Quarto other = (Quarto) obj;
		if (idQuarto == null) {
			if (other.idQuarto != null)
				return false;
		} else if (!idQuarto.equals(other.idQuarto))
			return false;
		return true;
	}
	
	
}
