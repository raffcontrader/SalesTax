package com.boku.model;

import com.boku.util.NumberUtils;


/**
 * Proprietà correlate all'Item
 * @author Raffaele Della Porta
 */
public class Item {

	private final int quantity;
	
	private final String itemDescription;
	
	private final double price;
	
	private final boolean isExcluded;
	
	private final boolean isImported;
	
	private double importTax;
	
	private double salesTax;
	
	public Item(int qty, String desc, double price, boolean isExcluded, boolean isImported) {
		this.quantity = qty;
		this.itemDescription = desc;
		this.price = price;
		this.isExcluded = isExcluded;
		this.isImported = isImported;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public double getPrice() {
		return price;
	}

	public boolean isExcluded() {
		return isExcluded;
	}

	public boolean isImported() {
		return isImported;
	}

	public double getTotalTax(){
		return this.salesTax + this.importTax;
	}
	
	public double getImportTax() {
		return importTax;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	
	
	@Override
	public String toString() {
		return quantity + "" + itemDescription +": "+  NumberUtils.roundOff(price+importTax+salesTax);
	}
}
