package com.friend.management.FM.service;

import java.util.ArrayList;
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
import com.friend.management.FM.model.CommonFriendsModel;
import com.friend.management.FM.model.FriendListResponse;
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

			Relationship relationship = wrapRelationship(null, relationshipModel);

			relationshipDaoImpl.savefriend(relationship);

		} catch (Exception e) {
			String error = String.format("Error occured while saving friends data ", relationshipModel.getRuserid2());
			logger.error(error, e);
			throw e;
		}
		return new SmResponseStatus(message);
	}

	private Relationship wrapRelationship(Long status, RelationshipModel relationshipModel) {

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

	public FriendListResponse getFriedlist(FriendsReqBody email) {
		User userTofindFrienlist = userImpl.getUSerByEmailId(email.getEmail());
		logger.info("serching for email :" + email + "got user " + userTofindFrienlist);
		List<Relationship> rlist = relationshipDaoImpl.friendList(userTofindFrienlist.getUserId());
		logger.info(" friendlist : " + rlist.toString());

		FriendListResponse response = new FriendListResponse();
		response.setCount(rlist.size());

		if (rlist.isEmpty()) {
			response.setSuccess(false);
		} else {
			response.setSuccess(true);
		}

		List<String> friends = new ArrayList<>();
		response.setFriends(friends);

		// preparing list of all user

		for (Relationship relationship : rlist) {
			User user = userImpl.getUser(relationship.getRuserid1());
			User userTwo = userImpl.getUser(relationship.getRuserid2());

			if (user != null && (user.getUserId() != userTofindFrienlist.getUserId())) {
				friends.add(user.getEmailId());
			}

			if (userTwo != null && userTwo.getUserId() != userTofindFrienlist.getUserId()) {
				friends.add(userTwo.getEmailId());
			}
		}

		return response;

	}

	/*
	 * private User wrapUser(Long userid, AddToUserRequest addToUserRequest) {
	 * User user = new User();
	 * 
	 * if (userid != null) { user.setEmailId(addToUserRequest.getEmailId()); }
	 * user.setEmailId(addToUserRequest.getEmailId());
	 * 
	 * return user; }
	 */
	public boolean commonfriend(CommonFriendsModel commonfriend) {
		logger.info("inside Commonfriend   :");
		if (commonfriend.getFriends().isEmpty() || commonfriend.getFriends().size() > 2)

			throw new CustomException("number of parameter are grater than 2 please send only 2");

		User user1 = userImpl.getUSerByEmailId(commonfriend.getFriends().get(0));
		User user2 = userImpl.getUSerByEmailId(commonfriend.getFriends().get(1));

		List<Relationship> commonfriendlist = relationshipDaoImpl.commonFriend(user1.getUserId(), user2.getUserId());

		logger.info("Common friends :  " + commonfriendlist.toString());
		if (commonfriendlist.isEmpty())
			return false;
		else
			return true;
	}

}
