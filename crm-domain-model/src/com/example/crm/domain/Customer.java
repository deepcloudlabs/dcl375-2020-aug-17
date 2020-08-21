package com.example.crm.domain;

public class Customer {
	private TcKimlikNo identity;
	private FullName fullname;
	private Email email;
	private Address address;
	private Phone phone;
	private Photo photo;
	private BirthYear birthYear;

	public Customer() {
	}

	private Customer(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.email = builder.email;
		this.address = builder.address;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
		this.phone = builder.phone;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public void setIdentity(TcKimlikNo identity) {
		this.identity = identity;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}

	public static class Builder {
		private final TcKimlikNo identity;
		private FullName fullname;
		private Email email;
		private Address address;
		private Phone phone;
		private Photo photo;
		private BirthYear birthYear;

		public Builder(String identity) {
			this.identity = TcKimlikNo.of(identity);
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = new FullName(firstName, lastName);
			return this;
		}

		public Builder email(String value) {
			this.email = Email.of(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.of(value);
			return this;
		}

		public Builder photo(byte[] value) {
			this.photo = Photo.of(value);
			return this;
		}

		public Builder phone(String countryCode, String cityCode, String number, String extension) {
			this.phone = Phone.of(countryCode, cityCode, number, extension);
			return this;
		}
		public Builder phone(String phoneString) {
			this.phone = Phone.of("", "", phoneString, "");
			return this;
		}

		public Builder address(String street, String city, String country, String zipCode) {
			this.address = new Address(street, city, country, zipCode);
			return this;
		}
		
		public Builder address(String addressString) {
			this.address = new Address(addressString, "", "", "");
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}

	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", birthYear=" + birthYear + "]";
	}

}
