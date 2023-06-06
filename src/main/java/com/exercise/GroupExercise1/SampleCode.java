package com.exercise.GroupExercise1;

import java.lang.annotation.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class SampleCode {


    @FunctionalInterface
    interface CommandAction {
        void doAction();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Option {
        int value();
        String label();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Repeatable(Options.class)
    public @interface MenuOption {
        int value();
        String label();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Options {
        MenuOption[] value();
    }

    public static class MenuExample {
        @Options({
                @MenuOption(value = 1, label = "New Game"),
                @MenuOption(value = 2, label = "Load Game"),
                @MenuOption(value = 3, label = "Options"),
                @MenuOption(value = 4, label = "Exit")
        })
        private String[] menus;

        private Map<Integer, CommandAction> options = new HashMap<>();

        public MenuExample() throws NoSuchFieldException {
            MenuOption[] menuOptions = this.getClass().getDeclaredField("menus").getAnnotationsByType(MenuOption.class);
            for (MenuOption option : menuOptions) {
                options.put(option.value(), () -> System.out.println(option.label()));
            }
        }

        public void displayMenu() throws NoSuchFieldException {
            System.out.println("Main Menu:");
            for (MenuOption option : this.getClass().getDeclaredField("menus").getAnnotationsByType(MenuOption.class)) {
                System.out.println(option.value() + ". " + option.label());
            }

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            CommandAction action = options.get(choice);
            if (action != null) {
                action.doAction();
            }
        }

        public static void main(String[] args) throws NoSuchFieldException {
            MenuExample menuExample = new MenuExample();
            menuExample.displayMenu();
        }
    }
}
