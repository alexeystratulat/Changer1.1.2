package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ini4j.Ini;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class RestartingServ {

	private String ipAddress;
	private String username;
	private String password;
	private String path;
	private String pathForRes;
	private String resFileName;
	private String pathForList;
	private Servers server;
	private String mainProgramFolder;
	private Ini resources;

	public RestartingServ(Servers server, String username, String password, String mainProgramFolder, Ini resources) {
		this.server = server;
		this.username = username;
		this.password = password;
		this.mainProgramFolder = mainProgramFolder;
		this.resources = resources;
		// TODO Auto-generated constructor stub
	}

	public void restarting() {

		{

			try {

				Connection conn = new Connection(server.getIpAdress().toString());

				conn.connect();

				boolean isAuthenticated = conn.authenticateWithPassword(username, password);

				if (isAuthenticated == false)
					throw new IOException("Authentication failed.");

				Session sess = conn.openSession();

				sess.execCommand(resources.get("command", "command1").toString());

				InputStream stdout = new StreamGobbler(sess.getStdout());

				BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

				while (true) {
					String line = br.readLine();
					if (line == null)
						break;

					System.out.println(line);
				}

				System.out.println("ExitCode: " + sess.getExitStatus());

				br.close();

				sess.close();

				conn.close();

			} catch (IOException e) {
				e.printStackTrace(System.err);
				System.exit(2);
			}
		}

	}

	public String status() {
		String statusOfServ = "STOPPED";

		try {

			Connection conn = new Connection(server.getIpAdress().toString());

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(username, password);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			Session sess = conn.openSession();

			sess.execCommand(resources.get("command", "command2").toString());

			InputStream stdout = new StreamGobbler(sess.getStdout());

			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				if (line.contains("RUNNING")) {

					statusOfServ = "RUNNING";

				}
				System.out.println(server.getIpAdress().toString()+resources.get("command", "command2").toString()+line);
			}

			// System.out.println(sess.getExitStatus());

			br.close();

			sess.close();

			conn.close();

			return statusOfServ;

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
		}

		return statusOfServ;

	}
	
	public String status2() {
		String statusOfServ = "STOPPED";

		try {

			Connection conn = new Connection(server.getIpAdress().toString());

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(username, password);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			Session sess = conn.openSession();

			sess.execCommand(resources.get("command", "command3").toString());

			InputStream stdout = new StreamGobbler(sess.getStdout());

			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				if (line.contains("RUNNING")) {

					statusOfServ = "RUNNING";

				}
				System.out.println(server.getIpAdress().toString()+resources.get("command", "command3").toString()+line);
			}

			// System.out.println(sess.getExitStatus());

			br.close();

			sess.close();

			conn.close();

			return statusOfServ;

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
		}

		return statusOfServ;

	}
	
	

}
