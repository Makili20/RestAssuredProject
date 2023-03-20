package day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class gettingRandomNumber {


    @Test
    public void testRandom(){
      //Random class is coming from java.util package and can provide some random
        // numbers in different type
        Random r=new Random();
        //this will give us random number from 0-10
       int randomNumber= r.nextInt(10);
        List<String>names= Arrays.asList("Anna","Vincent","Emrah","Zuura","Natallia","Zuleyha");

        System.out.println("randomNumber = " + randomNumber);
        System.out.println("Lucky name is = "+ names.get(randomNumber));

    }
}
