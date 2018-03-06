public class Sort {
    public static void main(String[] args){
        int[] numArray = stringsToInts(args);
        int mode = 0;
        if(args[0].equals("desc")) {
            mode = 1;
        }
        else if(args[0].equals("asec")){
            mode = 0;
        }
        bubbleSort(numArray,mode);
        printArray(numArray);
    }

    public static int[] stringsToInts(String[] numbers){
        int[] numArray = new int[numbers.length];
        for(int i = 1; i < numbers.length; i++){
            numArray[i-1] = Integer.parseInt(numbers[i]);
        }
        printArray(numArray);
        return numArray;
    }

    public static void printArray(int[] arrayToPrint){
        for(int i = 0; i < arrayToPrint.length; i++){
            System.out.print(" " + arrayToPrint[i]);
        }
    }

    public static void bubbleSort(int[] arrayToSort,int mode) {
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
        }
        else{
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
