package com.sumauma.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumauma.dscommerce.dto.ProductDTO;
import com.sumauma.dscommerce.entities.Product;
import com.sumauma.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)	
	public ProductDTO findById(Long id) {
		/*
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;
		*/
		
		//código simplificado - não testei
		//Optional<Product> result = repository.findById(id);
		//return new ProductDTO(result.get());
		
		//simplificado
		Product product = repository.findById(id).get();
		return new ProductDTO(product);
							
	}
	/* este método retorna uma lista completa de todos os objetos
	@Transactional(readOnly = true)	
	public List<ProductDTO> findAll() {
		List<Product> listProducts = repository.findAll();
		return listProducts.stream().map(x -> new ProductDTO(x)).toList();
	*/
	
	//este método busca toda uma lista de objetos porém de froma paginada
	//se nada for informado, será paginado de 20 em 20 objetos
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> listProducts = repository.findAll(pageable);
		return listProducts.map(x -> new ProductDTO(x));
									
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
		
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
		
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUri(dto.getImgUri());
		
	}

}
