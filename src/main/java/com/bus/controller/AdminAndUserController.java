package com.bus.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.dto.BusDto;
import com.bus.dto.FeedBackDto;
import com.bus.dto.ReservationDto;
import com.bus.dto.UserDto;
import com.bus.entity.Bus;
import com.bus.entity.FeedBack;
import com.bus.entity.Reservation;
import com.bus.serviceimpl.BusService;
import com.bus.serviceimpl.FeedBackService;
import com.bus.serviceimpl.ReservationService;
import com.bus.serviceimpl.UserService;
@CrossOrigin("*")
@RestController
public class AdminAndUserController {
	
	@Autowired
	private BusService busService;
	
    @Autowired
    private FeedBackService feedBackService;
    
    @Autowired
    private ReservationService reservationService;
    
    
    @GetMapping("/viewAllBuses")
	public List<Bus> viewAllBus(){
		return busService.viewAllBuses();
	}
    
	@GetMapping("/viewBus/{busId}")
	public BusDto viewBus(@PathVariable ("busId") int busId) {
	return busService.viewBus(busId);
    }

	@GetMapping("/viewBusByType/{busType}")
    public List<BusDto> viewBusByType(@PathVariable("busType") String busType){
	  return busService.viewBusByType(busType);
    }
	
	@GetMapping("/viewAllReservations")
	public List<Reservation> viewAllReservation(){
		return  reservationService.viewAllReservation();
	}
	
	@PostMapping("/viewReservationByDate/{date}")
	public List<ReservationDto> findByReservationDate(@PathVariable ("date") LocalDate date){
		return  reservationService.getAllReservation(date);
	}

     @GetMapping("/viewAllFeedBacks")
     public List<FeedBack> viewAllFeedBack(){
	 return feedBackService.viewAllFeedBack();
   }
     
         
    


}
