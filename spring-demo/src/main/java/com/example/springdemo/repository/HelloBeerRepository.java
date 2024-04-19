package com.example.springdemo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springdemo.model.HelloBeer;

public interface HelloBeerRepository extends JpaRepository<HelloBeer, Long>{
    Page<HelloBeer> findAll(Pageable pageable);
}
