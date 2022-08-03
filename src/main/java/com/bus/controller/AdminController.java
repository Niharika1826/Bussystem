package com.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.dto.BusDto;
import com.bus.dto.RouteDto;
import com.bus.dto.UserDto;
import com.bus.entity.Bus;
import com.bus.entity.Route;
import com.bus.entity.User;
import com.bus.serviceimpl.BusService;
import com.bus.serviceimpl.RouteService;
import com.bus.serviceimpl.UserService;

@CrossOrigin("*")
@RestController

public class AdminController {
	
	@Autowired
	private BusService busService;
	
    @Autowired
    private UserService userService;
    
    
    
    @Autowired
    private RouteService routeService;
    
  
    
    @PostMapping("/addBus")
	public BusDto addBus(@Valid @RequestBody BusDto busDto) {
		return busService.addBus(busDto);
	}
	
	@PutMapping("/updateBus/{busId}")
	public BusDto updateBus(@PathVariable ("busId")int busId,@RequestBody BusDto busDto) {
		return busService.updateBus(busId,busDto);
	}
	
	@DeleteMapping("/deleteBus/{busId}") 
	public BusDto deletBus(@PathVariable ("busId") int busId){
                return busService.deleteBus(busId);
	}
	
	@GetMapping("/viewUser/{userid}")
	public UserDto viewUser(@PathVariable("userid")int userLoginId) {
		return userService.viewUser(userLoginId);
	}
	
	@GetMapping("/viewAllUsers")
	public List<User> viewAllUsers(){
		return userService.viewAllUsers();
	}
	
	@PostMapping("/addRoute")
	public RouteDto addRoute(@Valid @RequestBody  RouteDto routeDto) {
		return routeService.addRoute(routeDto);
	}
	
	@PutMapping("/updateRoute/{routeId}")
	public RouteDto updateRoute(@RequestBody RouteDto routeDto,@PathVariable("routeId")int routeId) {
		return routeService.updateRoute(routeId,routeDto);
	}
	@DeleteMapping( "/deleteRoute/{routeId}")
	public RouteDto deleteRoute(@PathVariable("routeId")int routeId) {
		return routeService.deleteRoute(routeId);
	}
	
	@GetMapping("/getRouteById/{id}")
	public RouteDto getRouteById(@PathVariable("id") int id) {
		return routeService.viewRoute(id);
	}
	@GetMapping("/getAllRoutes")
	public List<Route> getAllRoutes(){
		return routeService.viewAllRoutes();
	}
	
	@PatchMapping("/addBustoRoute/{id}")
	public Route addRouteToBus(@PathVariable("id") int id ,@RequestBody Bus bus) {
		return routeService.addBusToRoute(id,bus);
	}
	
	
	

}
