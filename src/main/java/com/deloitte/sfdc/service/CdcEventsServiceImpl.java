package com.deloitte.sfdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.sfdc.model.CDCAuditLog;
import com.deloitte.sfdc.repository.CdcEventsRepositoryImpl;
import com.github.ggan.springboot.sfdcplatformevent.Application;

@Service
public class CdcEventsServiceImpl implements CdcEventsService {

	@Autowired
	CdcEventsRepositoryImpl cdcEventsRepositoryImpl;
	
	private static Logger logger = LoggerFactory.getLogger(CdcEventsServiceImpl.class);
	
	@Override
	public int insertCDCAuditLog(CDCAuditLog cdcAuditLogObj) throws Exception {
		logger.info("CdcEventsServiceImpl:: insertCDCAuditLog");
		return cdcEventsRepositoryImpl.insertCDCAuditLog(cdcAuditLogObj);
	}

	@Override
	public Long recordCount() throws Exception {
		return cdcEventsRepositoryImpl.cdcAuditLogRecordCount();
	}

}
