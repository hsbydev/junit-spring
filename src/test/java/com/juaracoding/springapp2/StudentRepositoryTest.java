package com.juaracoding.springapp2;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.juaracoding.springapp2.model.Student;
import com.juaracoding.springapp2.repository.StudentRepository;

@DataJpaTest
@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	@Order(1)
	@Rollback (value = false)
	public void saveStudentTesting() {
		Student student = Student.builder()
				.firstName("Test 1")
				.lastName("Test 1")
				.email("Test1@email.com")
				.phone("0812345")
				.build();
				studentRepository.save(student);
				Assertions.assertThat(student.getId()).isGreaterThan(0);
	}
	@Test
	@Order(2)
	public void getStudentTesting() {
		Student student = studentRepository.findById(1L).get();
		Assertions.assertThat(student.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	public void getListOfStudent() {
		List<Student> students = studentRepository.findAll();
		Assertions.assertThat(students.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateStudentTest() {
		Student student = studentRepository.findById(1L).get();
		student.setEmail("test1@email.com");
		Student studentUpdated = studentRepository.save(student);
		Assertions.assertThat(studentUpdated.getEmail()).isEqualTo("test1@email.com");
	}
	@Test
	@Order(5)
	public void getStudentByEmailTest() {
		Student student = studentRepository.findByEmail("test1@email.com").get();
		Assertions.assertThat(student.getEmail()).isEqualTo("test1@email.com");
	}
	
	@Test
	@Order(6)
	public void deleteStudentTest() {
		Student student = studentRepository.findById(1L).get();
		studentRepository.delete(student);
		Optional<Student> studentDeleted = studentRepository.findById(1L);
		Assertions.assertThat(studentDeleted).isEmpty();
	}
}
