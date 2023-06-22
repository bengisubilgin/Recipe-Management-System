package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MultipleChoiceCommand implements ControlCommand {
    private final String prompt;
    private final List<String> options;
    private final int maxChoices;
    private List<String> selectedOptions;

    public MultipleChoiceCommand(String prompt, List<String> options, int maxChoices) {
        this.prompt = prompt;
        this.options = options;
        this.maxChoices = maxChoices;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = scanner.nextLine();
        selectedOptions = Arrays.asList(input.split(","));

        selectedOptions = selectedOptions.stream()
                .map(String::trim)
                .limit(maxChoices)
                .collect(Collectors.toList());
    }

    public List<String> getListInput() {
        return selectedOptions;
    }
}
