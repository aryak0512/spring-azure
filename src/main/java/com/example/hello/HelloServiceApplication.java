package com.example.hello;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloServiceApplication {

	@Autowired
	private EmployeeRepository repo;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/message")
	public String read() {
		return "hi from azure!!!";
	}

	@GetMapping("/get")
	public List<Employee> get() {
		logger.info("Fetching employees from DB");
		return repo.findAll();
	}

	@GetMapping("/add/{name}/{dept}")
	public void add(@PathVariable("name") String name, @PathVariable("dept") String dept) {
		Employee e = new Employee();
		e.setDepartment(dept);
		e.setName(name);
		logger.info("Adding employee : {}", e);
		repo.save(e);
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloServiceApplication.class, args);
	}

}
