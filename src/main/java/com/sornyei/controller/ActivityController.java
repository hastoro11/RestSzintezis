package com.sornyei.controller;

import com.sornyei.model.Activity;
import com.sornyei.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@RestController
public class ActivityController {

	final static Logger logger = Logger.getLogger(ActivityController.class.getName().toUpperCase());

	@Autowired
	private ActivityService service;

	@RequestMapping(value = "/activities", method = RequestMethod.POST)
	public Activity create(@RequestBody Activity activity) {
		logger.info("In create ...");
		return service.create(activity);
	}

	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public List<Activity> findAll() {
		return service.findAll();
	}


}
