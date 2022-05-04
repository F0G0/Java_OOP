package calculator;

import calculator.commands.Command;
import calculator.exceptions.ArithmeticalException;
import calculator.exceptions.CommandException;
import calculator.exceptions.FactoryException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Calculator {

    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    private final Context data = new Context();

    private void executeCommand(String commandLine) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        int currentSymbolIndex = 0;

        while ((currentSymbolIndex < commandLine.length()) && !Character.isWhitespace(commandLine.charAt(currentSymbolIndex))) {
            currentSymbolIndex++;
        }

        String commandName = commandLine.substring(0, currentSymbolIndex++);

        String commandParameters = "";
        if (currentSymbolIndex < commandLine.length())
            commandParameters = commandLine.substring(currentSymbolIndex);

        Command command;
        try {
            command = Factory.getInstance().createCommand(commandName);
        } catch (FactoryException e) {
            logger.warning("Command Factory exception: " + e.getMessage());
            return;
        }

        try {
            command.execute(data, commandParameters);
            logger.info("Command \"" + commandName + "\" succeeded");
        } catch (ArithmeticalException e) {
            logger.warning("Arithmetical Exception: " + e.getMessage());
        } catch (CommandException e) {
            logger.warning("Command Exception: " + e.getMessage());
        }


    }


    public void calculate(BufferedReader reader) {

        try {

            String commandLine;
            while ((commandLine = reader.readLine()) != null) {
                executeCommand(commandLine);
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            logger.info("Error happened while reading stream");
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            }
        }

    }
    public static void main(String[] args) {

        try {
            logger.setUseParentHandlers(false);
            FileHandler fh = new FileHandler("log.txt");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Calculator calculator = new Calculator();
        logger.info("Calculator created");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (args.length > 0){
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
                logger.info("Opened file \"" + args[0] +"\"");
            } catch (FileNotFoundException e) {
                logger.info("Error opening file \"" + args[0] +"\". Reading System.in");
            }
        }
        calculator.calculate(reader);
    }

}