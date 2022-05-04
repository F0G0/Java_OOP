package calculator.commands;

import calculator.Context;
import calculator.exceptions.WrongArgumentsExpression;

public class Define implements Command {

    @Override
    public void execute(Context data, String parameters) {
        int current_index = 0;

        while ((current_index < parameters.length()) && !Character.isWhitespace(parameters.charAt(current_index)))
            current_index++;

        String name = parameters.substring(0,current_index++);

        double value;
        if (current_index < parameters.length()) {
            try{
                value = Double.parseDouble(parameters.substring(current_index).replaceAll(",", ".").replaceAll("[^0-9.-]", ""));
            }
            catch (NumberFormatException e){
                throw new WrongArgumentsExpression("Define", parameters);
            }
        }
        else{
            throw new WrongArgumentsExpression("Define", parameters);
        }
        data.add(name, value);

    }
}