package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        selectOption();
    }

    public static void selectOption() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filename = "tasks.csv";

        System.out.println(ConsoleColors.PURPLE_BACKGROUND + ConsoleColors.BLACK_BOLD + "Please select an option");
        System.out.println(ConsoleColors.PURPLE + """
                add
                remove
                list
                exit
                """);

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "add" -> addOption(filename);
            case "remove" -> removeOption(filename);
            case "list" -> listOption(filename);
            case "exit" -> exitOption();
            default -> selectOption();
        }
    }


    public static void addOption(String filename) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path pathToFileName = Paths.get(filename);

        System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD + "Please add task description");
        String taskDescription = scanner.nextLine();

        System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD + "Please add task due date");
        String taskDueDate = scanner.nextLine();

        System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD + "Is your task important? true/false");
        String taskImportance = scanner.next();

        String newTask = taskDescription + ", " + taskDueDate + ", " + taskImportance;

        boolean fileExistsAndNotEmpty = Files.exists(pathToFileName) && Files.size(pathToFileName) > 0;
        String toWrite = (fileExistsAndNotEmpty ? System.lineSeparator() : "") + newTask;
        Files.writeString(pathToFileName, toWrite, StandardOpenOption.APPEND, StandardOpenOption.CREATE);

        selectOption();
    }


    public static void removeOption(String filename) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path pathToFile = Paths.get(filename);

        String[] tasks = Files.exists(pathToFile)
                ? Files.readAllLines(pathToFile).toArray(new String[0])
                : new String[0];

        if (tasks.length == 0) {
            System.out.println(ConsoleColors.RED_UNDERLINED + "No tasks to remove.");
            selectOption();
            return;
        }

        System.out.println(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + "Please select number to remove");
        String removeNumberUserInput = scanner.nextLine();

        int removeNumber;

        try {
            removeNumber = Integer.parseInt(String.valueOf(removeNumberUserInput));
        } catch (NumberFormatException e) {
            System.out.println("Invalid value! Please enter a valid integer number.");
            selectOption();
            return;
        }

        if (removeNumber < 0 || removeNumber >= tasks.length) {
            System.out.println("Invalid number!");
            selectOption();
            return;
        }

        tasks = ArrayUtils.remove(tasks, removeNumber);

        Files.write(pathToFile, Arrays.stream(tasks)
                .filter(s -> !s.trim().isEmpty())
                .toList());

        Files.write(pathToFile, Arrays.asList(tasks));
        System.out.println(ConsoleColors.RESET + ConsoleColors.RED_UNDERLINED + "Value was successfully deleted");

        selectOption();
    }

    public static void listOption(String filename) throws IOException {
        int count = 0;

        try (FileReader fileReader = new FileReader(filename);
             Scanner fileScanner = new Scanner(fileReader)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + count + " " + line);
                count++;
            }
        }
        System.out.println();
        selectOption();
    }

    public static void exitOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_UNDERLINED + "Bye, bye.");
    }
}
