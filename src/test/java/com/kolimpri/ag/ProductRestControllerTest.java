package com.kolimpri.ag;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.kolimpri.ag.domain.Product;
import com.kolimpri.ag.domain.ProductOrder;
import com.kolimpri.ag.domain.Region;
import com.kolimpri.ag.repo.ProductOrderRepository;
import com.kolimpri.ag.repo.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRestControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private List<Product> productList = new LinkedList<>();
	
	private List<ProductOrder> productOrderList = new LinkedList<>();
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductOrderRepository productOrderRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.productRepository.deleteAll();
        this.productOrderRepository.deleteAll();
        
        //Product order data
        ProductOrder productOrder = new ProductOrder("OR0001", "Order 1");
        
        this.productOrderList.add(productOrderRepository.save(productOrder));
        this.productList.add(productRepository.save(
        		new Product("BBB00001", "Product 1", "This is a mock product description.", 4, 
        				Region.findByLabel("Aguascalientes"), productOrder)));
    }
	
	@Test
	public void createProduct() throws Exception {
		mockMvc.perform(post("/products/")
				.content(this.json(new Product()))
				.contentType(contentType))
				.andExpect(status().isCreated());
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
