package com.friend.management.FM.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friend.management.FM.dao.RelationshipDaoImpl;
import com.friend.management.FM.domain.Relationship;
import com.friend.management.FM.domain.User;
import com.friend.management.FM.model.RelationshipModel;
import com.friend.management.FM.model.SmResponseStatus;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipDaoImpl relationshipDaoImpl;

	public static final Logger logger = LoggerFactory.getLogger(RelationshipService.class);

	public SmResponseStatus addRelationship(RelationshipModel relationshipModel) {

		String message = null;
		try{
			
			Relationship relationship =wrapRelationship(null,relationshipModel, null, null);
			
			relationshipDaoImpl.savefriend(relationship);
			
		}catch(Exception e) {
			String error = String.format("Error occured while saving friends data ",relationshipModel.getRuserid2());
			logger.error(error, e);
			throw e;
		}
		return new SmResponseStatus(message);
	}

	private Relationship wrapRelationship(Long status, RelationshipModel relationshipModel, User ruserid1, User ruserid2) {
		
		Relationship relation = new Relationship();
		/*if(ruemailId != null) {
			relation.setRuemailId1(ruemailId);
		}*/
		//relation.setRuserid1(ruserid1);
		//relation.setRuserid2(ruserid2);
		relation.setStatus(relationshipModel.getStatus());
		relation.setActionuserid(relation.getActionuserid());
		
		return relation;
	}
	
}
