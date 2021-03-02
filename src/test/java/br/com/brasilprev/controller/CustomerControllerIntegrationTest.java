package br.com.brasilprev.controller;

import br.com.brasilprev.service.CustomerService;
import br.com.brasilprev.service.dto.AddressDto;
import br.com.brasilprev.service.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CustomerService customerService;

    private MockMvc mvc;

    private final String URL = "/api/customer/";
    private final int PAGE = 0;
    private final int SIZE = 20;
    private final String NAME = "Herbert Pancini de Andrade";
    private final String STREET = "Rua Manoel Gomes, 15, Nova Bethânia";

    private CustomerDto getCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(NAME);
        customerDto.setCpf("11352818744");
        customerDto.setAddress(this.getAddressDto());
        return customerDto;
    }

    private AddressDto getAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("Rua Manoel Gomes, 15");
        addressDto.setCity("Viana");
        addressDto.setRegion("Espírito Santo");
        addressDto.setPostalCode("29138241");
        addressDto.setCountry("Brasil");
        return addressDto;
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test01_saveSuccess() throws Exception {
        this.mvc.perform(post(URL)
                .content(convertObjectToJsonBytes(this.getCustomerDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void test02_saveBadRequest() throws Exception {
        this.mvc.perform(post(URL)
                .content(convertObjectToJsonBytes(this.getCustomerDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test03_updateSuccess() throws Exception {
        CustomerDto customerDto = customerService.findByNameContainingIgnoreCase(NAME, PageRequest.of(PAGE, SIZE)).getContent().get(0);
        customerDto.getAddress().setStreet(STREET);

        this.mvc.perform(put(URL + customerDto.getId())
                .content(convertObjectToJsonBytes(customerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test04_findByNameSuccess() throws Exception {
        this.mvc.perform(get(URL + "?name= " + NAME + "&page=" + PAGE + "&size="+ SIZE))
                .andExpect(status().isOk());
    }

    @Test
    public void test05_findByIdSuccess() throws Exception {
        CustomerDto customerDto = customerService.findByNameContainingIgnoreCase(NAME, PageRequest.of(PAGE, SIZE)).getContent().get(0);
        this.mvc.perform(get(URL + customerDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo(NAME)));
    }

    @Test
    public void test06_findByIdNoContent() throws Exception {
        this.mvc.perform(get(URL + "/0"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test07_deleteByIdSuccess() throws Exception {
        CustomerDto customerDto = customerService.findByNameContainingIgnoreCase(NAME, PageRequest.of(PAGE, SIZE)).getContent().get(0);
        this.mvc.perform(delete(URL + customerDto.getId()))
                .andExpect(status().isNoContent());
    }
}
