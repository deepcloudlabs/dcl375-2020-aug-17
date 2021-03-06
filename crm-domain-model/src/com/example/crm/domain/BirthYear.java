package com.example.crm.domain;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
// Value Object, Immutable
public final class BirthYear {
	private final int value;

	private BirthYear(int value) {
		this.value = value;
	}

	public static BirthYear of(int value) {
		if (value > 2020)
			throw new IllegalArgumentException("Birth Year cannot be from the future.");
		return new BirthYear(value);
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		BirthYear other = (BirthYear) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}

}
