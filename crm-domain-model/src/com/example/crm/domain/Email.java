package com.example.crm.domain;

import java.util.Objects;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
// Value Object, Immutable
public class Email {
	private final String value;

	private Email(String value) {
		this.value = value;
	}

	public static Email of(String value) {
		Objects.requireNonNull(value);
		if (!value.toLowerCase().matches("[a-z]+@[a-z]+\\.[a-z]+"))
			throw new IllegalArgumentException("This is not a valid e-mail.");
		return new Email(value);
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Email other = (Email) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Email [value=" + value + "]";
	}

}
