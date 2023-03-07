package com.spring.app.cliente.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.app.cliente.entities.Producto;

public interface IProductoRepository extends CrudRepository<Producto, Long>{

}
