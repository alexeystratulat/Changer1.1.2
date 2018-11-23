package app;

import java.awt.Color;
import java.awt.Font;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class WindowOne {

	private JFrame frmChangePrompts;
	private JComboBox comboBox;
	private String mainProgramFolder;
	private String listName;
	private Ini resources;
	private String resFileName;
	private String selectedItem;
	private String versionOfApp = "1.1.2";
	ArrayList<Servers> listOfServers;
	Parser pars;

	
	public WindowOne(Ini resources, String listName, String resFileName, String mainProgramFolder) {
		this.resources = resources;
		this.listName = listName;
		this.resFileName = resFileName;
		this.mainProgramFolder = mainProgramFolder;
		//System.out.println(resources.get("auth", "Username").toString());
		
		
		System.out.println(resources + " " + listName + " "+ resFileName+ " "+ mainProgramFolder );
		
		pars = new Parser(mainProgramFolder, listName);

		CreatingDirectory dirToDelete = new CreatingDirectory(mainProgramFolder);
		dirToDelete.deletingDir();
		
		
	}

	public void initialize() {

		frmChangePrompts = new JFrame();
		frmChangePrompts.setTitle("version " +versionOfApp);
		frmChangePrompts.setBounds(100, 100, 392, 550);
		frmChangePrompts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChangePrompts.getContentPane().setLayout(null);
		frmChangePrompts.setLocationRelativeTo(null);
		frmChangePrompts.setResizable(false);
//
		comboBox = new JComboBox(pars.parserForEnvIni());
		comboBox.setBackground(SystemColor.menu);
		comboBox.setFont(new Font("Linux Libertine Display G", Font.BOLD, 15));
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(55, 62, 278, 32);
		frmChangePrompts.getContentPane().add(comboBox);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(55, 260, 278, 2);
		frmChangePrompts.getContentPane().add(separator);

		JLabel lblChooseYourEnvironment = new JLabel("Choose your Environment");
		lblChooseYourEnvironment.setFont(new Font("Linux Biolinum G", Font.BOLD, 14));
		lblChooseYourEnvironment.setBounds(100, 19, 196, 32);
		frmChangePrompts.getContentPane().add(lblChooseYourEnvironment);

		JLabel picture = new JLabel("");

		frmChangePrompts.getContentPane().add(picture);

		frmChangePrompts.setVisible(true);
//buttons 
		JButton btnNext = new JButton("NEXT>");
		btnNext.setFont(new Font("Linux Biolinum G", Font.BOLD, 14));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Parser p1 =  new Parser(mainProgramFolder,  listName);
				
				System.out.println(resources.get("version","version"));
				
				if(resources.get("version","version").equals(versionOfApp)){
					
					
				
				
				
				try {
				//	System.out.println(p1.parserIniForIP(comboBox.getSelectedItem().toString()));
					listOfServers  = p1.parserIniForIP(comboBox.getSelectedItem());
					selectedItem = comboBox.getSelectedItem().toString();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmChangePrompts.setVisible(false);
				
				TheMostWindow most = new TheMostWindow(resources,  listName,  resFileName,selectedItem, listOfServers, mainProgramFolder  );
				most.initialize();
			}
			}
		});
		btnNext.setBounds(285, 478, 91, 32);
		frmChangePrompts.getContentPane().add(btnNext);

		JButton btnSettings = new JButton("settings");
		btnSettings.setFont(new Font("Linux Biolinum G", Font.BOLD, 14));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					ResourceServerWindow RW = new ResourceServerWindow(resources, listName, resFileName,
							mainProgramFolder);
					RW.initialize();
				} catch (InvalidFileFormatException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				frmChangePrompts.setVisible(false);
			}
		});
		btnSettings.setBounds(10, 478, 91, 32);
		frmChangePrompts.getContentPane().add(btnSettings);

	}

}
