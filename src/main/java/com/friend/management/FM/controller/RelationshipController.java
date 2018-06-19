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
import com.friend.management.FM.core.CustomException;
import com.friend.management.FM.model.RelationshipModel;
import com.friend.management.FM.model.SmResponseStatus;
import com.friend.management.FM.service.RelationshipService;

@RestController
@RequestMapping(name = UriConstants.FRIEND)
public class RelationshipController {

	private static final Logger logger = LoggerFactory.getLogger(RelationshipController.class);

	@Autowired
	private RelationshipService relationshipService;

	@RequestMapping(value = UriConstants.BLANK, method = RequestMethod.POST, produces = AppConstants.JSON)
	public SmResponseStatus addfriend(@RequestBody RelationshipModel relationshipModel) {

		logger.info("Request friend rrecieved  name [{}]]", relationshipModel.getRuserid1(),relationshipModel.getRuserid2());

		SmResponseStatus smResponseStatus = null;

		if (relationshipModel.getRuserid1() == null ||relationshipModel.getRuserid2()==null) {
			String error = String.format("userid can not empty");
			logger.error(error);
			throw new CustomException(error);
		}

		smResponseStatus = relationshipService.addRelationship(relationshipModel);

		return smResponseStatus;
	}
}
