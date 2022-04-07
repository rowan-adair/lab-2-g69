package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
	private static final double EPSILON = 0.000000001d;

	private Range rangeObjectUnderTest;

	@Before
	protected void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0",
				0, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
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
}
	
