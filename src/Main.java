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

        char operator_one = twostack.pop(operator).charAt(0);

        String tmp = operator_one + operand_two + operand_one;
        twostack.push(operand, tmp);
    }


    private static String infixToPrefix(String infix) {

        String trimmedInfix = infix.replaceAll("\\s+", "").trim();

        for (int i = 0; i < trimmedInfix.length(); i++) {

            if (!isOperator(trimmedInfix.charAt(i))) {

                twostack.push(operand,trimmedInfix.charAt(i) + "");

            } else {

                while (!twostack.isEmpty(operator) && getOperatorPrecedent(trimmedInfix.charAt(i)) <= getOperatorPrecedent(peekChar(operator))) {

                    shuffle();

                }

                twostack.push(operator, String.valueOf(trimmedInfix.charAt(i)));
            }
        }


        while (!twostack.isEmpty(operator)) {

            shuffle();

        }

        return twostack.peek(operand);
    }


    private static void debug(Boolean side) {
        String sideName = side ? "Right" : "Lefty";
        System.out.println("Stack: " + sideName + "\t-\t" + twostack.peek(side) + "\t-\t Stack Size: " + twostack.size(side));
    }

    public static void main(String[] args) {
        String infix = "A + B * C";
        System.out.println("Final result: " + infixToPrefix(infix));
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
