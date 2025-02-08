package com.cdac.campussync.Repository;

import com.cdac.campussync.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT MAX(s.id) FROM User s")
    Long findMaxId();

    Optional<User> findByUsername(String username);
}
