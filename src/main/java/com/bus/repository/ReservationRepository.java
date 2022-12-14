package com.bus.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
	
	public List<Reservation> findAllByReservationDate(LocalDate date );
	    
	
	
}
