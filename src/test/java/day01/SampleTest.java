package day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {
    @Test
    public void calculatorTest(){

        System.out.println("Hello World!");
        //Assert 4 + 5 is 9
        assertEquals(9,4+5);

        //how do we add error message when assertioon fail

      //  assertEquals(10,5+4,"Hey wrong result!");
    }
    //you can add the display name for your test instead of the method name

    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){

        //write a simple assertion
        //concetanate your first name and last name
        //and make assertion it equal to your full name
        String firstName="Marijana";
        String lastName="Igracki";

        assertEquals("Marijana Igracki",firstName+" "+lastName);




    }
}
