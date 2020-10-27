package com.ranjan.usermanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ranjan.usermanagement.model.User;
import com.ranjan.usermanagement.repository.UserRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		return (args) -> {
			repository.save(new User("Ranjan", "Venkatesh", "admin", LocalDate.parse("11-06-1992", formatter),
					new BCryptPasswordEncoder().encode("password"), "ADMIN"));
			repository.save(new User("Max", "Mueller", "user@web.de", LocalDate.parse("11-06-1992", formatter),
					new BCryptPasswordEncoder().encode("pass"), "USER"));
			repository.save(new User("Max", "Schwarz", "admin@web.de", LocalDate.parse("11-06-1992", formatter),
					new BCryptPasswordEncoder().encode("pass2"), "ADMIN"));
			repository.save(new User("Max", "Schmidt", "user2@web.de", LocalDate.parse("11-06-1992", formatter),
					new BCryptPasswordEncoder().encode("pass3"), "USER"));
			repository.save(new User("Max", "Boellinger", "admin2@web.de", LocalDate.parse("11-06-1992", formatter),
					new BCryptPasswordEncoder().encode("pass4"), "ADMIN"));
			repository.save(new User("Max", "Max", "user3@web.de", LocalDate.parse("11-06-1992", formatter),
					new BCryptPasswordEncoder().encode("pass5"), "USER"));
		};
	}
}
