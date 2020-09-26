package com.github.ggan.springboot.sfdcplatformevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.sfdc.model.CDCAuditLog;
import com.deloitte.sfdc.service.CdcEventsServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class SalesforcePlatformeventHandler {
	private static Logger logger = LoggerFactory.getLogger(SalesforcePlatformeventHandler.class);
	
	@Autowired
	CdcEventsServiceImpl cdcEventsServiceImpl;
	
	public synchronized void handleRequest(String payload, String salesforceEventName, Long replayId) {
		logger.info("SalesforcePlatformeventHandler :: handleRequest");
		logger.debug("Received payload from platform event " + salesforceEventName + " with replayId: " + replayId);
		logger.debug("Payload ::::: "+payload);
		// cdcEventsServiceImpl.insertCDCAuditLog(cdcAuditLogObj);
		try {
			if (payload != null && payload != "") {
				GsonBuilder builder = new GsonBuilder();
				builder.setPrettyPrinting();

				Gson gson = builder.create();
				CDCAuditLog cdcAuditLogObj = gson.fromJson(payload, CDCAuditLog.class);
				logger.info("Payload converted to cdcAuditLogObj object ::: "+cdcAuditLogObj);
				if(cdcAuditLogObj != null) {
					int recordCount = cdcEventsServiceImpl.insertCDCAuditLog(cdcAuditLogObj);
					logger.info(recordCount+" Record(s) inserted successfully.");
				} else {
					logger.debug("cdcAuditLogObj getting null value after converted from string payload to CDCAuditLog object using gson");
				}
				
				/*
				 * jsonString = gson.toJson(cdcAuditLogObj); System.out.println(jsonString);
				 */
			}
		} catch(Exception ex) {
			logger.error("Exception occured in SalesforcePlatformeventHandler::handleRequest "+ex.getMessage());
			ex.printStackTrace();
		}
		

	}

}
