package com.kolimpri.ag;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolimpri.ag.domain.Region;
import com.kolimpri.ag.service.ProductOrderService;
import com.kolimpri.ag.service.ProductService;

@SpringBootApplication
public class AgApplication implements CommandLineRunner{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductOrderService productOrderService;
	
	public static void main(String[] args) {
		SpringApplication.run(AgApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//Create default ProductOrders.
		productOrderService.createProductOrder("OR00001", "Order 1");
		productOrderService.createProductOrder("OR00002", "Order 2");
		productOrderService.createProductOrder("OR00003", "Order 3");
		productOrderService.createProductOrder("OR00004", "Order 4");
		productOrderService.lookup().forEach(productOrder -> System.out.println(productOrder));
		
		ProductFromFile.importProducs().forEach(p -> productService.createProduct(p.barcode, p.name, p.description, 
				Integer.parseInt(p.stock), Region.findByLabel(p.region), p.productOrder));
		System.out.println("Number of products=" + productService.total());
	}
	
	static class ProductFromFile {
		
		private String barcode, name, description, stock, region, productOrder;
		
		static List<ProductFromFile> importProducs() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
					.readValue(ProductFromFile.class.getResourceAsStream("/products.json"), 
							new TypeReference<List<ProductFromFile>>(){});
		}
	}
}
