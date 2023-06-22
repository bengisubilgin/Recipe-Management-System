package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerInputCommand implements ControlCommand {
    private final String prompt;
    private int input;

    public IntegerInputCommand(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    public int getIntegerInput() {
        return input;
    }
}