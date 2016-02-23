package com.sornyei.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@Component
public class Category {
	private long id;
	private String name;
	private List<Category> subCategories;
	private List<Activity> activities;
	private int totalDuration;

	public long getId() {
		return id;
	}

	public Category setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Category setName(String name) {
		this.name = name;
		return this;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public Category setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
		return this;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public Category setActivities(List<Activity> activities) {
		this.activities = activities;
		return this;
	}

	public int getTotalDuration() {
		int total = 0;
		if (subCategories == null) {
			if (activities != null) {
				for (Activity activity : activities) {
					total += activity.getDuration();
				}
			}
		} else {
			for (Category subCategory : subCategories) {
				total += subCategory.getTotalDuration();
			}
		}

		return total;
	}


}
