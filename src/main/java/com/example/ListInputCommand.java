package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class ListInputCommand implements ControlCommand {
    private final String prompt;
    private List<String> inputList;

    public ListInputCommand(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = scanner.nextLine();
        inputList = Arrays.asList(input.split(","));
    }

    public List<String> getListInput() {
        return inputList;
    }
}