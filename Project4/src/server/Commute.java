package server;

import java.util.Random;
import java.util.Vector;

// This class for commuting handling and synchronization
public class Commute extends Thread {

	private Vector lineUpCars = new Vector();
	private int numbStudentWaiting = 0;
	private int numbStudentTravelling;
	private int numbCarInLine = 0;

	Object waiting = new Object();
	Object travelling = new Object();

	public void waitingBus() {
		synchronized (waiting) {
			try {
				++numbStudentWaiting;
				waiting.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized int pickUp() {
		if (numbStudentWaiting > 0) {
			synchronized (waiting) {
				waiting.notify();
				numbStudentWaiting--;
			}
		}
		return numbStudentWaiting;
	}

	public void travelling() {
		synchronized (travelling) {
			try {
				++numbStudentTravelling;
				travelling.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized int dropOff() {
		if (numbStudentTravelling > 0) {
			synchronized (travelling) {
				travelling.notify();
				numbStudentTravelling--;
			}
		}
		return numbStudentTravelling;
	}

	public void lineUp() {
		Object lineUp = new Object();
		synchronized (lineUp) {
			numbCarInLine++;
			lineUpCars.addElement(lineUp);
			try {
				lineUp.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized int dropOffCar() {
		if (lineUpCars.size() > 0 && lineUpCars.elementAt(0) != null) {
			synchronized (lineUpCars.elementAt(0)) {
				lineUpCars.elementAt(0).notify();
				numbCarInLine--;
				lineUpCars.removeElementAt(0);
			}
		}
		return numbCarInLine;
	}

	public int getNumbCarInLine() {
		return numbCarInLine;
	}

	public void studentDriving() {
		try {
			Random random = new Random();
			Thread.sleep(random.nextInt(3000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void studentWalk() {
		// Nothing. Method just to run Project2
	}

	public void travel() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void parking() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}