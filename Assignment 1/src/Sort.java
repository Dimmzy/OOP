/**
 * The Sort class performs bubble sort on the given string array of numbers
 * according to the specified sorting order (ascending/descending).
 */
public class Sort {

    /**
     * Main function checks which ordering method has been given, it sends the
     * string array to the stringsToInts function to create an integer array it
     * then sends the array and the order type to the bubbleSort function.
     * After the sort has been made it prints the sorted array
     * @param args an array of strings, first one specifies order of sorting
     *             the rest of the strings are the numbers to be sorted.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input given. Exiting...");
            return;
        }
        int[] numArray = stringsToInts(args);

        /* We'll create a flag that dictates in which order to sort */
        int mode;
            if (args[0].equals("desc")) {
                mode = 1;
            } else if (args[0].equals("asc")) {
                mode = 0;
            } else {
                System.out.println("Invalid/No sorting method specified. "
                        + "Exiting...");
                return;
            }
        bubbleSort(numArray, mode);
        printArray(numArray);
    }

    /**
     * The function converts the passed string array into an integer array
     * from the [1] index onwards, since the [0] index contains the sort
     * order type).
     * @param numbers the string array to be made into an integer array
     * @return returns the integer array that was created using the string array
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] numArray = new int[numbers.length - 1];
        for (int i = 1; i < numbers.length; i++) {
            numArray[i - 1] = Integer.parseInt(numbers[i]);
        }
        return numArray;
    }

    /**
     * The function prints the elements of the array using a for loop that
     * iterates from the start to the index of the array and prints each
     * element.
     * @param arrayToPrint is the array that it's elements are going to be
     *                     printed using the method.
     */
    public static void printArray(int[] arrayToPrint) {
        for (int i = 0; i < arrayToPrint.length; i++) {
            /* Avoids printing trailing whitespace at start */
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arrayToPrint[i]);
        }
        System.out.println("");
    }

    /**
     * The function sorts the passed array using the order it has been given by
     * using the BubbleSort method.
     * @param arrayToSort is the passed int array that is going to be sorted.
     * @param mode the order in which to sort the elements in the array using
     *            bubble sort (0 = ascending 1 = descending)
     */
    public static void bubbleSort(int[] arrayToSort, int mode) {
        int arrSize = arrayToSort.length;
        if (mode == 0) {
            for (int i = 0; i < arrSize - 1; i++) {
                for (int j = 0; j < arrSize - i - 1; j++) {
                    if (arrayToSort[j] > arrayToSort[j + 1]) {
                        int temp = arrayToSort[j];
                        arrayToSort[j] = arrayToSort[j + 1];
                        arrayToSort[j + 1] = temp;
                    }
                }
            }
        } else {
            for (int i = 0; i < arrSize - 1; i++) {
                for (int j = 0; j < arrSize - i - 1; j++) {
                    if (arrayToSort[j] < arrayToSort[j + 1]) {
                        int temp = arrayToSort[j];
                        arrayToSort[j] = arrayToSort[j + 1];
                        arrayToSort[j + 1] = temp;
                    }
                }
            }
        }
    }
 }
