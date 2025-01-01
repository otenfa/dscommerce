package com.sumauma.dscommerce.dto;

import com.sumauma.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "não pode ser nulo")
	@Size(min=3, max=80, message="deve conter entre 3 e 80 caracteres")
	private String name;

	@NotBlank(message = "não pode ser nulo")
	@Size(min=10, message="deve conter no mínimo 10 caracteres")
	private String description;
	
	@Positive(message="deve ser um valor positivo")
	private Double price;
	private String imgUri;
	
	public ProductDTO() {		
	}

	public ProductDTO(Long id, String name, String description, Double price, String imgUri) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUri = imgUri;
	}
	
	public ProductDTO(Product entity) {
		super();
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUri = entity.getImgUri();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUri() {
		return imgUri;
	}
	
}
