package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.lang.IllegalArgumentException;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) throws IllegalArgumentException {
		if (name == null || name == "") {
			throw new IllegalArgumentException();
		}
		if (price == null || (price.compareTo(new BigDecimal("0.0")) == -1)) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.price = price;
		this.taxPercent = tax;
	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public BigDecimal getTaxPercent() {
		return this.taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return this.price.multiply(this.taxPercent.add(new BigDecimal("1.00")));
	}
}
