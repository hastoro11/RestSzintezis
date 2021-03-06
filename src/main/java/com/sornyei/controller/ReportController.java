package com.sornyei.controller;

import com.sornyei.model.Activity;
import com.sornyei.model.Category;
import com.sornyei.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@RestController
public class ReportController {

	final static Logger logger = Logger.getLogger(ReportController.class.getName().toUpperCase());
	@Autowired
	private ReportService service;

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public List<Category> findReport() {
		return service.findAll();
	}

	@RequestMapping(value = "/report/{date}", method = RequestMethod.GET)
	public List<Category> findByDate(@PathVariable("date") String date) {
		logger.info(date);
		return service.findByDate(date);
	}

}
