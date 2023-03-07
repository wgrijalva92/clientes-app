package com.spring.app.cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.cliente.entities.Cliente;
import com.spring.app.cliente.entities.Region;
import com.spring.app.cliente.repositories.IClienteRepository;
import com.spring.app.cliente.services.interfaces.IClienteService;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);
		
	}

	@Override
	public List<Region> findAllRegiones() {
		return (List<Region>)clienteRepository.findAllRegiones();
	}
	
	
}
