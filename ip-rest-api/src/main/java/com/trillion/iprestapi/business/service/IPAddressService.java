package com.trillion.iprestapi.business.service;

import com.trillion.iprestapi.data.entity.IpAddress;
import com.trillion.iprestapi.data.entity.User;
import com.trillion.iprestapi.data.repository.IP_Address_Repository;
import com.trillion.iprestapi.data.repository.UserRepository;
import com.trillion.iprestapi.util.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.LineUnavailableException;
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

    public boolean createIpAddress(String cidr){
        List<IpAddress> existence = ip_address_repository.findOneByCidr(cidr);
        if(existence.size()>0){
            return true;
        }

        List<String> addresses = new ArrayList<>();
        this.ip_address_repository.findAll().forEach(ip ->{
            addresses.add(ip.getIp_address());
        });

        Optional<User> existanceUser = userRepository.findOneByEmail("ab@gmail.com");
        User user = null;
        if(!existanceUser.isPresent()){
            user = userService.createUser("Ahmad","Asad","aa@gamil.com");
            System.out.println("Created:" + user.getEmail());
        }else {
            user = existanceUser.get();
            System.out.println("Found User: " + user.getEmail());
        }
        for(String ip : addresses){
            IpAddress ip_address = new IpAddress();
            ip_address.setCidr_Block(cidr);
            ip_address.setCreatedByUser(user);
            ip_address.setIp_address(ip);
            ip_address.setCurrentStatus(StatusType.AVAILABLE);
            ip_address_repository.save(ip_address);
            System.out.println("New IP Added: "+ ip);

        }
        return true;
    }

    public Boolean acquireIpAddress(String ipAddress) throws Exception{
        Optional<IpAddress> ip_address = ip_address_repository.findByIpAddress(ipAddress);
        if(ip_address.isPresent()){
            IpAddress address = ip_address.get();
            Optional<User> user = userRepository.findOneByEmail("ab@gmail.com");
            address.setUpdatedByUser(user.get());
            address.setCurrentStatus(StatusType.ACQUIRED);
            ip_address_repository.save(address);
            return true;
        }else {
            throw new Exception();
        }
    }

    public Boolean releaseIpAddress(String ipAddress) throws Exception{
        Optional<IpAddress> ip_address = ip_address_repository.findByIpAddress(ipAddress);
        if(ip_address.isPresent()){
            IpAddress address = ip_address.get();
            Optional<User> user = userRepository.findOneByEmail("ab@gmail.com");
            address.setUpdatedByUser(user.get());
            address.setCurrentStatus(StatusType.AVAILABLE);
            ip_address_repository.save(address);
            return true;
        }else{
            throw new Exception();
        }
    }

}
