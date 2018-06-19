package com.friend.management.FM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.friend.management.FM.domain.Relationship;
@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> { 

	@Query("SELECT p FROM Relationship p WHERE (ruserid1 =:ruserid1 and ruserid2=:ruserid2)or ((ruserid1 =:ruserid2 and ruserid2=:ruserid1)) and status =1")
    public List<Relationship> find(@Param("ruserid1") Long ruserid1,@Param("ruserid2") Long ruserid2);

	@Query("SELECT p FROM Relationship p WHERE (ruserid1 =:ruserid1 or ruserid2=:ruserid1) and status =1")
    public List<Relationship> findFriendship(@Param("ruserid1") Long ruserid1);

}
