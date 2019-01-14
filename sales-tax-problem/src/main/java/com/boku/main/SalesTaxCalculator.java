package com.boku.main;

import com.boku.service.TaxCalculatorService;
import com.boku.service.TaxCalculatorServiceImpl;

/**
 * @author Raffaele Della Porta
 *
 */
public class SalesTaxCalculator {
	
	private static TaxCalculatorService taxCalculatorService = TaxCalculatorServiceImpl.getInstance();

	public static void main(String [] args){
		if (args.length > 0){
			try {
				taxCalculatorService.calculateTax(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Specificare il nome del filo dopo il comando: java -jar SalesTax.jar <filePath>");
		}
	}
}
