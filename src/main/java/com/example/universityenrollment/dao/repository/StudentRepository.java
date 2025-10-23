package com.example.universityenrollment.dao.repository;

import com.example.universityenrollment.dao.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
