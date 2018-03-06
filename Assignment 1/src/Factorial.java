
public class Factorial {

    public static void main(String[] args){
        int userNumber = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(userNumber));
        System.out.println("iterative: " + factorialIter(userNumber));


    }

    public static long factorialRecursive(long n){
        if(n <= 0){
            return -1;
        }
        else if(n >= 2){
            return factorialRecursive(n-1) * n;
        }
        else {
            return n;
        }

    }

    public static long factorialIter(long n){
        long factSum = 1;
        for(long i = 2; i <= n; i++){
            factSum *= i;
        }
        return factSum;
    }
}