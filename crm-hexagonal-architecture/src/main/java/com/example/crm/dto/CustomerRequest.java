package com.example.crm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class CustomerRequest {
	private String identity;
	private String fullname;
	@Email
	private String email;
	@Size(min = 10, max = 40)
	private String address;
	@Pattern(regexp = "^\\+\\d{8,12}$")
	private String phone;
	private String photo;
	@Min(1941)
	private int birthYear;

	public CustomerRequest() {
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
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
		return "EmployeeRequest [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", address="
				+ address + ", phone=" + phone + ", birthYear=" + birthYear + "]";
	}

}
