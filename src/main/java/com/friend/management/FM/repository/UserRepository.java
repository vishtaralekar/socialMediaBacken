package com.friend.management.FM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.friend.management.FM.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT p FROM User p WHERE LOWER(p.emailId) = LOWER(:emailId)")
    public List<User> find(@Param("emailId") String emailId);
}
