package com.pragmatic;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameters{

    @Parameters ({"browserName"})
    @Test (groups = {"regression"})
    public void testMethod1(String browserName) {
        System.out.println("TestNGParameters.testMethod1");
        System.out.println("browserName = " + browserName);
    }

    @Test (groups = {"smoke"})
    public void testMethod2() {

        System.out.println("TestNGMethodsTest.testMethod2");
    }

    @Test (groups = {"regression"})
    public void testMethod3() {

        System.out.println("TestNGMethodsTest.testMethod3");
    }

    @Test @Parameters ({"browserName"})
    public void testMethod4(String browserName) {
        System.out.println("browserName = " + browserName);
        System.out.println("TestNGMethodsTest.testMethod4");
    }

    @Test (groups = {"smoke"})
    public void testMethod5() {

        System.out.println("TestNGMethodsTest.testMethod5");
    }
}
