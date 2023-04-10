package com.java.devops3.devops3;

import lombok.*;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

	public Long getId() {
		// TODO Auto-generated method
		return this.id;
	}

	public Object getFirstName() {
		return this.firstName;

	}

	public Object getEmail() {
		return this.email;
	}

	public void setFirstName(Object firstName2) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(Object email2) {
		// TODO Auto-generated method stub
		
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}