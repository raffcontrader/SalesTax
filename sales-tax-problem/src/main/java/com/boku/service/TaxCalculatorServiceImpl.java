package com.boku.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.boku.model.Item;
import com.boku.model.TaxCalculator;
import com.boku.parser.InputParser;
import com.boku.parser.OutputPrinter;
import com.boku.util.InputReader;;

/**
 * Questa classe SerivceImplement espone il metodo per trovare l'imposta sulle vendite e il costo totale degli articoli acquistati.
 * @author Raffaele Della Porta
 *
 */
public class TaxCalculatorServiceImpl implements TaxCalculatorService{

	private static class TaxCalculatorServiceImplLoader {
        private static final TaxCalculatorServiceImpl INSTANCE = new TaxCalculatorServiceImpl();
    }
	
    private TaxCalculatorServiceImpl() {
        if (TaxCalculatorServiceImplLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }
    
    public static TaxCalculatorServiceImpl getInstance() {
        return TaxCalculatorServiceImplLoader.INSTANCE;
    }
    
	@Override
	public void calculateTax(String filePath) throws FileNotFoundException {
		InputReader reader = read(filePath);
		List<Item> items = extractItems(reader.readInput(), reader.readExclusions()); 
		TaxCalculator taxCalculator = calculate(items); 
		taxCalculator.computeTax();
		print(items, taxCalculator.getSalesTaxes(), taxCalculator.getTotalAll());
	}

	private InputReader read(String filePath){
		InputReader reader = new InputReader(filePath);
		return reader;
	}
	
	private List<Item> extractItems(List<String> inputText, List<String> exclusions){
		return new InputParser(inputText, exclusions).extractDataFromText();
	}
	
	private TaxCalculator calculate(List<Item> items){
		return new TaxCalculator(items);
	}
	
	private void print(List<Item> items, double salesTaxes, double total){
		OutputPrinter consolePrinter = new OutputPrinter(items, salesTaxes, total);
		consolePrinter.print();
	}
}
