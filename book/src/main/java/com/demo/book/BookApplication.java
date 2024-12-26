package com.demo.book;

<<<<<<< HEAD
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
=======
import jakarta.persistence.Cacheable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
>>>>>>> bc5fa73 (First commit with root directory and submodules)
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
<<<<<<< HEAD
=======
@EnableCaching
>>>>>>> bc5fa73 (First commit with root directory and submodules)
public class BookApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookApplication.class, args);
	}

}
