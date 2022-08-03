package com.bus.serviceimpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dto.BusDto;
import com.bus.dto.ReservationDto;
import com.bus.entity.Bus;
import com.bus.entity.Reservation;
import com.bus.entity.User;
import com.bus.exceptions.BusNotFoundException;
import com.bus.exceptions.ReservationNotFoundException;
import com.bus.exceptions.UserNotFoundException;
import com.bus.repository.BusRepository;
import com.bus.repository.ReservationRepository;
import com.bus.repository.UserRepository;

@Service
public class ReservationService{
	
	
	
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private UserRepository userRepository;

	public ReservationDto addReservation(ReservationDto reservationDto) {
		
	Reservation reservation = new Reservation();
	User user = userRepository.findById(reservationDto.getUser().getUserLoginId()).orElseThrow(UserNotFoundException::new);
	Bus bus = busRepository.findById(reservationDto.getBus().getBusId()).orElseThrow(BusNotFoundException::new);
	
	BeanUtils.copyProperties(reservationDto, reservation);
	System.out.println(user+"******"+bus);
	reservation.setBus(bus);
	
	reservation.setUser(user);
	reservation.setReservationTime(LocalTime.now());
	reservationRepository.save(reservation);
	int availableSeats = bus.getAvailableSeats()-1;
	bus.setAvailableSeats(availableSeats);
	busRepository.saveAndFlush(bus);
	return reservationDto;
		
	}

	public ReservationDto updateReservation(int reservationId,ReservationDto reservationDto) {
		
		if(!reservationRepository.existsById(reservationId)&&reservationDto.getBus().getAvailableSeats()>0) {
			throw new ReservationNotFoundException();
		}
		
		 Reservation reservation = reservationRepository.findById(reservationId).get();
		  		  ReservationDto reservationDto1 = new ReservationDto();
		  BeanUtils.copyProperties(reservation, reservationDto1);

		 reservationDto1.setDestination(reservationDto.getDestination());
		 reservationDto1.setReservationDate(reservationDto.getReservationDate());
		 reservationDto1.setReservationType(reservationDto.getReservationType());
		 reservationDto1.setSource(reservationDto.getSource());
		 
		 
		  BeanUtils.copyProperties(reservationDto1, reservation);
		
		  reservationRepository.saveAndFlush(reservation);
		  
		  return reservationDto1;
	}

	
	public ReservationDto deleteReservation(int reservationId) {
		if(!reservationRepository.existsById(reservationId)) {
			throw new ReservationNotFoundException();
		}
		ReservationDto reservationDto=new ReservationDto();
		Reservation reservation=new Reservation();
		reservation = reservationRepository.findById(reservationId).get();
		       BeanUtils.copyProperties(reservation, reservationDto);
		       reservationRepository.deleteById(reservationId);
		       return reservationDto;
			}


	public ReservationDto viewReservation(int reservationId) {
		if(!reservationRepository.existsById(reservationId)) {
			throw new ReservationNotFoundException();
		}
		ReservationDto reservationDto=new ReservationDto();
		Reservation reservation=new Reservation();
		
		reservation=reservationRepository.findById(reservationId).get();
		BeanUtils.copyProperties(reservation, reservationDto);
		
		return reservationDto;
	}

	public List<Reservation> viewAllReservation() {
		List<ReservationDto> reservationDtoList=new ArrayList<>();
		List<Reservation> reservationList=reservationRepository.findAll();
		ReservationDto reservationDto=new ReservationDto();
		for(Reservation reservation:reservationList) {
			BeanUtils.copyProperties(reservation, reservationDto);
			reservationDtoList.add(reservationDto);
		}
		return reservationList;
	
	}

	public List<ReservationDto> getAllReservation(LocalDate date) {
		
		List<Reservation> reservationList = reservationRepository.findAllByReservationDate(date);
		List<ReservationDto> reservationDtoList = new ArrayList<>();
		
		ReservationDto reservationDto = new ReservationDto();
		
		for(Reservation reservation : reservationList) {
			BeanUtils.copyProperties(reservation, reservationDto);
			reservationDtoList.add(reservationDto);
		}
		
		return reservationDtoList;
				
		
	}
	
}