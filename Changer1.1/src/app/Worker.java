package app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class Worker {
	static Ini resources;
	static String listName = "list.ini";
	static String resFileName = "resources.ini";
	static String mainProgramFolder = "C:\\Changer1.1";

	public static void main(String[] args) throws IOException {
		
		//
		try {
			resources = new Ini(new File(mainProgramFolder + "\\" + resFileName));
		} catch (IOException e) {
			System.out.println("ERROR");
		}
		
		//listFile - env ip file 
		File listFile = new File(mainProgramFolder + "\\" + listName);

		if (!listFile.exists()) {

			ResourceServerWindow RseWindow = new ResourceServerWindow(resources, listName, resFileName,
					mainProgramFolder);
			RseWindow.initialize();
		}
		else {
								
			WindowOne w1 = new WindowOne(resources, listName, resFileName,
					mainProgramFolder);
			
			w1.initialize();
			
			
		}

	}

}
