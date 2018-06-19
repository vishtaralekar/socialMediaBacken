package com.friend.management.FM.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.friend.management.FM.domain.Relationship;
import com.friend.management.FM.repository.RelationshipRepository;

@Component
public class RelationshipDaoImpl implements RelationshipDao {

	public static final Logger logger = LoggerFactory.getLogger(RelationshipDaoImpl.class);

	@Autowired
	private RelationshipRepository relationshipRepository;

	@Override
	public void savefriend(Relationship relationship) {
		try {
			relationshipRepository.save(relationship);
		} catch (Exception e) {
			String error = String.format("Error occured while saving accept friend request with name [%s] ",relationship.getRuserid2());
			logger.error(error);
			throw e;

		}
	}

	@Override
	public List<Relationship> checkFriendship(Long userid1, Long userid2) {
		
		return relationshipRepository.find(userid1,userid2);
	}
	@Override
	public List<Relationship> friendList(Long userid1) {
		
		return relationshipRepository.findFriendship(userid1);
	}
	
}
