package com.bus.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dto.FeedBackDto;
import com.bus.dto.UserDto;
import com.bus.entity.Bus;
import com.bus.entity.FeedBack;
import com.bus.entity.User;
import com.bus.exceptions.BusNotFoundException;
import com.bus.exceptions.FeedBackNotFoundException;
import com.bus.exceptions.UserNotFoundException;
import com.bus.repository.BusRepository;
import com.bus.repository.FeedBackRepository;
import com.bus.repository.UserRepository;
import com.bus.service.IFeedBackService;


@Service
public class FeedBackService implements IFeedBackService {
	
	@Autowired
    private FeedBackRepository feedBackRepository;
	
     @Autowired
     private UserRepository userRepository;
     
     @Autowired
     private BusRepository busRepository;
     

	public FeedBackDto addFeedBack(FeedBackDto feedBackDto) {
		
		FeedBack feedBack = new FeedBack();
//		User user = userRepository.findById(feedBackDto.getUser().getUserLoginId()).get();
//		Bus bus = busRepository.findById(feedBackDto.getBus().getBusId()).get();
		BeanUtils.copyProperties(feedBackDto, feedBack);
//		feedBack.setBus(bus);
//		feedBack.setUser(user);
		feedBack.setFeedBackDate(LocalDate.now());
		feedBackRepository.save(feedBack);
		return feedBackDto;
	}

	
	public FeedBackDto updateFeedBack(int feedbackId,FeedBackDto feedBackDto) {
		
		if(!feedBackRepository.existsById(feedbackId)) {
			throw new FeedBackNotFoundException();
		}
		
		FeedBackDto feedBackDto1 = new FeedBackDto();
	
		FeedBack feedback=feedBackRepository.findById(feedbackId).get();
		BeanUtils.copyProperties(feedback, feedBackDto1);
		feedBackDto1.setOverallRating(feedBackDto.getOverallRating());

		feedBackDto1.setComments(feedBackDto.getComments());
		feedBackDto1.setDriverRating(feedBackDto.getDriverRating());
		feedBackDto1.setServiceRating(feedBackDto.getServiceRating());
		
		BeanUtils.copyProperties(feedBackDto1, feedback);
		
		feedBackRepository.saveAndFlush(feedback);
		
	
			
		return feedBackDto1;
	}

	
	public FeedBackDto viewFeedBack(int feedbackId) {
		if(!feedBackRepository.existsById(feedbackId)) {
			throw new FeedBackNotFoundException();
		}
		FeedBackDto feedbackDto = new FeedBackDto();
		FeedBack feedback= feedBackRepository.findById(feedbackId).get();
		
		BeanUtils.copyProperties(feedback, feedbackDto);
		
				return feedbackDto;
	}

	
	public List<FeedBack> viewAllFeedBack() {
        List<FeedBackDto> feedbackDtoList= new ArrayList<>();
        List<FeedBack> feedbackList=feedBackRepository.findAll();

        for(FeedBack feedback:feedbackList) {
            FeedBackDto feedbackDto =new FeedBackDto();
            BeanUtils.copyProperties(feedback,feedbackDto);
            System.out.println(feedbackDto);
            feedbackDtoList.add(feedbackDto);


        }


        return feedbackList;
    }

}
