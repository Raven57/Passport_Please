package utils;

import java.util.Scanner;

public class Util {
	public static Scanner sc = new Scanner(System.in);
	public static void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
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
