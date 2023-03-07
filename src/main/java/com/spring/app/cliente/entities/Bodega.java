package com.spring.app.cliente.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "BODEGA", schema = "CLIENTEAPP_OW")
@TableGenerator(name = "BOD_ID", initialValue = 1, pkColumnName = "CNT_NOMBRE", pkColumnValue = "BOD_ID", valueColumnName = "CNT_VALOR", allocationSize = 1, table = "CONTADOR", schema = "CLIENTEAPP_OW")
public class Bodega extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "REG_ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "REG_ID")
	private Long regId;
	
	@Column(name = "REG_NOMBRE")
	private String regNombre;

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public String getRegNombre() {
		return regNombre;
	}

	public void setRegNombre(String regNombre) {
		this.regNombre = regNombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(regId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bodega other = (Bodega) obj;
		return Objects.equals(regId, other.regId);
	}

	@Override
	public String toString() {
		return "Region [regId=" + regId + ", regNombre=" + regNombre + "]";
	}

}
