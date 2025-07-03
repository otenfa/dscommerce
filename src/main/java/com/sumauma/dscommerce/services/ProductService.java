package com.sumauma.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sumauma.dscommerce.dto.ProductDTO;
import com.sumauma.dscommerce.entities.Product;
import com.sumauma.dscommerce.repositories.ProductRepository;
import com.sumauma.dscommerce.services.customExceptions.DataBaseException;
import com.sumauma.dscommerce.services.customExceptions.RessourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	/*retorna uma lista com todos os produtos*/
//	@Transactional(readOnly = true)
//	public List<ProductDTO> findAll() {
//		return repository.findAll().stream().map(x -> new ProductDTO(x)).toList();
//	}

//	/*retorna uma lista com todos os produtos - paginados*/
//	@Transactional(readOnly = true)
//	public Page<ProductDTO> findAll(Pageable pageable) {
//		Page<Product> result = repository.findAll(pageable); 
//		return result.map(x -> new ProductDTO(x));
//	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(x -> new ProductDTO(x));
	}

	
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		/*
		 * Optional<Product> result = repository.findById(id); Product product =
		 * result.get(); ProductDTO dto = new ProductDTO(product); return dto;
		 */

		// código simplificado - não testei
		// Optional<Product> result = repository.findById(id);
		// return new ProductDTO(result.get());
		// constructor do objeto DTO recebeno uma entidade produto como parametro
		// Product product = repository.findById(id).get();
		// return new ProductDTO(product);

		// usando os métodos setter para popular a projeção do produto no DTO
		// Product product = repository.findById(id).get();
		// ProductDTO dto = new ProductDTO();
		// dto.setId(product.getId());
		// dto.setName(product.getName());
		// dto.setDescription(product.getDescription());
		// dto.setPrice(product.getPrice());
		// dto.setImgUri(product.getImgUri());
		// return dto;
		// simplificado
		Product product = repository.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("Recurso não encontrado"));
		return new ProductDTO(product);

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
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new RessourceNotFoundException("Recurso não encontrado");
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RessourceNotFoundException("recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}

	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUri(dto.getImgUri());

	}

}
