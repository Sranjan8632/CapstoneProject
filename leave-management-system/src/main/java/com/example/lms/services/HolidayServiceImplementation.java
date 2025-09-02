package com.example.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Holiday;
import com.example.lms.database.HolidayDatabase;

@Service
public class HolidayServiceImplementation implements HolidayService {

	@Autowired
	HolidayDatabase holidaydatabase;
	
	@Override
	public Holiday addHoliday(Holiday holiday) {
		// TODO Auto-generated method stub
		return holidaydatabase.save(holiday);
	}

	@Override
	public List<Holiday> getAllHolidays() {
		// TODO Auto-generated method stub
		return holidaydatabase.findAll();
	}

	
}
