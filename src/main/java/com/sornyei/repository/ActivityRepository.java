package com.sornyei.repository;

import com.sornyei.model.Activity;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
@Repository
public class ActivityRepository {
	private List<Activity> activities;

	public ActivityRepository() {
		activities = new ArrayList<Activity>();
	}

	public Activity create(Activity activity) {
		activity.setId(activities.size() + 1);
		activities.add(activity);

		return activity;
	}

	public List<Activity> getActivities(String date) {
		ArrayList<Activity> result = new ArrayList<Activity>();
		for (Activity activity : activities) {
			if (activity.getDate().equals(date)) {
				result.add(activity);
			}
		}
		return result;
	}
}
