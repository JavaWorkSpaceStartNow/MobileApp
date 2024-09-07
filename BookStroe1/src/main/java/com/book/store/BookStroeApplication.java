package com.book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = { "com.book.store.*" })
@EnableJpaRepositories(basePackages = "com.book.store.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.book.store.model")
public class BookStroeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStroeApplication.class, args);
	}

}
