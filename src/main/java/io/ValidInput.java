package io;

public class ValidInput {
    String command;
    String description;
    public Enum value;

    public ValidInput(String command, String description) {
        this.command = command;
        this.description = description;
        this.value = null;
    }

    public ValidInput(String command, String description, Enum value) {
        this.command = command;
        this.description = description;
        this.value = value;
    }
}
