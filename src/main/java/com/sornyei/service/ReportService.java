package com.sornyei.service;

import com.sornyei.model.Activity;
import com.sornyei.model.Category;
import com.sornyei.repository.ActivityRepository;
import com.sornyei.repository.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@Service
public class ReportService {

	final static Logger logger = Logger.getLogger(ReportService.class.getName().toUpperCase());

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ActivityRepository activityRepository;

	public List<Category> findAll() {
		List<Category> rootCategories = categoryRepository.getRootCategories();
		for (Category rootCategory : rootCategories) {
			rootCategory.setSubCategories(categoryRepository.getSubCategories(rootCategory.getId()));
		}
		for (Category rootCategory : rootCategories) {
			for (Category subCategory : rootCategory.getSubCategories()) {
				subCategory.setActivities(activityRepository.findByCategoryId(subCategory.getId()));
			}
		}

		removeEmptyCategories(rootCategories);
		return rootCategories;
	}

	public List<Category> findByDate(String date) {
		List<Category> rootCategories = categoryRepository.getRootCategories();
		for (Category rootCategory : rootCategories) {
			rootCategory.setSubCategories(categoryRepository.getSubCategories(rootCategory.getId()));
		}
		for (Category rootCategory : rootCategories) {
			for (Category subCategory : rootCategory.getSubCategories()) {
				List<Activity> result = new ArrayList<>();
				List<Activity> allActivities = activityRepository.findByCategoryId(subCategory.getId());
				for (Activity activity : allActivities) {
					if (activity.getStartTime().indexOf(date) > -1) {
						result.add(activity);
					}
				}

				subCategory.setActivities(result);
			}
		}

		removeEmptyCategories(rootCategories);
		return rootCategories;


	}

	private void removeEmptyCategories(List<Category> parent) {
		for (int i = 0; i < parent.size(); i++) {
			if (parent.get(i).getSubCategories() == null || parent.get(i).getSubCategories().size() == 0) {
				if (parent.get(i).getActivities() == null || parent.get(i).getActivities().size() == 0) {
					parent.remove(i);
				}
			} else {
				removeEmptyCategories(parent.get(i).getSubCategories());
			}
		}
	}


}
