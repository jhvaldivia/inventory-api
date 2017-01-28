package com.kolimpri.ag.repo;

import org.springframework.data.repository.CrudRepository;

import com.kolimpri.ag.domain.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer> {

}
