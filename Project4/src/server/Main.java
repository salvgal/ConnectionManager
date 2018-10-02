package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {

	public static void main(String[] args) {
		// ServerSocket creation and threads helpers server side for incoming
		// threads client connection
		boolean listening = true;
		int port = Integer.parseInt(args[0]);
		Commute com = new Commute();
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println(
					"Main Server Running on " + InetAddress.getLocalHost().getHostName() + " on port #" + port);
			while (listening) {
				new Thread(new ClientHelper(serverSocket.accept(), com)).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + port);
			System.exit(-1);
		}

	}

}
