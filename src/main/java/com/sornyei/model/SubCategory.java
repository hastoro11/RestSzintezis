package com.sornyei.model;

import org.springframework.stereotype.Component;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
@Component
public class SubCategory {
	private long id;
	private long catId;
	private String name;

	public SubCategory() {
	}

	public SubCategory(long id, long catId, String name) {
		this.id = id;
		this.catId = catId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public SubCategory setId(long id) {
		this.id = id;
		return this;
	}

	public long getCatId() {
		return catId;
	}

	public SubCategory setCatId(long catId) {
		this.catId = catId;
		return this;
	}

	public String getName() {
		return name;
	}

	public SubCategory setName(String name) {
		this.name = name;
		return this;
	}
}
