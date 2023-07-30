package com.gl.student.registrations.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.student.registrations.entities.Student;
import com.gl.student.registrations.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> students = studentService.findAll();
		theModel.addAttribute("students", students);
		return "students/list-students";
	}

	@GetMapping("/add")
	public String addStudent(Model theModel) {
		Student student = new Student();
		String heading = "Add Students";
		theModel.addAttribute("heading", heading);
		theModel.addAttribute("student", student);
		return "students/student-form";
	}

	@PostMapping("/update")
	public String updateStudent(@RequestParam("id") int id, Model theModel) {
		Student student = studentService.findById(id);
		String heading = "Update Student";
		theModel.addAttribute("heading", heading);
		theModel.addAttribute("student", student);
		return "students/student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.save(student);
		return "redirect:/students/list";
	}

	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("id") int id) {
		studentService.deleteById(id);
		return "redirect:/students/list";

	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}
