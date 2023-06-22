package com.tecnocampus.blackjack.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecnocampus.blackjack.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}