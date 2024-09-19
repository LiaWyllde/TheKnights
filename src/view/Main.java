package view;

import java.util.concurrent.Semaphore;
import controller.Hallway;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaph = new Semaphore(1);
		Semaphore semaph2 = new Semaphore(1);
		Semaphore semaph3 = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			Hallway knight = new Hallway(semaph, i, semaph2, semaph3);
			knight.start();
		}
		
		
	}

}
