package com.example;

import java.util.Scanner;

public class PromptCommand implements ControlCommand{
    private final String prompt;
    private String input;

    public PromptCommand(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        input = scanner.nextLine();
    }

    public String getInput() {
        return input;
    }
}
