/* Assignment - 2 
 * Jiawei Lu
 * CWID: 20007605
 * 
 * */

public class Complexity {
    // O(n^2) time complexity
    public static void method1(int n){
        int counter = 0; 
        for (int i = 0; i < n; i++) {
            for(int j =0; j < n; j++){
                System.out.println("Operation "+counter); 
                counter++; 
            }
        }
    }


    // O(n^3) time complexity
    public static void method2(int n){
        int counter = 0; 
        for (int i = 0; i < n; i++) {
            for(int j =0; j < n; j++){
                for(int k =0; k < n; k++){
                    System.out.println("Operation "+counter); 
                    counter++; 
                }
            }
        }
    }



// O(logn) time complexity
public static void method3(int n){
    int counter = 0;
    for (int i = 1; i < n; i = i *2){
        System.out.println("Operation "+counter); 
        counter++; 
    }
}


// O(nlogn) time complexity
public static void method4(int n){
        int counter = 0;
        for (int i = 0; i < n; i++){
            for (int j = 1; j < n; j = j *2){
                System.out.println("Operation "+counter); 
                counter++; 
        }
    }
}


// O(loglogn) time complexity
public static void method5(int n) {
    int counter = 0;
    for (int i=2; i<n; i= i * i) {
        System.out.println("Operation " + counter);
        counter++;
    }
}

    // O(2^n) time complexity
    static int counter = 0;
    public static int method6(int n) {
        if (n <= 0){
            counter++;
            System.out.println("Operation " + counter);
            return 0;
        } else if (n == 1){
            counter++;
            System.out.println("Operation " + counter);
            return 1;
        }
        else{
        counter++;
        System.out.println("Operation " + counter);
        return method6(n -1 ) + method6(n-1);
        }
    }


public static void main(String[] args){ 
    System.out.println("Method1 with complexity of O(n^2) start");
    method1(10);
    System.out.println("Method1 with complexity of O(n^2) end");

    System.out.println("Method2 with complexity of O(n^3) start");
    method2(10);
    System.out.println("Method2 with complexity of O(n^3) end");

    System.out.println("Method3 with complexity of O(logn) start");
    method3(10);
    System.out.println("Method3 with complexity of O(logn) end");
    
    System.out.println("Method4 with complexity of O(nlogn) start");
    method4(10);
    System.out.println("Method4 with complexity of O(nlogn) end");

    System.out.println("Method5 with complexity of O(loglogn) start");
    method5(10);
    System.out.println("Method5 with complexity of O(loglogn) end");

    System.out.println("Method6 with complexity of O(2^n) start");
    method6(10);
    System.out.println("Method6 with complexity of O(2^n) end");
}
}