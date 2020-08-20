package com.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.desafio.enums.SituacaoPedidoEnum;

@Entity
@Table
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3392388743403643224L;

	@Id 
	@GeneratedValue (generator = "system-uuid") 
	@GenericGenerator(name = "system-uuid", strategy = "uuid") 
    @Column(name = "id", unique = true, nullable = false, length = 32)
	private UUID uuid;	
			
	private LocalDate data;
	
	private LocalTime hora;
	
	private String observacao;
	
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@OneToMany(mappedBy="pedido",cascade = CascadeType.ALL)
    private Set<ItemPedido> itens = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	private SituacaoPedidoEnum situacao;
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public SituacaoPedidoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPedidoEnum situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		Pedido other = (Pedido) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	

}
