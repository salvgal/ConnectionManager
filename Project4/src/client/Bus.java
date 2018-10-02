package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Bus implements Runnable {

	public static long time = System.currentTimeMillis();

	private int id;
	private int bc;
	private int numberStudentsWaiting;
	private int numberStudentsTravelling;
	private String serverName;
	private int port;

	public Bus(int id, int bc, String serverName, int port) {
		this.id = id;
		this.bc = bc;
		this.serverName = serverName;
		this.port = port;
		new Thread(this).start();
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "] Bus " + id + ": " + m);
	}

	public void run() {

		try (Socket socket = new Socket(serverName, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			String fromServer = "";
			boolean listening = true;

			// Client make request of this type:
			// For the first request: ThreadType, numMethToExecute, id of thread
			// For the next requests: ThreadType, numMethToExecute
			while ((fromServer = in.readLine()) != null) {
				switch (fromServer) {
				case "connectionOK":
					msg("connected to the server, ask server to run meth1");
					out.println("Bus,1," + id);
					break;
				case "Bus_meth1_executed":
					msg("meth1executed, ask server to run meth2");
					out.println("Bus,2");
					break;
				case "Bus_meth2_executed":
					msg("meth2executed, exit");
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