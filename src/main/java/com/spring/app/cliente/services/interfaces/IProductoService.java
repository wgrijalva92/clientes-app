package com.spring.app.cliente.services.interfaces;

import java.util.List;

import com.spring.app.cliente.entities.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void delete(Long id);
	
}
