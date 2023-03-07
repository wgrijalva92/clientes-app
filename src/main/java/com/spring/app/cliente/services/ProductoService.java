package com.spring.app.cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.cliente.entities.Producto;
import com.spring.app.cliente.repositories.IProductoRepository;
import com.spring.app.cliente.services.interfaces.IProductoService;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>)productoRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void delete(Long id) {
		productoRepository.deleteById(id);
		
	}
	
}
