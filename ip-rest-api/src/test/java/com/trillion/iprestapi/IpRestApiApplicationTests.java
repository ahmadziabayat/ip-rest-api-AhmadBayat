package com.trillion.iprestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trillion.iprestapi.business.service.IPAddressService;
import com.trillion.iprestapi.data.entity.IpAddress;
import com.trillion.iprestapi.data.entity.User;
import com.trillion.iprestapi.data.repository.IP_Address_Repository;
import com.trillion.iprestapi.data.repository.UserRepository;
import com.trillion.iprestapi.util.StatusType;
import com.trillion.iprestapi.web.IpAddressWebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



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

   	    ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(ipAddress);

		mockMvc.perform(post(URL + "/create")
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
				.content(requestJson))
				.andExpect(status().is5xxServerError());

	}

	public static String objectToJson(Object obj) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
