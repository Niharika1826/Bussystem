package com.bus.service;

import java.time.LocalDate;
import java.util.List;

import com.bus.dto.ReservationDto;
import com.bus.entity.Reservation;

public interface IReservationService {

	public ReservationDto addReservation(ReservationDto reservationDto);
	public ReservationDto updateReservation(int reservationId,ReservationDto reservationDto);
	public ReservationDto deleteReservation(int reservationId);
	public ReservationDto viewReservation(int reservationId);
	public List<Reservation> viewAllReservation();
	public List<ReservationDto> getAllReservation(LocalDate date) ;
	
}
