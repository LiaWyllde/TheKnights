package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Hallway extends Thread {
	
	private int[] knight  = new int[4];
	//movimentacao //item // bonus //name
	private static int torch = 1;
	private static int rock = 1;
	private Semaphore semaph;
	private final int hallwaySize = 2000;
	private Semaphore cat;
	private Semaphore meow;
	private Random rand = new Random();
	private static ArrayList <String> doors = new ArrayList<String>();
	private static boolean od = true;
	private static boolean damon = true;
	private static boolean demon = true;
	String skull = """	
\nHighway to Hell
			
 ⢀⣠⣤⣤⣄⡀⠀
⣴⣿⣿⣿⣿⣿⣿⣦
⣿⣿⣿⣿⣿⣿⣿⣿
⣇⠈⠉⡿⢿⠉⠁⢸
⠙⠛⢻⣷⣾⡟⠛⠋
			""";
	String angel = """
\nHeavens doors
			
 ⣠⡤⠴⠶⠶⠶⠶⢤⣄⠀⠀⠀⠀⠀⠀⠀⠀
⠈⠳⣤⣤⣤⣤⣤⣤⠴⠟⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
			""";
	//Lista encadeada simples
	
	//static String[] doors = new String[4];
	//		this.doors[0] = "Hallway to Hell";
	//		this.doors[1] = "Hallway to Hell";
	//		this.doors[2] = "Hallway to Hell";
	//		this.doors[3] = "Heaven's Door";
	
	public Hallway(Semaphore s, int i, Semaphore s2, Semaphore s3) {
		this.knight[3] = (i+1);
		this.semaph = s;
		this.cat = s2;
		this.meow = s3;
	}
	
	private void openTheDoors() {
		doors.add(0, skull);
		doors.add(1, skull);
		doors.add(2, skull);
		doors.add(3, angel);
	}
	
	private int[] bonus(int[] sl) {
		int [] theKnight = sl;
		
		if (torch == 1 && theKnight[2] != 2) {
			theKnight[2] = 2;
		}
		
		if (rock == 1 && theKnight[2] != 2) {
			theKnight[2] = 2;
		}
 
		return theKnight;
	}
	
	private void move() {
		int step;
		
		while (knight[0] < hallwaySize) {
			
			if (knight[0] >= 1996) {
				int complement = knight[0] - hallwaySize;
				knight[0] += complement;
				break;
			}
			
			step = (rand.nextInt(2,5));
			
			knight[0] += (step = (rand.nextInt(2,5) + knight[2]));
			
			try {
				sleep(5);
				//System.out.println("The knight " +  knight[3] + " ran " + step);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (knight[0] >= 500 && torch == 1 && knight[2] != 2) {
				if(damon == true) {
					try {
						cat.acquire();
						knight = bonus(knight);
						knight[1] = torch;
						--torch;
						System.out.println(knight[3]+ " Got a torch!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally { 
						damon = false;
						cat.release(); 
					}
				}
			}
			
			if (knight[0] >= 1500 && rock == 1 && knight[2] != 2) {
				if(demon == true) {
					try {
						meow.acquire();
						knight = bonus(knight);
						knight[1] = rock;
						--rock;
						//System.out.println(knight[3]+ " pegou, arroxa!");
						System.out.println(knight[3]+ " Got a Magic Rock!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						demon = false;
						meow.release(); 
					}
				}
			}
		}
	}
	
	private void theDoors() {
		if(od == true) {
			openTheDoors();
			od = false;
		}
		int algum = rand.nextInt(0,doors.size());

		System.out.println("The knight " + knight[3] + " is on the " + doors.get(algum));
		doors.remove(algum);
	}
	
	@Override
	public void run() {
		move();	
		try {
			semaph.acquire();
			theDoors();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {semaph.release();}
	}
}



