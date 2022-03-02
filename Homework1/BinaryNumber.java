//Assignment 1
//Name Jiawei Lu
//ID: 20007605
import java.util.Arrays;
import java.lang.Integer;

public class BinaryNumber{
    private int[] Array;  //Declare int[] array
    private boolean overflow;  //Declare overflow
    public BinaryNumber(int length){
        Array = new int[length];
        for(int i=0; i<length;i++) {
		Array[i]=0;
	}

    }

    public BinaryNumber(String str){
        int length = str.length();
        Array = new int[length];
        for (int i =0; i < length; i++) {
            char c = str.charAt(i);
            if (Character.getNumericValue(c) > 1){
                System.out.println("Warning: not a binary number");
            }
            Array[i] = Character.getNumericValue(c);
        }
    }

    public int getLength() {
        return Array.length;
    }


    public int getDigit(int index) {
        if (index >= 0 && index < Array.length ) {
            return Array[index];
        } else {
            System.out.println("Binary number out of bound!");
            return -1;
        }
    }


    public int toDecimal() {
        int decimal = 0;
        for (int i = 0; i< Array.length; i++) {
            decimal += Array[i] * Math.pow(2, i);
        }

        return decimal;
    }
    //Shrift right
    public void shiftR(int amount) {

        int[] shiftArray = Arrays.copyOf(Array, Array.length + amount);

        for (int i = Array.length - 1; i >= 0; i--) {
            shiftArray[i + amount] = shiftArray[i];
        }
        for (int i = 0; i < amount; i++){
            shiftArray[i] = 0;
        }
    System.out.println("The shifted array is " + Arrays.toString(shiftArray));

    }
    public void clearOverflow() {
			overflow = false;
		}
    //Addition of Binary Numbers
   public void add(BinaryNumber aBinaryNumber){
       int decimal = 0;

       int sumOfDigit = 0;
       int carry = 0;

       if(Array.length != aBinaryNumber.getLength()){
           System.out.println("the lengths of binary numbers don't match!");
       }
       
        for (int i = 0; i< Array.length; i++) {
                decimal += Array[i] * Math.pow(2, i);
            }
        int sum= decimal + aBinaryNumber.toDecimal();
        int sumValue = sum;
        int[] binaryNum = new int[Integer.toBinaryString(sum).length()];
        int index = 0;
        while (sum > 0) 
        {
            // storing remainder in binary array
            binaryNum[index] = sum % 2;
            sum = sum / 2;
            index++;
        }
            if (binaryNum.length>Array.length){
                overflow = true;
            } else {
               clearOverflow();
            }
            Array = binaryNum;
   }
   //Transforming a binary number to a String.
   @Override
   public String toString(){
       String string = "";
			if(overflow==true) {
				return  "Overflow";
			}
            for (int i = 0; i < Array.length; i++) {
            string += Array[i];
        } return string;
		}

    public static void main(String[] args) {

        BinaryNumber binaryNumber1 = new BinaryNumber("10110");
		BinaryNumber binaryNumber2 = new BinaryNumber("11100");
        System.out.println(binaryNumber1.getLength());
        System.out.println(binaryNumber1.getDigit(3));
        System.out.println(binaryNumber1.toDecimal());

		System.out.println(binaryNumber1.toString());
		System.out.println(binaryNumber2.toString());
        binaryNumber1.add(binaryNumber2);
        System.out.println("The sum of two binary number: " + binaryNumber1.toString());


        BinaryNumber AbinaryNumber1 = new BinaryNumber("10110");
		BinaryNumber AbinaryNumber2 = new BinaryNumber("11101");

        System.out.println(AbinaryNumber1.toString());
		System.out.println(AbinaryNumber2.toString());
        AbinaryNumber1.add(AbinaryNumber2);
        System.out.println("The sum of two binary number: " +AbinaryNumber1.toString());

        BinaryNumber binaryNumber3 = new BinaryNumber("10110");
        binaryNumber3.shiftR(3);

        BinaryNumber binaryNumber4 = new BinaryNumber("10110");
        System.out.println(binaryNumber4.getDigit(3));
        BinaryNumber binaryNumber5 = new BinaryNumber("11101");
        System.out.println(binaryNumber5.toDecimal());


	}
}











