package com.friend.management.FM.dao;

import java.util.List;

import com.friend.management.FM.domain.Relationship;

public interface RelationshipDao {

	public void savefriend(Relationship relationship);

	public List<Relationship> checkFriendship(Long userid1, Long userid2);

	public List<Relationship> friendList(Long userid1);
	
	public List<Relationship> commonFriend(Long userid1, Long userid2);
	
	public List<Relationship> subFriend(Long userid1, Long userid2);

	List<Relationship> blockFriend(Long userid1, Long userid2);

	List<Relationship> reciveUpadate(Long userid1);
	
	public void updaterelation(Relationship relationship); 
}
