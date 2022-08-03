package com.bus.service;

import java.util.List;

import com.bus.dto.FeedBackDto;
import com.bus.entity.FeedBack;

public interface IFeedBackService {
 
	public FeedBackDto addFeedBack(FeedBackDto feedBackDto);
	public FeedBackDto updateFeedBack(int feedbackId, FeedBackDto feedBackDto);
	public FeedBackDto viewFeedBack(int feedbackId);
	public List<FeedBack> viewAllFeedBack();
	
}
