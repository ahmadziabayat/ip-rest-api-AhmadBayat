package com.trillion.iprestapi.business.service;

import com.trillion.iprestapi.data.entity.IpAddress;
import com.trillion.iprestapi.data.entity.User;
import com.trillion.iprestapi.data.repository.IP_Address_Repository;
import com.trillion.iprestapi.data.repository.UserRepository;
import com.trillion.iprestapi.util.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IPAddressService {

    private final IP_Address_Repository ip_address_repository;
    private final UserRepository userRepository;
    private final userService userService;

    @Autowired
    public IPAddressService(IP_Address_Repository ip_address_repository, UserRepository userRepository, com.trillion.iprestapi.business.service.userService userService) {
        this.ip_address_repository = ip_address_repository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public Iterable<IpAddress> getAllIpAddress(){
     return this.ip_address_repository.findAll();
    }

    public boolean createIpAddress(String cidr, String email){
        List<IpAddress> listCount = new ArrayList<>();
        this.ip_address_repository.findAll().forEach(items ->{
            if(items.getCidr_Block() == cidr){
                listCount.add(items);
            }
        });
        if (listCount.size() > 0){
            return true;
        }

        List<String> addresses = new ArrayList<>();

        this.ip_address_repository.findAll().forEach(ip_address ->{
            addresses.add(ip_address.getIp_address());
        });

       List<User> userExist = new ArrayList<>();
      this.userRepository.findAll().forEach(user ->{
            if (user.getEmail() == email){
                userExist.add(user);
            }
        });
      User user = null;

      if (userExist.size() == 0){
          user = this.userService.createUser("testFirstName", "testLast", email);
          System.out.println("User Created"+ user.getEmail());
      }else {
          for(String address : addresses){
              IpAddress ipAddress = new IpAddress();
              ipAddress.setCidr_Block(cidr);
              ipAddress.setCreatedByUser(user);
              ipAddress.setIp_address(address);
              ipAddress.setCurrentStatus(StatusType.AVAILABLE);
              ip_address_repository.save(ipAddress);
              System.out.println("Created new Ip Address");
          }
      }
        return true;
    }

}
