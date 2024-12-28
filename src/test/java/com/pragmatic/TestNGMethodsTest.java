package com.pragmatic;

import org.testng.annotations.*;

public class TestNGMethodsTest extends TestBase{

    @Test (priority = 1, invocationCount = 5, invocationTimeOut = 5000)
    public void testMethod1() throws InterruptedException {
        //invocationcount - how many times do you want iterrate the function
        Thread.sleep(1500);
        System.out.println("TestNGMethodsTest.testMethod1");

        // 5000/1500=3 loops
    }

    @Test (priority = 2)
    public void testMethod2() {
        System.out.println("TestNGMethodsTest.testMethod2");
    }

    @Test (priority = 4)
    public void testMethod3() {
        System.out.println("TestNGMethodsTest.testMethod3");
    }

    @Test (enabled = false)
    public void testMethod4() {
        System.out.println("TestNGMethodsTest.testMethod4");
    }

    @Test (priority = 3)
    public void testMethod5() {
        System.out.println("TestNGMethodsTest.testMethod5");
    }

}
