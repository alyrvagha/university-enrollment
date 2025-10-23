package com.example.universityenrollment;

import com.example.universityenrollment.dao.entity.Course;
import com.example.universityenrollment.dao.entity.Student;
import com.example.universityenrollment.service.EnrollmentService;
import com.example.universityenrollment.dao.repository.CourseRepository;
import com.example.universityenrollment.dao.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityEnrollmentApplication.class, args);
	}

	@Bean
	CommandLineRunner run(StudentRepository studentRepository,
						  CourseRepository courseRepository,
						  EnrollmentService enrollmentService) {
		return args -> {
			Student s1 = studentRepository.save(new Student(null, "Jane", "jane@mail.com"));
			Student s2 = studentRepository.save(new Student(null, "Martin", "martin@mail.com"));

			Course c1 = courseRepository.save(new Course(null, "Java Programming", 1, 0));

			enrollmentService.enrollStudent(s1.getId(), c1.getId());

			try {
				enrollmentService.enrollStudent(s2.getId(), c1.getId());
			} catch (Exception e) {
				System.out.println(" Error: " + e.getMessage());
			}
		};
	}
}
