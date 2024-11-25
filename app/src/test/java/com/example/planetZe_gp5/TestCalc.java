package com.example.planetZe_gp5;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

public class TestCalc {
    @Test
    public void testCarUnvalid1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 0);
        assertEquals(calculation.calculateCar(map),0, 0);
    }
    @Test
    public void testCarUnvalid2(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 0);
        assertEquals(calculation.calculateCar(map),0, 0);
    }
    @Test
    public void testCar1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 1);
        map.put("driveDistance", 100);
        assertEquals(calculation.calculateCar(map),24, 0);
    }
    @Test
    public void testCar2(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 2);
        map.put("driveDistance", 100);
        assertEquals(calculation.calculateCar(map),27, 0);
    }
    @Test
    public void testCar3(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 3);
        map.put("driveDistance", 100);
        assertEquals(calculation.calculateCar(map),16, 0);
    }
    @Test
    public void testCar4(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 4);
        map.put("driveDistance", 100);
        assertEquals(calculation.calculateCar(map),5, 0);
    }
    @Test
    public void testPtInvalid1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 4);
        assertEquals(calculation.calculatePT(map), 0, 0);
    }
    @Test
    public void testPtInvalid2(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 4);
        assertEquals(calculation.calculatePT(map), 0, 0);
    }
    @Test
    public void testPt1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 0);
        map.put("ptUsage", 0);
        assertEquals(calculation.calculatePT(map), 0, 0);
    }
    @Test
    public void testPt2(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 1);
        map.put("ptUsage", 0);
        assertEquals(calculation.calculatePT(map), 246, 0);
    }
    @Test
    public void testPt3(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 1);
        map.put("ptUsage", 1);
        assertEquals(calculation.calculatePT(map), 819, 0);
    }
    @Test
    public void testPt4(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 1);
        map.put("ptUsage", 3);
        assertEquals(calculation.calculatePT(map), 1638, 0);
    }
    @Test
    public void testPt5(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 1);
        map.put("ptUsage", 5);
        assertEquals(calculation.calculatePT(map), 3071, 0);
    }
    @Test
    public void testPt6(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 1);
        map.put("ptUsage", 10);
        assertEquals(calculation.calculatePT(map), 4095, 0);
    }
    @Test
    public void testPt7(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 3);
        map.put("ptUsage", 0);
        assertEquals(calculation.calculatePT(map), 573, 0);
    }
    @Test
    public void testPt8(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 3);
        map.put("ptUsage", 1);
        assertEquals(calculation.calculatePT(map), 1911, 0);
    }
    @Test
    public void testPt9(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 3);
        map.put("ptUsage", 3);
        assertEquals(calculation.calculatePT(map), 3822, 0);
    }
    @Test
    public void testPt10(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 3);
        map.put("ptUsage", 5);
        assertEquals(calculation.calculatePT(map), 7166, 0);
    }
    @Test
    public void testPt11(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ptTime", 3);
        map.put("ptUsage", 10);
        assertEquals(calculation.calculatePT(map), 9555, 0);
    }
    @Test
    public void testFlightInvalid1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 4);
        assertEquals(calculation.calculateFlight(map), 0, 0);
    }
    @Test
    public void testFlightInvalid2(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("lh", 4);
        assertEquals(calculation.calculateFlight(map), 0, 0);
    }
    @Test
    public void testFlight(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("lh", 1);
        map.put("sh", 1);
        assertEquals(calculation.calculatePT(map), 1050, 0);
        map.replace("sh", 3);
        assertEquals(calculation.calculatePT(map), 1425, 0);
        map.replace("sh", 6);
        assertEquals(calculation.calculatePT(map), 2025, 0);
        map.replace("sh", 10);
        assertEquals(calculation.calculatePT(map), 2625, 0);
        map.replace("lh", 3);
        assertEquals(calculation.calculatePT(map), 4000, 0);
        map.replace("lh", 6);
        assertEquals(calculation.calculatePT(map), 6200, 0);
        map.replace("sh", 10);
        assertEquals(calculation.calculatePT(map), 8400, 0);
    }
    @Test
    public void testTransportInvalid1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 4);
        assertEquals(calculation.calculateTransport(map), 0, 0);
    }
    @Test
    public void testFTransportCarOwn(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carOwn", 1);
        map.put("driveDistance", 100);
        map.put("ptTime", 0);
        map.put("ptUsage", 1);
        map.put("lh", 1);
        map.put("sh", 1);
        map.put("carType", 1);
        assertEquals(calculation.calculateTransport(map), 1320, 0);
    }
    @Test
    public void testFTransportCarNotOwn(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carOwn", 0);
        map.put("driveDistance", 100);
        map.put("ptTime", 0);
        map.put("ptUsage", 1);
        map.put("lh", 1);
        map.put("sh", 1);
        map.put("carType", 1);
        assertEquals(calculation.calculateTransport(map), 1296, 0);
    }
    @Test
    public void testFoodInvalid1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carType", 4);
        assertEquals(calculation.calculateFood(map), 0, 0);
    }
    @Test
    public void testBeef(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("beef", 0);
        assertEquals(calculation.calculateBeef(map), 0, 0);
        map.replace("beef", 1);
        assertEquals(calculation.calculateBeef(map), 1300, 0);
        map.replace("beef", 3);
        assertEquals(calculation.calculateBeef(map), 1900, 0);
        map.replace("beef", 5);
        assertEquals(calculation.calculateBeef(map), 2500, 0);
    }
    @Test
    public void testChicken(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("chicken", 0);
        assertEquals(calculation.calculateChicken(map), 0, 0);
        map.replace("chicken", 1);
        assertEquals(calculation.calculateChicken(map), 450, 0);
        map.replace("chicken", 3);
        assertEquals(calculation.calculateChicken(map), 860, 0);
        map.replace("chicken", 5);
        assertEquals(calculation.calculateChicken(map), 1450, 0);
    }
    @Test
    public void testPork(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("pork", 0);
        assertEquals(calculation.calculatePork(map), 0, 0);
        map.replace("pork", 1);
        assertEquals(calculation.calculatePork(map), 200, 0);
        map.replace("pork", 3);
        assertEquals(calculation.calculatePork(map), 600, 0);
        map.replace("pork", 5);
        assertEquals(calculation.calculatePork(map), 950, 0);
    }
    @Test
    public void testSeafood(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("seafood", 0);
        assertEquals(calculation.calculateSeafood(map), 0, 0);
        map.replace("seafood", 1);
        assertEquals(calculation.calculateSeafood(map), 150, 0);
        map.replace("seafood", 3);
        assertEquals(calculation.calculateSeafood(map), 500, 0);
        map.replace("seafood", 5);
        assertEquals(calculation.calculateSeafood(map), 800, 0);
    }
    @Test
    public void testLeftover(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("leftover", 0);
        assertEquals(calculation.calculateLeftover(map), 0, 0);
        map.replace("leftover", 1);
        assertEquals(calculation.calculateLeftover(map), 23.4, 0);
        map.replace("leftover", 3);
        assertEquals(calculation.calculateLeftover(map), 70.2, 0);
        map.replace("leftover", 5);
        assertEquals(calculation.calculateLeftover(map), 140.4, 0);
    }
}
