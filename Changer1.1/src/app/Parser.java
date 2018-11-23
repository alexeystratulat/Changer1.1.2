package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;





public class Parser {
	static String mainProgramFolder;
	static String listName;
	static Ini list;
	static Ini listOfIP ;


	public Parser(String mainProgramFolder, String listName) {
		this.mainProgramFolder = mainProgramFolder;
		this.listName = listName;

	}

	public static String[] parserForEnvIni() {
		try {
			 list = new Ini(new File(mainProgramFolder + "\\" + listName));

			String[] massToBeSent = new String[list.keySet().size()];

			int counter = 0;
			for (String sectionName : list.keySet()) {

				massToBeSent[counter] = sectionName.toString();
				counter++;

			}

			return massToBeSent;

		} catch (InvalidFileFormatException e) {
			System.err.println("\n" + mainProgramFolder + "\\" + listName + "  - WRONG FORMAT FILE ! \n");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	public static ArrayList<Servers> parserIniForIP( Object object) throws InvalidFileFormatException, IOException {
		ArrayList<Servers> list = new ArrayList<Servers>();
		

		
		listOfIP = new Ini(new File(mainProgramFolder+ "\\"+listName ));

		Section section = listOfIP.get(object);

			for (String optionKey : section.keySet()) {

				// System.out.println(optionKey.toString());
				// System.out.println(section.toString());

			//	list.add(new Servers(optionKey.toString(), section.get(optionKey), Main.settings.get("server", "user"),
				//		Main.settings.get("server", "password")));
				
				
				list.add(new Servers(optionKey.toString(), section.get(optionKey), "user",
						"password"));

			}

			
			return list;

		

		

	
	
	
	}
}