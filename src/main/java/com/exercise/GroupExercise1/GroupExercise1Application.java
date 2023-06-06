package com.exercise.GroupExercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class GroupExercise1Application {

	List<Employee> employeeList = new ArrayList<>();
	public static void main(String args[]) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Employee employee1 = new Employee(741258,"Sheldon","Lee","Cooper",dateFormat.parse("20220101"));
		Employee employee2 = new Employee(789456,"Bernadette","Rostenkowski","Wolowitz",dateFormat.parse("20220202"));
		Employee employee3 = new Employee(789456,"Bruno",null,"Mars",dateFormat.parse("20220202"));
		//mainScreen();

		displayRecord(Arrays.asList(new Employee[]{employee1, employee2, employee3}));
	}
	static void mainScreen(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Main Options \n" +
				"[1] List All Employee Records \n" +
				"[2] Add New Employee Record \n" +
				"[3] Delete Employee Record \n" +
				"[4] Search Employee Record \n" +
				"[-1] Exit \n" );

		int action = getInput();
		switch(action){
			case 1: //displayRecord();
					break;
			case 2:
					break;
			case 3:
					break;
			case 4:
					break;
			case -1: System.out.println("\nGoodbye!");
				System.exit(0);
		}
	}

	static int getInput(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean isInputValid = false;
		int action = -1;
		while(!isInputValid){
			try{
				System.out.println("\nSelect action:  ");
				String input = reader.readLine();
				action = Integer.parseInt(input);
				if(action < -1 || action > 4 || action == 0){
					System.out.println("Invalid Entry. Try Again.");
				} else {
					isInputValid = true;
				}
			} catch(NumberFormatException nfe){
				System.out.println("Invalid Entry. Try Again.");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return action;
	}

	static void displayRecord(List<Employee> employeeList){
		printDivider();
		printRecord("Employee Number","Name","Date Hired");
		printDivider();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		employeeList.stream().forEach(e -> printRecord(Integer.toString(e.getEmployeeId()),
														getFullName(e),
														dateFormat.format(e.getDateHired())));
		printDivider();
	}

	static String getFullName(Employee employee){
		return employee.getFirstName() + " " + employee.getMiddleName() +" "+ employee.getLastName();
	}

	static void printRecord(String employeeNumber, String name, String dateHired){
		System.out.printf("%-20s%-50s%-10s%n", employeeNumber, name, dateHired);
	}
	static void printDivider(){
		IntStream.range(0, 80).forEach(i -> System.out.print("="));
		System.out.println();
	}
	static void sortScreen(){

	}
}
