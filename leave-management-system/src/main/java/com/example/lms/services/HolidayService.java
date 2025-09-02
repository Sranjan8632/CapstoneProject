package com.example.lms.services;

import java.util.List;

import com.example.lms.entity.Holiday;

public interface HolidayService {

	Holiday addHoliday(Holiday holiday);
	List<Holiday> getAllHolidays();
	
}
