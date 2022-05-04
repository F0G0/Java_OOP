package calculator.exceptions;

public class CommandNotFoundException extends CommandException {

    public CommandNotFoundException(String command) {
        super("Command \"" + command + "\" not found");
    }
}