package com.sornyei.model;

import org.springframework.stereotype.Component;

/**
 * Created by gaborsornyei on 16. 02. 20..
 */
@Component
public class Category {
	private long id;
	private String name;

	public Category() {
	}

	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}

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
}
