package com.prospecta;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

//		A1: 5, A2: 7, A3: 9, B1: 3, B2: 8, B3: =4+5, C1: =5+A1, C2: =A2+B2, C3: =C2+B3
	
//	I want a program that will take the CSV input above and produce CSV output with the results.  
//	If it is a value, then return a value.  If it is a formula then calculate the formula and 
//	return the value of that formula.

		public static void main(String[] args) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Insert an input and Press Enter.");

	        String input = sc.nextLine();
	        
	        System.out.println("================================================================");
	        System.out.println("The OutPut of the CSV Input is :- ");
	        System.out.println("================================================================");
	        
	        csvOutput(input);

	    }

	    public static void csvOutput(String input) {

	        String[] arr = input.split(", ");

	        if (arr.length == 0) {

	            System.out.println("Invalid Input");

	            return;
	        }

	        Map<String, String> variables = new LinkedHashMap<>();

	        try {
	            for (String str : arr) {

	                String[] variableVal = str.split(": ");

	                if (variableVal.length != 2) {

	                    System.out.println("Invalid Input");

	                    return;
	                }

	                String variable = variableVal[0];

	                String value = variableVal[1];

	                if (isValidvariable(variable)) {

	                    if (isNum(value)) {

	                        variables.put(variable, value);

	                    } else {

	                        if (isValidExpression(value)) {

	                            String exp = value.substring(1);

	                            String[] exparr = exp.split("[+*/-]");

	                            boolean add = false;

	                            if (exp.contains("+"))
	                                add = true;

	                            Integer val1 = null;

	                            Integer val2 = null;

	                            if (isValidvariable(exparr[0])) {

	                                if (variables.containsKey(exparr[0])) {

	                                    val1 = Integer.parseInt(variables.get(exparr[0]));

	                                } else {

	                                    System.out.println("Invalid Input");

	                                    return;
	                                }
	                            }
	                            if (isValidvariable(exparr[1])) {

	                                if (variables.containsKey(exparr[1])) {

	                                    val2 = Integer.parseInt(variables.get(exparr[1]));

	                                } else {

	                                    System.out.println("Invalid Input");

	                                    return;
	                                }
	                            }

	                            if (isNum(exparr[0]) && isNum(exparr[1])) {

	                                Integer res = null;

	                                Integer num1 = Integer.parseInt(exparr[0]);

	                                Integer num2 = Integer.parseInt(exparr[1]);

	                                if (add) {

	                                    res = add(num1, num2);
	                                }

	                                variables.put(variable, "" + res);

	                            } else if (val1 != null && val2 != null) {

	                                Integer res = null;

	                                if (add) {

	                                    res = add(val1, val2);
	                                }

	                                variables.put(variable, "" + res);

	                            } else if (val1 != null && val2 == null) {

	                                Integer res = null;

	                                Integer num1 = val1;

	                                Integer num2 = Integer.parseInt(exparr[1]);

	                                if (add) {

	                                    res = add(num1, num2);
	                                }

	                                variables.put(variable, "" + res);

	                            } else if (val1 == null && val2 != null) {

	                                Integer res = null;

	                                Integer num1 = Integer.parseInt(exparr[0]);

	                                Integer num2 = val2;

	                                if (add) {
	                                    res = add(num1, num2);
	                                }

	                                variables.put(variable, "" + res);

	                            } else {

	                                System.out.println("Invalid Input");

	                                return;
	                            }
	                        } else {

	                            System.out.println("Invalid Input");

	                            return;
	                        }
	                    }
	                } else {

	                    System.out.println("Invalid variable=" + variable);

	                    return;
	                }
	            }
	        } catch (NumberFormatException e) {

	            System.out.println("Invalid Input");

	            return;
	        }
	        System.out.println(variables);
	    }

	    public static boolean isValidvariable(String str) {
	        return Pattern.matches("^[A-Z]{1,2}[1-9]{1}[0-9]{0,4}$", str);
	    }

	    public static boolean isNum(String str) {
	        return Pattern.matches("^[0-9]+$", str);
	    }

	    public static boolean isValidExpression(String str) {
	        return Pattern.matches("^=[A-Z0-9]+[+*/-][A-Z0-9]+$", str);
	    }

	    public static int add(int num1, int num2) {
	        return num1 + num2;

	}


}
