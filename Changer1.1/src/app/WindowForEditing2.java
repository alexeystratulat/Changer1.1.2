package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.ini4j.Ini;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;



public class WindowForEditing2 {

	private static JFrame frame;
	private String pathToFile;
	private Ini resources;
	private static JTextArea txtrText;
	private static JButton button;
	private static JButton btnApply;
	private String serverName;
	private String selectedItem;
	private ArrayList<Servers> listOfServers;
	private String mainProgramFolder;
	private String resFileName;
	private String listName;
	private JTextField search_textField;
	private JLabel res_label;
	private int CurrentRes = 0;
	private int TotalRes = 0;
	private int[] Rows = new int[1000];
	private JLabel lblResult;
	

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
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {	 
            @Override
            public void windowClosing(WindowEvent we) {
            	if (btnApply.isEnabled() ) {
	            	String ObjButtons[] = {"Save and exit","Exit without saving", "No"};         
	                int PromptResult = JOptionPane.showOptionDialog(null,
	                        "All unsaved changes will be lost.\n"
	                        + "Are you want to exit?", 
	                        "Information",
	                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
	                        ObjButtons, ObjButtons[0]);
	                if (PromptResult == 0) {
	                	saveToFile();
	    				System.exit(0);
	                }
	                if (PromptResult == 1) {
	    				System.exit(0);
	                }
            	} else {
            		System.exit(0);
            	}
            }
        });	
		frame.setTitle(selectedItem + "  "+ serverName);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 629);
		frame.getContentPane().add(scrollPane);

		txtrText = new JTextArea();
		scrollPane.setViewportView(txtrText);
		txtrText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				btnApply.setEnabled(true);
			}
		});
		button = new JButton("<< Back");
		button.setBounds(563, 655, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				TheMostWindow most = new TheMostWindow(resources,  listName,  resFileName,selectedItem, listOfServers, mainProgramFolder  );
				most.initialize();
			}
		});
		
		frame.getContentPane().add(button);
		btnApply = new JButton("apply");
		btnApply.setEnabled(false);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFile();
			}
		});
		
		btnApply.setBounds(662, 655, 112, 23);
		frame.getContentPane().add(btnApply);
		JButton btnNext = new JButton(">");
		btnNext.setEnabled(false);
		JButton btnPrev = new JButton("<");
		btnPrev.setEnabled(false);
	
		search_textField = new JTextField();
		search_textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (search_textField.getText().equals("Search") ) {
					search_textField.setText("");	
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (search_textField.getText().equals("") ) {
					search_textField.setText("Search");	
				}				
			}
		});
		
		search_textField.setText("Search");
		search_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search_text = search_textField.getText().toLowerCase();		
				if (search_text.length() > 0) {
					TotalRes = 0;
					CurrentRes = 0;
					Rows = new int[1000];
					int counter = 0;
					// Start searching in text area
					StringReader sr = new StringReader(txtrText.getText()); 
					BufferedReader br = new BufferedReader(sr); 
					String nextLine = ""; 		 			
					try {
						while ((nextLine = br.readLine()) != null){ 									
							if (nextLine.toLowerCase().contains(search_text) ) {			
								Rows[TotalRes] = counter;
								TotalRes ++;
							}
							counter++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}				
					// Check result
					if (TotalRes > 0) {
						 btnNext.setEnabled(true);
						 btnPrev.setEnabled(true);
						 res_label.setText("0 / "+Integer.toString(TotalRes)); 		
					} else {
						 btnNext.setEnabled(false);
						 btnPrev.setEnabled(false);
						 res_label.setText("0 / 0"); 
					}	
				} else {
					 btnNext.setEnabled(false);
					 btnPrev.setEnabled(false);
					 res_label.setText("0 / 0"); 				  
				}
			}
		});

		search_textField.setBounds(10, 656, 247, 20);
		frame.getContentPane().add(search_textField);
		search_textField.setColumns(10);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				CurrentRes ++;	
				if (CurrentRes > TotalRes){
					CurrentRes = 1;	
				}
				changeSelectedText();
			 }
		});
		
		btnNext.setBounds(309, 657, 42, 17);
		frame.getContentPane().add(btnNext);
		res_label = new JLabel("0 / 0");
		res_label.setBounds(66, 687, 55, 14);
		frame.getContentPane().add(res_label);
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentRes --;	
				if (CurrentRes < 1){
					CurrentRes = TotalRes;	
				}
				changeSelectedText();
			}
		});
		btnPrev.setBounds(268, 657, 42, 17);
		frame.getContentPane().add(btnPrev);
		lblResult = new JLabel("Result:");
		lblResult.setBounds(10, 687, 46, 14);
		frame.getContentPane().add(lblResult);
		frame.setVisible(true);
		show();
	}

	protected void changeSelectedText() {
		res_label.setText(Integer.toString(CurrentRes)+" / "+Integer.toString(TotalRes)); 		
		int current_row = Rows[CurrentRes-1];	
		try {
			int start_point = txtrText.getLineStartOffset(current_row);
			int end_point = txtrText. getLineEndOffset(current_row);
			// Start and End for selecting
			txtrText.setSelectionStart(start_point);
			txtrText.setSelectionEnd(end_point-1);
			txtrText.requestFocus();
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}		
	}

	protected void saveToFile() {
		String content = txtrText.getText();	
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			// PATH TO FILE
			String path = pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString();		
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(content);
		}  catch(Exception e1){
			System.out.println(e1);
		} finally {
			try {
				bw.close();
				fw.close();
				btnApply.setEnabled(false);
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void show() {
		FileReader reader = null;
		try {
			reader = new FileReader(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString());	
			System.out.println(pathToFile + "\\" + resources.get("path", "nameOfConfigFile").toString());
		} catch (FileNotFoundException e) {
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
		txtrText.setCaretPosition(0);
		txtrText.requestFocus();
	}
}
