package com.friend.management.FM.dao;

import java.util.ArrayList;
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
			String error = String.format("Error occured while saving accept friend request with name [%s] ",
					relationship.getRuserid2());
			logger.error(error);
			throw e;

		}
	}

	@Override
	public List<Relationship> checkFriendship(Long userid1, Long userid2) {

		return relationshipRepository.find(userid1, userid2);
	}

	// public List<Relationship> friendList(String string) {

	public List<Relationship> friendList(Long ruserid) {

		List<Relationship> friendList1 = new ArrayList<>();
		friendList1 = relationshipRepository.findFriendship(ruserid);

		return friendList1;
	}

	/*
	 * @Override public List<Relationship> commonFriend(Long userid1, Long
	 * userid2) {
	 * 
	 * return relationshipRepository.find(userid1, userid2); }
	 */
	@Override
	public List<Relationship> commonFriend(Long userid1, Long userid2) {

		return relationshipRepository.find(userid1, userid2);
	}

	@Override
	public List<Relationship> subFriend(Long userid1, Long userid2) {

		return relationshipRepository.subscribe(userid1, userid2);
	}

	@Override
	public List<Relationship> blockFriend(Long userid1, Long userid2) {

		return relationshipRepository.blcokfriend(userid1, userid2);
	}

	@Override
	public List<Relationship> reciveUpadate(Long userid1) {
		return relationshipRepository.receiveupadte(userid1);
	}

	@Override
	public void updaterelation(Relationship relationship) {
		
		relationshipRepository.save(relationship);
	}

}
