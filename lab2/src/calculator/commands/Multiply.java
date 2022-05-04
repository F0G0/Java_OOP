package calculator.commands;

import calculator.Context;
import calculator.exceptions.EmptyStackException;

public class Multiply implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (data.size() < 2)
            throw new EmptyStackException("Multiply");

        Double value2 = data.pop();

        Double value1 = data.pop();

        Double result = value1 * value2;

        data.push(result);

    }
}