package utils;

import java.util.Scanner;

public class Scan {
	public static Scanner sc = new Scanner(System.in);
	public int scanInt(int min, int max) {
		int i = 0;
		do {
			try {
				i = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Input!");
			} finally {				
				sc.nextLine();
			}
		} while (i<min||i>max);
		return i;
	}
	public int scanInt() {
		int i = 0;
			try {
				i = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Input!");
			} finally {				
				sc.nextLine();
			}
		return i;
	}
}
