import exceptions.IllegalOperatorException;

public class Main {

    private static TwostackArray<String> twostack = new TwostackArray<>(100);
    private static boolean operand = false;
    private static boolean operator = true;

    private static boolean isOperand(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private static boolean isOperator(char c) {
        String operators = "-+*/";
        for (int i = 0; i < operators.length(); i++) {
            if (operators.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }

    private static int getOperatorPrecedent(char c) {
        switch (c) {
            case '-':
            case '+':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                throw new IllegalOperatorException(c);
        }
    }

    private static char peekChar(Boolean right) {
        return twostack.peek(right).charAt(0);
    }





    private static String infixToPrefixConvector(String infix) {

        String trimmedInfix = infix.replaceAll("\\s+", "").trim();

        for (int i = 0; i < trimmedInfix.length(); i++) {

            char c = trimmedInfix.charAt(i);
            System.out.println("Char at: " + c);

            if (isOperand(c)) {

                twostack.push(operand, Character.toString(c));
                System.out.println("Added " + c + " to operand stack");

            } else if (isOperator(c)) {

                if (twostack.isEmpty(operator)) {

                    twostack.push(operator, Character.toString(c));
                    System.out.println("Added " + c + " to operator stack");

                } else {

                    System.out.println("I supposed to run the loop right after this <---");

                    while (!twostack.isEmpty(operator) && getOperatorPrecedent(c) > getOperatorPrecedent(peekChar(operator))) {

                        System.out.println("I AM THE LOOOP, I AM WORKING !!!");

                        String operator_one;
                        String operand_one = null, operand_two = null;

                        if (twostack.isEmpty(operand)) {

                             operand_one = twostack.pop(operand);

                             if (twostack.isEmpty(operand)) {

                                operand_two = twostack.pop(operand);

                            }
                        }

                        operator_one = twostack.pop(operator);

                        if (operator_one != null && operand_one != null && operand_two != null) {

                            String prefix = operator_one + operand_one + operand_two;
                            System.out.println("Prefix: " + prefix);
                            twostack.push(operand, prefix);

                        }

                    }

                    twostack.push(operator, Character.toString(c));
                    System.out.println("Added " + c + " to operator stack after loop");

                }

            }

        }

        return twostack.peek(operand);

    }

    private static void debug(Boolean side) {
        String sideName = side ? "Right" : "Lefty";
        System.out.println("Stack: " + sideName + "\t-\t" + twostack.peek(side) + "\t-\t Stack Size: " + twostack.size(side));
    }

    public static void main(String[] args) {
        String infix = "A + B * C";
        System.out.println("Final result: " + infixToPrefixConvector(infix));
    }

    //https://stackoverflow.com/questions/31562506/implement-two-stacks-using-one-array
    private void test() {
        System.out.println("-------PUSH RIGHT-------");

        twostack.push(true, "apple");
        debug(true);
        twostack.push(true, "mango");
        debug(true);
        twostack.push(true, "melon");
        debug(true);

        System.out.println("-------PUSH LEFT-------");

        twostack.push(false, "carrot");
        debug(false);
        twostack.push(false, "potato");
        debug(false);
        twostack.push(false, "salad");
        debug(false);

        System.out.println("-------POP RIGHT-------");

        debug(true);
        twostack.pop(true);
        debug(true);
        twostack.pop(true);
        debug(true);
        twostack.pop(true);

        System.out.println("-------POP LEFT-------");

        debug(false);
        twostack.pop(false);
        debug(false);
        twostack.pop(false);
        debug(false);
        twostack.pop(false);

        System.out.println("-------CLEAR-------");
        twostack.push(true, "1");
        twostack.push(true, "2");
        twostack.push(true, "3");
        twostack.push(true, "4");
        twostack.push(true, "5");
        debug(true);

        twostack.push(false, "1");
        twostack.push(false, "2");
        twostack.push(false, "3");
        twostack.push(false, "4");
        twostack.push(false, "5");
        debug(false);

        twostack.clear();

        debug(true);
        debug(false);

        twostack.push(true, "cat");
        twostack.push(false, "dog");

        debug(true);
        debug(false);
    }

}
