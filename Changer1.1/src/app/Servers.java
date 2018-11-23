package app;

import java.awt.Checkbox;

import javax.swing.JCheckBox;

public class Servers {
	

	
	
	private String ipAdress;
	private String userName;
	private String password;
	private String serverName;
	
	//
	private String connectionStatus;
	private String promptStatus;
	
	
	private JCheckBox check1;
	
	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public String getPromptStatus() {
		return promptStatus;
	}

	public void setPromptStatus(String promptStatus) {
		this.promptStatus = promptStatus;
	}

	public JCheckBox getCheck1() {
		return check1;
	}

	public void setCheck1(JCheckBox chckbxCheckBox) {
		this.check1 = chckbxCheckBox;
	}

	
	
	public Servers(String serverName, String ipAdress, String userName, String password) {
		this.serverName = serverName;
		this.ipAdress = ipAdress;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return"\n====\n"+"Servers : \n[serverName = " + serverName + "]\n[ipAdress= " + ipAdress + "]\n[userName= " + userName + "]\n[password= " + password + "]\n====\n";
	}
	
	

}