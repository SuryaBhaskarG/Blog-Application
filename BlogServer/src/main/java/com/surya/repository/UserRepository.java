package com.surya.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surya.model.User;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserName(String username);
}
