package com.friend.management.FM.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friend.management.FM.core.CustomException;
import com.friend.management.FM.dao.RelationshipDaoImpl;
import com.friend.management.FM.dao.UserDaoImpl;
import com.friend.management.FM.domain.Relationship;
import com.friend.management.FM.domain.User;
import com.friend.management.FM.model.BlockModel;
import com.friend.management.FM.model.CommonFriendsModel;
import com.friend.management.FM.model.FriendListResponse;
import com.friend.management.FM.model.FriendsReqBody;
import com.friend.management.FM.model.ReceiveUpdateModel;
import com.friend.management.FM.model.ReceiveUpdateReqBody;
import com.friend.management.FM.model.RelationshipModel;
import com.friend.management.FM.model.SmResponseStatus;
import com.friend.management.FM.model.SubscribeModel;

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

		String myEmail = email.getEmail();
		logger.info("serching for email :" + email);

		FriendListResponse response = findfriendByEmail(myEmail);

		return response;

	}

	private FriendListResponse findfriendByEmail(String myEmail) {
		List<String> friends = new ArrayList<>();

		User userTofindFrienlist = userImpl.getUSerByEmailId(myEmail);

		List<Relationship> rlist = relationshipDaoImpl.friendList(userTofindFrienlist.getUserId());
		logger.info(" friendlist : " + rlist.toString());
		logger.info("got user " + userTofindFrienlist);
		FriendListResponse response = new FriendListResponse();
		response.setCount(rlist.size());

		if (rlist.isEmpty()) {
			response.setSuccess(false);
		} else {
			response.setSuccess(true);
		}

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

	public FriendListResponse commonfriend(CommonFriendsModel commonfriend) {
		logger.info("inside Commonfriend   :");
		if (commonfriend.getFriends().isEmpty() || commonfriend.getFriends().size() > 2)

			throw new CustomException("number of parameter are grater than 2 please send only 2");

		User user1 = userImpl.getUSerByEmailId(commonfriend.getFriends().get(0));
		User user2 = userImpl.getUSerByEmailId(commonfriend.getFriends().get(1));
		FriendListResponse firstResponse = findfriendByEmail(user1.getEmailId());
		FriendListResponse secondResponse = findfriendByEmail(user2.getEmailId());
		FriendListResponse finalresponse = new FriendListResponse();

		if (firstResponse.isSuccess() && secondResponse.isSuccess()) {
			finalresponse.setSuccess(true);
		} else {
			finalresponse.setSuccess(false);
		}

		List<String> commonFriends = new ArrayList<>(firstResponse.getFriends());

		commonFriends.retainAll(secondResponse.getFriends());

		finalresponse.setFriends(commonFriends);
		finalresponse.setCount(commonFriends.size());
		return finalresponse;
	}

	public boolean subFriend(SubscribeModel subfriend) {
		logger.info("inside subscribe Friend   :");

		User userOne = userImpl.getUSerByEmailId(subfriend.getRequestor());
		User userTwo = userImpl.getUSerByEmailId(subfriend.getTarget());

		List<Relationship> subcriberlist = relationshipDaoImpl.subFriend(userOne.getUserId(), userTwo.getUserId());
		logger.info("Subscribe status:::::  " + subcriberlist.toString());
		if (subcriberlist.isEmpty())
			return false;
		else
			return true;
	}

	public boolean blockFriend(BlockModel blockfriend) {
		logger.info("inside block Friend   :");

		User userOne = userImpl.getUSerByEmailId(blockfriend.getRequestor());
		User userTwo = userImpl.getUSerByEmailId(blockfriend.getTarget());

		List<Relationship> blocklist = relationshipDaoImpl.blockFriend(userOne.getUserId(), userTwo.getUserId());
		logger.info("Block friends status:::::  " + blocklist.toString());
		for (Relationship rela : blocklist) {
			// relation save dao
			rela.setStatus(3L);
			relationshipDaoImpl.savefriend(rela);
		}

		if (blocklist.isEmpty())
			return false;
		else
			return true;
	}

	public ReceiveUpdateModel receivefriend(ReceiveUpdateReqBody reveicefriend) {
		logger.info("inside Receive update friend  :");

		List<String> frienList = new ArrayList<>();
		User user1 = userImpl.getUSerByEmailId(reveicefriend.getSender());

		String text = reveicefriend.getText();
		// parse this string and extract email id from it

		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(text);
		while (m.find()) {
			System.out.println(m.group());
			frienList.add(m.group());
		}

		List<Relationship> resList = relationshipDaoImpl.reciveUpadate(user1.getUserId());

		for (Relationship rel : resList) {
			User user = userImpl.getUser(rel.getRuserid2());
			frienList.add(user.getEmailId());
		}

		ReceiveUpdateModel firstupdateResponse = new ReceiveUpdateModel();

		// when they are either friend or subscriber
		if (frienList.isEmpty()) {
			firstupdateResponse.setSuccess(false);

		} else {
			firstupdateResponse.setSuccess(true);
		}

		firstupdateResponse.setRecipient(frienList);

		return firstupdateResponse;

	}
}
