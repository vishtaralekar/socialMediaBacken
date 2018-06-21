package com.friend.management.FM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.friend.management.FM.config.AppConstants;
import com.friend.management.FM.config.UriConstants;
import com.friend.management.FM.model.BlockModel;
import com.friend.management.FM.model.CommonFriendsModel;
import com.friend.management.FM.model.FriendListResponse;
import com.friend.management.FM.model.FriendsReqBody;
import com.friend.management.FM.model.ReceiveUpdateModel;
import com.friend.management.FM.model.ReceiveUpdateReqBody;
import com.friend.management.FM.model.SmResponseStatus;
import com.friend.management.FM.model.SubscribeModel;
import com.friend.management.FM.service.RelationshipService;

@RestController
@RequestMapping(value = UriConstants.FRIEND)
public class FriendController {
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	private RelationshipService relationshipService;


	@RequestMapping(value = UriConstants.CHECKFRIENDSHIP, method = RequestMethod.POST, produces = AppConstants.JSON)
	public SmResponseStatus getFriensds(@RequestBody FriendsReqBody friendsReqBody) {
		logger.info("request come for finding friend :" + friendsReqBody.toString());

		return new SmResponseStatus(Boolean.toString(relationshipService.checkFriendShip(friendsReqBody)));
	}

	@RequestMapping(value = UriConstants.FRIENDLIST, method = RequestMethod.POST, produces = AppConstants.JSON)
	public FriendListResponse getFriendList(@RequestBody FriendsReqBody friendsReqBody) {

		logger.info("request come for finding friend :" + friendsReqBody.getEmail());

		return relationshipService.getFriedlist(friendsReqBody);

	}

	@RequestMapping(value = UriConstants.COMMONFRIEND, method = RequestMethod.POST, produces = AppConstants.JSON)
	public FriendListResponse getcommonFriensds(@RequestBody CommonFriendsModel commonfriend) {
		logger.info("request come for finding common friend :" + commonfriend.toString());

		return relationshipService.commonfriend(commonfriend);

	}
	@RequestMapping(value = UriConstants.SUBSCRIBE, method = RequestMethod.POST, produces = AppConstants.JSON)
	public SmResponseStatus getSubFriensd(@RequestBody SubscribeModel subfriend) {
		logger.info("request come for subscribe friend :" + subfriend.toString());

		return new SmResponseStatus(Boolean.toString(relationshipService.subFriend(subfriend)));
	}

	@RequestMapping(value = UriConstants.BLOCK, method = RequestMethod.POST, produces = AppConstants.JSON)
	public SmResponseStatus getBlockFriensd(@RequestBody BlockModel blockfriend) {
		logger.info("request come for block friend :" + blockfriend.toString());

		return new SmResponseStatus(Boolean.toString(relationshipService.blockFriend(blockfriend)));
	}
	@RequestMapping(value = UriConstants.RECEIVEUPDATE, method = RequestMethod.POST, produces = AppConstants.JSON)
	public  ReceiveUpdateModel getReceiveUpdate(@RequestBody  ReceiveUpdateReqBody revicefriend) {
		logger.info("request come for finding common friend :" + revicefriend.toString());

		return relationshipService.receivefriend(revicefriend);
	}

}
