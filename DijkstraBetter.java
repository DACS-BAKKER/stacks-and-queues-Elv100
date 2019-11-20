import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
 * Dijksta's TwoStack Implementation w/ Order of Operations and Line Parsing
 * Elven Shum
 */

public class DijkstraBetter {
    public static boolean isValidExp(String exprn){
        //
        return true;
    }
    //Two Stack that Handles AND
    private static int precedence(String oper){
        switch(oper){
            case ("+"):
            case ("-"):
                return 0;

            case ("*"):
            case ("/"):
                return 1;

            case ("^"):
                return 2;

            case (")"):
            case ("("):
                return 3;

            default:
                StdOut.println("Precedence: Not a Valid Operator");
                return -1;
        }
    }

    private static int indexOfFirstOper(String exprn) {
        int curToken = 0;
        int length = exprn.length();
        while (curToken < length){
            String first = exprn.substring(curToken,curToken+1);
            if (first.equals("+")|| first.equals("-") || first.equals("*") || first.equals("/") || first.equals("^") || first.equals("(") || first.equals(")")){
                return curToken;
            }
            curToken++;
        }
        return -1;
    }

    private static boolean isOperator(String token) {
        return (token.equals("+")|| token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"));

    }


    //TheFirstStack
    //parses Already Valid Equation into correct order
    private static Queue<String> parseEqn(String exprn) {
        Queue<String> output = new Queue<String>();
        Stack<String> opers = new Stack<String>();
        String curToken;
        StdOut.println(exprn);

        while (!exprn.isEmpty()) {
            //determines Current Token
            if (exprn.contains(" ")) {
                int firSpace = exprn.indexOf(" ");
                curToken = exprn.substring(0, firSpace);
                exprn = exprn.substring(firSpace + 1);
            } else {
                curToken = exprn;
                exprn = "";
            }

            if (isOperator(curToken)) {//operator case
                while (!opers.isEmpty()
                        && precedence(curToken) <= precedence(opers.peek())
                        && !opers.peek().equals("(")) {
                    output.enqueue(opers.pop());
                }
                opers.push(curToken);
            } else if (curToken.equals("(")) {       //"(" case
                opers.push(curToken);
            } else if (curToken.equals(")")) {        //")" case
                while (!opers.peek().equals("(")) {
                    output.enqueue(opers.pop());
                }
                if (opers.peek().equals("(")) {
                    opers.pop();    //can eliminate "("
                }
            } else {                                //number case; curToken is just a number
                output.enqueue(curToken);
            }

            StdOut.println("CurTOken:" + curToken + " out:"+output.toString() + " oper:"+opers.toString());
        }
        while (!opers.isEmpty()) {
            output.enqueue(opers.pop());
        }
        return output;
    }

    //TheSecondStack
    //evaluates Parsed Equation
    private static double eval(Queue<String> eqn){
        Stack<Double> holder = new Stack<Double>();
        Double operand1, operand2;
        while (!eqn.isEmpty()){
            if(!isOperator(eqn.peek())){     //if front of eqn is number
                holder.push(Double.parseDouble(eqn.dequeue()));     //pushes to Holder, parses to double
            } else {                            //if front of eqn is Operator
                operand1 = holder.pop();
                operand2 = holder.pop();
                holder.push(doMath(eqn.dequeue(), operand2, operand1));
            }
//            StdOut.println(holder.toString());
        }
        return holder.pop();    //there should be nothing left if all math is correct
    }



    private static double doMath(String oper, double val1, double val2) {
        switch (oper) {
            case ("+"):
                return val1 + val2;
            case ("-"):
                return val1 - val2;
            case ("*"):
                return val1 * val2;
            case ("/"):
                return val1 / val2;
            case ("^"):
                return Math.pow(val1, val2);

            default:
                StdOut.println("Math Failed");
                return -1;
        }
    }

    private static double calc(String[] expArr){
        String exp = "";
        for (String tokens:expArr){
            exp = exp + tokens + " ";
        }
        StdOut.println("Your Expression:" + exp);
        return eval(parseEqn(exp));
    }


    public static void main(String[] args){
        StdOut.println("Welcome to Elven's Superior Dijkstra's Two Stack");
        StdOut.println("I calculate expressions with w/ Order of Operations \n");
        StdOut.println("Please follow the Format when inputting equations:");
        StdOut.println("Put spaces between different operators and operands,");
        StdOut.println("        example:  ( 1 - 7 ) ^ 2 / 3 - 10 * 22 / 2");
        StdOut.println("When you're finished, end the expression by pressing \"return\", then \"⌘\" + \"d\", ");
        StdOut.println("        example: ( 1 - 7 ) ^ 2 / 3 - 10 * 22 / 2 \n                  ⌘d");
        StdOut.println("Input your desired Expression:");
        String[] eqn = StdIn.readAllStrings();
        StdOut.println(calc(eqn));
    }

}
