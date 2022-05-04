package calculator.commands;

import calculator.Context;
import calculator.exceptions.DivisionByZeroException;
import calculator.exceptions.EmptyStackException;


public class Division implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (data.size() < 2)
            throw new EmptyStackException("Divide");

        if (data.peek() == 0.)
            throw new DivisionByZeroException();

        Double value2 = data.pop();

        Double value1 = data.pop();

        Double result = value1 / value2;

        data.push(result);

    }
}