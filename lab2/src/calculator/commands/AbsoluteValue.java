package calculator.commands;


import calculator.Context;
import calculator.exceptions.EmptyStackException;

public class AbsoluteValue implements Command {
    @Override
    public void execute(Context data, String parameters) {

        if (data.empty())
            throw new EmptyStackException("absolute");

        Double value = data.pop();

        Double result = Math.abs(value);

        data.push(result);
    }
}
