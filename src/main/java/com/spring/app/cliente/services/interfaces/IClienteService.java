package com.spring.app.cliente.services.interfaces;

import java.util.List;

import com.spring.app.cliente.entities.Cliente;
import com.spring.app.cliente.entities.Region;

public interface IClienteService {
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public Cliente save(Cliente cliente);
	public void delete(Long id);
	public List<Region> findAllRegiones();
	
}
