package com.exercise.GroupExercise1;

public class Screens {
    @Option(optionId = 1, label = "List All Employee Records")
    @Option(optionId = 2, label = "Add New Employee Record")
    @Option(optionId = 3, label = "Delete Employee Record")
    @Option(optionId = 4, label = "Search Employee Record")
    @Option(optionId = -1, label = "Exit")
    private String mainScreen;
    @Option(optionId = 1, label = "Sorted By Employee Number")
    @Option(optionId = 2, label = "Sorted By First Name")
    @Option(optionId = 3, label = "Sorted By Last Name")
    @Option(optionId = 4, label = "Sorted By Hiring Date")
    @Option(optionId = -1, label = "Back")
    private String sortScreen;

    @Option(optionId = 1, label = "Search By Employee Number")
    @Option(optionId = 2, label = "Search By First Name")
    @Option(optionId = 3, label = "Search By Middle Name")
    @Option(optionId = 4, label = "Search By Last Name")
    @Option(optionId = 5, label = "Search By Hiring Date")
    @Option(optionId = -1, label = "Back")
    private String searchScreen;
}
