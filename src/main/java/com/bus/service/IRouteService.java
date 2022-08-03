package com.bus.service;

import java.util.List;

import com.bus.dto.RouteDto;
import com.bus.entity.Bus;
import com.bus.entity.Route;

public interface IRouteService {

	public RouteDto addRoute(RouteDto routeDto) ;
	public RouteDto updateRoute(int routeId,RouteDto routeDto);
	public RouteDto deleteRoute(int routeId);
	public RouteDto viewRoute(int routeId);
	public List<Route> viewAllRoutes();
	public Route addBusToRoute(int routeid,Bus bus);
}
