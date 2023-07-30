package com.gl.student.registrations.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "role_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
	private Set<User> users = new HashSet<>();

}
