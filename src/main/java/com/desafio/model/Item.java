package com.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.desafio.enums.TipoItemEnum;

@Entity
@Table
public class Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3534498942902256092L;

	@Id 
	@GeneratedValue (generator = "system-uuid") 
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator") 
    @Column(name = "id", unique = true, nullable = false, length = 32)
	private UUID uuid;
	
	@NotNull
	@Column(unique = true)
	private String codigo;
	
	@NotNull
	private String titulo;
	
	private String descricao;
	
	@NotNull
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	private TipoItemEnum tipo;
	
	private Boolean ativo;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(@NotNull String codigo, @NotNull String titulo, String descricao, @NotNull BigDecimal valorUnitario,
			TipoItemEnum tipo, Boolean ativo) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.tipo = tipo;
		this.ativo = ativo;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public TipoItemEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoItemEnum tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		Item other = (Item) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
}
