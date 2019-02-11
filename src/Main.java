import twostack.TwostackArray;

public class Main {

    private static boolean operand = false;
    private static boolean operator = true;

    private static TwostackArray<String> twostack = new TwostackArray<>(100);

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
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static char peekChar(Boolean right) {
        return twostack.peek(right).charAt(0);
    }

    private static void shuffle() {
        String operand_one = twostack.pop(operand);
        String operand_two = twostack.pop(operand);

        String operator_one = twostack.pop(operator);

        String prefix = operator_one + operand_two + operand_one;
        twostack.push(operand, prefix);
    }


    private static String infixToPrefix(String infix) {

        String trimmedInfix = infix.replaceAll("\\s+", "").trim();

        for (int i = 0; i < trimmedInfix.length(); i++) {

            char c = trimmedInfix.charAt(i);

            if (!isOperator(c)) {

                twostack.push(operand,trimmedInfix.charAt(i) + "");

            } else {

                while (!twostack.isEmpty(operator) && getOperatorPrecedent(c) <= getOperatorPrecedent(peekChar(operator))) {

                    shuffle();

                }

                twostack.push(operator, String.valueOf(c));

            }
        }


        while (!twostack.isEmpty(operator)) {

            shuffle();

        }

        return twostack.peek(operand);
    }

    public static void main(String[] args) {
        String infix = "A + B * C";
        System.out.println("Final result: " + infixToPrefix(infix));
    }

}
