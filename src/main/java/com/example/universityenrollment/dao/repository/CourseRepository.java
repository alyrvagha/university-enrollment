package com.example.universityenrollment.dao.repository;

import com.example.universityenrollment.dao.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
