package com.trillion.iprestapi;
		import com.trillion.iprestapi.data.entity.IpAddress;
		import com.trillion.iprestapi.data.entity.User;
		import com.trillion.iprestapi.data.repository.IP_Address_Repository;
		import com.trillion.iprestapi.data.repository.UserRepository;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class IpRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpRestApiApplication.class, args);
	}

	@RestController
	@RequestMapping("/users")
	public class UserController{
		@Autowired
		private UserRepository userRepository;

		@GetMapping
		public Iterable<User> getUser(){
			return this.userRepository.findAll();
		}
	}

	@RestController
	@RequestMapping("/ip_address")
	public class IpAddressController{
		@Autowired
		private IP_Address_Repository ip_address_repository;

		@GetMapping
		public Iterable<IpAddress> getIpAddress(){
			return this.ip_address_repository.findAll();
		}
	}
}
