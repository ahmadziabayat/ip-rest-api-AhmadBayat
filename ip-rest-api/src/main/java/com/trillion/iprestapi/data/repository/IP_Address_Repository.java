package com.trillion.iprestapi.data.repository;

import com.trillion.iprestapi.data.entity.IpAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface IP_Address_Repository extends CrudRepository<IpAddress, Long> {

    @Query(value = "SELECT ip FROM IpAddress ip where ip.ip_address=:ip_address")
    Optional<IpAddress> findByIpAddress(@Param("ip_address") String ip_address);

    @Query(value = "SELECT i FROM IpAddress i WHERE i.cidr_Block=:cidr ")
    List<IpAddress> findOneByCidr(@Param("cidr") String cidr);
}
