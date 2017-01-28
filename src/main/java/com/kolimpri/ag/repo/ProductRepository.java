package com.kolimpri.ag.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kolimpri.ag.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findByProductOrderCode(@Param("code") String code);
}
