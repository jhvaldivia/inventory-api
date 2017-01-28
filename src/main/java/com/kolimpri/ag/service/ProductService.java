package com.kolimpri.ag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolimpri.ag.domain.Product;
import com.kolimpri.ag.domain.ProductOrder;
import com.kolimpri.ag.domain.Region;
import com.kolimpri.ag.repo.ProductOrderRepository;
import com.kolimpri.ag.repo.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	private ProductOrderRepository productOrderRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository, ProductOrderRepository productOrderRepository) {
		this.productRepository = productRepository;
		this.productOrderRepository = productOrderRepository;
	}
	
	public Product createProduct(String barcode, String name, String description, Integer stock, 
			Region region, String productOrderCode) {
		
		ProductOrder productOrder = productOrderRepository.findOne(productOrderCode);
		if (productOrder == null) {
			throw new RuntimeException(("Product order does not exist: " + productOrderCode));
		}
		
		return productRepository.save( new Product(barcode, name, description, stock, region, productOrder) );
	}
	
	public Iterable<Product> lookup() {
		return productRepository.findAll();
	}
	
	public long total() {
		return productRepository.count();
	}
}
