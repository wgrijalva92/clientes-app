package com.spring.app.cliente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.app.cliente.entities.Cliente;
import com.spring.app.cliente.entities.Region;

public interface IClienteRepository extends CrudRepository<Cliente, Long>{

	@Query("from Region")
	public List<Region> findAllRegiones();
}
