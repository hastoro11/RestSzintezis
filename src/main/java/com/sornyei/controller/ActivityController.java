package com.sornyei.controller;

import com.sornyei.model.Activity;
import com.sornyei.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
@RestController
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	static final Logger logger = Logger.getLogger(ActivityController.class.getName().toUpperCase());

	@RequestMapping(value = "/activities/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<Activity>> getActivities(@PathVariable("date") String date) {
		return new ResponseEntity<List<Activity>>(activityService.getActivities(date), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities", method = RequestMethod.POST)
	public ResponseEntity<Activity> addNewActivity(@RequestBody Activity activity) {
		Activity act = activityService.create(activity);
		if (act == null) {
			return new ResponseEntity<Activity>(HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			return new ResponseEntity<Activity>(act, HttpStatus.CREATED);
		}
	}
}
