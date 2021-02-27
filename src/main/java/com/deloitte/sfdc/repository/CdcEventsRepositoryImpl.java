package com.deloitte.sfdc.repository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deloitte.sfdc.model.CDCAuditLog;
import com.deloitte.sfdc.service.CdcEventsServiceImpl;

@Repository
public class CdcEventsRepositoryImpl implements CdcEventsRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	private static Logger logger = LoggerFactory.getLogger(CdcEventsRepositoryImpl.class);

	@Override
	public int insertCDCAuditLog(CDCAuditLog cdcAuditLogObj) throws Exception {
		logger.info("CdcEventsRepositoryImpl::insertCDCAuditLog");
		try {
			final String auditLogQuery = " insert into CDCAuditLog(cdcAuditLogName, cdcField, cdcNewValue, cdcObject, cdcOperation, cdcOwner, cdcOwnerName, cdcOwnerProfile, cdcRecordId, cdcCreatedDate) "
					+ "values(:CDC_AuditLogName__c, :CDC_Field__c, :CDC_NewValue__c, :CDC_Object__c, :CDC_Operation__c, :CDC_Owner__c, :CDC_OwnerName__c, :CDC_OwnerProfile__c, :CDC_RecordId__c, :CDC_CreatedDate__c)";
			logger.debug("Query for auditlog : " + auditLogQuery);
			KeyHolder holder = new GeneratedKeyHolder();
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("CDC_AuditLogName__c", cdcAuditLogObj.getCDC_AuditLogName__c())
					.addValue("CDC_Field__c", cdcAuditLogObj.getCDC_Field__c())
					.addValue("CDC_NewValue__c", cdcAuditLogObj.getCDC_NewValue__c())
					.addValue("CDC_Object__c", cdcAuditLogObj.getCDC_Object__c())
					.addValue("CDC_Operation__c", cdcAuditLogObj.getCDC_Operation__c())
					.addValue("CDC_Owner__c", cdcAuditLogObj.getCDC_Owner__c())
					.addValue("CDC_OwnerName__c", cdcAuditLogObj.getCDC_OwnerName__c())
					.addValue("CDC_OwnerProfile__c", cdcAuditLogObj.getCDC_OwnerProfile__c())
					.addValue("CDC_RecordId__c", cdcAuditLogObj.getCDC_RecordId__c())
				        .addValue("CDC_CreatedDate__c", cdcAuditLogObj.getCDC_CreatedDate__c());
			logger.debug("Query parameters : " + param.toString());
			return template.update(auditLogQuery, param, holder);
		} catch (Exception ex) {
			logger.error("Exception occured in CdcEventsRepositoryImpl: insertCDCAuditLog " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}

	}

	@Override
	public Long cdcAuditLogRecordCount() throws Exception {
		try {
			final String recordCountQuery = "SELECT COUNT(*) FROM CDCAuditLog;";
			//return template.getJdbcOperations().update(recordCountQuery);
			return template.queryForObject(recordCountQuery, (HashMap)null, Long.class);
		} catch(Exception ex) {
			logger.error("Exception occured in CdcEventsRepositoryImpl: cdcAuditLogRecordCount " + ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

}
