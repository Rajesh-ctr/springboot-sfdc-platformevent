package com.deloitte.sfdc.model;
import java.util.Date;

public class CDCAuditLog {
	
	private String CDC_AuditLogName__c;
	private String CDC_Field__c;
	private String CDC_NewValue__c;
	private String CDC_Object__c;
	private String CDC_Operation__c;
	private String CDC_Owner__c;
	private String CDC_OwnerName__c;
	private String CDC_OwnerProfile__c;
	private String CDC_RecordId__c;
	private Date CDC_CreatedDate__c;
	
	public String getCDC_AuditLogName__c() {
		return CDC_AuditLogName__c;
	}
	public void setCDC_AuditLogName__c(String cDC_AuditLogName__c) {
		CDC_AuditLogName__c = cDC_AuditLogName__c;
	}
	public String getCDC_Field__c() {
		return CDC_Field__c;
	}
	public void setCDC_Field__c(String cDC_Field__c) {
		CDC_Field__c = cDC_Field__c;
	}
	public String getCDC_NewValue__c() {
		return CDC_NewValue__c;
	}
	public void setCDC_NewValue__c(String cDC_NewValue__c) {
		CDC_NewValue__c = cDC_NewValue__c;
	}
	public String getCDC_Object__c() {
		return CDC_Object__c;
	}
	public void setCDC_Object__c(String cDC_Object__c) {
		CDC_Object__c = cDC_Object__c;
	}
	public String getCDC_Operation__c() {
		return CDC_Operation__c;
	}
	public void setCDC_Operation__c(String cDC_Operation__c) {
		CDC_Operation__c = cDC_Operation__c;
	}
	public String getCDC_Owner__c() {
		return CDC_Owner__c;
	}
	public void setCDC_Owner__c(String cDC_Owner__c) {
		CDC_Owner__c = cDC_Owner__c;
	}
	public String getCDC_OwnerName__c() {
		return CDC_OwnerName__c;
	}
	public void setCDC_OwnerName__c(String cDC_OwnerName__c) {
		CDC_OwnerName__c = cDC_OwnerName__c;
	}
	public String getCDC_OwnerProfile__c() {
		return CDC_OwnerProfile__c;
	}
	public void setCDC_OwnerProfile__c(String cDC_OwnerProfile__c) {
		CDC_OwnerProfile__c = cDC_OwnerProfile__c;
	}
	public String getCDC_RecordId__c() {
		return CDC_RecordId__c;
	}
	public void setCDC_RecordId__c(String cDC_RecordId__c) {
		CDC_RecordId__c = cDC_RecordId__c;
	}
	
	public Date getCDC_CreatedDate__c() {
		return CDC_CreatedDate__c;
	}
	public void setCDC_CreatedDate__c(Date cDC_CreatedDate__c) {
		CDC_CreatedDate__c = cDC_CreatedDate__c;
	}
	
	
	public String toString() {
		return "CDC_AuditLogName__c : "+CDC_AuditLogName__c+" CDC_Field__c : "+CDC_Field__c+" CDC_NewValue__c : "+CDC_NewValue__c+
				" CDC_Object__c : "+CDC_Object__c+" CDC_Operation__c : "+CDC_Operation__c+" CDC_Owner__c : "+CDC_Owner__c+
				" CDC_OwnerName__c : "+CDC_OwnerName__c+" CDC_OwnerProfile__c : "+CDC_OwnerProfile__c+" CDC_RecordId__c : "+CDC_RecordId__c;
	}
	
	

}
