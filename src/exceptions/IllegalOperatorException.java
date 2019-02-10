package exceptions;

public class IllegalOperatorException extends IllegalArgumentException {

    public IllegalOperatorException(Character c) {
        super(Character.toString(c));
    }
}
