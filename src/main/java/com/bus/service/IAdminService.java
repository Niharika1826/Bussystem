package com.bus.service;

import com.bus.dto.AdminDto;
import com.bus.entity.Admin;

public interface IAdminService {
	
	public AdminDto addAdmin(AdminDto adminDto);
	public Admin adminLogin(String adminUserName,String adminPassword);

}
