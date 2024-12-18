package com.sumauma.dscommerce.dto;

import com.sumauma.dscommerce.entities.Product;

public class ProductDTO {

	private Long id;
	private String name;
	private String description;
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
