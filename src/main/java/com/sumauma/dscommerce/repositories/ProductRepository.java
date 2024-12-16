package com.sumauma.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sumauma.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
