package com.sornyei.service;

import com.sornyei.model.Activity;
import com.sornyei.repository.ActivityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	static final Logger logger = Logger.getLogger(ActivityService.class.getName().toUpperCase());

	public Activity create(Activity activity) {
		logger.info(activity);
		return activityRepository.create(activity);
	}

	public List<Activity> getActivities(String date) {
		return activityRepository.getActivities(date);
	}
}
