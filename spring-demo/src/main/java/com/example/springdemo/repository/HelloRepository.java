package com.example.springdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.Repository;
//import org.springframework.data.jpa.repository.Query;

import com.example.springdemo.model.HelloModel;


// import java.util.List;

public interface HelloRepository  extends JpaRepository<HelloModel, Long>{
    Optional<HelloModel> findByUsernameAndPassword(String username, String password);

    // @Query(value = "SELECT * FROM hello_model WHERE username = ?1 AND paasword = ?2", nativeQuery = true)
    // HelloModel finduser(String username, String password);
}
