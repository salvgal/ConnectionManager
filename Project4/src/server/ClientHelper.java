package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHelper implements Runnable {

	public Socket socket;
	public Bus bus;
	public Coordinator coordinator;
	public Student student;
	public int numStud = 0;
	public int numBus = 0;
	public int numCoordinator = 0;
	public Commute com;

	public ClientHelper(Socket socket, Commute com) {
		this.socket = socket;
		this.com = com;
	}

	public void run() {
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			String inputLine, outputLine;
			boolean dismiss = false;
			// Initiate conversation with client
			out.println("connectionOK");

			while ((inputLine = in.readLine()) != null) {

				String[] input = inputLine.split(",");

				// Bus Thread helper
				if (input[0].equals("Bus")) {
					switch (input[1]) {
					case "1":
						bus = new Bus(Integer.parseInt(input[2]), 4, com);
						bus.meth1();
						out.println("Bus_meth1_executed");
						break;
					case "2":
						bus.meth2();
						out.println("Bus_meth2_executed");
						dismiss = true;
						break;
					} // Coordinator Thread helper
				} else if (input[0].equals("Coordinator")) {
					switch (input[1]) {
					case "1":
						coordinator = new Coordinator(Integer.parseInt(input[2]), com);
						coordinator.meth1();
						out.println("Coordinator_meth1_executed");
						break;
					case "2":
						coordinator.meth2();
						out.println("Coordinator_meth2_executed");
						break;
					case "3":
						coordinator.meth3();
						out.println("Coordinator_meth3_executed");
						dismiss = true;
						break;
					} // Student travelling by bus helper
				} else if (input[0].equals("StudentBus")) {
					switch (input[1]) {

					case "1":
						student = new Student(Integer.parseInt(input[2]), "byBus", com);
						student.meth1();
						out.println("StudentBus_meth1_executed");
						break;
					case "2":
						student.meth2();
						out.println("StudentBus_meth2_executed");
						break;
					case "3":
						student.meth3();
						out.println("StudentBus_meth3_executed");
						dismiss = true;
						break;
					} // Student travelling by car helper
				} else if (input[0].equals("StudentCar")) {
					switch (input[1]) {
					case "1":
						student = new Student(Integer.parseInt(input[2]), "byCar", com);
						student.meth4();
						out.println("StudentCar_meth4_executed");
						break;
					case "2":
						student.meth5();
						out.println("StudentCar_meth5_executed");
						break;
					case "3":
						student.meth6();
						out.println("StudentCar_meth6_executed");
						dismiss = true;
						break;
					}
					if (dismiss)
						break;
				}
			}
		} catch (IOException e) {

		}
	}
}
