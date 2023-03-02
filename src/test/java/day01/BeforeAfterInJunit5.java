package day01;

import org.junit.jupiter.api.*;

public class BeforeAfterInJunit5 {
    @BeforeAll //before All will run only once before the entire test
    public static void setUp() {
        System.out.println("This run before All!");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Running before test!");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 is running!");
    }
    @Disabled //same as ignored
    @Test
    public void test2() {
        System.out.println("Test 2 is running!1");
    }

    @AfterEach
    public  void afterEachTest(){
        System.out.println("Running after each test!");
    }

    @AfterAll
    public static void tearDown() {

        System.out.println("This run all the way at the end!");
    }

}
