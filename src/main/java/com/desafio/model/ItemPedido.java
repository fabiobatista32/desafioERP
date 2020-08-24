package com.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class ItemPedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2406397736369785812L;

	@Id 
	@GeneratedValue (generator = "system-uuid") 
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator") 
    @Column(name = "id", unique = true, nullable = false, length = 32)
	private UUID uuid;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_item")
	private Item item;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	private BigDecimal valorUnitario;
	
	private Integer quantidade;
	
	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemPedido(Item item, Pedido pedido, BigDecimal valorUnitario, Integer quantidade) {
		super();
		this.item = item;
		this.pedido = pedido;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	/**
     * Retorna o valor total do item pedido. 
     * O valor total do item pedido será valor unitário do item multiplicado pela quantidade
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
    	return valorUnitario != null ? valorUnitario.multiply(new BigDecimal(quantidade)) : BigDecimal.ZERO;
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
		ItemPedido other = (ItemPedido) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
