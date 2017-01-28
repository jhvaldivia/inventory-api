package com.kolimpri.ag.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String barcode;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Integer stock;
	
	@Column
	private Region region;
	
	@ManyToOne
	private ProductOrder productOrder;
	
	//So the class can only be fully instantiated.
	protected Product() {};

	public Product(String barcode, String name, String description, Integer stock, Region region, ProductOrder productOrder) {
		this.barcode = barcode;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.region = region;
		this.productOrder = productOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
}
