package com.example.crm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.validation.TcKimlikNo;

// JPA: Java Persistence API (Java EE Specification)
// JPA: ORM Tool: Table <--> Entity (Mapping)
// Mapping: Annotations
//          @Entity, @Table, @SecondaryTable, @Id, @Column, @OneToOne, @OneToMany, ...
// JPA Providers: Hibernate, EclipseLink, OpenJpa, ...
// JPA -> EntityManager (persist, merge, remove, find, ...)
// JPA -> DAO Pattern -> EntityManager
// Spring Data JPA -> Interface
// Alt + Shift + S : Source generation
// Ctrl + Shift + F : Format
@Entity
@Table(name = "customers")
@DynamicUpdate
@DynamicInsert
public class Customer {
	@Id
	@TcKimlikNo
	private String identity;
	@Column(name = "full_name")
	private String fullname;
	@Column(unique = true)
	@Email
	private String email;
	@Size(min = 10, max=40)
	private String address;
	@Pattern(regexp = "^\\+\\d{8,12}$")
	private String phone;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@Column(name = "birth_year")
	@Min(1941)
	private int birthYear;

	public Customer() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", birthYear=" + birthYear + "]";
	}

}
