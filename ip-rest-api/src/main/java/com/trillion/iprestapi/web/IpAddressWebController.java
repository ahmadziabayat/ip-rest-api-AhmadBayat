package com.trillion.iprestapi.web;

import com.trillion.iprestapi.business.service.IPAddressService;
import com.trillion.iprestapi.data.entity.IpAddress;
import com.trillion.iprestapi.data.repository.IP_Address_Repository;
import com.trillion.iprestapi.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="api/ip")
public class IpAddressWebController {

    private final IPAddressService ip_address_service;
    private final IP_Address_Repository ip_address_repository;
    private final UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IpAddressWebController(IPAddressService ip_address_service, IP_Address_Repository ip_address_repository, UserRepository userRepository) {
        this.ip_address_service = ip_address_service;
        this.ip_address_repository = ip_address_repository;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/getAllIpAddress", method = RequestMethod.GET)
    public ResponseEntity<List<IpAddress>> getAllIpAddress() {
        Iterable<IpAddress> ipList = this.ip_address_repository.findAll();
        List<IpAddress> IpAddressList = new ArrayList<>();
        try {
            ipList.forEach(item -> {
                IpAddressList.add(item);
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(IpAddressList, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createIpAddresses(@RequestBody IpAddress cidrBlock) {
        try {
            System.out.println(cidrBlock);
            String cidr = cidrBlock.getCidr_Block();
            System.out.println("cidrBlock: " + cidr);

            String emailAddress = cidrBlock.getCreatedByUser().getEmail();
            boolean createNewIpAddress = ip_address_service.createIpAddress(cidr);

            if (!createNewIpAddress) {
                System.out.println(createNewIpAddress);
                System.out.println("email Address: " + emailAddress);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
