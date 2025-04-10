package com.sumauma.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ProductDTO> findAll() {
		return repository.findAll().stream().map(x -> new ProductDTO(x)).toList();
	}
	
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
		
		//constructor do objeto DTO recebeno uma entidade produto como parametro
		//Product product = repository.findById(id).get();
		//return new ProductDTO(product);
							
		//usando os métodos setter para popular a projeção do produto no DTO
		Product product = repository.findById(id).get();
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setImgUri(product.getImgUri());
		return dto;
		
	}
}
