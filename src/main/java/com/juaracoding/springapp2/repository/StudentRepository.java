package com.juaracoding.springapp2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.springapp2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByEmail(String email);
	
}
