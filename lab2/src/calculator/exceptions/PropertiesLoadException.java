package calculator.exceptions;

public class PropertiesLoadException extends FactoryException{

    public PropertiesLoadException(){
        super("Cannot load properties file");
    }

}