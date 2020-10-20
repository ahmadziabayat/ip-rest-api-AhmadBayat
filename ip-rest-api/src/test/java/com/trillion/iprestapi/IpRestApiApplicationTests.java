package com.trillion.iprestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trillion.iprestapi.business.service.IPAddressService;
import com.trillion.iprestapi.data.entity.IpAddress;
import com.trillion.iprestapi.data.entity.User;
import com.trillion.iprestapi.data.repository.IP_Address_Repository;
import com.trillion.iprestapi.data.repository.UserRepository;
import com.trillion.iprestapi.util.StatusType;
import com.trillion.iprestapi.web.IpAddressWebController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(IpAddressWebController.class)
public class IpRestApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	IPAddressService ipAddressService;
	@MockBean
	IP_Address_Repository ip_address_repository;

	@MockBean
	UserRepository userRepository;

	private final String URL = "http://localhost:8080/api/ip";

	@Test
	public void testAddIpAddress() throws Exception {


		IpAddress ipAddress = new IpAddress();
		ipAddress.setCidr_Block("192.168.100.0/22");
		ipAddress.setIp_address("");
		ipAddress.setCurrentStatus(StatusType.AVAILABLE);

		User user = new User();

		user.setFirstName("Ahmad");
		user.setLastName("Bayat");
		user.setEmail("ahmad.bayatTest@gmail.com");

		ipAddress.setCreatedByUser(user);


		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(objectToJson(ipAddress))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		Assert.assertEquals("success", HttpStatus.OK.value(), status);



	}

	/*public static <T> T jsonToObject(String json, Class<T> classOf){
		Gson gs = new Gson();
		return gs.fromJson(json,classOf);
	}*/

	public static String objectToJson(Object obj) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
