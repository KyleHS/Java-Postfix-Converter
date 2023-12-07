import java.util.Stack;

public class JavaPostfixConverter {
    //initialization of constant variable 
    private static final char variable = 0;
    
    private static int precedence(char operator) {
        if (operator == '^') return 3;  //returns case 3 in precedence while loop
        if (operator == '*' || operator == '/') return 2; //returns case 2 in precedence while loop
        if (operator == '+' || operator == '-') return 1; //returns case 1 in precedence while loop
        return 0;
    }

	public static String convertToPostfix(String infix) {
        Stack<Character> operatorStack = new Stack<>(); //creation of stack
        String postfix = " "; //new empty string postfix

        for (char nextCharacter : infix.toCharArray()) {
        	
            if ((nextCharacter >= 'a' && nextCharacter <= 'z') || (nextCharacter >= '0' && nextCharacter <= '9')) {
                postfix += nextCharacter;  //checks all letter characters and numerical digits
            } else {
                switch (nextCharacter) 
                {
                //Cases for conversion
                    case variable:
                    	postfix += nextCharacter; //method for appending to nextCharacter
                    	break;
                    case '^':
                        operatorStack.push(nextCharacter);
                        break;
                    case '+': case '-': case '*': case '/': //precedence cases
                        while (!operatorStack.isEmpty() &&
                                precedence(nextCharacter) <= precedence(operatorStack.peek())) 
                        {
                            postfix += operatorStack.pop();
                        }
                        operatorStack.push(nextCharacter);
                        break;
                    case '(':
                        operatorStack.push(nextCharacter);
                        break;
                    case ')':
                        char topOperator = operatorStack.pop();
                        while (topOperator != '(') {
                            postfix += topOperator;
                            topOperator = operatorStack.pop();
                        }
                        break;
                    default: break; // Ignores unexpected characters within string
                }
            }
          
        }
        //When stack is empty will append topOperator to postfix string
        while (!operatorStack.isEmpty()) 
        {
        	char topOperator = operatorStack.pop();
            postfix += topOperator;
        }
        return postfix.toString();
     }
}

//test class that uses conversion
class Test {
    public static void main(String[] args) {
        String before = "a/b*(c+(d-e))"; //example string from problem in book (Figure 5-9)
        String after = JavaPostfixConverter.convertToPostfix(before); //prints ab/cde-+*

        //console logs
        System.out.println("The Infix Expression: " + before); //prints out infix express pre conversion
        System.out.println("``````````````````````````````````");
        System.out.println("```````````CONVERTS TO````````````"); //spacing
        System.out.println("``````````````````````````````````");
        System.out.println("The Postfix Expression: " + after); //prints the end result
    }
}
