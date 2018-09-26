package mypack;

import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TB extends Thread {

	private ObjectOutputStream oos;

	private OutputStream os3;
	private OutputStream os4;

	public TB(OutputStream os3, OutputStream os4, ObjectOutputStream oos) {
		this.os3 = os3;
		this.os4 = os4;
		this.oos = oos;
	}

	public void run() {

		try {
			// TB send object to TA
			Message m = new Message(25, 1);
			System.out.println("Sending object Message from TB to TA " + m);
			oos = new ObjectOutputStream(os3);
			oos.writeObject(m);

			// TB send Primitive to TC
			System.out.println("Sending primitive char B from TB to TC");
			os4.write('B');

		} catch (Exception e) {
			System.out.println("Error TB: " + e);
		}
	}
}
