package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Student implements Runnable {

	public static long time = System.currentTimeMillis();

	private int id;
	private String commuteType;
	private String serverName;
	private int port;

	public Student(int id, String commuteType, String serverName, int port) {
		this.id = id;
		this.commuteType = commuteType;
		this.serverName = serverName;
		this.port = port;
		new Thread(this).start();
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "] Student " + id + ": " + m);
	}

	public void run() {

		// path for student by bus
		if (this.commuteType.equals("byBus")) {

			try (Socket socket = new Socket(serverName, port);
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
				String fromServer = "";
				boolean listening = true;

				// Client make request of this type:
				// For the first request: ThreadType, numMethToExecute, id of
				// thread
				// For the next requests: ThreadType, numMethToExecute
				while ((fromServer = in.readLine()) != null) {
					switch (fromServer) {
					case "connectionOK":
						msg("connected to the server, ask to run meth1");
						out.println("StudentBus,1," + id);
						break;
					case "StudentBus_meth1_executed":
						msg("meth1 executed by the server, ask to run meth2");
						out.println("StudentBus,2");
						break;
					case "StudentBus_meth2_executed":
						msg("meth2 executed by the server, ask to run meth3");
						out.println("StudentBus,3");
						break;
					case "StudentBus_meth3_executed":
						msg("meth3 executed by the server, exit");
						listening = false;
						break;
					}
					if (!listening)
						break;
				}
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + serverName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + serverName);
				System.exit(1);
			}

		}

		// path for student by car
		if (this.commuteType.equals("byCar")) {

			try (Socket socket = new Socket(serverName, port);
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
				String fromServer = "";
				boolean listening = true;

				// Client make request of this type:
				// For the first request: ThreadType, numMethToExecute, id of
				// thread
				// For the next requests: ThreadType, numMethToExecute
				while ((fromServer = in.readLine()) != null) {
					switch (fromServer) {
					case "connectionOK":
						msg("connected to the server, ask to run meth4");
						out.println("StudentCar,1," + id);
						break;
					case "StudentCar_meth4_executed":
						msg("meth4 executed by the server, ask to run meth5");
						out.println("StudentCar,2");
						break;
					case "StudentCar_meth5_executed":
						msg("meth5 executed by the server, ask to run meth6");
						out.println("StudentCar,3");
						break;
					case "StudentCar_meth6_executed":
						msg("meth6 exectued by the server, stop");
						listening = false;
						break;
					}
					if (!listening)
						break;
				}
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + serverName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + serverName);
				System.exit(1);
			}
		}
	}

}