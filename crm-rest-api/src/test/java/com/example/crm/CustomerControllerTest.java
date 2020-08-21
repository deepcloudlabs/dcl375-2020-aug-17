package com.example.crm;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.crm.domain.CustomerDocument;
import com.example.crm.service.CustomerMongoService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = CrmRestApiApplication.class )
@AutoConfigureMockMvc
class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private CustomerMongoService mongoSrv;
    
	@Test
	void getCustomersWithPagination_thenReturnsTwoCustomers() throws Exception {
		// Fixture
		CustomerDocument jack = new CustomerDocument();
		jack.setIdentity("1");
		jack.setAddress("istanbul turkey");
		jack.setPhone("+905425555555");
		jack.setEmail("jack@example.com");
		jack.setBirthYear(1956);
		jack.setFullname("Jack Bauer");
		jack.setPhoto(null);
		CustomerDocument kate = new CustomerDocument();
		kate.setIdentity("2");
		kate.setAddress("ankara turkey");
		kate.setPhone("+90542333333");
		kate.setEmail("kate@example.com");
		kate.setBirthYear(1985);
		kate.setFullname("Kate Austen");
		kate.setPhoto(null);
		Mockito.when(mongoSrv.findCustomersWithPagination(0, 2))
		       .thenReturn(List.of(jack,kate));
		// Call Exercise Method + Verification
		mockMvc.perform(get("/customers?page=0&size=2")
				          .contentType(MediaType.APPLICATION_JSON_VALUE))
		       // protocol mapping verification
		       .andExpect(status().isOk()) 
		       // representation verification
		       .andExpect(jsonPath("$", Matchers.hasSize(2)))
		       .andExpect(jsonPath("$[0].identity", is("1")))
		       .andExpect(jsonPath("$[1].identity", is("2")));
	}

}
