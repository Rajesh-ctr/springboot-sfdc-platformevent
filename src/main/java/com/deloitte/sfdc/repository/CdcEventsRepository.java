package com.deloitte.sfdc.repository;

import com.deloitte.sfdc.model.CDCAuditLog;

public interface CdcEventsRepository {

	public int insertCDCAuditLog(CDCAuditLog auditLogObj) throws Exception;
	
	public Long cdcAuditLogRecordCount() throws Exception;
}
