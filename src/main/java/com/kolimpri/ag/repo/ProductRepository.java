package com.kolimpri.ag.repo;

import org.springframework.data.repository.CrudRepository;

import com.kolimpri.ag.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
