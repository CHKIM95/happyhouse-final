package com.ssafy.happyhouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableTransactionManagement
public class SSAFYApplication {
	public static void main(String[] args) {
		SpringApplication.run(SSAFYApplication.class, args);
	}
}
//http://localhost:8000/happyhouse/swagger-ui.html