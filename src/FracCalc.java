import java.util.*;
public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
    	Scanner console = new Scanner(System.in);
    	System.out.print("Enter a fraction calculation, enter \"done\" to finish. ");
    	String input = console.nextLine();
    	while (!input.equals("done")) {
    		System.out.println(produceAnswer(input));
    		System.out.print("Enter a fraction calculation, enter \"done\" to finish. ");
    		input = console.nextLine();
    		
    	}
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
    	String firstNum = input.substring(0, input.indexOf(" "));
    	String operation = input.substring(input.indexOf(" ") + 1,input.indexOf(" ") + 2);
    	String secondNum = input.substring(input.indexOf(" ") + 3);
    	String firstWhole;
    	String firstNumerator;
    	String firstDenominator;
    	String secondDenomintaor;
    	String secondWhole;
    	String secondNumerator;
    	String secondDenominator;
    	if ((firstNum.indexOf("_") != -1)) {
    		firstWhole = firstNum.substring(0, firstNum.indexOf("_"));                             // Here I break the input into it's respective parts
    	} else if ((firstNum.indexOf("_") == -1) && (firstNum.indexOf("/") != -1)) {
    		firstWhole = "0";
    	} else {
    		firstWhole = firstNum;
    	}
    	if (firstNum.indexOf("/") != -1) {
    		firstNumerator = firstNum.substring(firstNum.indexOf("_") + 1,firstNum.indexOf("/"));
    		firstDenominator = firstNum.substring(firstNum.indexOf("/") + 1);
    	} else {
    		firstNumerator = "0";
    		firstDenominator = "1";
    	}
    	
    	if ((secondNum.indexOf("_") != -1)) {
    		secondWhole = secondNum.substring(0, secondNum.indexOf("_"));
    	} else if ((secondNum.indexOf("_") == -1) && (secondNum.indexOf("/") != -1)) {
    		secondWhole = "0";
    	} else {
    		secondWhole = secondNum;
    	}
    	if (secondNum.indexOf("/") != -1) {
    		secondNumerator = secondNum.substring(secondNum.indexOf("_") + 1,secondNum.indexOf("/"));
        	secondDenominator = secondNum.substring(secondNum.indexOf("/") + 1);
    	} else {
    		secondNumerator = "0";
    		secondDenominator = "1";
    	}
    	int outputWhole;
    	int outputNumerator;
    	int outputDenominator;
    	//The basic way this program works is by converting the input numbers into fractions
    	//Once in fractions, doing the math is a matter of simple operations.
    	//it calculates the final answer as an unreduced fraction which is then reduced and returned as a proper fraction.
    	//The returned value needs to range between mixed, fraction and whole numbers so there is logic to do the right operations in the right situation
    	//The return states also function similarly.
    	if (operation.equals("*")) {
    		int outputFirstNumerator = Math.abs(Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator)) + Integer.parseInt(firstNumerator);
    		int outputSecondNumerator = Math.abs(Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator)) + Integer.parseInt(secondNumerator);
    		outputNumerator = outputFirstNumerator * outputSecondNumerator;
    		if ((Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator) + Integer.parseInt(firstNumerator) < 0) || ((Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator)) + Integer.parseInt(secondNumerator) < 0)) {
    			if (!((Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator) + Integer.parseInt(firstNumerator) < 0) && ((Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator)) + Integer.parseInt(secondNumerator) < 0))) {
    				if (Integer.parseInt(firstWhole) == 0 || Integer.parseInt(secondWhole) == 0) {
    					outputNumerator = outputFirstNumerator * outputSecondNumerator;
    				} else {
    				outputNumerator = -outputFirstNumerator * outputSecondNumerator;
    				}
    			} else if ((firstNum.indexOf("_") == -1 && secondNum.indexOf("_") != -1) || (secondNum.indexOf("_") == -1 && firstNum.indexOf("_") != -1)) {
    				outputNumerator = outputFirstNumerator * outputSecondNumerator;
    			} else if ((Integer.parseInt(firstWhole) == 0 && Integer.parseInt(secondWhole) != 0) || (Integer.parseInt(secondWhole) == 0 && Integer.parseInt(firstWhole) != 0)) {
    				outputNumerator = -outputFirstNumerator * outputSecondNumerator;
    			} else {
    				outputNumerator = outputFirstNumerator * outputSecondNumerator;
        		}
    		}
    		outputDenominator = Integer.parseInt(firstDenominator) * Integer.parseInt(secondDenominator);
    		int gcd = greatestCommonDivisor(outputNumerator,outputDenominator);
    		if (outputFirstNumerator * outputSecondNumerator == 0) {
    			return "0";
    		} else if (outputNumerator/outputDenominator == 0) {
    			return Integer.toString(outputNumerator / gcd) + "/" + Integer.toString(outputDenominator / gcd);
    		} else if (outputNumerator % outputDenominator == 0) {
    			return Integer.toString(outputNumerator/outputDenominator);
    		} else {
    			return Integer.toString(outputNumerator/outputDenominator) + "_" + Integer.toString(Math.abs((outputNumerator % outputDenominator) / gcd)) + "/" + Integer.toString(outputDenominator / gcd);
    		}
    	} else if (operation.equals("/")) {
    		int outputFirstNumerator = Math.abs(Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator)) + Integer.parseInt(firstNumerator);
    		int outputSecondNumerator = Math.abs(Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator)) + Integer.parseInt(secondNumerator);
    		outputNumerator = outputFirstNumerator * Integer.parseInt(secondDenominator);
    		if ((Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator) + Integer.parseInt(firstNumerator) < 0) || ((Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator)) + Integer.parseInt(secondNumerator) < 0)) {
    			if (!((Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator) + Integer.parseInt(firstNumerator) < 0) && ((Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator)) + Integer.parseInt(secondNumerator) < 0))) {
    				if (Integer.parseInt(firstWhole) == 0 || Integer.parseInt(secondWhole) == 0) {
    					outputNumerator = outputFirstNumerator * Integer.parseInt(secondDenominator);
    				} else {
    				outputNumerator = -outputFirstNumerator * Integer.parseInt(secondDenominator);
    				}
    			} else if ((firstNum.indexOf("_") == -1 && secondNum.indexOf("_") != -1) || (secondNum.indexOf("_") == -1 && firstNum.indexOf("_") != -1)) {
    				outputNumerator = outputFirstNumerator * Integer.parseInt(secondDenominator);
    			} else if ((Integer.parseInt(firstWhole) == 0 && Integer.parseInt(secondWhole) != 0) || (Integer.parseInt(secondWhole) == 0 && Integer.parseInt(firstWhole) != 0)) {
    				outputNumerator = -outputFirstNumerator * Integer.parseInt(secondDenominator);
    			} else {
    				outputNumerator = outputFirstNumerator * Integer.parseInt(secondDenominator);
        		}
    		}
    		outputDenominator = Integer.parseInt(firstDenominator) * outputSecondNumerator;
    		int gcd = greatestCommonDivisor(outputNumerator,outputDenominator);
    		if (outputFirstNumerator / outputSecondNumerator == 0) {
    			return "0";
    		} else if (outputNumerator/outputDenominator == 0) {
    			return Integer.toString(outputNumerator / gcd) + "/" + Integer.toString(outputDenominator / gcd);
    		} else if (outputNumerator % outputDenominator == 0) {
    			return Integer.toString(outputNumerator/outputDenominator);
    		} else {
    			return Integer.toString(outputNumerator/outputDenominator) + "_" + Integer.toString(Math.abs((outputNumerator % outputDenominator) / gcd)) + "/" + Integer.toString(outputDenominator / gcd);
    		}
    	} else if (operation.equals("+")) {
    		int outputFirstNumerator = (Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator) + negative(Integer.parseInt(firstWhole)) * Integer.parseInt(firstNumerator)) * Integer.parseInt(secondDenominator);
    		int outputSecondNumerator = (Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator) + negative(Integer.parseInt(secondWhole)) * Integer.parseInt(secondNumerator)) * Integer.parseInt(firstDenominator); 
    		outputNumerator = outputFirstNumerator + outputSecondNumerator;
    		System.out.println(outputFirstNumerator);
    		System.out.println(outputSecondNumerator);
    		outputDenominator = Integer.parseInt(secondDenominator) * Integer.parseInt(firstDenominator);
    		int gcd = greatestCommonDivisor(outputNumerator,outputDenominator);
    		if (outputFirstNumerator + outputSecondNumerator == 0) {
    			return "0";
    		} else if (outputNumerator/outputDenominator == 0) {
    			return Integer.toString(outputNumerator / gcd) + "/" + Integer.toString(outputDenominator / gcd);
    		} else if (outputNumerator % outputDenominator == 0) {
    			return Integer.toString(outputNumerator/outputDenominator);
    		} else {
    			return Integer.toString(outputNumerator/outputDenominator) + "_" + Integer.toString(Math.abs((outputNumerator % outputDenominator) / gcd)) + "/" + Integer.toString(outputDenominator / gcd);
    		}
    	} else {
    		int outputFirstNumerator = (Integer.parseInt(firstWhole) * Integer.parseInt(firstDenominator) + negative(Integer.parseInt(firstWhole)) * Integer.parseInt(firstNumerator)) * Integer.parseInt(secondDenominator);
    		int outputSecondNumerator = (Integer.parseInt(secondWhole) * Integer.parseInt(secondDenominator) + negative(Integer.parseInt(secondWhole)) * Integer.parseInt(secondNumerator)) * Integer.parseInt(firstDenominator); 
    		outputNumerator = outputFirstNumerator - outputSecondNumerator;
    		outputDenominator = Integer.parseInt(secondDenominator) * Integer.parseInt(firstDenominator);
    		int gcd = greatestCommonDivisor(outputNumerator,outputDenominator);
    		if (outputFirstNumerator - outputSecondNumerator == 0) {
    			return "0";
    		} else if (outputNumerator/outputDenominator == 0) {
    			return Integer.toString(outputNumerator / gcd) + "/" + Integer.toString(outputDenominator / gcd);
    		} else if (outputNumerator % outputDenominator == 0) {
    			return Integer.toString(outputNumerator/outputDenominator);
    		} else {
    			return Integer.toString(outputNumerator/outputDenominator) + "_" + Integer.toString(Math.abs((outputNumerator % outputDenominator) / gcd)) + "/" + Integer.toString(outputDenominator / gcd);
    		}
    	}
    
  
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "secondWhole:1 secondNumerator:2 secondDenominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        
    }

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    
    // negative just returns whether a number is negative, with 0 returning as 1 for my programs purpose
    public static int negative(int a) {
    	if (a >= 0) {
    		return 1;
    	} else {
    		return -1;
    	}
    }
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
