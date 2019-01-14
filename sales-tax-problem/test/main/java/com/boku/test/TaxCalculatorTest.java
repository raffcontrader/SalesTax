package com.boku.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.boku.model.Item;
import com.boku.model.TaxCalculator;

public class TaxCalculatorTest {
	
	private List<Item> items = null;
	
	private TaxCalculator taxCalculator;

	@Before
	public void setUp() throws Exception {
		items = new ArrayList<Item>();
//		items.add(new Item(1, "imported bottle of perfume", 27.99, false, true));
//		items.add(new Item(1, "bottle of perfume", 18.99, false, false));
//		items.add(new Item(1, "packet of headache pills", 9.75, true, false));
//		items.add(new Item(1, "box of imported chocolates", 11.25, true, true));
		
//		items.add(new Item(1, "imported box of chocolates", 10.00, true, true));
//		items.add(new Item(1, "imported bottle of perfume", 47.50, false, true));
		
		items.add(new Item(1, "book", 12.49, true, false));
		items.add(new Item(1, "music CD", 14.99, false, false));
		items.add(new Item(1, "chocolate bar", 0.85, true, false));
	}

	@After
	public void tearDown() throws Exception {
		items = null;
	}

	@Test
	public void testCalculateTax() {
		taxCalculator = new TaxCalculator(items);
		taxCalculator.computeTax();
		System.out.println(taxCalculator.getTotalAll() + " "+ taxCalculator.getSalesTaxes());
		Assert.assertEquals(29.84, taxCalculator.getTotalAll());
		Assert.assertEquals(1.50, taxCalculator.getSalesTaxes());
	}

}
