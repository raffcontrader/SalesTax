package com.boku.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boku.model.Item;
import com.google.common.base.Joiner;

/**
 * @author Raffaele Della Porta
 * Analizza il file di input passato dall'utente e classifica gli elementi in base al fatto:
 * è escluso, importato o entrambi. 
 *
 */
public class InputParser {

	private final List<String> inputText;
	
	private final List<String> exclusions;
	
	private final String REGEX_CLASSIFIER = "(\\d+)([a-zA-Z0-9_\\s]+?)( at )(\\d+.\\d+)";
	
	private final String IMPORTED_CLASSIFIER = "IMPORTED";
	
	public InputParser(List<String> inputText, List<String> exclusions){
		this.inputText = inputText;
		this.exclusions = exclusions;
	}
	
	/*
	 * prende ciascuna descrizione dell'item e la analizza, ed estrae le informazioni relative richieste.
	 */
	public List<Item> extractDataFromText(){
		List<Item> items = new ArrayList<Item>();
		for (String item : inputText) {
			items.add(parser(item)); 
		}
		return items;
	}
	
	/*
	 * analizza l'item e assegna il valore ai suoi rispettivi attributi.
	*/
	private Item parser(String itemText){
		Pattern textPattern = Pattern.compile(REGEX_CLASSIFIER, Pattern.CASE_INSENSITIVE);
		Matcher textMatcher = textPattern.matcher(itemText);
		Integer qty = null;
		Double price = null;
		String desc = null;
		if (textMatcher.find()){
			qty = Integer.parseInt(textMatcher.group(1));
			price = Double.parseDouble(textMatcher.group(4));
			desc = textMatcher.group(2);
		}
		return new Item(qty, desc, price, isExcluded(itemText), isImported(itemText));
	}
	
	/*
	 * @param: stringa contenente la descrizione dell'oggetto
	 * @output: booleano (indipendentemente dall'item escluso o meno).
	*/
	private boolean isExcluded(String itemText){
		Joiner joiner = Joiner.on("|"); //Restituisce un joiner che posiziona automaticamente il separatore tra elementi consecutivi.
		Pattern exclusionPattern = Pattern.compile(joiner.join(exclusions), Pattern.CASE_INSENSITIVE);
		Matcher exclusionMatcher = exclusionPattern.matcher(itemText);
		return exclusionMatcher.find();
	}
	
	/*
	 * @param: stringa contenente la descrizione dell'oggetto
	 * @output: booleano
	 * controlla se una determinata riga contiene item importati in base a un pattern predefinito.
	*/
	private boolean isImported(String itemText){
		Pattern importPattern = Pattern.compile(IMPORTED_CLASSIFIER, Pattern.CASE_INSENSITIVE);
		Matcher importMatcher = importPattern.matcher(itemText);
		return importMatcher.find();
	}
	
}
