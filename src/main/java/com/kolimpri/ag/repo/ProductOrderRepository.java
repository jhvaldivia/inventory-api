package com.kolimpri.ag.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kolimpri.ag.domain.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, String> {
	
	ProductOrder findByName(@Param("name") String name);
}
