package calculator.commands;
import calculator.Context;
public interface Command {

    void execute(Context data, String parameters);
}