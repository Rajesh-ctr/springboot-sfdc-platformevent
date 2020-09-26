package com.deloitte.sfdc.service;

import com.deloitte.sfdc.model.CDCAuditLog;

public interface CdcEventsService {
	
	public int insertCDCAuditLog(CDCAuditLog cdcAuditLogObj) throws Exception;
	
	public Long recordCount() throws Exception;
}
