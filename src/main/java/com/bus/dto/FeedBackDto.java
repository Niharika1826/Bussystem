package com.bus.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;

import org.springframework.stereotype.Component;

import com.bus.entity.Bus;
import com.bus.entity.User;

@Component
public class FeedBackDto {
	
	private int feedBackId;
	@Max(5)
	private int serviceRating;
	@Max(5)
	private int driverRating;
	@Max(10)
	private int overallRating;
	private String comments;
	private LocalDate feedBackDate;
	private User user;
	private Bus bus;
	
	public FeedBackDto() {}
	
	public FeedBackDto(int feedBackId, int serviceRating, int driverRating, int overallRating, String comments,
			LocalDate feedBackDate, User user, Bus bus) {
		super();
		this.feedBackId = feedBackId;
		this.serviceRating = serviceRating;
		this.driverRating = driverRating;
		this.overallRating = overallRating;
		this.comments = comments;
		this.feedBackDate = feedBackDate;
		this.user = user;
		this.bus = bus;
	}

	public int getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(int feedBackId) {
		this.feedBackId = feedBackId;
	}

	public int getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(int serviceRating) {
		this.serviceRating = serviceRating;
	}

	public int getDriverRating() {
		return driverRating;
	}

	public void setDriverRating(int driverRating) {
		this.driverRating = driverRating;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getFeedBackDate() {
		return feedBackDate;
	}

	public void setFeedBackDate(LocalDate feedBackDate) {
		this.feedBackDate = feedBackDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
}
