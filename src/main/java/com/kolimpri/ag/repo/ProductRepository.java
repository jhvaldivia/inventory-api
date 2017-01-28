package com.kolimpri.ag.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kolimpri.ag.domain.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	Page<Product> findByProductOrderCode(@Param("code") String code, Pageable pageable);
}
