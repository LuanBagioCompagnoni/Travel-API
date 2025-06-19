package com.travelapi.travelapi.repositories;

import com.travelapi.travelapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByToken(String token);

    User findByUsername(String username);
}
