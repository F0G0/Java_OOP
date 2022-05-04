package calculator.commands;

import calculator.Context;
import calculator.exceptions.WrongArgumentsExpression;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Save implements Command {

    @Override
    public void execute(Context data, String parameters) {

        int current_index = 0;

        while ((current_index < parameters.length()) && !Character.isWhitespace(parameters.charAt(current_index)))
            current_index++;

        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(parameters.substring(0,current_index)));
            for ( Double i : data.getStack()) {
                writer.write(i.toString() + System.lineSeparator());
            }
        }
        catch (IOException e)
        {
            throw  new WrongArgumentsExpression("Save", parameters);
        }
        finally
        {
            if (null != writer)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {

                }
            }
        }

    }
}