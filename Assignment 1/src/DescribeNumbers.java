/**
 *  The DescribeNumbers class receives a string of numbers, converts them
 *  to an integer array and then describes the array's element's properties.
 *  Maximal number, Minimal number and the Average of all the numbers.
 */

public class DescribeNumbers {

    /**
     * The main method receives several arguments and calls the stringsToInts
     * method to convert the received string into an integer array,
     * it then prints the result of calling the min,max and average methods.
     * @param args  arguments from shell
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input given. Exiting...");
            return;
        }
        int[] numArray = stringsToInts(args);
        System.out.println("min: " + min(numArray));
        System.out.println("max: " + max(numArray));
        System.out.println("avg: " + avg(numArray));
    }

    /**
     * the method performs a loop that converts the strings into integers.
     * @param numbers a string of numbers received from main.
     * @return returns an integer array with the numbers received initially
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] numArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numArray[i] = Integer.parseInt(numbers[i]);
        }
        return numArray;
    }

    /**
     * the method searches and compares the values of each index
     * setting the smallest element into the variable when it's found.
     * @param numbers a number array to be checked
     * @return returns the minimal number from the array
     */
    public static int min(int[] numbers) {
        int minNumber = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minNumber) {
                minNumber = numbers[i];
            }
        }
        return minNumber;
    }

    /**
     * The method searches and compares the values of each index
     * setting the largest element into the variable when it's found.
     * @param numbers a number array to be checked
     * @return returns the maximal number from the array
     */
    public static int max(int[] numbers) {
        int maxNumber = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > maxNumber) {
                maxNumber = numbers[i];
            }
        }
        return maxNumber;
    }

    /**
     * The method sums all the elements in the array and then returns their
     * average by dividing the sum by the number of elements.
     * @param numbers a number array to be checked
     * @return returns the sum of all the elements divided by the number
     *         of elements, cast into float
     */
    public static float avg(int[] numbers) {
        int numsSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            numsSum += numbers[i];
        }
        return (float) numsSum / numbers.length;
    }
}

