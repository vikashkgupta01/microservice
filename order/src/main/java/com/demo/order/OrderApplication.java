package com.demo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.EnableAspectJAutoProxy;
>>>>>>> bc5fa73 (First commit with root directory and submodules)

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
		System.out.println("Order started!!!");
	}

}
