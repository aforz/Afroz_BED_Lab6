package com.gl.student.registrations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.student.registrations.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
