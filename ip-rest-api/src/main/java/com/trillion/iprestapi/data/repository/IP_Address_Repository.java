package com.trillion.iprestapi.data.repository;

import com.trillion.iprestapi.data.entity.IpAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IP_Address_Repository extends CrudRepository<IpAddress, Long> {

}
