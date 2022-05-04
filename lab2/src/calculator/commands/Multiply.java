package calculator.commands;

import calculator.Context;
import calculator.exceptions.EmptyStackException;

public class Multiply implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (data.size() < 2)
            throw new EmptyStackException("Multiply");
        Double b = data.pop();
        Double a = data.pop();
        Double c = a * b;

        data.push(c);

    }
}