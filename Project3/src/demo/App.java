package demo;

import java.util.Random;

public class App {

	private static int s = 20;
	private static int bc = 4;
	private static int c = 3;

	public static void main(String[] args) {

		final Commute com = new Commute();

		// Decide randomly if student is going to school byCar or byBus
		// Instance threads of students by commute type
		for (int i = 0; i < s; i++) {
			Random random = new Random();
			if (random.nextFloat() < 0.5)
				new Student(i + 1, "byCar", com);
			else
				new Student(i + 1, "byBus", com);
		}

		// Instance two buses
		new Bus(1, bc, com);
		new Bus(2, bc, com);

		// Instance three coordinators
		new Coordinator(1, com);
		new Coordinator(2, com);
		new Coordinator(3, com);
	}

}
