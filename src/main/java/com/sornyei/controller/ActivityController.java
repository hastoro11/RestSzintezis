package com.sornyei.controller;

import com.sornyei.model.Activity;
import com.sornyei.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Activity> create(@RequestBody Activity activity) {
		Activity act = service.create(activity);
		if (act.getId() == 0) {
			return new ResponseEntity<Activity>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<Activity>(act, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public List<Activity> findAll() {
		return service.findAll();
	}


}
