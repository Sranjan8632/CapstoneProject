package com.example.lms.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.Holiday;

@Repository
public interface HolidayDatabase extends JpaRepository<Holiday, Integer>  {

}
