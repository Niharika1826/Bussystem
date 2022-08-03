package com.bus.serviceimpl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dto.BusDto;
import com.bus.entity.Bus;
import com.bus.exceptions.BusNotFoundException;
import com.bus.repository.BusRepository;
import com.bus.service.IBusService;


@Service

public class BusService implements IBusService {
	
	@Autowired
	private BusRepository busRepository;
	
	public BusDto addBus(BusDto busDto) {
		Bus bus = new Bus();
		
		
		BeanUtils.copyProperties(busDto, bus);
		busRepository.save(bus);
		return busDto;
	}
	
	public BusDto updateBus(int busId, BusDto busDto) {
		if(!busRepository.existsById(busId)) {
			throw new BusNotFoundException();
		}
		  Bus bus = busRepository.findById(busId).get();
		 
		  BusDto busDto1 = new BusDto();
		  BeanUtils.copyProperties(bus, busDto1);
		 
		  busDto1.setBusName(busDto.getBusName());
		  busDto1.setAvailableSeats(busDto.getAvailableSeats());
		  busDto1.setBusType(busDto.getBusType());
		  busDto1.setDepartureTime(busDto.getDepartureTime());
		  busDto1.setDriverName(busDto.getDriverName());
		  busDto1.setRouteFrom(busDto.getRouteFrom());
		  busDto1.setRouteTo(busDto.getRouteTo());
		 
		  BeanUtils.copyProperties(busDto1, bus);

		  busRepository.saveAndFlush(bus);
		  
		  return busDto;
		}
	
	
	public BusDto deleteBus(int busId) {
		if(!busRepository.existsById(busId)) {
			throw new BusNotFoundException();
		}
        BusDto busDto = new BusDto();
		Bus bus = busRepository.findById(busId).get();
		busRepository.deleteById(busId);
		BeanUtils.copyProperties(bus, busDto);
		return busDto;
		
	}
	
	
	public BusDto viewBus(int busId) {
		if(!busRepository.existsById(busId)) {
			throw new BusNotFoundException();
		}
		BusDto busDto = new BusDto();
		Bus bus = busRepository.findById(busId).get();
		BeanUtils.copyProperties(bus, busDto);
		return busDto;
	}
	
	public List<BusDto> viewBusByType(String busType){
		if(busRepository.findByBusType(busType)!=null) {
			throw new BusNotFoundException();
		}
		BusDto busDto = new BusDto();
		List<BusDto> busDtoList = new ArrayList<>();
	    List<Bus> busList =  busRepository.findByBusType(busType);
	    for(Bus bus : busList) {
	    	BeanUtils.copyProperties(bus, busDto);
	    	busDtoList.add(busDto);
	    }
		return busDtoList;
	}
	
	public List<Bus> viewAllBuses(){
		
        List<Bus> busList = busRepository.findAll();
        List<BusDto> busDtoList = new ArrayList<>();
        BusDto busDto = new BusDto();
        for(Bus bus : busList) {
        	BeanUtils.copyProperties(bus, busDto);
        	busDtoList.add(busDto);
        }
        return busList;
	}
}

