package com.example.crm.document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Document(collection = "customers")
@Sharded(shardKey = "identity", immutableKey = true)
public class CustomerDocument {
	@Id
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

	public CustomerDocument() {
	}

	public CustomerDocument(String identity, String fullname, @Email String email,
			@Size(min = 10, max = 40) String address, @Pattern(regexp = "^\\+\\d{8,12}$") String phone, String photo,
			@Min(1941) int birthYear) {
		this.identity = identity;
		this.fullname = fullname;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.photo = photo;
		this.birthYear = birthYear;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDocument other = (CustomerDocument) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerDocument [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", address="
				+ address + ", phone=" + phone + ", birthYear=" + birthYear + "]";
	}

}
