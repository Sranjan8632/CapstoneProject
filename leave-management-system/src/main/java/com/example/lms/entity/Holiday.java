package com.example.lms.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Holiday {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 10)
	private LocalDate holidayDate;
	@Column(length = 20)
	private String holidayDetails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return holidayDate;
	}
	public void setDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getHolidayDetails() {
		return holidayDetails;
	}
	public void setHolidayDetails(String holidayDetails) {
		this.holidayDetails = holidayDetails;
	}
	
	
	
	

}
