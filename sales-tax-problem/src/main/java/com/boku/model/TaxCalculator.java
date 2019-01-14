package com.boku.model;

import java.util.List;
import com.boku.util.NumberUtils;

/**
 * @author Raffaele Della Porta
 * Questa classe calcola la tassa di vendita
 * 
 */
public class TaxCalculator {
	
	private final double SALES_TAX_RATE = 0.10d;
	
	private final double IMPORT_TAX_RATE = 0.05d;
	
	private final List<Item> items;
	
	private double total;
	
	private double salesTaxes;

	
	public TaxCalculator(List<Item> items){
		this.items = items;
	}
	
	public double getTotalAll() {
		return NumberUtils.roundOff(total+salesTaxes);
	}

	public double getSalesTaxes() {
		return salesTaxes;
	}
	
	/*
	 * Questo metodo fornisce:
	 * 1) total: memorizza il prezzo totale degli articoli.
	 * 2) salesTaxes:  memorizza l'imposta sulle vendite totale.
	 */
	public void computeTax(){
		for (Item item : items) {
			if (!item.isExcluded())
				item.setSalesTax(NumberUtils.roundOff(getSalesTax(item.getPrice())));
			if (item.isImported())
				item.setImportTax(NumberUtils.roundOff(getImportTax(item.getPrice())));
			total = total + (item.getQuantity()*item.getPrice());
			salesTaxes = NumberUtils.roundOff(salesTaxes + item.getTotalTax());
		}
	}
		
	private double getSalesTax(double price){
		return price * SALES_TAX_RATE;
	}
	
	private double getImportTax(double price){
		return price * IMPORT_TAX_RATE;
	}
}
