import java.util.ArrayList;
import java.util.Scanner;

public class SayNumber1 {
    
    public static String sayNumber(String numeral) {
        // This method takes a string representation of a number and returns its spoken equivalent in English.
        // The spoken equivalent is returned as a string.
        
        if (numeral.equals("0")) {
            return "zero";
        }
        // If the input is 0, return "zero" immediately
        
        StringBuilder sb = new StringBuilder();
        // Create a StringBuilder object to build the spoken equivalent
        
        String[] groups = {"", "thousand", "million", "billion", "trillion"};
        // Create an array of group names for numbers (e.g. thousand, million, billion)
        
        int groupIndex = 0;
        // Initialize a variable to keep track of the current group (thousand, million, etc.)
        
        while (!numeral.isEmpty()) {
            // Keep looping until there are no more digits left in the input number
            
            String group = numeral.substring(Math.max(0, numeral.length() - 3));
            // Get the last three digits of the input number (or less if there are fewer than three digits remaining)
            
            numeral = numeral.substring(0, Math.max(0, numeral.length() - 3));
            // Remove the last three digits from the input number
            
            int num = Integer.parseInt(group);
            // Convert the group of digits to an integer
            
            if (num > 0) {
                // If the group is not zero, add it to the spoken equivalent
                
                String groupStr = readThreeDigitNum(num);
                // Use the readThreeDigitNum method to get the spoken equivalent of the group
                
                if (groupIndex > 0) {
                    groupStr += " " + groups[groupIndex];
                    // Add the group name (e.g. thousand, million, etc.) to the spoken equivalent
                }
                
                if (sb.length() > 0) {
                    sb.insert(0, " ");
                    // Add a space between groups in the spoken equivalent
                }
                
                sb.insert(0, groupStr);
                // Add the current group to the beginning of the spoken equivalent
            }
            
            groupIndex++;
            // Increment the group index for the next iteration
        }
        
        return sb.toString();
        // Return the full spoken equivalent of the input number
    }
    
    private static String readThreeDigitNum(int num) {
        // This method takes an integer between 1 and 999 and returns its spoken equivalent in English.
        // The spoken equivalent is returned as a string.
        
        String[] digits = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        // Create an array of digit names (e.g. one, two, three)
        
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        // Create an array of teen numbers (e.g. ten, eleven, twelve)
        
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        // Create an array of ten numbers (e.g. twenty, thirty, forty)
        
        StringBuilder sb = new StringBuilder();
        // Create a StringBuilder object to build the spoken equivalent
        
        int hundreds = num / 100;
        // Get the hundreds

        if (hundreds > 0) {
            sb.append(digits[hundreds]).append(" hundred");
            num %= 100;
        }
        if (num == 0) {
            return sb.toString();
        }
        if (sb.length() > 0) {
            sb.append(" and ");
        }
        if (num < 10) {
            sb.append(digits[num]);
        } else if (num < 20) {
            sb.append(teens[num - 10]);
        } else {
            int tensDigit = num / 10;
            int onesDigit = num % 10;
            sb.append(tens[tensDigit]);
            if (onesDigit > 0) {
                sb.append("-").append(digits[onesDigit]);
            }
        }
        return sb.toString();
    }
    

    //Main function 
    //Takes an input from the user saves it to num string
    //The vairble is the passed to the sayNumber method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a Number: ");
        String num = sc.next();

        System.out.println(sayNumber(num));
    }
    
}
