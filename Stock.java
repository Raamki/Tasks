package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stock {
	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private int quantity;
	private float price;
	private String manufactureDate;
	public int getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	@Override
	public String toString() {
		return "\nproductId=" + productId + "\n productName=" + productName + "\n quantity=" + quantity + "\n price="
				+ price + "\n manufactureDate=" + manufactureDate;
	}
	
}
