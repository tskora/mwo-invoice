package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	private final Integer invoiceNumber;
	private static Integer counter;
	
	public Invoice() {
		if (counter == null) {
			counter = 1;
			invoiceNumber = counter;
		}
		else {
			counter += 1;
			invoiceNumber = counter;
		}
	}

	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		products.put(product, quantity);
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}

	public int getInvoiceNumber() {
		// TODO Auto-generated method stub
		return invoiceNumber;
	}
	
	public String toString() {
		String firstLine = invoiceNumber.toString()+"\n";
		String body = "";
		for (Product product : products.keySet()) {
			body += product.getName().toString()+" "+product.getPrice().toString();
		}
		return firstLine + body;
	}
}
