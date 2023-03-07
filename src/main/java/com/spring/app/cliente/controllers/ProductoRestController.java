package com.spring.app.cliente.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.cliente.entities.Producto;
import com.spring.app.cliente.services.interfaces.IProductoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/productos")
	public List<Producto> index(){
		return productoService.findAll();
	}
	
	@GetMapping("productos/{id}")
	public ResponseEntity<?> getProducto(@PathVariable Long id) {
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> save(@Valid @RequestBody Producto producto, BindingResult result) {
		Producto newProducto = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors =  result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			producto.setFecCrea(new Date());
			producto.setUsuCrea("wilmer.grijalva");//TODO setear con datos de sesion
			producto.setProdEstado(BigDecimal.ONE);
			producto.setRegActivo(1);
			newProducto = productoService.save(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar registro en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put("mensaje", "Error al insertar registro en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Producto creado exitosamente");
		response.put("producto", newProducto);
		return new ResponseEntity<Map<String,Object>> (response, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
		Producto productoactual = null;
		Producto productoupdated = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors =  result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			productoactual = productoService.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(productoactual == null) {
			response.put("mensaje", "Error. El producto ID: ".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			productoactual.setProdNombre(producto.getProdNombre());
			productoactual.setProdDetalle(producto.getProdDetalle());
			productoactual.setProdExistencias(producto.getProdExistencias());
			productoactual.setProdExistenciaMin(producto.getProdExistenciaMin());
			productoactual.setProdDetalle(producto.getProdDetalle());
			productoactual.setFecModi(new Date());
			productoactual.setUsuModi("wilmer.grijalva"); //TODO setear con datos de sesión
			
			productoupdated = productoService.save(productoactual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put("mensaje", "Error al actualizar producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Producto actualizado exitosamente");
		response.put("producto", productoupdated);
		return new ResponseEntity<Map<String,Object>> (response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.delete(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put("mensaje", "Error al eliminar producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Producto eliminado exitosamente");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}
