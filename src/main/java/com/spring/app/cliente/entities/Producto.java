package com.spring.app.cliente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "PRODUCTOS", schema = "CLIENTEAPP_OW")
@TableGenerator(name = "PROD_ID", initialValue = 1, pkColumnName = "CNT_NOMBRE", pkColumnValue = "PROD_ID", valueColumnName = "CNT_VALOR", allocationSize = 1, table = "CONTADOR", schema = "CLIENTEAPP_OW")
public class Producto extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROD_ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROD_ID")
	private Long prodId;

	@NotEmpty
	@Size(min = 3, max = 300)
	@Column(name = "PROD_NOMBRE", nullable = false)
	private String prodNombre;
	
	@Size(min = 3, max = 1000)
	@Column(name = "PROD_DETALLE")
	private String prodDetalle;
	
	@NotNull
	@Column(name = "PROD_PRECIO", nullable = false)
	private BigDecimal prodPrecio;
	
	@NotNull
	@Column(name = "PROD_EXISTENCIAS", nullable = false)
	private BigDecimal prodExistencias;
	
	@NotNull
	@Column(name = "PROD_EXISTENCIA_MIN", nullable = false)
	private BigDecimal prodExistenciaMin;
	
	@NotNull
	@Column(name = "PROD_ESTADO", nullable = false)
	private BigDecimal prodEstado;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PROD_BODEGA_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Bodega bodega;*/
	

	@Override
	public int hashCode() {
		return Objects.hash(prodId);
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdNombre() {
		return prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public String getProdDetalle() {
		return prodDetalle;
	}

	public void setProdDetalle(String prodDetalle) {
		this.prodDetalle = prodDetalle;
	}

	public BigDecimal getProdPrecio() {
		return prodPrecio;
	}

	public void setProdPrecio(BigDecimal prodPrecio) {
		this.prodPrecio = prodPrecio;
	}

	public BigDecimal getProdExistencias() {
		return prodExistencias;
	}

	public void setProdExistencias(BigDecimal prodExistencias) {
		this.prodExistencias = prodExistencias;
	}

	public BigDecimal getProdExistenciaMin() {
		return prodExistenciaMin;
	}

	public void setProdExistenciaMin(BigDecimal prodExistenciaMin) {
		this.prodExistenciaMin = prodExistenciaMin;
	}

	public BigDecimal getProdEstado() {
		return prodEstado;
	}

	public void setProdEstado(BigDecimal prodEstado) {
		this.prodEstado = prodEstado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(prodId, other.prodId);
	}

	@Override
	public String toString() {
		return "Producto [prodId=" + prodId + ", prodNombre=" + prodNombre + ", prodDetalle=" + prodDetalle
				+ ", prodPrecio=" + prodPrecio + ", prodExistencias=" + prodExistencias + ", prodExistenciaMin="
				+ prodExistenciaMin + ", prodEstado=" + prodEstado + "]";
	}

}
