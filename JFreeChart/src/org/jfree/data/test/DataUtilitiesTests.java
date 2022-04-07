package org.jfree.data.test;


import junit.framework.TestCase;
import org.jfree.data.*;
import org.junit.After;
import org.junit.Test;

import java.security.InvalidParameterException;


public class DataUtilitiesTests extends TestCase {

    public static final double  EPSILON = 0.0000001d;

    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private void compareDoubleAndNumberArray2D(Number[][] numArray, double[][] data) {
        assertEquals(data.length, numArray.length);
        for (int i = 0; i < numArray.length; i++) {
            assertEquals(data[i].length, numArray[i].length);
            for (int j = 0; j < numArray[i].length; j++) {
                assertEquals(data[i][j], numArray[i][j]);
            }
        }
    }

    private void compareDoubleAndNumberArray(double[] data, Number[] numArray) {
        assertEquals(data.length, numArray.length);
        for (int i = 0; i < numArray.length; i++) {
            assertEquals(data[i], numArray[i]);
        }
    }

    //******************************************************************************************************************
    // Test Create Number Array
    // TC 1: Test Create Number Array
    @Test
    public void testCreateNumberArray() {
        double[] data = new double[] { 1.0, 2.0, 3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 2: Test Create Number Array All Negative Values
    @Test
    public void testCreateNumberArrayAllNegativeValues() {
        double[] data = new double[] { -1.0, -2.0, -3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 3
    @Test
    public void testCreateNumberArrayPositiveAndNegativeValues() {
        double[] data = new double[] { -1.0, 2.0, 3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }


    // TC 4
    @Test
    public void testCreateNumberArrayExtremeUpperBound() {
        double[] data = new double[] { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 5
    @Test
    public void testCreateNumberArrayExtremeLowerBound() {
        double[] data = new double[] { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 6
    @Test
    public void testCreateNumberArrayExtremeUpperBoundAndExtremeLowerBound() {
        double[] data = new double[] { Double.MAX_VALUE, 0.0, Double.MIN_VALUE } ;
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 7
    @Test
    public void testCreateNumberArrayOneValue() {
        double[] data = new double[] { 1.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 8
    @Test
    public void testCreateNumberArrayEmpty() {
        double[] data = new double[] {};
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 9
    @Test
    public void testCreateNumberArrayThrowsIllegalArgumentException() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    //******************************************************************************************************************
    // Test Create Number Array 2D
    // TC 1
    @Test
    public void testCreateNumberArray2D() {
        double[][] data = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 2
    @Test
    public void testCreateNumberArray2DAllNegativeValues() {
        double[][] data = new double[][] { { -1.0, -2.0, -3.0 }, { -4.0, -5.0, -6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 3
    @Test
    public void testCreateNumberArray2DPositiveAndNegativeValues(){
        double[][] data = new double[][] { { -1.0, 2.0, -3.0 }, { 4.0, -5.0, 6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 4
    @Test
    public void testCreateNumberArray2DExtremeUpperBound() {
        double[][] data = new double[][] { { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE }, { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 5
    @Test
    public void testCreateNumberArray2DExtremeLowerBound() {
        double[][] data = new double[][] { { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE }, { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 6
    @Test
    public void testCreateNumberArray2DExtremeLowerBoundAndExtremeUpperBound(){
        double[][] data = new double[][] { { Double.MIN_VALUE, 0.0, Double.MAX_VALUE }, { Double.MIN_VALUE, 0.0, Double.MAX_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 7
    @Test
    public void testCreateNumberArray2DOneValue() {
        double[][] data = new double[][] { { 1.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 8
    @Test
    public void testCreateNumberArray2DEmpty() {
        double[][] data = new double[][] { {}, {} };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 9
    @Test
    public void testCreateNumberArray2DThrowsIllegalArgumentException() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException.");
        } catch (Exception  e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    //******************************************************************************************************************
    // Calculate Column Total
    // TC 1
    @Test
    public void testCalculateColumnTotal() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal += i;
            data.addValue(i, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 2
    @Test
    public void testCalculateColumnTotalAllNegative(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal -= i;
            data.addValue(-i, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 3
    @Test
    public void testCalculateColumnTotalOneValue(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 1;
        data.addValue(1, 0, 0);
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 4
    @Test
    public void testCalculateColumnTotalPositiveAndNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal += i*5;
            data.addValue(i*5, i, 0);
        }
        for(int i = 5; i < 10; i++) {
            expectedTotal -= i;
            data.addValue(-i, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 5
    @Test
    public void testCalculateColumnTotalEqualZero(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            data.addValue(i, i, 0);
        }
        for(int i = 0; i < 5; i++) {
            data.addValue(-i, i+5, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 6
    @Test
    public void testCalculateColumnTotalExtremeLowerBound() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MIN_VALUE * 5;
        for (int i = 0; i < 5; i++) {
            data.addValue(Double.MIN_VALUE, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 7
    @Test
    public void testCalculateColumnTotalExtremeUpperBound() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MAX_VALUE * 5;
        for (int i = 0; i < 5; i++) {
            data.addValue(Double.MAX_VALUE, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }


    // TC 8
    @Test
    public void testCalculateColumnTotalFractionalValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = 0;
        for (int i = 0; i < 5; i++) {
            expectedTotal += (i / 2.0);
            data.addValue(i / 2.0, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 9
    @Test
    public void testCalculateColumnTotalFractionalNegativeValues(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = 0;
        for (int i = 0; i < 5; i++) {
            expectedTotal -= (i / 2.0);
            data.addValue(-i / 2.0, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 10
    @Test
    public void testCalculateColumnTotalPositiveAndNegativeFractionalValues(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = 0;
        for (int i = 0; i < 5; i++) {
            expectedTotal += (i / 2.0);
            data.addValue(i / 2.0, i, 0);
        }
        for (int i = 5; i < 10; i++) {
            expectedTotal -= (i / 2.0);
            data.addValue(-i / 2.0, i, 0);
        }
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 11
    @Test
    public void testCalculateColumnTotalIndexOutOfBounds() {
        DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
        defaultKeyedValues2D.addValue(1, 0, 0);
        try {
            DataUtilities.calculateColumnTotal(defaultKeyedValues2D, -1);
            fail("Exception index out of bounds expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    // TC 12
    @Test
    public void testCalculateColumnTotalNullData() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Exception NullPointerException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    //******************************************************************************************************************
    // Calculate Row Total
    // TC 1
    @Test
    public void testCalculateRowTotal() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal += i;
            data.addValue(i, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 2
    @Test
    public void testCalculateRowTotalAllNegative(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal -= i;
            data.addValue(-i, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 3
    @Test
    public void testCalculateRowTotalOneValue(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 1;
        data.addValue(1, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 4
    @Test
    public void testCalculateRowTotalPositiveAndNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal += i;
            data.addValue(i, i, 0);
        }
        for(int i = 5; i < 10; i++) {
            expectedTotal -= i;
            data.addValue(-i, i, 0);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 5
    @Test
    public void testCalculateRowTotalEqualZero(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            data.addValue(i, 0, i);
        }
        for(int i = 0; i < 5; i++) {
            data.addValue(-i, 0, i+5);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 6
    @Test
    public void testCalculateRowTotalExtremeLowerBound() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MIN_VALUE;
        data.addValue(Double.MIN_VALUE, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 7
    @Test
    public void testCalculateRowTotalExtremeUpperBound() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MAX_VALUE;
        data.addValue(Double.MAX_VALUE, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 8
    @Test
    public void testCalculateRowTotalFractionalPositiveValues(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = 0;
        for (int i = 0; i < 5; i++) {
            expectedTotal += (i / 2.0);
            data.addValue(i / 2.0, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 9
    @Test
    public void testCalculateRowTotalFractionalNegativeValues(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = 0;
        for (int i = 0; i < 5; i++) {
            expectedTotal -= (i / 2.0);
            data.addValue(-i / 2.0, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    //  TC 10
    @Test
    public void testCalculateRowTotalPositiveAndNegativeFractionalValues(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = 0;
        for (int i = 0; i < 5; i++) {
            expectedTotal += (i / 2.0);
            data.addValue(i / 2.0, 0, i);
        }
        for (int i = 5; i < 10; i++) {
            expectedTotal -= (i / 2.0);
            data.addValue(-i / 2.0, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 11
    @Test
    public void testCalculateRowTotalIndexOutOfBounds() {
        DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
        defaultKeyedValues2D.addValue(1, 0, 0);
        try {
            DataUtilities.calculateRowTotal(defaultKeyedValues2D, 1);
            fail("Exception NullPointerException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    // TC 12
    @Test
    public void testCalculateRowTotalNullData() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Exception NullPointerException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    //******************************************************************************************************************
    // Tests for getCumulativePercentages
    // TC 1
    @Test
    public void testGetCumulativePercentages() {
        DefaultKeyedValues data = new DefaultKeyedValues();

        data.addValue("0", 5);
        data.addValue("1", 9);
        data.addValue("2", 2);

        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.3125, percentages.getValue(0));
        assertEquals(0.875, percentages.getValue(1));
        assertEquals(1.0, percentages.getValue(2));
    }

    // TC 2
    @Test
    public void testGetCumulativePercentagesNegativeValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", -5);
        data.addValue("1", -9);
        data.addValue("0", -2);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.3125, percentages.getValue(0));
        assertEquals(0.875, percentages.getValue(1));
        assertEquals(1.0, percentages.getValue(2));
    }

    // TC 3
    @Test
    public void testGetCumulativePercentagesPositiveAndNegativeValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", -5);
        data.addValue("1", 9);
        data.addValue("2", -2);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.3125, percentages.getValue(0));
        assertEquals(0.875, percentages.getValue(1));
        assertEquals(1.0, percentages.getValue(2));
    }

    // TC 4
    @Test
    public void testGetCumulativePercentagesOneValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(1.0, percentages.getValue(0));
    }

    // TC 5
    @Test
    public void testGetCumulativePercentagesNegativeValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", -5);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
    }

    // TC 6
    @Test
    public void testGetCumulativePercentagesZeroValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 0);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
    }

    // TC 7
    @Test
    public void testGetCumulativePercentagesNullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Exception InvalidParameterException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    // TC 8
    @Test
    public void testGetCumulativePercentagesEmptyData() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0, percentages.getItemCount());
    }

    // TC 9
    @Test
    public void testGetCumulativePercentagesWithNullValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", null);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(Double.NaN, percentages.getValue(0));
    }
}
