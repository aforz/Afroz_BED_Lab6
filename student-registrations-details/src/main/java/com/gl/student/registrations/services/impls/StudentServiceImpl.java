package com.gl.student.registrations.services.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.student.registrations.entities.Student;
import com.gl.student.registrations.repositories.StudentRepository;
import com.gl.student.registrations.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student findById(int id) {
		Optional<Student> studentFromDB = studentRepository.findById(id);
		Student student = null;
		if (studentFromDB.isPresent()) {
			student = studentFromDB.get();
		} else {
			throw new RuntimeException("Student doesn't exist: " + id);
		}
		return student;
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

}
