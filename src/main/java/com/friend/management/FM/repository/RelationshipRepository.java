package com.friend.management.FM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.friend.management.FM.domain.Relationship;
@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> { 

	
}
