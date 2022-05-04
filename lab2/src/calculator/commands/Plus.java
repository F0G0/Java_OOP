package calculator.commands;

import calculator.Context;
import calculator.exceptions.EmptyStackException;

public class Plus implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (data.size() < 2)
            throw new EmptyStackException("Plus");

        Double b = data.pop();
        Double a = data.pop();
        Double c = a + b;
        data.push(c);

    }
}