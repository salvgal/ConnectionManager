package demo;

public class Student implements Runnable {

	public static long time = System.currentTimeMillis();

	private int id;
	private String commuteType;
	private Commute com;

	public Student(int id, String commuteType, Commute com) {
		this.id = id;
		this.commuteType = commuteType;
		this.com = com;
		new Thread(this).start();
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "] Student " + id + ": " + m);
	}

	public void run() {

		// path for student by bus
		if (this.commuteType.equals("byBus")) {

			meth1();

			meth2();

			meth3();
		}

		// path for student by car
		if (this.commuteType.equals("byCar")) {

			meth4();

			meth5();

			meth6();
		}
	}

	public void meth1() {
		msg("Waiting at the bus stop");
		com.waitingBus();
	}

	public void meth2() {
		msg("Student is in the bus");
		com.travelling();
	}

	public void meth3() {
		msg("Student go at the gym");
		com.studentWalk();
	}

	public void meth4() {
		// student drive (sleep) for a random tyme.
		msg("Student driving car");
		com.studentDriving();
	}

	public void meth5() {
		// students block at the drop off station
		msg("Student with car arrived at the college");
		com.lineUp();
	}

	public void meth6() {
		// coordinators make them drop off
		msg("Student go to the gym");
		com.studentWalk();
	}

}