package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products;
	private BigDecimal subtotal = new BigDecimal("0.00");
	private BigDecimal tax = new BigDecimal("0.00");
	private BigDecimal total = new BigDecimal("0.00");

	public void addProduct(Product product) {
		this.products.add(product);
		this.subtotal = this.subtotal.add(product.getPrice());
		this.total = this.total.add(product.getPriceWithTax());
		this.tax = this.tax.add(product.getPriceWithTax().subtract(product.getPrice()));
	}

	public void addProduct(Product product, Integer quantity) {
		for (int i=0; i<quantity; i++) {
			this.addProduct(product);
		}
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public BigDecimal getTotal() {
		return this.total;
	}
}
