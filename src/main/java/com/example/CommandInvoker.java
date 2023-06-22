package com.example;

import java.util.List;

public class CommandInvoker {
    private ControlCommand command;

    public void execute(ControlCommand command) {
        this.command = command;
        this.command.execute();
    }

    public String getInput() {
        if (command instanceof PromptCommand) {
            return ((PromptCommand) command).getInput();
        }
        return null;
    }

    public List<String> getListInput() {
        if (command instanceof ListInputCommand) {
            return ((ListInputCommand) command).getListInput();
        } else if (command instanceof MultipleChoiceCommand) {
            return ((MultipleChoiceCommand) command).getListInput();
        }
        return null;
    }

    public int getIntegerInput() {
        if (command instanceof IntegerInputCommand) {
            return ((IntegerInputCommand) command).getIntegerInput();
        }
        return 0;
    }



}
