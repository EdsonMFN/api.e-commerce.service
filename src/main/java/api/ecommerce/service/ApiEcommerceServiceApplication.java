package api.ecommerce.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiEcommerceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEcommerceServiceApplication.class, args);

	}

}
