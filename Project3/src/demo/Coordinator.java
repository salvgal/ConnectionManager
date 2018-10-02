package demo;

public class Coordinator implements Runnable {

	public static long time = System.currentTimeMillis();

	private int id;
	private Commute com;
	private int numbCarsInLine;

	public Coordinator(int id, Commute com) {
		this.id = id;
		this.com = com;
		new Thread(this).start();
	}

	public void msg(String m) {
		System.out.println("[" + (System.currentTimeMillis() - time) + "] Coordinator -" + id + ": " + m);
	}

	public void run() {

		meth1();

		meth2();

		meth3();

	}

	public void meth1() {
		// Coordinators arrives to help
		coordinatorsWaiting();
	}

	public void meth2() {
		// Coordinators take people from car and help them drop off
		// TODO: TOFIX - Synchronization problem. After one thread leave the
		// loop because no more care in line
		// the other two thread still call dropOffCar even if no cars anymore.
		// As result in the console output: coordinator available for drop off
		// appears two times more than the students that are in line.
		numbCarsInLine = com.getNumbCarInLine();
		while (numbCarsInLine != 0)
			doDropOff();
	}

	public void meth3() {
		// at the end coordinators let the Summer Camp start
		coordinatorsLeft();
		msg("All students are at the gym! Our job is completed. Let's the Summer Camp start!");
	}

	public synchronized void doDropOff() {
		if (numbCarsInLine != 0) {
			msg("Coordinator available for drop off work serve one car");
			numbCarsInLine = com.dropOffCar();
		}
	}

	public void coordinatorsWaiting() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void coordinatorsLeft() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
