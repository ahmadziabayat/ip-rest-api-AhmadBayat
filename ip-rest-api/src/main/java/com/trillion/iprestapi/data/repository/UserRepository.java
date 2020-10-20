package com.trillion.iprestapi.data.repository;

import com.trillion.iprestapi.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT uItem FROM User uItem WHERE uItem.email=:email ")
    Optional<User> findOneByEmail(@Param("email") String email);
}
