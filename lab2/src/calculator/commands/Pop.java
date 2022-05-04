package calculator.commands;

import calculator.Context;

public class Pop implements Command {

    @Override
    public void execute(Context data, String parameters) {

        if (!data.empty())
            data.pop();

    }
}