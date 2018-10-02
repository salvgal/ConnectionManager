package server;

public class Bus implements Runnable {

	public static long time = System.currentTimeMillis();

	private int id;
	private int bc;
	private int numberStudentsWaiting;
	private int numberStudentsTravelling;
	private Commute com;

	public Bus(int id, int bc, Commute com) {
		this.id = id;
		this.bc = bc;
		this.com = com;
		// new Thread(this).start();
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "] Bus " + id + ": " + m);
	}

	public void run() {

		// meth1();

		// meth2();
	}

	public void meth1() {
		// TODO: TOFIX - Synchronization problem. After one thread leave the
		// loop because no more student to transport,
		// the other thread still do one more round of the loop, hence call
		// pickUp and dropOff one more time even if no students anymore.
		// As result in the console output: one bus does one more round trip
		// with no students to transport.
		do {

			// Bus travelling to Bus Stop
			com.travel();
			msg("Bus arrived at the bus stop");

			// picking up group of 4
			msg("Bus pickup a group");
			synchronized (this) {
				for (int i = 0; i < bc; i++)
					numberStudentsWaiting = com.pickUp();
				msg("Bus full - Transiting");
			}

			// Bus travelling to College
			com.travel();

			// dropping off group
			msg("Dropping off students");
			synchronized (this) {
				for (int i = 0; i < bc; i++)
					numberStudentsTravelling = com.dropOff();
				msg("Returning to the bus stop");
			}

		} while (numberStudentsTravelling > 0);
	}

	public void meth2() {
		// bus go to park
		com.parking();
		msg("Bus Parked");
	}

}