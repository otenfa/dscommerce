package com.sumauma.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		return repository.findAll().stream().map(x -> new ProductDTO(x)).toList();
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
	/*
	 * este método retorna uma lista completa de todos os objetos
	 * 
	 * @Transactional(readOnly = true) public List<ProductDTO> findAll() {
	 * List<Product> listProducts = repository.findAll(); return
	 * listProducts.stream().map(x -> new ProductDTO(x)).toList();
	 */

	// este método busca toda uma lista de objetos porém de froma paginada
	// se nada for informado, será paginado de 20 em 20 objetos

	/*
	 * @Transactional(readOnly = true) public Page<ProductDTO> findAll(String name,
	 * Pageable pageable) { Page<Product> listProducts =
	 * repository.searchByName(name, pageable); return listProducts.map(x -> new
	 * ProductDTO(x));
	 * 
	 * }
	 */
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
