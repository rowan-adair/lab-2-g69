package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
	private static final double EPSILON = 0.000000001d;

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//**************************************************************************************************************
	// Tests for contains
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

	//**************************************************************************************************************
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

	//**************************************************************************************************************
	// Tests for getCentral()
	// TC 1: Test getCentral() with positive lower and upper margins
	@Test
	public void testGetCentral1() {
		Range B1 = new Range(1, 10);
		assertEquals("getCentral() Did not return expected output", 5.5, B1.getCentralValue());
	}

	// TC 2: Test getCentral() with negative lower and positive upper margins
	@Test
	public void testGetCentralNegativeLowerBound() {
		Range B2 = new Range(-10, 10);
		assertEquals("getCentral(): Did not return expected output", 0.0, B2.getCentralValue());
	}

	// TC 3: Test getCentral() with negative lower and negative upper margins
	@Test
	public void testGetCentralAllNegative() {
		Range B3 = new Range(-10, -1);
		assertEquals("getCentral(): Did not return expected output", -5.5, B3.getCentralValue());
	}

	// TC 4: Test getCentral() no range
	@Test
	public void testGetCentralNoRange() {
		Range B4 = new Range(10, 10);
		assertEquals("getCentral(): Did not return expected output", 10.0, B4.getCentralValue());
	}
	//@Test
	//public void testGetCentral5() {
	//	Range B5 = new Range(1,10);
	//assertEquals("contains: Did not return expected output", 5.5, B5.getCentralValue());
	//  }

	//**************************************************************************************************************
	// Tests for constrain()
	@Test
	public void testConstrainWithinRange() {
		Range C1 = new Range(1, 10);
		assertEquals("contains: Did not return expected output", 9.0, C1.constrain(9));
	}
	@Test
	public void testConstrainOutsideRange() {
		Range C2 = new Range(1, 10);
		assertEquals("contains: Did not return expected output", 10.0, C2.constrain(11));
	}
	@Test
	public void testConstrainOutsideRangeNegative() {
		Range C3 = new Range(1, 10);
		assertEquals("contains: Did not return expected output", 1, C3.constrain(-10));
	}
	@Test
	public void testConstrainNegativeWithinRange() {
		Range C4 = new Range(-10, -1);
		assertEquals("contains: Did not return expected output", -9.0, C4.constrain(-9));
	}
	@Test
	public void testConstrainDecimal() {
		Range C5 = new Range(1, 10);
		assertEquals("contains: Did not return expected output", 10.0, C5.constrain(12.3));
	}

	//**************************************************************************************************************
	// Tests for shift()
	@Test
	public void testShiftby1() {
		Range D1 = new Range(0, 10);
		Range d1 = new Range(1, 11);
		assertEquals("contains: Did not return expected output", d1, Range.shift(D1, 1));
	}

	@Test
	public void testShiftByMoreThan1() {
		Range D2 = new Range(0, 10);
		Range d2 = new Range(10, 20);
		assertEquals("contains: Did not return expected output", d2, Range.shift(D2, 10));
	}

	@Test
	public void testShiftByNegative() {
		Range D3 = new Range(0, 10);
		Range d3 = new Range(-1, 9);
		assertEquals("contains: Did not return expected output", d3, Range.shift(D3, -1));
	}

	@Test
	public void testShiftByDecimal() {
		Range D4 = new Range(0, 10);
		Range d4 = new Range(0.5, 10.5);
		assertEquals("contains: Did not return expected output", d4, Range.shift(D4, 0.5));
	}

	@Test
	public void testShiftByLargeNegDecimal() {
		Range D5 = new Range(0, 10);
		Range d5 = new Range(-100, -90);
		assertEquals("contains: Did not return expected output", d5, Range.shift(D5, -100));
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

	@Test
	public void testExpandToIncludeNullRangeCreatesNewRange() {
		Range r = Range.expandToInclude(null,10.0);
		assertEquals(r, new Range(10.0, 10.0));
	}

	// *********************************************************************************************
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

	//**************************************************************************************************************
	// Tests for equals()
	// TC 1: Test equals() with equal ranges
	@Test
	public void testEquals() {
		Range E1 = new Range(0, 10);
		Range e1 = new Range(0, 10);
		assertTrue("equals: Did not return expected output", E1.equals(e1));
	}

	// TC 2: Test equals() with different lower
	@Test
	public void testEqualsDifferentLower() {
		Range E2 = new Range(0, 10);
		Range e2 = new Range(1, 10);
		assertFalse("equals: Did not return expected output", E2.equals(e2));
	}

	// TC 3: Test equals() with different upper
	@Test
	public void testEqualsDifferentUpper() {
		Range E3 = new Range(0, 10);
		Range e3 = new Range(0, 11);
		assertFalse("equals: Did not return expected output", E3.equals(e3));
	}

	// TC 4: Test equals() with different lower and upper
	@Test
	public void testEqualsDifferentLowerAndUpper() {
		Range E4 = new Range(0, 10);
		Range e4 = new Range(1, 11);
		assertFalse("equals: Did not return expected output", E4.equals(e4));
	}

	// TC 5: Test equals() with negative ranges
	@Test
	public void testEqualsNegativeRange() {
		Range E5 = new Range(-10, -1);
		Range e5 = new Range(-10, -1);
		assertTrue("equals: Did not return expected output", E5.equals(e5));
	}

	// TC 6: Test equals() with lower negative ranges
	@Test
	public void testEqualsNegativeRangeDifferentLower() {
		Range E6 = new Range(-10, -1);
		Range e6 = new Range(-11, -1);
		assertFalse("equals: Did not return expected output", E6.equals(e6));
	}

	// TC 7: Test equals() with different upper negative ranges
	@Test
	public void testEqualsNegativeRangeDifferentUpper() {
		Range E7 = new Range(-10, -1);
		Range e7 = new Range(-10, -2);
		assertFalse("equals: Did not return expected output", E7.equals(e7));
	}

	// TC 8: Test equals() with different lower and upper negative ranges
	@Test
	public void testEqualsNegativeRangeDifferentLowerAndUpper() {
		Range E8 = new Range(-10, -1);
		Range e8 = new Range(-11, -2);
		assertFalse("equals: Did not return expected output", E8.equals(e8));
	}

	// TC 9: Test equals() with different class
	@Test
	public void testEqualsDifferentClass() {
		Range E5 = new Range(0, 10);
		String e5 = "Range";
		assertFalse("equals: Did not return expected output", E5.equals(e5));
	}

	// TC 10: Test equals() with null
	@Test
	public void testEqualsNull() {
		Range E6 = new Range(0, 10);
		assertFalse("equals: Did not return expected output", E6.equals(null));
	}

}

