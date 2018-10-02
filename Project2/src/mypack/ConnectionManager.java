package mypack;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ConnectionManager {

	// Following schema is used to create 4 pipes
	// TA sends primitive to TC
	// TB sends object to TA
	// TB sends primitive to TC
	// TC sends object to TA

	static private PipedInputStream pis1;
	static private PipedOutputStream pos1;

	static private PipedInputStream pis2;
	static private PipedOutputStream pos2;

	static private PipedInputStream pis3;
	static private PipedOutputStream pos3;

	static private PipedInputStream pis4;
	static private PipedOutputStream pos4;

	static private ObjectOutputStream oos;
	static private ObjectInputStream ois;

	static private ObjectOutputStream oos2;
	static private ObjectInputStream ois2;

	public static void main(String argv[]) {
		try {

			System.out.println("Pipe setup");
			// Pipe from TA to TC
			pos1 = new PipedOutputStream();
			pis1 = new PipedInputStream(pos1);

			// Pipe from TC to TA
			pos2 = new PipedOutputStream();
			pis2 = new PipedInputStream(pos2);

			// Pipe from TB to TA
			pos3 = new PipedOutputStream();
			pis3 = new PipedInputStream(pos3);

			// Pipe from TB to TC
			pos4 = new PipedOutputStream();
			pis4 = new PipedInputStream(pos4);

			TA ta = new TA(pis2, pos1, pis3, ois, ois2);
			TC tc = new TC(pis1, pos2, pis4, oos2);
			TB tb = new TB(pos3, pos4, oos);

			System.out.println("Thread execution");
			ta.start();
			tb.start();
			tc.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
