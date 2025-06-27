package pl.coderslab;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        selectOption();
    }

    public static void selectOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleColors.PURPLE_BACKGROUND + ConsoleColors.BLACK_BOLD + "Please select an option");
        System.out.println(ConsoleColors.PURPLE + """
                add
                remove
                list
                exit
                """);

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "add" -> addOption();
            case "remove" -> removeOption();
            case "list" -> listOption();
            case "exit" -> exitOption();
        }
    }


    public static void addOption() {
//      add logika: dodanie taska z tymi parametrami co ktos wpisal do pliku tasks.csv
        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD + "Please add task description");
        String taskDescription = scanner.next();

        System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD + "Please add task due date");
        String taskDueDate = scanner.next();

        System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD + "Is your task important? true/false");
        String taskImportance = scanner.next();

        selectOption();
    }

    public static void removeOption() {
//        add logika: usuwanie taska o numerze X
//            add logika: usuniecie taska z pliku tasks.csv
        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + "Please select number to remove");
        String removeNumber = scanner.next();

        System.out.println(ConsoleColors.RED_UNDERLINED + "Value was successfully deleted");

        selectOption();
    }

    public static void listOption() {
//          add logika: printowanie w konsoli taskow ktore zostaly dodane
//            add logika: zapis do pliku tasks.csv
        selectOption();
    }

    public static void exitOption() {
        Scanner scanner = new Scanner(System.in);
//        String userInput = scanner.nextLine();


            System.out.println(ConsoleColors.YELLOW_UNDERLINED + "Bye, bye.");

    }
}
