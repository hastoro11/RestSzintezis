package com.sornyei.service;

import com.sornyei.model.Activity;
import com.sornyei.repository.ActivityRepository;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repository;

	static final Logger logger = Logger.getLogger(ActivityService.class.getName().toUpperCase());

	public Activity create(Activity activity) {
		LocalTime startB = new LocalTime(activity.getStartTime());
		LocalTime endB = new LocalTime(activity.getEndTime());
		List<Activity> activities = repository.getActivities(activity.getDate());
		for (Activity act : activities) {
			LocalTime startA = new LocalTime(act.getStartTime());
			LocalTime endA = new LocalTime(act.getEndTime());
			if ((startA.isBefore(endB) || startA.isEqual(endB)) && (endA.isAfter(startB) || endA.isEqual(startB))) {
				//overlap
				return null;
			}
		}
		return repository.create(activity);
	}

	public List<Activity> getActivities(String date) {
		return repository.getActivities(date);
	}
}
