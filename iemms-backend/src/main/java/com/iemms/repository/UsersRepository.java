//SHIVAAAHHHHH


package com.iemms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iemms.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
	Optional<Users> findByEmail(String Email);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByMobileNumber(String mobileNumber);
	

}
