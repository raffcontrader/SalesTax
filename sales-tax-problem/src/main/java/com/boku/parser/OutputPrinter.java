package com.boku.parser;

import java.util.List;

import com.boku.model.Item;

/**
 * @author Raffaele Della Porta
 * Stampa l'output
 */
public class OutputPrinter {
	
	private final List<Item> items;
	
	private final double total;
	
	private final double salesTaxes;
	
	private final String SALEX_TAXES_PREFIX = "Sales Taxes: ";
	
	private final String TOTAL_PREFIX = "Total: ";
	
	public OutputPrinter(List<Item> items, double salesTaxes, double total){
		this.items = items;
		this.salesTaxes = salesTaxes;
		this.total = total;
	}
	
	public void print(){
		System.out.println("Output");
		System.out.println("--------------");
		
		for (Item item : items) {
			System.out.println(item.toString());
		}
		System.out.println(SALEX_TAXES_PREFIX + String.valueOf(salesTaxes));
		System.out.println(TOTAL_PREFIX + String.valueOf(total));
	}
}
