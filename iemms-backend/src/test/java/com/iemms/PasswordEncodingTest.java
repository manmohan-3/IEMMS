//SHIVAAAHHHHHH


package com.iemms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncodingTest {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Test
	void passwordEncodingTest() {
		String pass="SHIVAA03";
		String encodedPassword=passwordEncoder.encode(pass);
		assertNotEquals(pass, encodedPassword);
		assertTrue(passwordEncoder.matches(pass, encodedPassword));
		assertFalse(passwordEncoder.matches("Wrong-Password", encodedPassword));
	}

}
