package mypack;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class TA extends Thread {

	private ObjectInputStream ois;
	private ObjectInputStream ois2;

	private InputStream is2;
	private OutputStream os1;
	private InputStream is3;

	public TA(InputStream is2, OutputStream os1, InputStream is3, ObjectInputStream ois, ObjectInputStream ois2) {
		this.is2 = is2;
		this.os1 = os1;
		this.is3 = is3;
		this.ois = ois;
		this.ois2 = ois2;
	}

	public void run() {

		try {

			// TA receive object from TC
			ois = new ObjectInputStream(is2);
			Message m = (Message) ois.readObject();
			System.out.println("TA receives object Message from TC: " + m);

			// TA receive object from TB
			ois2 = new ObjectInputStream(is3);
			Message m2 = (Message) ois2.readObject();
			System.out.println("TA receives object Message from TB: " + m2);

			// TA send a primitive to TC
			System.out.println("Sending primitive char A from TA to TC");
			os1.write('A');

		} catch (Exception e) {
			System.out.println("Error TA: " + e);
		}
	}
}
