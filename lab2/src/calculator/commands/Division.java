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
        Double b = data.pop();
        Double a = data.pop();
        Double c = a / b;
        data.push(c);

    }
}