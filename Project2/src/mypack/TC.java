package mypack;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TC extends Thread {

	private ObjectOutputStream oos2;

	private InputStream is1;
	private OutputStream os2;
	private InputStream is4;

	public TC(InputStream is1, OutputStream os2, InputStream is4, ObjectOutputStream oos2) {
		this.is1 = is1;
		this.os2 = os2;
		this.is4 = is4;
		this.oos2 = oos2;
	}

	public void run() {

		try {

			// TC send object to TA
			Message m = new Message(50, 2);
			System.out.println("Sending object Message from TC to TA " + m);
			oos2 = new ObjectOutputStream(os2);
			oos2.writeObject(m);

			// TC receive primitive from TA
			System.out.println("TC receive a primitive from TA: " + (char) is1.read());

			// TC receive primitive from TB
			System.out.println("TC receive a primitive from TB: " + (char) is4.read());

		} catch (Exception e) {
			System.out.println("Error TC: " + e);
		}
	}
}
