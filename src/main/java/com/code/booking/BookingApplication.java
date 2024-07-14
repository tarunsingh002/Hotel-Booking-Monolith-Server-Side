package com.code.booking;

import com.code.booking.entity.Role;
import com.code.booking.entity.User;
import com.code.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookingApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		if (!userService.adminExists()) {
//			User user = new User();
//			user.setEmail("******@**");
//			user.setRole(Role.Admin);
//			user.setPassword(new BCryptPasswordEncoder().encode("******"));
//			userService.addUser(user);
//
//		}

	}
}
