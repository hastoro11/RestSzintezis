package com.sornyei.service;

import com.sornyei.model.Activity;
import com.sornyei.repository.ActivityRepository;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@Service
public class ActivityService {

	final static Logger logger = Logger.getLogger(ActivityService.class.getName().toUpperCase());

	@Autowired
	private ActivityRepository repository;

	public Activity create(Activity activity) {
		List<Activity> activityList = repository.findAll();
		for (Activity act : activityList) {
			if (isOverlap(act, activity)) {
				Activity a = new Activity();
				a.setId(0);
				return a;
			}
		}

		return repository.create(activity);
	}

	private boolean isOverlap(Activity act1, Activity act2) {
		LocalDateTime startA = stringToLocalDateTime(act1.getStartTime());
		LocalDateTime endA = stringToLocalDateTime(act1.getEndTime());
		LocalDateTime startB = stringToLocalDateTime(act2.getStartTime());
		LocalDateTime endB = stringToLocalDateTime(act2.getEndTime());

		return (startA.isBefore(endB) || startA.isEqual(endB)) &&
				(endA.isAfter(startB) || endA.isEqual(startB));
	}

	private LocalDateTime stringToLocalDateTime(String dateTimeString) {
		String str[] = dateTimeString.split(" ");

		String dateString = str[0];
		String timeString = str[1];
		String[] dateArray = dateString.split("\\.");

		String[] timeArray = timeString.split(":");
		LocalDateTime ldt = new LocalDateTime(
				Integer.parseInt(dateArray[0]),
				Integer.parseInt(dateArray[1]),
				Integer.parseInt(dateArray[2]),
				Integer.parseInt(timeArray[0]),
				Integer.parseInt(timeArray[1])
		);
		return ldt;
	}

	public List<Activity> findAll() {
		return repository.findAll();
	}

	public Activity findById(long id) {
		return repository.findById(id);
	}

	public List<Activity> findByCategoryId(long categoryId) {
		return repository.findByCategoryId(categoryId);
	}
}
