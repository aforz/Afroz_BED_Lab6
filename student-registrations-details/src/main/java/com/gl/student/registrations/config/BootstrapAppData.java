package com.gl.student.registrations.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.gl.student.registrations.entities.Role;
import com.gl.student.registrations.entities.Student;
import com.gl.student.registrations.entities.User;
import com.gl.student.registrations.repositories.StudentRepository;
import com.gl.student.registrations.repositories.UserRepository;

@Configuration
public class BootstrapAppData {

	@Bean
	public RestTemplate resTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Faker faker = new Faker();

	@EventListener(ApplicationReadyEvent.class)
	public void addStudents(ApplicationReadyEvent event) {
		for (int i = 0; i < 5; i++) {
			Student student = new Student();
			student.setFirstName(faker.name().firstName());
			student.setLastName(faker.name().lastName());
			student.setCourse(faker.programmingLanguage().name());
			student.setCountry(faker.country().name());
			this.studentRepository.saveAndFlush(student);
		}
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void addUserAndRoles(ApplicationReadyEvent event) {

		User afroz = new User();
		User alam = new User();

		afroz.setName("afroz");
		afroz.setPassword(this.passwordEncoder.encode("admin"));

		alam.setName("alam");
		alam.setPassword(this.passwordEncoder.encode("user"));

		Role userRole = new Role();
		Role adminRole = new Role();

		userRole.setName("ROLE_USER");
		adminRole.setName("ROLE_ADMIN");

		afroz.addRole(userRole);
		afroz.addRole(adminRole);

		alam.addRole(userRole);

		this.userRepository.save(afroz);
		this.userRepository.save(alam);
	}
}
