package client;

import java.net.UnknownHostException;
import java.util.Random;

public class Main {

	private static String serverName;
	public static int port;
	private static int s = 20;
	private static int bc = 4;
	private static int c = 3;

	public static void main(String[] args) throws UnknownHostException {

		serverName = args[0];
		port = Integer.parseInt(args[1]);
		// serverName = InetAddress.getLocalHost().getHostName();

		// Decide randomly if student is going to school byCar or byBus
		// Instance threads of students by commute type
		for (int i = 0; i < s; i++) {
			Random random = new Random();
			if (random.nextFloat() < 0.5)
				new Student(i + 1, "byCar", serverName, port);
			else
				new Student(i + 1, "byBus", serverName, port);
		}

		// Instance two buses
		for (int i = 0; i < 2; i++) {
			new Bus(i + 1, bc, serverName, port);
		}

		// Instance three coordinators
		for (int i = 0; i < c; i++) {
			new Coordinator(i + 1, serverName, port);
		}

	}

}
