package com.sumauma.dscommerce.services;

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
}
