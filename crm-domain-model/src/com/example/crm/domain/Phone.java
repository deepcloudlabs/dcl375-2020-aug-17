package com.example.crm.domain;

import java.util.Objects;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
// Value Object, Immutable
public class Phone {
	private final String countryCode;
	private final String cityCode;
	private final String number;
	private final String extension;

	private Phone(String countryCode, String cityCode, String number, String extension) {
		this.countryCode = countryCode;
		this.cityCode = cityCode;
		this.number = number;
		this.extension = extension;
	}

	public static Phone of(String countryCode, String cityCode, String number, String extension) {
		Objects.requireNonNull(cityCode);
		Objects.requireNonNull(number);
		return new Phone(countryCode, cityCode, number, extension);
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getNumber() {
		return number;
	}

	public String getExtension() {
		return extension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityCode == null) ? 0 : cityCode.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((extension == null) ? 0 : extension.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Phone other = (Phone) obj;
		if (cityCode == null) {
			if (other.cityCode != null)
				return false;
		} else if (!cityCode.equals(other.cityCode))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return countryCode + "-" + cityCode + "-" + number + "-" + extension;
	}

}
