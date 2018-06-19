package com.oopexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
	
	public static void main( String[] args) {
		
		
		String first = "r";
		Scanner sc;
		
		do {
			DressWizard dressWizard = new DressWizard();
			dressWizard.initData();
			System.out.println("Enter input, seperate by 'space', ex. HOT 8 6 4 2 1 7");
			System.out.println("Enter 'Q' to quit");
			sc  = new Scanner(System.in);
			
			Temperature temperature ;
			
			if ( sc.hasNext()) {
				/*
				 * read inputs
				 */
				String input = sc.nextLine();
				String[] inputArray = input.trim().split("\\s+");
				first = inputArray[0];
				if ( first.toUpperCase().equals("Q")) {
					break;
				}
				temperature = Temperature.valueOf(inputArray[0].toUpperCase());
				List<Integer> commands = new ArrayList<>();
				for ( int i = 1; i < inputArray.length; i ++) {
					commands.add( Integer.valueOf(inputArray[i]));
				}
				
				/*
				 * run program
				 */
				dressWizard.runWizard(temperature, commands);
				
				System.out.println("Output: " + dressWizard.output());
				System.out.println("");
			}
			
		} while ( !first.toUpperCase().equals("Q") );
		
		sc.close();
		
		
	}

}
