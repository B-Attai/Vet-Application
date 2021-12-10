package com.group213.vet.app.respository;

import com.group213.vet.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
