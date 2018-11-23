package app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import org.ini4j.Ini;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TheMostWindow {

	private String mainProgramFolder;
	private String listName;
	private Ini resources;
	private String resFileName;
	private String selectedItem; // name of choisen combobox
	private ArrayList<Servers> listOfServers;
	private Connect ckeckConnection;
	private Connect toChangePrompts;
	private RestartingServ restartingSer;
	private RestartingServ servStatus,servStatus2;
	private Editor edit;
	private WindowForEditing2 wind;
	private int ForCheckboxDistance2, forLableIPDistance2, forConnectionDistance2, forTypeOfPromptsDistance2,
			forEditButtonDistance2, forVariable1Distance2, forVariable2Distance2, forVariable3Distance2,
			forVariable4Distance2, forVariable5Distance2;
	private JButton switchToManualButton;
	private JButton switchToAutomationButton;
	private JButton restartButton;
	private JFrame frame;

	private JButton[] editButton = new JButton[10];
	private JCheckBox[] CheckBoxes = new JCheckBox[10];
	private JLabel[] labels = new JLabel[10];
	private JLabel[] lblconnecton = new JLabel[10];
	private JLabel[] typeOfprompts = new JLabel[10];
	private JLabel[] nameForStatusServer1 = new JLabel[10];
	private JLabel[] nameForStatusServer2 = new JLabel[10];
	private JLabel[] statusServer1 = new JLabel[10];
	private JLabel[] statusServer2 = new JLabel[10];
	private JCheckBox chckbxNewCheckBox;
	private JLabel[] variables1 = new JLabel[10];
	private JLabel[] variables2 = new JLabel[10];
	private JLabel[] variables3 = new JLabel[10];
	private JLabel[] variables4 = new JLabel[10];
	private JLabel[] variables5 = new JLabel[10];

	public TheMostWindow(Ini resources, String listName, String resFileName, String selectedItem,
			ArrayList<Servers> listOfServers, String mainProgramFolder) {
		this.resources = resources;
		this.listName = listName;
		this.resFileName = resFileName;
		this.mainProgramFolder = mainProgramFolder;
		this.selectedItem = selectedItem;
		this.listOfServers = listOfServers;
		System.out.println(resources.get("auth", "Username").toString());
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JLabel lblNewLabel = new JLabel(selectedItem);
		lblNewLabel.setBounds(338, 11, 177, 14);
		frame.getContentPane().add(lblNewLabel);
		//
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 764, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 140, 764, 2);
		frame.getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 240, 764, 2);
		frame.getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 340, 764, 2);
		frame.getContentPane().add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 440, 764, 2);
		frame.getContentPane().add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(10, 540, 764, 2);
		frame.getContentPane().add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(10, 640, 764, 2);
		frame.getContentPane().add(separator_7);
		//
		chckbxNewCheckBox = new JCheckBox("check all");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("Check all");
				if (chckbxNewCheckBox.isSelected()) {
					for (int counter = 0; counter < listOfServers.size(); counter++) {
						CheckBoxes[counter].setSelected(true);

					}
				} else
					for (int counter = 0; counter < listOfServers.size(); counter++) {
						CheckBoxes[counter].setSelected(false);
					}

			}
		});
		chckbxNewCheckBox.setBounds(10, 7, 111, 23);

		frame.getContentPane().add(chckbxNewCheckBox);

		System.out.println(listOfServers.size());

		forConnectionDistance2 = 115;
		ForCheckboxDistance2 = 77;
		forLableIPDistance2 = 53;
		forTypeOfPromptsDistance2 = 115;
		forEditButtonDistance2 = 77;
		forVariable1Distance2 = 45;
		forVariable2Distance2 = 64;
		forVariable3Distance2 = 83;
		forVariable4Distance2 = 102;
		forVariable5Distance2 = 121;

		for (int counter = 0; counter < listOfServers.size(); counter++) {

			showServers(listOfServers.get(counter), counter, ForCheckboxDistance2, forLableIPDistance2,
					forConnectionDistance2, forTypeOfPromptsDistance2, forEditButtonDistance2, forVariable1Distance2,
					forVariable2Distance2, forVariable3Distance2, forVariable4Distance2, forVariable5Distance2);
			ForCheckboxDistance2 += 100;
			forLableIPDistance2 += 100;
			forConnectionDistance2 += 100;
			forTypeOfPromptsDistance2 += 100;
			forEditButtonDistance2 += 100;
			forVariable1Distance2 += 100;
			forVariable2Distance2 += 100;
			forVariable3Distance2 += 100;
			forVariable4Distance2 += 100;
			forVariable5Distance2 += 100;

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		JButton button = new JButton("<< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				WindowOne w1 = new WindowOne(resources, listName, resFileName, mainProgramFolder);

				w1.initialize();

				frame.setVisible(false);

			}
		});
		button.setBounds(685, 655, 89, 23);
		frame.getContentPane().add(button);
		
	
		
		

		frame.setVisible(true);

		switchToManualButton = new JButton("Switch to manual");
		switchToManualButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int counter = 0; counter < listOfServers.size(); counter++) {

					if (CheckBoxes[counter].isSelected() == true) {

						toChangePrompts = new Connect(listOfServers.get(counter), resources.get("auth", "Username"),
								resources.get("auth", "Password"), mainProgramFolder, resources);

						toChangePrompts.setManual();

					}

				}

				frame.setVisible(false);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TheMostWindow most = new TheMostWindow(resources, listName, resFileName, selectedItem, listOfServers,
						mainProgramFolder);
				most.initialize();

				System.out.println("Bytton is typed");

			}
		});
		switchToManualButton.setBounds(185, 655, 170, 23);
		switchToManualButton.setEnabled(false);
		frame.getContentPane().add(switchToManualButton);

		switchToAutomationButton = new JButton("Switch to automation");
		switchToAutomationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int counter = 0; counter < listOfServers.size(); counter++) {

					if (CheckBoxes[counter].isSelected() == true) {

						toChangePrompts = new Connect(listOfServers.get(counter), resources.get("auth", "Username"),
								resources.get("auth", "Password"), mainProgramFolder, resources);

						toChangePrompts.setAutomation();

					}

				}

				frame.setVisible(false);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TheMostWindow most = new TheMostWindow(resources, listName, resFileName, selectedItem, listOfServers,
						mainProgramFolder);
				most.initialize();

				System.out.println("Bytton is typed");

			}
		});
		switchToAutomationButton.setBounds(385, 655, 170, 23);
		switchToAutomationButton.setEnabled(false);
		frame.getContentPane().add(switchToAutomationButton);
		
		
		restartButton = new JButton("restart");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int counter = 0; counter < listOfServers.size(); counter++) {

					if (CheckBoxes[counter].isSelected() == true) {

						restartingSer = new RestartingServ(listOfServers.get(counter), resources.get("auth", "Username"),resources.get("auth", "Password"), mainProgramFolder, resources);

						restartingSer.restarting();

					}

				}

				frame.setVisible(false);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TheMostWindow most = new TheMostWindow(resources, listName, resFileName, selectedItem, listOfServers,
						mainProgramFolder);
				most.initialize();

				System.out.println("Bytton is typed");

			}
		});
		restartButton.setBounds(585, 655, 80, 23);
		restartButton.setEnabled(false);
		frame.getContentPane().add(restartButton);
		
		

	}

	//
	private void showServers(final Servers server, final int counter, int ForCheckboxDistance2, int forLableIPDistance2,
			int forConnectionDistance2, int forTypeOfPromptsDistance2, int forEditButtonDistance2,
			int forVariable1Distance2, int forVariable2Distance2, int forVariable3Distance2, int forVariable4Distance2,
			int forVariable5Distance2) {
		CheckBoxes[counter] = new JCheckBox(server.getServerName().toString());
		CheckBoxes[counter].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(counter);
				boolean temp = true;
				for (int counter = 0; counter < listOfServers.size(); counter++) {

					if (CheckBoxes[counter].isSelected() == false) {
						temp = false;
					}

				}
				chckbxNewCheckBox.setSelected(temp);
			}

		});

		CheckBoxes[counter].setBounds(10, ForCheckboxDistance2, 97, 23);
		//
		//
		labels[counter] = new JLabel(server.getIpAdress().toString());
		labels[counter].setBounds(30, forLableIPDistance2, 101, 14);
		//
		nameForStatusServer1[counter] = new JLabel(resources.get("nameOfServ", "name1"));
		nameForStatusServer1[counter].setBounds(140, forLableIPDistance2, 50, 14);
		nameForStatusServer2[counter] = new JLabel(resources.get("nameOfServ", "name2"));
		nameForStatusServer2[counter].setBounds(140, ForCheckboxDistance2, 50, 23);
		statusServer1[counter] = new JLabel("");
		statusServer1[counter].setBounds(190,  forLableIPDistance2, 97, 14);
		statusServer2[counter] = new JLabel("");
		statusServer2[counter].setBounds(190,  ForCheckboxDistance2, 97, 23);

		variables1[counter] = new JLabel("");
		variables1[counter].setBounds(290, forVariable1Distance2, 450, 12);
		frame.getContentPane().add(variables1[counter]);
		//
		variables2[counter] = new JLabel("");
		variables2[counter].setBounds(290, forVariable2Distance2, 450, 12);
		frame.getContentPane().add(variables2[counter]);
		//
		variables3[counter] = new JLabel("");
		variables3[counter].setBounds(290, forVariable3Distance2, 450, 12);
		frame.getContentPane().add(variables3[counter]);
		//
		variables4[counter] = new JLabel("");
		variables4[counter].setBounds(290, forVariable4Distance2, 450, 12);
		frame.getContentPane().add(variables4[counter]);
		//
		variables5[counter] = new JLabel("");
		variables5[counter].setBounds(290, forVariable5Distance2, 450, 12);
		frame.getContentPane().add(variables5[counter]);
		//

		frame.getContentPane().add(CheckBoxes[counter]);
		//
		frame.getContentPane().add(labels[counter]);
		frame.getContentPane().add(statusServer1[counter]);
		frame.getContentPane().add(statusServer2[counter]);
		frame.getContentPane().add(nameForStatusServer1[counter]);
		frame.getContentPane().add(nameForStatusServer2[counter]);

		//
		lblconnecton[counter] = new JLabel("connecton...");
		lblconnecton[counter].setBounds(30, forConnectionDistance2, 131, 14);
		lblconnecton[counter].setForeground(Color.BLUE);
		//
		typeOfprompts[counter] = new JLabel("loading...");
		typeOfprompts[counter].setBounds(140, forTypeOfPromptsDistance2, 131, 14);
		//
		frame.getContentPane().add(lblconnecton[counter]);
		frame.getContentPane().add(typeOfprompts[counter]);
		System.out.println("======" + server.getIpAdress().toString());

		Thread thread1 = new Thread() {
			public void run() {

				System.out.println("CheckConnection = " + server.getIpAdress().toString());
				ckeckConnection = new Connect(server, resources.get("auth", "Username"),
						resources.get("auth", "Password"), mainProgramFolder, resources);
				lblconnecton[counter].setText(ckeckConnection.checkConnection());
				if (lblconnecton[counter].getText().toString().equals("connected")) {
					editButton[counter].setEnabled(true);

					switchToAutomationButton.setEnabled(true);
					switchToManualButton.setEnabled(true);
					restartButton.setEnabled(true);

					edit = new Editor(mainProgramFolder + "\\" + server.getServerName().toString(), resources,
							server.getIpAdress().toString());
					typeOfprompts[counter].setText(edit.compareFiles().toString());
					
					
//
					servStatus = new RestartingServ(listOfServers.get(counter), resources.get("auth", "Username"),resources.get("auth", "Password"), mainProgramFolder, resources);

					statusServer1[counter].setText(servStatus.status().toString());
					if (statusServer1[counter].getText().toString().equals("STOPPED")) {
						statusServer1[counter].setForeground(new Color(255, 84, 84));	
						
						}else statusServer1[counter].setForeground(new Color(26, 142, 11));
					
					
					
					servStatus2 = new RestartingServ(listOfServers.get(counter), resources.get("auth", "Username"),resources.get("auth", "Password"), mainProgramFolder, resources);
					statusServer2[counter].setText(servStatus2.status2().toString());
					if (statusServer2[counter].getText().toString().equals("STOPPED")) {
						statusServer2[counter].setForeground(new Color(255, 84, 84));	
						
						}else statusServer2[counter].setForeground(new Color(26, 142, 11));
					
					//

					variables1[counter]
							.setText(edit.toShowVariables(counter, resources.get("variables", "variable1").toString()));
					variables2[counter]
							.setText(edit.toShowVariables(counter, resources.get("variables", "variable2").toString()));
					variables3[counter]
							.setText(edit.toShowVariables(counter, resources.get("variables", "variable3").toString()));
					variables4[counter]
							.setText(edit.toShowVariables(counter, resources.get("variables", "variable4").toString()));
					variables5[counter]
							.setText(edit.toShowVariables(counter, resources.get("variables", "variable5").toString()));

				}

				if (typeOfprompts[counter].getText().toString().equals("manual prompts")) {
					typeOfprompts[counter].setForeground(Color.RED);

				}
				if (typeOfprompts[counter].getText().toString().equals("automated prompts")) {
					typeOfprompts[counter].setForeground(Color.BLUE);

				}

			}
		};
		thread1.start();

		editButton[counter] = new JButton("edit");
		editButton[counter].setEnabled(false);
		editButton[counter].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				wind = new WindowForEditing2(mainProgramFolder + "\\" + server.getServerName().toString(),
						server.getServerName().toString(), resources, listName, resFileName, selectedItem,
						listOfServers, mainProgramFolder);
				wind.initialize();
				frame.setVisible(false);

				System.out.println("BUTTON TYPED");
				System.out.println(CheckBoxes[counter].isSelected());
			}
		});
		editButton[counter].setBounds(677, forEditButtonDistance2, 97, 23);
		frame.getContentPane().add(editButton[counter]);

		//

	}

}
