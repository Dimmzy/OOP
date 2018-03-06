public class DescribeNumbers {

    public static void main(String[] args) {
        int[] numArray = stringsToInts(args);
        System.out.println("min: " + min(numArray));
        System.out.println("max: " + max(numArray));
        System.out.println("avg: " + avg(numArray));
    }

    public static int[] stringsToInts(String[] numbers){
        int[] numArray = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            numArray[i] = Integer.parseInt(numbers[i]);
        }
        return numArray;
    }

    public static int min(int[] numbers){
        int minNumber = numbers[0];
        for(int i = 1; i < numbers.length; i++){
            if(numbers[i] < minNumber){
                minNumber = numbers[i];
            }
        }
        return minNumber;
    }

    public static int max(int[] numbers){
        int maxNumber = numbers[0];
        for(int i = 0; i < numbers.length; i++){
            if (numbers[i] > maxNumber){
                maxNumber = numbers[i];
            }
        }
        return maxNumber;
    }

    public static float avg(int[] numbers){
        int numsSum = 0;
        for(int i = 0; i < numbers.length; i++){
            numsSum += numbers[i];
        }
        return (float)numsSum / numbers.length;
    }
}

