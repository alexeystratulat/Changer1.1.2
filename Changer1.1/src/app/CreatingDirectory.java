package app;

import java.io.File;
import java.io.IOException;

public class CreatingDirectory {
	public String mainProgramFolder;

	public CreatingDirectory(String mainProgramFolder) {
		this.mainProgramFolder = mainProgramFolder;
	}

	public void createDir() {

		File theDir = new File(mainProgramFolder);

		if (!theDir.exists()) {
			System.out.println("working dir not exist ");

			theDir.mkdir();

			System.out.println("Dir is created");
		}

	}

	public void createDirForConfigOfServer(String name) {

		File file = new File(mainProgramFolder + "\\" + name);
		if (!file.exists()) {
			System.out.println(mainProgramFolder + "\\" + name+ "working dir not exist ");
			file.mkdir();
			System.out.println(mainProgramFolder + "\\" + name+ " created");
		}
		System.out.println(mainProgramFolder + "\\" + name+ " exists");
	}

	
	public void deletingDir() {		
		
		File theDir = new File(mainProgramFolder);
		
		
		for (File file : theDir.listFiles()) {
			if (file.isDirectory()) {

				for (File fileToDeleteTxt : file.listFiles()) {
					fileToDeleteTxt.delete();

				}

				file.delete();
			}

		}

		
		
	}
	
	
}
