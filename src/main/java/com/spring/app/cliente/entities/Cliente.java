package com.spring.app.cliente.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CLIENTE", schema = "CLIENTEAPP_OW")
@TableGenerator(name = "CLI_ID", initialValue = 1, pkColumnName = "CNT_NOMBRE", pkColumnValue = "CLI_ID", valueColumnName = "CNT_VALOR", allocationSize = 1, table = "CONTADOR", schema = "CLIENTEAPP_OW")
public class Cliente extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLI_ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CLI_ID")
	// @TableGenerator(name = "CLI_ID", schema = "CLIENTEAPP_OW", table =
	// "CONTADOR", pkColumnName = "CNT_NOMBRE", valueColumnName = "CNT_VALOR",
	// pkColumnValue = "CLI_ID", allocationSize = 1)
	private Long cliId;

	@NotEmpty
	@Size(min = 3, max = 255)
	@Column(name = "CLI_NOMBRE", nullable = false)
	private String cliNombre;
	
	@Size(min = 3, max = 255)
	@Column(name = "CLI_APELLIDO")
	private String cliApellido;
	
	@NotEmpty	
	@Size(min = 3, max = 255)
	@Email
	@Column(name = "CLI_EMAIL", nullable = false, unique = true)
	private String cliEmail;
	
	@Size(min = 3, max = 255)
	@Column(name = "CLI_TELEFONO")
	private String cliTelefono;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CLI_REG_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	
	@PrePersist
	public void prePersist() {
		setFecCrea(new Date());
	}

	public Long getCliId() {
		return cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public String getCliNombre() {
		return cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	public String getCliApellido() {
		return cliApellido;
	}

	public void setCliApellido(String cliApellido) {
		this.cliApellido = cliApellido;
	}

	public String getCliEmail() {
		return cliEmail;
	}

	public void setCliEmail(String cliEmail) {
		this.cliEmail = cliEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliId);
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getCliTelefono() {
		return cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cliId, other.cliId);
	}

	@Override
	public String toString() {
		return "Cliente [cliId=" + cliId + ", cliNombre=" + cliNombre + ", cliApellido=" + cliApellido + ", cliEmail="
				+ cliEmail + ", cliTelefono=" + cliTelefono + ", region=" + region + "]";
	}

}
