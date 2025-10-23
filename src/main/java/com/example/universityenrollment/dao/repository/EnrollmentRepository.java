package com.example.universityenrollment.dao.repository;

import com.example.universityenrollment.dao.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
