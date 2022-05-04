package calculator.commands;

import calculator.Context;
import calculator.exceptions.EmptyStackException;
import calculator.exceptions.SqrtOfNegativeValueException;

public class Sqrt implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (data.empty())
            throw new EmptyStackException("Sqrt");
        if (data.peek() < 0)
            throw new SqrtOfNegativeValueException();
        Double a = data.pop();
        Double c = Math.sqrt(a);
        data.push(c);

    }
}