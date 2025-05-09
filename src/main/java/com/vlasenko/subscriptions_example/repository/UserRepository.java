package com.vlasenko.subscriptions_example.repository;

import com.vlasenko.subscriptions_example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}