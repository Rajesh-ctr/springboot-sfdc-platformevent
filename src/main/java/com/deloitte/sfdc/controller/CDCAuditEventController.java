package com.deloitte.sfdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.sfdc.service.CdcEventsServiceImpl;

@RestController
public class CDCAuditEventController {
	
	@Autowired
	CdcEventsServiceImpl cdcEventsServiceImpl;
	
	@GetMapping("/api/{name}")
	public String helloWorld(@PathVariable String name) {
		System.out.println(name);
		return "Welcome "+name;
	}
	
	@GetMapping("/api/recordCount")
	public ResponseEntity<String> auditEventRecordCount() {
		try {
			Long recordCount = cdcEventsServiceImpl.recordCount();
			return ResponseEntity.status(HttpStatus.CREATED).body(" <b> "+recordCount+" </b> record(s) avalible in AuditEvent table.");
		} catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
}
