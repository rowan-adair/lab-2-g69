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
    public void testCreateNumberArraySucceedsWithValidDoubleArrayAllPositive() {
        double[] data = new double[] { 1.0, 2.0, 3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 2: Test Create Number Array All Negative Values
    @Test
    public void testCreateNumberArraySucceedsWithValidDoubleArrayAllNegative() {
        double[] data = new double[] { -1.0, -2.0, -3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 3
    @Test
    public void testCreateNumberArraySucceedsWithValidDoubleArrayWithNegativeAndPositiveValues() {
        double[] data = new double[] { -1.0, 2.0, 3.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }


    // TC 4
    @Test
    public void testCreateNumberArraySucceedsWithValidDoubleArrayExtremeMaxDoubleValues() {
        double[] data = new double[] { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 5
    @Test
    public void testCreateNumberArraySucceedsWithValidDoubleArrayExtremeMinDoubleValues() {
        double[] data = new double[] { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 6
    @Test
    public void testCreateNumberArraySucceedsWithValidDoubleArrayExtremeMinAndExtremeMaxDoubleValues() {
        double[] data = new double[] { Double.MAX_VALUE, 0.0, Double.MIN_VALUE } ;
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 7
    @Test
    public void testCreateNumberArraySucceedsWithValidDoubleArraySingleValue() {
        double[] data = new double[] { 1.0 };
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 8
    @Test
    public void testCreateNumberArraySucceedsWithValidEmptyDoubleArray() {
        double[] data = new double[] {};
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }

    // TC 9
    @Test
    public void testCreateNumberArrayThrowsIllegalArgumentExceptionWhenInputIsNull() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException.");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    /**
    // TC 10
    @Test
    public void testCreateNumberArrayDoesNotCompileWithNullValueDoubleArray() {
        double[] data = new double[] {null};
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }
    */

    /**
    // TC 11
    @Test
    public void testCreateNumberArrayDoesNotCompileWithNullAndValidDoubleArray() {
        double[] data = new double[] {null, 1.0};
        Number[] numArray = DataUtilities.createNumberArray(data);
        compareDoubleAndNumberArray(data, numArray);
    }
    */

    //******************************************************************************************************************
    // Test Create Number Array 2D
    // TC 1
    @Test
    public void testCreateNumberArray2DSucceedsWithValidDoubleArrayAllPositive() {
        double[][] data = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 2
    @Test
    public void testCreateNumberArray2DAllNegativeValuesSucceedsWithValidDoubleArrayAllNegative() {
        double[][] data = new double[][] { { -1.0, -2.0, -3.0 }, { -4.0, -5.0, -6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 3
    @Test
    public void testCreateNumberArray2DSucceedsWithValidDoubleArrayWithNegativeAndPositiveValues(){
        double[][] data = new double[][] { { -1.0, 2.0, -3.0 }, { 4.0, -5.0, 6.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 4
    @Test
    public void testCreateNumberArray2DSucceedsWithValidDoubleArrayExtremeMaxDoubleValues() {
        double[][] data = new double[][] { { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE }, { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 5
    @Test
    public void testCreateNumberArray2DSucceedsWithValidDoubleArrayExtremeMinDoubleValues() {
        double[][] data = new double[][] { { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE }, { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 6
    @Test
    public void testCreateNumberArray2DSucceedsWithValidDoubleArrayExtremeMinAndExtremeMaxDoubleValues(){
        double[][] data = new double[][] { { Double.MIN_VALUE, 0.0, Double.MAX_VALUE }, { Double.MIN_VALUE, 0.0, Double.MAX_VALUE } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 7
    @Test
    public void testCreateNumberArray2DSucceedsWithValidDoubleArraySingleValue() {
        double[][] data = new double[][] { { 1.0 } };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    /**
    // TC 8
    @Test
    public void testCreateNumberArray2DSucceedsWithDoubleArrayWithJustNullValues() {
        double[][] data = new double[][] { {null };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }
    */

    /**
     // TC 9
     @Test
     public void testCreateNumberArray2DSucceedsWithDoubleArrayWithNullValues() {
     double[][] data = new double[][] { {null, 1.0} };
     Number[][] numArray = DataUtilities.createNumberArray2D(data);
     compareDoubleAndNumberArray2D(numArray, data);
     }
     */

    // TC 10
    @Test
    public void testCreateNumberArray2DSucceedsWithValidEmpty2DDoubleArray() {
        double[][] data = new double[][] { {}, {} };
        Number[][] numArray = DataUtilities.createNumberArray2D(data);
        compareDoubleAndNumberArray2D(numArray, data);
    }

    // TC 11
    @Test
    public void testCreateNumberArray2DThrowsIllegalArgumentExceptionWhenInputIsNull() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException.");
        } catch (Exception  e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    //******************************************************************************************************************
    // Calculate Column Total
    // TC 1
    @Test
    public void testCalculateColumnTotalSucceedsWithValidPositiveValues() {
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
    public void testCalculateColumnTotalSucceedsWithValidNegativeValues(){
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
    public void testCalculateColumnTotalSucceedsWithOneValue(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 1;
        data.addValue(1, 0, 0);
        double total = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 4
    @Test
    public void testCalculateColumnTotalSucceedsWithPositiveAndNegativeValues() {
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
    public void testCalculateColumnTotalSucceedsWithValuesTotallingToZero(){
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
    public void testCalculateColumnTotalSucceedsWithExtremeLowerValues() {
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
    public void testCalculateColumnTotalSucceedsWithExtremeUpperValues() {
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
    public void testCalculateColumnTotalSucceedsWithFractionalValues() {
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
    public void testCalculateColumnTotalSucceedsWithFractionalNegativeValues(){
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
    public void testCalculateColumnTotalSucceedsWithPositiveAndNegativeFractionalValues(){
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

    // TC 11: Test calculateColumnTotal with null data throws invalid parameter exception
    @Test
    public void testCalculateColumnTotalThrowsIllegalArgumentExceptionWhenDataIsNull() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Exception IllegalArgumentException expected");
        } catch(Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
    /**
    // TC 12: Test calculateColumnTotal with null column throws invalid parameter exception
    @Test
    public void testCalculateColumnTotalThrowsIllegalArgumentExceptionWhenColumnIsNull() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        try {
            DataUtilities.calculateColumnTotal(data, null);
            fail("Exception IllegalArgumentException expected");
        } catch(Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
    */

    // TC 13: Test calculateColumnTotal with invalid column throws invalid parameter exception
    @Test
    public void testCalculateColumnTotalThrowsIllegalArgumentExceptionWhenColumnIsInvalid() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        for (int i = 0; i < 5; i++) {
            data.addValue(i, i, 0);
        }
        try {
            DataUtilities.calculateColumnTotal(data, -1);
            fail("Exception IllegalArgumentException expected");
        } catch(Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    // TC 14: Test calculateColumnTotal with invalid column throws invalid parameter exception
    @Test
    public void testCalculateColumnTotalThrowsIllegalArgumentExceptionWhenColumnIsTooLarge() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        for (int i = 0; i < 5; i++) {
            data.addValue(i, i, 0);
        }
        try {
            DataUtilities.calculateColumnTotal(data, 10);
            fail("Exception IndexOutOfBoundsException expected");
        } catch(Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    // TC 15: Test calculateColumnTotal with invalid out-of-bounds column throws invalid parameter exception
    @Test
    public void testCalculateColumnTotalThrowsInvalidParameterExceptionWhenColumnIsTooSmall() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        try {
            DataUtilities.calculateColumnTotal(data, -1);
            fail("Exception IndexOutOfBoundsException expected");
        } catch(Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    //******************************************************************************************************************
    // Calculate Row Total
    // TC 1
    @Test
    public void testCalculateRowTotalSucceedsWithValidPositiveValues() {
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
    public void testCalculateRowTotalSucceedsWithValidNegativeValues(){
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
    public void testCalculateRowTotalSucceedsWithOneValue(){
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 1;
        data.addValue(1, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 4
    @Test
    public void testCalculateRowTotalSucceedsWithPositiveAndNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        int expectedTotal = 0;
        for(int i = 0; i < 5; i++) {
            expectedTotal += i;
            data.addValue(i, 0, i);
        }
        for(int i = 5; i < 10; i++) {
            expectedTotal -= i;
            data.addValue(-i, 0,i);
        }
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 5
    @Test
    public void testCalculateRowTotalSucceedsWithValuesTotallingToZero(){
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
    public void testCalculateRowTotalSucceedsWithExtremeLowerValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MIN_VALUE;
        data.addValue(Double.MIN_VALUE, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 7
    @Test
    public void testCalculateRowTotalSucceedsWithExtremeUpperValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double expectedTotal = Double.MAX_VALUE;
        data.addValue(Double.MAX_VALUE, 0, 0);
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(expectedTotal, total, EPSILON);
    }

    // TC 8
    @Test
    public void testCalculateRowTotalSucceedsWithFractionalValues(){
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
    public void testCalculateRowTotalSucceedsWithFractionalNegativeValues(){
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
    public void testCalculateRowTotalSucceedsWithPositiveAndNegativeFractionalValues(){
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
    public void testCalculateRowTotalThrowsInvalidParameterExceptionWhenRowIndexIsOutOfBounds() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        double total = DataUtilities.calculateRowTotal(data, 0);
        assertEquals(0.0, total, EPSILON);
    }

    // TC 12: Test calculate row total succeeds with null values in row.
    @Test
    public void testCalculateRowTotalSucceedsWithNullValuesInRow() {
        DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
        defaultKeyedValues2D.addValue(1.0, 0, 0);
        defaultKeyedValues2D.addValue(null, 0, 1);
        double total = DataUtilities.calculateRowTotal(defaultKeyedValues2D, 0);
        assertEquals(1.0, total, EPSILON);
    }

    // TC 13: Test calculate row total succeeds with just null values in row.
    @Test
    public void testCalculateRowTotalSucceedsWithJustNullValuesInRow() {
        DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
        defaultKeyedValues2D.addValue(null, 0, 0);
        double total = DataUtilities.calculateRowTotal(defaultKeyedValues2D, 0);
        assertEquals(0.0, total, EPSILON);
    }

    // TC 14
    @Test
    public void testCalculateRowTotalThrowsInvalidParameterExceptionWhenRowIndexIsNegative() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, 0, 0);
        try {
            double total = DataUtilities.calculateRowTotal(data, -1);

            fail("Exception NullPointerException expected");
        } catch(Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }

    // TC 15
    @Test
    public void testCalculateRowTotalThrowsInvalidParameterDataIsNull() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Exception InvalidParameterException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    //******************************************************************************************************************
    // Tests for getCumulativePercentages
    // TC 1
    @Test
    public void testGetCumulativePercentagesSucceedsWithAllPositiveValues() {
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
    public void testGetCumulativePercentagesSucceedsWithSingleValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 5);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(1.0, percentages.getValue(0));
    }

    // TC 5
    @Test
    public void testGetCumulativePercentagesNegativeValueSucceedsWithSingleNegativeValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", -5);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
    }

    // TC 6
    @Test
    public void testGetCumulativePercentagesSucceedsWithSingleZeroValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", 0);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.0, percentages.getValue(0));
    }

    // TC 7
    @Test
    public void testGetCumulativePercentagesThrowsInvalidParameterExceptionWithNullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Exception InvalidParameterException expected");
        } catch(Exception e) {
            assertEquals(InvalidParameterException.class, e.getClass());
        }
    }

    // TC 8
    @Test
    public void testGetCumulativePercentagesSucceedsWithEmptyData() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(0, percentages.getItemCount());
    }

    // TC 9
    @Test
    public void testGetCumulativePercentagesReturnsNaNWithNullSingleValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("0", null);
        KeyedValues percentages = DataUtilities.getCumulativePercentages(data);
        assertEquals(Double.NaN, percentages.getValue(0));
    }
}
