package com.sornyei.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.Date;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
public class Activity {
	private long id;
	private String name;
	private long subCatId;
	private String date;
	private String startTime;
	private String endTime;
	private int duration;

	public long getId() {
		return id;
	}

	public Activity setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Activity setName(String name) {
		this.name = name;
		return this;
	}

	public long getSubCatId() {
		return subCatId;
	}

	public Activity setSubCatId(long subCatId) {
		this.subCatId = subCatId;
		return this;
	}

	public String getDate() {
		return date;
	}

	public Activity setDate(String date) {
		this.date = date;
		return this;
	}

	public String getStartTime() {
		return startTime;
	}

	public Activity setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	public String getEndTime() {
		return endTime;
	}

	public Activity setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public int getDuration() {
		LocalTime start = new LocalTime(this.startTime);
		LocalTime end = new LocalTime(this.endTime);

		return Minutes.minutesBetween(start, end).getMinutes();
	}

	@Override
	public String toString() {
		return "Activity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", subCatId=" + subCatId +
				", date='" + date + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", duration=" + this.getDuration() +
				'}';
	}
}
