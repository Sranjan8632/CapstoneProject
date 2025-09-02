package com.example.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Holiday;
import com.example.lms.services.HolidayService;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
	@Autowired
	private HolidayService holidayservice;
	
	@PostMapping("/addholidaydetails")
	public Holiday addHoliday(@RequestBody Holiday holiday) {
		return holidayservice.addHoliday(holiday);
	}
	
	@GetMapping("/viewholidaylist")
	public List<Holiday> getAllPublicHolidays(){
		return holidayservice.getAllHolidays();
	}

}
