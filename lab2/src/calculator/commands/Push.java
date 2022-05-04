package calculator.commands;

import calculator.Context;
import calculator.exceptions.UndefinedVariableException;
import calculator.exceptions.WrongArgumentsExpression;

public class Push implements Command {

    @Override
    public void execute(Context data, String parameters) {

        int current_index = 0;

        while ((current_index < parameters.length()) && !Character.isWhitespace(parameters.charAt(current_index)))
            current_index++;

        Double value;
        if (Character.isAlphabetic(parameters.charAt(0))) {
            value = data.get(parameters.substring(0, current_index));
            if (value == null)
                throw new UndefinedVariableException(parameters.substring(0, current_index));
        }
        else {
            try {

                value = Double.parseDouble(parameters.substring(0, current_index).replaceAll(",", ".").replaceAll("[^0-9.-]", ""));
            }
            catch (NumberFormatException e){
                throw new WrongArgumentsExpression(parameters.substring(0, current_index));
            }
        }

        data.push(value);

    }
}