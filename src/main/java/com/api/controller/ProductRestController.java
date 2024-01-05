package com.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.bean.Product;

@RestController
public class ProductRestController {
	
	static List<Product> products = new ArrayList<>();
	
	static {
		Product p1 = new Product(1, "TV", 24000, "Samsung curved TV");
		Product p2 = new Product(2, "Smart Mobile", 13000, "Lenovo Touch mobile");
		Product p3 = new Product(3, "Bike", 2_00_000, "TVS Bike");
		
		products.add(p1);
		products.add(p2);
		products.add(p3);
	}
	
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return products;
	}
	
	
	@GetMapping("/product")
	public   ResponseEntity<Product>  getproductById(@RequestParam(name = "id", defaultValue = "0") Integer id) {
		System.out.println("getproductById - > "+id);
		
		Optional<Product> matchingProduct  = products.stream().filter( i-> i.getId() == id).findFirst();
		
		
		return matchingProduct.map(product -> ResponseEntity.ok().body(product))
		        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product(0, "NOT FOUND", 0, "PRODUCT NOT FOUND")));
	}
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		
		int lastProductId = products.get(products.size()-1).getId();
		System.out.println(lastProductId);
		int newId = lastProductId+1;
		
		Product addProduct = new Product(newId, product.getName(), product.getPrice(), product.getDescription());
		products.add(addProduct);
		return addProduct;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
