package com.code.booking.repository;

import com.code.booking.entity.Role;
import com.code.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email); //check this

    public Optional<User> findByRole(Role role); //check this

}
