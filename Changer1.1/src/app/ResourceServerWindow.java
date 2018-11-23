package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.FlowLayout;

public class ResourceServerWindow {

	private JFrame frmFillRequiredValues;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String listName;
	private String resFileName;
	private String mainProgramFolder;
	Ini resources;

	public ResourceServerWindow(Ini resources, String listName, String resFileName, String mainProgramFolder)
			throws InvalidFileFormatException, IOException {
		this.resources = resources;
		this.listName = listName;
		this.resFileName = resFileName;
		this.mainProgramFolder = mainProgramFolder;

	}

	public void initialize() throws InvalidFileFormatException, IOException {
		//
		CreatingDirectory dir = new CreatingDirectory(mainProgramFolder);
		dir.createDir();
		//
		CreatingResourcesFiles resFile = new CreatingResourcesFiles(mainProgramFolder, resFileName);
		try {
			resFile.creatingFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		resources = new Ini(new File(mainProgramFolder + "\\" + resFileName));         //-------------------------?????

		// WINDOW

		frmFillRequiredValues = new JFrame();

		frmFillRequiredValues.setBounds(100, 100, 209, 273);
		frmFillRequiredValues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFillRequiredValues.getContentPane().setLayout(null);

		JLabel lblIpAddress = new JLabel("IP address");
		lblIpAddress.setBounds(10, 30, 181, 14);
		frmFillRequiredValues.getContentPane().add(lblIpAddress);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 80, 181, 14);
		frmFillRequiredValues.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 130, 181, 14);
		frmFillRequiredValues.getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setBounds(10, 50, 181, 20);
		frmFillRequiredValues.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(resources.get("auth", "IPaddres"));

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 100, 181, 20);
		frmFillRequiredValues.getContentPane().add(textField_1);
		textField_1.setText(resources.get("auth", "Username"));

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 150, 181, 20);
		frmFillRequiredValues.getContentPane().add(textField_2);
		textField_2.setText(resources.get("auth", "Password"));

		JButton btnNewButton = new JButton("next >");
		btnNewButton.setBounds(121, 210, 72, 23);
		frmFillRequiredValues.getContentPane().add(btnNewButton);
		frmFillRequiredValues.setLocationRelativeTo(null);
		frmFillRequiredValues.setResizable(false);

		frmFillRequiredValues.setVisible(true);
		frmFillRequiredValues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resources.put("auth", "IPaddres", textField.getText());
				resources.put("auth", "Username", textField_1.getText());
				resources.put("auth", "Password", textField_2.getText());

				try {
					resources.store();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Connect servToGetResourses = new Connect(resources.get("auth", "IPaddres"),
						resources.get("auth", "Username"), resources.get("auth", "Password"),
						resources.get("auth", "Path"),resources.get("auth", "PathResources"), mainProgramFolder + "\\" + listName,mainProgramFolder + "\\" + resFileName,resources);
				servToGetResourses.downloadingSourseFile();

				WindowOne w1 = new WindowOne(resources, listName, resFileName, mainProgramFolder);

				//w1.initialize();
				
				Worker restartapp = new Worker();
				try {
					restartapp.main(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				frmFillRequiredValues.setVisible(false);
			}
		});

	}
}
