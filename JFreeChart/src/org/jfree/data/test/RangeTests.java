package org.jfree.data.test;

import junit.framework.TestCase;
import org.jfree.data.*;
import org.junit.Test;

public class RangeTests extends TestCase {

    public static final double  EPSILON = 0.0000001d;

    @Test
    public void testCreateRange() {
        Range r = new Range(1.0, 2.0);
        assertEquals(r, new Range(1.0, 2.0));
    }

    @Test
    public void testCreateRangeInvalid() {
        try {
            new Range(1.0, 0.0);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    public void testCreateRangeInvalidNegative() {
        try {
            new Range(1.0, -2.0);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    // Tests for getLowerBound
    // TC 1: Test getLowerBound() with positive lower bound
    @Test
    public void testGetLowerBound() {
        Range r = new Range(1.0, 2.0);
        assertEquals(1.0, r.getLowerBound(), EPSILON);
    }

    // TC 2: Test getLowerBound() with negative lower bound
    @Test
    public void testGetNegativeLowerBound() {
        Range r = new Range(-1.0, 2.0);
        assertEquals(-1.0, r.getLowerBound(), EPSILON);
    }

    // TC 3: Test getLowerBound() with negative range
    @Test
    public void testGetLowerBoundNegativeRange() {
        Range r = new Range(-2.0, -1.0);
        assertEquals(-2.0, r.getLowerBound(), EPSILON);
    }

    // TC 4: Test getLowerBound() with zero lower margin
    @Test
    public void testGetLowerBoundEqualsZero() {
        Range r = new Range(0.0, 1.0);
        assertEquals(0.0, r.getLowerBound(), EPSILON);
    }

    // TC 5 : Test getLowerBound() does not return upper bound
    @Test
    public void testGetLowerBoundDoesNotReturnUpperBound() {
        Range r = new Range(0.0, 1.0);
        assertFalse(r.getLowerBound() == 1.0);
    }

    // *********************************************************************************************
    // Tests for get upper bound
    // TC 1: Test getUpperBound() valid
    @Test
    public void testGetUpperBoundValid() {
        Range r = new Range(1.0, 2.0);
        assertEquals(2.0, r.getUpperBound(), EPSILON);
    }

    // TC 2: Test getUpperBound() with negative lower and upper margins
    @Test
    public void testGetNegativeUpperBound() {
        Range r = new Range(-3.0, -1.0);
        assertEquals(-1.0, r.getUpperBound(), EPSILON);
    }

    // TC 3: Test getUpperBound() with negative range
    @Test
    public void testGetUpperBoundEqualsZero() {
        Range r = new Range(-1.0, 0.0);
        assertEquals(0.0, r.getUpperBound(), EPSILON);
    }

    // TC 4 : Test getUpperBound() does not return lower bound
    @Test
    public void testGetUpperBoundDoesNotReturnLowerBound() {
        Range r = new Range(0.0, 1.0);
        assertFalse(r.getUpperBound() == 0.0);
    }

    //*********************************************************************************************
    // Tests for expand()
    // TC 1: Test expand() with positive lower and upper margins
    @Test
    public void testExpand() {
        Range r = new Range(1.0, 2.0);
        Range.expand(r, 0.0, 3.0);
        assertEquals(4.0, r.getLength(), EPSILON);
    }

    // TC 2: Test expand() with negative lower and upper margins
    @Test
    public void testExpandNegativeLowerMargin() {
        Range r = new Range(1.0, 2.0);
        Range.expand(r, -1.0, 3.0);
        assertEquals(4.0, r.getLength(), EPSILON);
    }

    // TC 3: Test expand() with zero lower margin
    @Test
    public void testExpandZeroLowerMargin() {
        Range r = new Range(1.0, 2.0);
        Range.expand(r, 0.0, 3.0);
        assertEquals(4.0, r.getLength(), EPSILON);
    }

    // TC 4: Test expand() with zero upper margin
    @Test
    public void testExpandZeroUpperMargin(){
        Range r = new Range(1.0, 2.0);
        Range.expand(r, 0.0, -1.0);
        assertEquals(4.0, r.getLength(), EPSILON);
    }

    // TC 5: Test expand() with zero lower and upper margins
    @Test
    public void testExpandInvalidNegativeMargins(){
        try {
            Range r = new Range(1.0, 2.0);
            Range.expand(r, -1.0, 0.0);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }

    }

    // TC 6: Test expand() with negative upper margin
    @Test
    public void testExpandNegativeUpperMargin(){
        try {
            Range r = new Range(1.0, 2.0);
            Range.expand(r, 1.0, -2.0);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    //*********************************************************************************************
    // Test for expandToInclude()
    // TC 1: Test expandToInclude() with positive lower and upper margins
    @Test
    public void testExpandToInclude() {
        Range r = new Range(1.0, 2.0);
        Range.expandToInclude(r, 0.0);
        assertEquals(3.0, r.getLength(), EPSILON);
    }

    // TC 2: Test expandToInclude() with negative lower and upper margins
    @Test
    public void testExpandToIncludeNegativeLowerMargin() {
        Range r = new Range(1.0, 2.0);
        Range.expandToInclude(r, -1.0);
        assertEquals(3.0, r.getLength(), EPSILON);
    }

    // TC 3: Test expandToInclude() with negative upper margin
    @Test
    public void testExpandToIncludeNegativeUpperMargin(){
        Range r = new Range(1.0, 2.0);
        Range.expandToInclude(r, 3.0);
        assertEquals(3.0, r.getLength(), EPSILON);
    }

    // TC 4: Test expandToInclude() with zero lower and upper margins
    @Test
    public void testExpandToIncludeNegativeLowerAndUpperMargin(){
        Range r = new Range(1.0, 2.0);
        Range.expandToInclude(r, -1.0);
        assertEquals(3.0, r.getLength(), EPSILON);
    }

    // TC 5: Test expandToInclude() with zero lower margin
    @Test
    public void testExpandToIncludeZeroLowerMargin() {
        Range r = new Range(1.0, 2.0);
        r = Range.expandToInclude(r, 0.0);
        assertEquals(2.0, r.getLength(), EPSILON);
    }

    // TC 6: Test expandToInclude() with zero upper margin
    @Test
    public void testExpandToIncludeZeroUpperMargin(){
        Range r = new Range(1.0, 2.0);
        r = Range.expandToInclude(r, 3.0);
        assertEquals(2.0, r.getLength(), EPSILON);
    }

    //*********************************************************************************************
    // Test for contains()
    // TC 1: Test contains() with positive lower and upper margins
    @Test
    public void testContains() {
        Range r = new Range(1.0, 2.0);
        assertTrue(r.contains(1.5));
    }

    // TC 2: Test contains outside of range lower bound
    @Test
    public void testContainsSmallerThanLower() {
        Range r = new Range(1.0, 2.0);
        assertFalse(r.contains(0.5));
    }

    // TC 3: Test contains outside of range upper bound
    @Test
    public void testContainsGreaterThanUpper() {
        Range r = new Range(1.0, 2.0);
        assertFalse(r.contains(3.5));
    }

    // TC 4: Test contains within negative to positive range
    @Test
    public void testContainsNegativeLowerMargin() {
        Range r = new Range(-1.0, 2.0);
        assertTrue(r.contains(-0.5));
    }

    // TC 5: Test contains outside of negative to positive range
    @Test
    public void testContainsNegativeLowerAndUpper() {
        Range r = new Range(-2.0, -1.0);
        assertTrue(r.contains(-1.5));
    }

    // TC 6: Test contains extreme lower
    @Test
    public void testContainsExtremeLower() {
        Range r = new Range(-1.0, 2.0);
        assertTrue(r.contains(-1.0));
    }

    // TC 7: Test contains extreme upper
    @Test
    public void testContainsExtremeUpper() {
        Range r = new Range(-1.0, 2.0);
        assertTrue(r.contains(2.0));
    }

    //*********************************************************************************************
    // Tests for combine()

    // TC 1: Test combine() lower range first higher range second
    @Test
    public void testCombineLowerRangeFirstHigherRangeSecond() {
        Range r1 = new Range(1.0, 2.0);
        Range r2 = new Range(3.0, 4.0);
        Range r3 = Range.combine(r1, r2);
        assertEquals(1.0, r3.getLowerBound(), EPSILON);
        assertEquals(4.0, r3.getUpperBound(), EPSILON);
    }

    // TC 2: Test combine() higher range first lower range second
    @Test
    public void testCombineHigherRangeFirstLowerRangeSecond() {
        Range r1 = new Range(3.0, 4.0);
        Range r2 = new Range(1.0, 2.0);
        Range r3 = Range.combine(r1, r2);
        assertEquals(1.0, r3.getLowerBound(), EPSILON);
        assertEquals(4.0, r3.getUpperBound(), EPSILON);
    }

    // TC 3: Test combine() negative and positive ranges
    @Test
    public void testCombineNegativeAndPositiveRanges() {
        Range r1 = new Range(-1.0, 2.0);
        Range r2 = new Range(3.0, 4.0);
        Range r3 = Range.combine(r1, r2);
        assertEquals(-1.0, r3.getLowerBound(), EPSILON);
        assertEquals(4.0, r3.getUpperBound(), EPSILON);
    }

    // TC 4: Test combine() negative and negative ranges
    @Test
    public void testCombineNegativeRanges() {
        Range r1 = new Range(-2.0, -1.0);
        Range r2 = new Range(-4.0, -3.0);
        Range r3 = Range.combine(r1, r2);
        assertEquals(-4.0, r3.getLowerBound(), EPSILON);
        assertEquals(-1.0, r3.getUpperBound(), EPSILON);
    }

    // TC 5: Test combine() second range null
    @Test
    public void testCombineNullRange() {
        Range r1 = new Range(1.0, 2.0);
        Range r3 = Range.combine(r1, null);
        assertEquals(1.0, r3.getLowerBound(), EPSILON);
        assertEquals(2.0, r3.getUpperBound(), EPSILON);
    }

    // TC 6: Test combine() first range null
    @Test
    public void testCombineNullRange2() {
        Range r1 = new Range(1.0, 2.0);
        Range r3 = Range.combine(null, r1);
        assertEquals(1.0, r3.getLowerBound(), EPSILON);
        assertEquals(2.0, r3.getUpperBound(), EPSILON);
    }

    // TC 7: Test combine() both ranges null
    @Test
    public void testCombineBothRangesNull() {
        Range r3 = Range.combine(null, null);
        assertNull(r3);
    }

}
