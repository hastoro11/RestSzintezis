package com.sornyei.service;

import com.sornyei.model.Activity;
import com.sornyei.repository.ActivityRepository;
import org.apache.log4j.Logger;
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
		return repository.create(activity);
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
