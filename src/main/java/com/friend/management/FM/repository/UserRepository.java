package com.friend.management.FM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friend.management.FM.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

}
