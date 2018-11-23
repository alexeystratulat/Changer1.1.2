package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.ini4j.Ini;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class WindowForEditing2 {

	private JFrame frame;
	private String pathToFile;
	private Ini resources;
	private PrintWriter writer = null;
	private FileReader reader = null;
	private Scanner read;
	private JTextArea txtrText;
	private JButton button;
	private JButton btnApply;
	private String serverName;
	private String selectedItem;
	private ArrayList<Servers> listOfServers;
	private String mainProgramFolder;
	private String resFileName;
	private String listName;

	public WindowForEditing2(String pathToFile, String serverName,Ini resources, String listName, String resFileName, String selectedItem,
			ArrayList<Servers> listOfServers, String mainProgramFolder) {

		this.pathToFile = pathToFile;		
		this.serverName = serverName;
		this.resources = resources;
		this.listName = listName;
		this.resFileName = resFileName;
		this.selectedItem = selectedItem;
		this.mainProgramFolder = mainProgramFolder;
		this.listOfServers = listOfServers;

		System.out.println(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString());

	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(selectedItem + "  "+ serverName);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 629);
		frame.getContentPane().add(scrollPane);

		txtrText = new JTextArea();
		scrollPane.setViewportView(txtrText);
		
		button = new JButton("<< Back");
		button.setBounds(685, 655, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				
				
				frame.setVisible(false);
				
				TheMostWindow most = new TheMostWindow(resources,  listName,  resFileName,selectedItem, listOfServers, mainProgramFolder  );
				most.initialize();
			}
		});
		frame.getContentPane().add(button);
		
		
		btnApply = new JButton("apply");
		btnApply.setBounds(385, 655, 170, 23);
		btnApply.setEnabled(false);
		frame.getContentPane().add(btnApply);
		frame.setVisible(true);
		//
		show();

	}

	private void show() {

		FileReader reader = null;

		try {
			reader = new FileReader(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scanner s = new Scanner(reader);

		while (s.hasNext()) {
			if (s.hasNextInt()) {
				txtrText.append(s.nextLine() + "\n");
			} else {
				txtrText.append(s.nextLine() + "\n");
			}
		}

	}
}
