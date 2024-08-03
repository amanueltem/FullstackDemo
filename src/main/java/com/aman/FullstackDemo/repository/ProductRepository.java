package com.aman.FullstackDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aman.FullstackDemo.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
