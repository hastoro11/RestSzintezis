package com.sornyei.model;

import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@Component
public class Activity {

	final static Logger logger = Logger.getLogger(Activity.class.getName().toUpperCase());

	private long id;
	private String startTime;
	private String endTime;
	private String name;
	private long categoryId;
	private int duration;

	public long getId() {
		return id;
	}

	public Activity setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public Activity setName(String name) {
		this.name = name;
		return this;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public Activity setCategoryId(long categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public int getDuration() {
		return Minutes.minutesBetween(stringToLocalDateTime(this.startTime), stringToLocalDateTime(this.endTime))
				.getMinutes();
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
}
