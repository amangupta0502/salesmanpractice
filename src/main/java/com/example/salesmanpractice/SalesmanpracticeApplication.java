package com.example.salesmanpractice;
import com.example.salesmanpractice.entities.Salesman;
import com.example.salesmanpractice.repositories.SalesmanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalesmanpracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalesmanpracticeApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(SalesmanRepository salesmanRepository){
		return args -> {
            salesmanRepository.save(new Salesman(null, 2000, new Date(), "washing machine", "alex"));
            salesmanRepository.save(new Salesman(null, 1000, new Date(), "refrigerator", "rohit"));
            salesmanRepository.save(new Salesman(null, 3000, new Date(), "music system", "jay"));

			salesmanRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});
		};
	}
}