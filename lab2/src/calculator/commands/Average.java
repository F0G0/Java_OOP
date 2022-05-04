package calculator.commands;

import calculator.Context;
import calculator.exceptions.EmptyStackException;
import calculator.exceptions.WrongArgumentsExpression;

public class Average implements Command {

    @Override
    public void execute(Context data, String parameters) {

        int n = 0;

        if (0 < parameters.length()) {
            try {
                n = Integer.parseInt(parameters.replaceAll("[^0-9]", ""));
            }
            catch (NumberFormatException e){
                throw new WrongArgumentsExpression("Average", parameters);
            }
        }
        if (n <= 0)
            throw new WrongArgumentsExpression("Average", parameters);

        if (data.size() < n)
            throw new EmptyStackException("Average");

        double sum = 0;

        for (int i = 0; i < n; ++i){
            sum += data.pop();
        }

        data.push(sum / n);

    }
}