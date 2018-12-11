package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

public class Editor {
	private String pathToFile;
	private String ServerIP;
	private String variableForManual = null;
	private Ini resources;
	private PrintWriter writer = null;
	private FileReader reader = null;
	private Scanner read;
	private Ini showingVariables;

	////////////
	public Editor(String pathToFile, Ini resources, String ServerIP) {
		this.pathToFile = pathToFile;
		this.resources = resources;
		this.ServerIP = ServerIP;
		variableForManual = resources.get("manual", "variable4").toString()+"=http://" + ServerIP + resources.get("manual", "variable3").toString();
System.out.println(resources.get("manual", "variable4").toString()+"=http://" + ServerIP + resources.get("manual", "variable3").toString());
System.out.println(resources.get("automated", "variable3"));



	}

	public void makingVportal_au() {

		try {

			reader = new FileReader(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString());

			read = new Scanner(reader);
			String line;

			writer = new PrintWriter(pathToFile + "\\" + resources.get("path", "nameOfAlternativeConfigFile"));

			while ((line = read.nextLine()) != null) {

				line = line.replaceAll(resources.get("manual", "variable1"), resources.get("automated", "variable1"));
				line = line.replaceAll(resources.get("manual", "variable2"), resources.get("automated", "variable2"));
				line = line.replaceAll(variableForManual, resources.get("automated", "variable3"));
				
			//	line = line.replaceAll(variableForManual, resources.get("automated", "variable3"));

				// checking for mistake
				line = line.replaceAll(resources.get("check", "mistake1"), resources.get("automated", "variable1"));
				line = line.replaceAll(resources.get("check", "mistake2"), resources.get("automated", "variable2"));
				line = line.replaceAll(resources.get("check", "mistake3"), resources.get("automated", "variable3"));
				line = line.replaceAll(resources.get("check", "mistake4"), resources.get("automated", "variable2"));
				
				
				//System.out.println(resources.get("check", "mistake4") );
				

				writer.println(line);

			}
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Alternative Vportal is created by Editor " + pathToFile + "\\"
					+ resources.get("path", "nameOfStandartConfigFile"));
			writer.close();
			read.close();

			try {
				reader.close();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}

	}

	public void makingVportal_st() {

		try {

			reader = new FileReader(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString());

			read = new Scanner(reader);
			String line;

			writer = new PrintWriter(pathToFile + "\\" + resources.get("path", "nameOfStandartConfigFile"));

			while ((line = read.nextLine()) != null) {

				line = line.replaceAll(resources.get("automated", "variable1"), resources.get("manual", "variable1"));
				line = line.replaceAll(resources.get("automated", "variable2"), resources.get("manual", "variable2"));
				line = line.replaceAll(resources.get("automated", "variable3"), variableForManual);
				
				
				// checking for mistake
				line = line.replaceAll(resources.get("check", "mistake3"), variableForManual);
				
				
				
				

				writer.println(line);

			}
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Alternative Vportal is created by Editor " + pathToFile + "\\"
					+ resources.get("path", "nameOfStandartConfigFile"));
			writer.close();
			read.close();

			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public String compareFiles() {

		File vportalFile = new File(pathToFile + "\\" + resources.get("path", "nameOfConfigFile"));
		File vportal_stFile = new File(pathToFile + "\\" + resources.get("path", "nameOfStandartConfigFile"));
		File vportal_auFile = new File(pathToFile + "\\" + resources.get("path", "nameOfAlternativeConfigFile"));

		System.out.println("Comparing: " + pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString()
				+ ": " + vportalFile.length());
		System.out.println("Comparing: " + pathToFile + "\\"
				+ resources.get("path", "nameOfStandartConfigFile").toString() + ": " + vportal_stFile.length());
		System.out.println("Comparing: " + pathToFile + "\\"
				+ resources.get("path", "nameOfAlternativeConfigFile").toString() + ": " + vportal_auFile.length());

		if (vportalFile.length() == vportal_stFile.length()) {

			return "manual prompts";

		}

		if (vportalFile.length() == vportal_auFile.length()) {

			return "automated prompts";

		}

		return "unknown";

	}

	public String toShowVariables(int counter, String key) {
		
		
		
		try {
			showingVariables = new Ini(
					new File(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString()));
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String sectionName : showingVariables.keySet()) {

			Section section = showingVariables.get(sectionName.toString());

			for (String optionKey : section.keySet()) {
				if (optionKey.toString().equals(key)) {
					
					System.out.println(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString()+"                    "+ key + section.get(optionKey) );
					return optionKey.toString() + " = " + section.get(optionKey);
					
					
					
					
				}

			}

		}

		return null;

	}

}
