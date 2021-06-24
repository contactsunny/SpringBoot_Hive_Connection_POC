package com.contactsunny.poc.springboothiveconnectionpoc.controllers;

import com.contactsunny.poc.springboothiveconnectionpoc.services.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hive")
public class HiveController {

	@Autowired
	private HiveService hiveService;

	@RequestMapping(value = "/{schema}/tables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getTablesForSchema(@PathVariable String schema) {
		List<Map<String, Object>> rows = hiveService.getTables(schema);
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}

	@RequestMapping(value = "/schemas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getSchemas() {
		List<Map<String, Object>> rows = hiveService.getSchemas();
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}

	@RequestMapping(value = "/{schema}/preview/{table}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> previewTable(
			@PathVariable String schema, @PathVariable String table
	) {
		List<Map<String, Object>> rows = hiveService.getTablePreview(schema, table);
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}
}
