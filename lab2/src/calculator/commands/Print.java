package calculator.commands;

import calculator.Context;
import calculator.exceptions.EmptyStackException;

public class Print implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (data.empty())
            throw new EmptyStackException("Print");
        System.out.println(data.peek());

    }
}