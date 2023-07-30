package com.gl.student.registrations.services;

import java.util.List;

import com.gl.student.registrations.entities.Student;

public interface StudentService {

	public List<Student> findAll();

	public Student findById(int id);

	public void save(Student student);

	public void deleteById(int id);

}
