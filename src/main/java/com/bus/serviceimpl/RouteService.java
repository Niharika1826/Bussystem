package com.bus.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dto.BusDto;
import com.bus.dto.RouteDto;
import com.bus.entity.Bus;
import com.bus.entity.Route;
import com.bus.exceptions.BusNotFoundException;
import com.bus.exceptions.RouteNotFoundException;
import com.bus.repository.BusRepository;
import com.bus.repository.RouteRepository;
import com.bus.service.IRouteService;

@Service
public class RouteService implements IRouteService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private BusRepository busRepository;

	
	public RouteDto addRoute(RouteDto routeDto) {
		
	List<Bus> bus=routeDto.getBus();
	System.out.println(bus);
	Route route = new Route();
		BeanUtils.copyProperties(routeDto, route);
		routeRepository.save(route);
		return routeDto;
	}

	public RouteDto updateRoute(int routeId,RouteDto routeDto) {
		if(!routeRepository.existsById(routeId)) {
			throw new RouteNotFoundException();
		}
		 Route route = routeRepository.findById(routeId).get();
		  
		  RouteDto routeDto1 = new RouteDto();
		  BeanUtils.copyProperties(route, routeDto1);
	
		 routeDto1.setDistance(routeDto.getDistance());
		 routeDto1.setRouteFrom(routeDto.getRouteFrom());
		 routeDto1.setRouteTo(routeDto.getRouteTo());
		 
		  BeanUtils.copyProperties(routeDto1,route);
		
		  routeRepository.saveAndFlush(route);
		  
		  return routeDto1;		
	}
	
	

	public RouteDto deleteRoute(int routeId) {
		                                               
		if(!routeRepository.existsById(routeId)) {
			throw new RouteNotFoundException();
		}
		
		RouteDto routeDto = new RouteDto();
		Route route = new Route();
		route = routeRepository.findById(routeId).get();
		BeanUtils.copyProperties(route, routeDto);
		routeRepository.deleteById(routeId);
		return routeDto;
	}

	
	public RouteDto viewRoute(int routeId) {
		
		if(!routeRepository.existsById(routeId)) {
			throw new RouteNotFoundException();
		}

		RouteDto routeDto = new RouteDto();
		Route route =new Route();
		route=routeRepository.findById(routeId).get();
		BeanUtils.copyProperties(route, routeDto);
		return routeDto;
	}

	
	public List<Route> viewAllRoutes() {
		List<RouteDto>routeDtoList=new ArrayList<>();
		List<Route>routeList=routeRepository.findAll();
		RouteDto routeDto=new RouteDto();
		for(Route route:routeList) {
			BeanUtils.copyProperties(route, routeDto);
			routeDtoList.add(routeDto);
		}
		return routeList;

	}
	
	public Route addBusToRoute(int routeid, Bus bus) {
		Route route = routeRepository.findById(routeid).orElseThrow(RouteNotFoundException::new);
		
		List<Bus>buses =route.getBus();
		busRepository.save(bus);
		buses.add(bus);
		route.setBus(buses);
		routeRepository.save(route);
		return route;
	}
	}

	
