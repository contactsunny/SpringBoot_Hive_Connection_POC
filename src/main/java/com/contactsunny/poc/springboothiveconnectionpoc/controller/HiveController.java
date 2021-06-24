package com.contactsunny.poc.springboothiveconnectionpoc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hive")
public class HiveController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SHOW_TABLES_QUERY = "show tables";
	private static final String SHOW_DATABASES_QUERY = "show databases";
	private static final String TABLE_PLACEHOLDER = "{table}";
	private static final String PREVIEW_TABLE_QUERY = "select * from " + TABLE_PLACEHOLDER + " limit 10";

	@RequestMapping(value = "/{schema}/tables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getTablesForSchema(@PathVariable String schema) {
		jdbcTemplate.execute("use " + schema);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SHOW_TABLES_QUERY);

		return new ResponseEntity<>(rows, HttpStatus.OK);
	}

	@RequestMapping(value = "/schemas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getSchemas() {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SHOW_DATABASES_QUERY);
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}

	@RequestMapping(value = "/{schema}/preview/{table}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> previewTable(
			@PathVariable String schema, @PathVariable String table
	) {
		jdbcTemplate.execute("use " + schema);
		String query = PREVIEW_TABLE_QUERY.replace(TABLE_PLACEHOLDER, table);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}
}
