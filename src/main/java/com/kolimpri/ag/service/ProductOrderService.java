package com.kolimpri.ag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolimpri.ag.domain.ProductOrder;
import com.kolimpri.ag.repo.ProductOrderRepository;

@Service
public class ProductOrderService {
	
	private ProductOrderRepository productOrderRepository;

	@Autowired
	public ProductOrderService(ProductOrderRepository productOrderRepository) {
		this.productOrderRepository = productOrderRepository;
	}
	
	public ProductOrder createProductOrder(String code, String name) {
		if (!productOrderRepository.exists(code)) {
			productOrderRepository.save( new ProductOrder(code, name));
		}
		
		return null;
	}
	
	public Iterable<ProductOrder> lookup() {
		return productOrderRepository.findAll();
	}
	
	public long total() {
		return productOrderRepository.count();
	}
}
