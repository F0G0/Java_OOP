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
        Factory localInstance = instance;

        if (localInstance == null) {
            synchronized (Factory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Factory();
                }
            }
        }
        return localInstance;
    }

    public Command createCommand(String commandName)
    {

        String commandClassName = properties.getProperty(commandName);
        if (commandClassName == null)
            throw new CommandNotFoundException(commandName);

        Command command;
        try {
            command = (Command) Class.forName(commandClassName).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CommandNotFoundException(commandName);
        }

        return command;
    }
}
