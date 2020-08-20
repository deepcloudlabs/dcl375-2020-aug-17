package com.example.crm.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
// Value Object, Immutable
public final class TcKimlikNo {
	private final String value;
	private static final Map<String, TcKimlikNo> cache = new ConcurrentHashMap<>();

	private TcKimlikNo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static TcKimlikNo of(String value) {
		// Validation
		if (!isValid(value))
			throw new IllegalArgumentException("This is not a valid identity");
		// Object Caching
		var cachedValue = cache.get(value);
		if (Objects.isNull(cachedValue)) {
			cachedValue = new TcKimlikNo(value);
			cache.put(value, cachedValue);
		}
		return cachedValue;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		var result = 1;
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
		var other = (TcKimlikNo) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	private static boolean isValid(String value) {
		if (value == null)
			return false;
		if (!value.matches("^\\d{11}$")) {
			return false;
		}
		var digits = new int[11];
		for (var i = 0; i < digits.length; ++i) {
			digits[i] = value.charAt(i) - '0';
		}
		var x = digits[0];
		var y = digits[1];
		for (var i = 1; i < 5; i++) {
			x += digits[2 * i];
		}
		for (var i = 2; i <= 4; i++) {
			y += digits[2 * i - 1];
		}
		var c1 = 7 * x - y;
		if (c1 % 10 != digits[9]) {
			return false;
		}
		var c2 = 0;
		for (var i = 0; i < 10; ++i) {
			c2 += digits[i];
		}
		if (c2 % 10 != digits[10]) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TcKimlikNo [value=" + value + "]";
	}

}