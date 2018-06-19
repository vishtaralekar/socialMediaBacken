package com.friend.management.FM.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friend.management.FM.core.CustomException;
import com.friend.management.FM.dao.RelationshipDaoImpl;
import com.friend.management.FM.dao.UserDaoImpl;
import com.friend.management.FM.domain.Relationship;
import com.friend.management.FM.domain.User;
import com.friend.management.FM.model.FriendsReqBody;
import com.friend.management.FM.model.RelationshipModel;
import com.friend.management.FM.model.SmResponseStatus;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipDaoImpl relationshipDaoImpl;

	@Autowired
	private UserDaoImpl userImpl;

	public static final Logger logger = LoggerFactory.getLogger(RelationshipService.class);

	public SmResponseStatus addRelationship(RelationshipModel relationshipModel) {

		String message = null;
		try {

			Relationship relationship = wrapRelationship(null, relationshipModel, null, null);

			relationshipDaoImpl.savefriend(relationship);

		} catch (Exception e) {
			String error = String.format("Error occured while saving friends data ", relationshipModel.getRuserid2());
			logger.error(error, e);
			throw e;
		}
		return new SmResponseStatus(message);
	}

	private Relationship wrapRelationship(Long status, RelationshipModel relationshipModel, User ruserid1,
			User ruserid2) {

		Relationship relation = new Relationship();
		
		relation.setStatus(relationshipModel.getStatus());
		relation.setActionuserid(relation.getActionuserid());

		return relation;
	}

	public boolean checkFriendShip(FriendsReqBody friendsReqBody) {
		logger.info("inside checkFriendShip   :");
		if (friendsReqBody.getFriends().isEmpty())
			return false;
		if (friendsReqBody.getFriends().size() > 2) {
			throw new CustomException("number of parameter are grater than 2 please send only 2");
		}

		User userOne = userImpl.getUSerByEmailId(friendsReqBody.getFriends().get(0));
		User userTwo = userImpl.getUSerByEmailId(friendsReqBody.getFriends().get(1));

		List<Relationship> relationshiplist = relationshipDaoImpl.checkFriendship(userOne.getUserId(),
				userTwo.getUserId());
		logger.info("Relationship status:::::  " + relationshiplist.toString());
		if (relationshiplist.isEmpty())
			return false;
		else
			return true;
	}

	public List<RelationshipModel> getFriedlist(FriendsReqBody email)
	{
		User userOne = userImpl.getUSerByEmailId(email.getEmail());
	
		List<Relationship>  rlist=	relationshipDaoImpl.friendList(userOne.getUserId());
		logger.info(" friendlist : "+rlist.toString());
		return null;
	}
	
}
