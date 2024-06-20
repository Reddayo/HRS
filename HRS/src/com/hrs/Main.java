 package com.hrs;

import java.util.Scanner;
public class Main {
	public static void main (String[] args) {
		Scanner scn = new Scanner(System.in);
		Menu menu = new Menu(scn);
		menu.start();
	}
}
