package calculator.exceptions;

public class DivisionByZeroException extends ArithmeticalException {

    public DivisionByZeroException() {
        super("You cannot divide by zero");
    }
}