package com.boku.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Questa classe legge il file di input dal percorso del file specificato e
 * esclude gli articoli citati dal percorso del file specificato.
 * @author Raffaele Della Porta
 * 
 */
public class InputReader {

	private File inputfile;
	
	private File exclusionFile;
	
	private final String EXCLUSION_FILE_PATH = "input/exclusions.txt";
	
	private Scanner s;
	
	public InputReader(String pathName) {
		inputfile = new File(pathName);
		exclusionFile = new File(EXCLUSION_FILE_PATH);
	}
	
	public List<String> readExclusions() throws FileNotFoundException{
		return readExclusionsFile();
	}
	
	public List<String> readInput() throws FileNotFoundException{
		return readInputFile();
	}
	
	private List<String> readInputFile() throws FileNotFoundException{
		s = new Scanner(inputfile);
		List<String> inputs = new ArrayList<String>();
		while(s.hasNext()){
			inputs.add(s.nextLine());
		}
		s.close();
		return inputs;
	}
	
	private List<String> readExclusionsFile() throws FileNotFoundException{
		s = new Scanner(exclusionFile);
		List<String> exclusions = new ArrayList<String>();
		while(s.hasNext()){
			exclusions.add(s.nextLine());
		}
		s.close();
		return exclusions;
	}
}
