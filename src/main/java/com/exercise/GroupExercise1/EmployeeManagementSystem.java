package com.exercise.GroupExercise1;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;


public class EmployeeManagementSystem {


    private static List<Employee> employeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static Screens screens = new Screens();
    static final String mainScreen = "mainScreen";
    static final String sortScreen = "sortScreen";
    static final String searchScreen = "searchScreen";

    private static void initializeValues() throws ParseException {
        //Initial values
        Employee employee1 = new Employee(741258,"Sheldon","Lee","Cooper",dateFormat.parse("20220101"));
        Employee employee2 = new Employee(789456,"Bernadette","Rostenkowski","Wolowitz",dateFormat.parse("20220202"));
        Employee employee3 = new Employee(789456,"Bruno",null,"Mars",dateFormat.parse("20220202"));
        employeeList = Arrays.asList(employee1,employee2,employee3);
    }

    public static void main(String[] args) throws ParseException, NoSuchFieldException {
        initializeValues();

        displayScreen(mainScreen);
        /*boolean exit = false;
        while (!exit) {
            System.out.println("\nEmployee Management System");
            System.out.println("--------------------------");
            System.out.println("1. List all employees");
            System.out.println("2. Add a new employee");
            System.out.println("3. Delete an employee");
            System.out.println("4. Search employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    listAllEmployees();
                    break;
                case 2:
                    addNewEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    searchEmployee();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the program. Goodbye!");*/
    }

    static void displayScreen(String screenName)  {
        Field fd = null;
        try {
            fd = screens.getClass().getDeclaredField(screenName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        Option[] optionList = fd.getAnnotationsByType(Option.class);
        System.out.println(screenName.equals(mainScreen)? "Main Options" : "Choose an action");
        Arrays.stream(optionList).forEach(option ->
                System.out.println("["+option.optionId()+"] "+ option.label()));
        CommandAction exit = () -> {
            System.out.println("Goodbye!");
            System.exit(0);
        };

        int selected = getInput();

        if(screenName.equals(mainScreen)){
            switch(selected){
                case 1: displayScreen(sortScreen); break;
                case 4: displayScreen(searchScreen); break;
                case -1: exit.doAction();
            }
        } else if(screenName.equals(sortScreen)){
            switch(selected){
                case 1: displayRecord(employeeList); break; //go back to Main Screen?
                case 4:
                case -1: displayScreen(mainScreen);
            }
        }

    }

    static int getInput(){
        boolean isInputValid = false;
        int action = -1;
        while(!isInputValid){
            try{
                System.out.println("\nSelect action:  ");
                String input = scanner.next();
                action = Integer.parseInt(input);
                if(action < -1 || action > 4 || action == 0){
                    System.out.println("Invalid Entry. Try Again.");
                } else {
                    isInputValid = true;
                }
            } catch(NumberFormatException nfe){
                System.out.println("Invalid Entry. Try Again.");
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
    private static void listAllEmployees() {
        System.out.println("\nList of all employees:");
        System.out.println("----------------------");

        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("Sort order:");
        System.out.println("1. Employee Number (Ascending)");
        System.out.println("2. Employee Number (Descending)");
        System.out.println("3. First Name (Ascending)");
        System.out.println("4. First Name (Descending)");
        System.out.println("5. Last Name (Ascending)");
        System.out.println("6. Last Name (Descending)");
        System.out.print("Enter your choice: ");

        int sortChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Comparator<Employee> comparator = null;

        switch (sortChoice) {
            case 1:
                comparator = Comparator.comparing(Employee::getEmployeeId);
                break;
            case 2:
                comparator = Comparator.comparing(Employee::getEmployeeId).reversed();
                break;
            case 3:
                comparator = Comparator.comparing(Employee::getFirstName);
                break;
            case 4:
                comparator = Comparator.comparing(Employee::getFirstName).reversed();
                break;
            case 5:
                comparator = Comparator.comparing(Employee::getLastName);
                break;
            case 6:
                comparator = Comparator.comparing(Employee::getLastName).reversed();
                break;
            default:
                System.out.println("Invalid choice. Displaying employees without sorting.");
        }

        if (comparator != null) {
            employeeList.stream()
                    .sorted(comparator)
                    .forEach(System.out::println);
        } else {
            employeeList.forEach(System.out::println);
        }
    }

    private static void addNewEmployee() {
        System.out.println("\nEnter employee information:");

        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Middle Name: ");
        String middleName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Hiring Date (yyyy-MM-dd): ");
        String hiringDateString = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiringDate;
        try {
            hiringDate = sdf.parse(hiringDateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Employee not added.");
            return;
        }

        Employee employee = new Employee(employeeId, firstName, middleName, lastName, hiringDate);
        employeeList.add(employee);

        System.out.println("Employee added successfully.");
    }

    private static void deleteEmployee() {
        System.out.print("Enter the Employee Number to delete: ");
        int employeeId = scanner.nextInt();

        boolean removed = employeeList.removeIf(e -> e.getEmployeeId() == employeeId);

        if (removed) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void searchEmployee() {
        System.out.println("\nSearch employee by:");
        System.out.println("1. Employee Number");
        System.out.println("2. First Name");
        System.out.println("3. Middle Name");
        System.out.println("4. Last Name");
        System.out.println("5. Hiring Date");
        System.out.print("Enter your choice: ");

        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (searchChoice) {
            case 1:
                searchByEmployeeNumber();
                break;
            case 2:
                searchByFirstName();
                break;
            case 3:
                searchByMiddleName();
                break;
            case 4:
                searchByLastName();
                break;
            case 5:
                searchByHiringDate();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void searchByEmployeeNumber() {
        System.out.print("Enter the Employee Number to search: ");
        int employeeId = scanner.nextInt();

        Optional<Employee> employee = employeeList.stream()
                .filter(e -> e.getEmployeeId() == employeeId)
                .findFirst();

        /*employee.ifPresentOrElse(
                e -> System.out.println("Employee found:\n" + e),
                () -> System.out.println("Employee not found.")
        );*/
    }

    private static void searchByFirstName() {
        System.out.print("Enter the First Name to search: ");
        String firstName = scanner.nextLine();

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getFirstName().equalsIgnoreCase(firstName)) {
                System.out.println("Employee found:\n" + employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    private static void searchByMiddleName() {
        System.out.print("Enter the Middle Name to search: ");
        String middleName = scanner.nextLine();

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getMiddleName().equalsIgnoreCase(middleName)) {
                System.out.println("Employee found:\n" + employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    private static void searchByLastName() {
        System.out.print("Enter the Last Name to search: ");
        String lastName = scanner.nextLine();

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println("Employee found:\n" + employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    private static void searchByHiringDate() {
        System.out.print("Enter the Hiring Date to search (yyyy-MM-dd): ");
        String hiringDateString = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiringDate;
        try {
            hiringDate = sdf.parse(hiringDateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Employee not found.");
            return;
        }

        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getDateHired().equals(hiringDate)) {
                System.out.println("Employee found:\n" + employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }
}