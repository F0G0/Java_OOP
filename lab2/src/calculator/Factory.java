package calculator;


import calculator.commands.Command;
import calculator.exceptions.CommandNotFoundException;
import calculator.exceptions.PropertiesLoadException;

import java.io.IOException;
import java.util.Properties;

public class Factory {
    private static volatile Factory instance = null;
    private final Properties properties = new Properties();

    private Factory(){
        try {
            properties.load(Factory.class.getResourceAsStream("command.config"));
        }
        catch (Exception e) {
            throw new PropertiesLoadException();
        }
    }

    public static Factory getInstance() throws IOException {
        Factory l_instance = instance;

        if (l_instance == null) {
            synchronized (Factory.class) {
                l_instance = instance;
                if (l_instance == null) {
                    instance = l_instance = new Factory();
                }
            }
        }
        return l_instance;
    }

    public Command createCommand(String commandName)
    {

        String class_name = properties.getProperty(commandName);
        if (class_name == null)
            throw new CommandNotFoundException(commandName);

        Command command;
        try {
            command = (Command) Class.forName(class_name).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CommandNotFoundException(commandName);
        }

        return command;
    }
}
