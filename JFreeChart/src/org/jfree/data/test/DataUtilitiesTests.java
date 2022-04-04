package org.jfree.data.test;


import org.jfree.data.*;
import org.junit.Test;


import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class DataUtilitiesTests {
    public static final double  EPSILON = 0.0000001d;

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

    // Test Create Number Array
    @Test
    public void testCreateNumberArray() {
        double[] data = new double[] { 1.0, 2.0, 3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    @Test
    public void createNumberArrayAllNegativeValues() {
        double[] data = new double[] { -1.0, -2.0, -3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    @Test
    public void testCreateNumberArrayExtremeUpperBound() {
        double[] data = new double[] { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    public void createNumberArrayExtremeLowerBound() {
        double[] data = new double[] { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    @Test
    public void testCreateNumberArrayOneValue() {
        double[] data = new double[] { 1.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    @Test
    public void testCreateNumberArrayEmpty() {
        double[] data = new double[] {};
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }


    @Test
    public void testCreateNumberArrayThrowsIllegalArgumentException() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // Test Create Number Array 2D
    @Test
    public void testCreateNumberArray2D() {
        double[][] data = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    @Test
    public void testCreateNumberArray2DAllNegativeValues() {
        double[][] data = new double[][] { { -1.0, -2.0, -3.0 }, { -4.0, -5.0, -6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    @Test
    public void testCreateNumberArray2DExtremeUpperBound() {
        double[][] data = new double[][] { { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE }, { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    @Test
    public void testCreateNumberArray2DExtremeLowerBound() {
        double[][] data = new double[][] { { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE }, { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    @Test
    public void testCreateNumberArray2DOneValue() {
        double[][] data = new double[][] { { 1.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    @Test
    public void testCreateNumberArray2DEmpty() {
        double[][] data = new double[][] { {}, {} };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    @Test
    public void testCreateNumberArray2DThrowsIllegalArgumentException() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // Calculate Column Total
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

    @Test
    public void testCalculateColumnTotalOneValue(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 1;
        data.addValue(1, 0, 0);
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

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

    @Test
    public void testCalculateColumnTotalIndexOutOfBounds() {
        DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
        defaultKeyedValues2D.addValue(1, 0, 0);
        try {
            DataUtilities.calculateColumnTotal(defaultKeyedValues2D, -1);
            fail("Exception index out of bounds expected");
        } catch(Exception e) {
            assertEquals(e.getClass(), IndexOutOfBoundsException.class);
        }
    }

    // Calculate Row Total
    @Test
    public void testCalculateRowTotal() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal += i;
            data.addValue(i, i, 0);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

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

    @Test
    public void testCalculateRowTotalOneValue(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 1;
        data.addValue(1, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

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

    @Test
    public void testCalculateRowTotalExtremeLowerBound() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MIN_VALUE * 5;
        for (int i = 0; i < 5; i++) {
            data.addValue(Double.MIN_VALUE, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    @Test
    public void testCalculateRowTotalExtremeUpperBound() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MAX_VALUE * 5;
        for (int i = 0; i < 5; i++) {
            data.addValue(Double.MAX_VALUE, 0, i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

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

    @Test
    public void testCalculateRowTotalFractionalValues(){
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

    @Test
    public void testCalculateRowTotalIndexOutOfBounds() {
        DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
        defaultKeyedValues2D.addValue(1, 0, 0);
        try {
            DataUtilities.calculateRowTotal(defaultKeyedValues2D, 1);
            fail("Exception NullPointerException expected");
        } catch(Exception e) {
            assertEquals(e.getClass(), IndexOutOfBoundsException.class);
        }
    }

    @Test
    public void testCalculateRowTotalNullData() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Exception NullPointerException expected");
        } catch(Exception e) {
            assertEquals(e.getClass(), NullPointerException.class);
        }
    }

    @Test
    public void testGetCumulativePercentages() {
        DefaultKeyedValues data = new DefaultKeyedValues();

        data.addValue((Comparable) 0, 5);
        data.addValue((Comparable) 1, 9);
        data.addValue((Comparable) 2, 2);

        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.3125, percentages.getValue(0));
        assertEquals(0.875, percentages.getValue(1));
        assertEquals(1.0, percentages.getValue(2));
    }

    @Test
    public void testGetCumulativePercentagesNullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Exception InvalidParameterException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    @Test
    public void testGetCumulativePercentagesEmptyData() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0, percentages.getItemCount());
    }

    @Test
    public void testGetCumulativePercentagesOneValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, 5);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(1.0, percentages.getValue(0));
    }

    @Test
    public void testGetCumulativePercentagesZeroValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, 0);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
    }

    @Test
    public void testGetCumulativePercentagesNegativeValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, -5);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
    }

    @Test
    public void testGetCumulativePercentagesNegativeValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, -5);
        data.addValue((Comparable) 1, -9);
        data.addValue((Comparable) 2, -2);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
        assertEquals(0.0, percentages.getValue(1));
        assertEquals(0.0, percentages.getValue(2));
    }

    @Test
    public void testGetCumulativePercentagesPositiveAndNegativeValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, -5);
        data.addValue((Comparable) 1, 9);
        data.addValue((Comparable) 2, -2);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
        assertEquals(0.5, percentages.getValue(1));
        assertEquals(0.0, percentages.getValue(2));
    }

    @Test
    public void testGetCumulativePercentagesWithNullValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, null);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(Double.NaN, percentages.getValue(0));
    }

    @Test
    public void testGetCumulativePercentagesWithNullKeyThrowsIllegalArgumentException() {
        try {
            DefaultKeyedValues data = new DefaultKeyedValues();
            data.addValue(null, 9);
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    @Test
    public void testGetCumulativePercentagesWithNullKeyAndValueInvalidParameterException() {
        try {
            DefaultKeyedValues data = new DefaultKeyedValues();
            data.addValue(null, null);
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }
}
