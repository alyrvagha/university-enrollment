package com.example.universityenrollment.service;

import com.example.universityenrollment.dao.entity.*;
import com.example.universityenrollment.exception.CourseFullException;
import com.example.universityenrollment.dao.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public void enrollStudent(Long studentId, Long courseId) {
        log.info("Enrollment started: studentId={}, courseId={}", studentId, courseId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getEnrolledCount() >= course.getCapacity()) {
            log.warn("Course is full! Cannot enroll student {}", student.getName());
            throw new CourseFullException("Course " + course.getName() + " is already full!");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .createdAt(LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollment);

        course.setEnrolledCount(course.getEnrolledCount() + 1);
        courseRepository.save(course);

        log.info("Student {} successfully enrolled to {}", student.getName(), course.getName());
    }
}
