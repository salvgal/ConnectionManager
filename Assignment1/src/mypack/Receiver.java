package mypack;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Receiver extends Thread {

	// to write Objects
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	// to write raw bytes
	private InputStream is;
	private OutputStream os;

	public Receiver(InputStream is, OutputStream os, ObjectInputStream ois, ObjectOutputStream oos) {
		this.is = is;
		this.os = os;
		this.ois = ois;
		this.oos = oos;
	} // end CONSTRUCTOR

	public void run() {
		System.out.println("Receiver starting execution.");
		try {

			// create the message
			Message m = new Message();
			m.theMessage = "Hey there!";
			String[] s = { "uno", "dos", "tres" };
			m.someLines = s;
			m.someNumber = 64;

			System.out.println("Receiver sends: ");
			System.out.println(m);

			// let objects be transmitted across the pipe, but before connect
			// them
			oos = new ObjectOutputStream(os);
			oos.writeObject(m);

			// Sender and receiver go back and forth. Start receiver send 10,
			// and sender read and send a character
			// we have numbers from the receiver to the sender, and characters
			// from the sender to the receiver
			int curr = 0;
			// 255 is the end of file
			while (curr != 255) {
				// receiver receive message by sender a group of 10 characters
				os.write(10);

				String msg = "";
				do {
					// read is a blocking operation.
					// If you wait for the other to read, and the other wait to
					// read, it is a deadlock.
					curr = is.read();
					if (curr != '\r' && curr != '\n')
						msg += (char) curr;
				} while (is.available() != 0);
				System.out.println("Receiver reads: " + msg);

			} // end WHILE

		} // end TRY
		catch (Exception exc) {
			System.out.println("Error Receiver: " + exc);
		} // end CATCH

	} // end METHOD run

} // end CLASS Receiver