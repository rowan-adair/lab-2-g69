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

	//**************************************************************************************************************
	// Tests for contains
	// TC 1: Test contains() with positive lower and upper margins
	@Test
	public void testContainsReturnsTrueWithValueInRange() {
		Range r = new Range(1.0, 2.0);
		assertTrue(r.contains(1.5));
	}

	// TC 2: Test contains outside of range lower bound
	@Test
	public void testContainsReturnsFalseWithValueSmallerThanLowerBound() {
		Range r = new Range(1.0, 2.0);
		assertFalse(r.contains(0.5));
	}

	// TC 3: Test contains outside of range upper bound
	@Test
	public void testContainsReturnsFalseWithValueGreaterThanUpperBound() {
		Range r = new Range(1.0, 2.0);
		assertFalse(r.contains(3.5));
	}

	// TC 4: Test contains returns true with value equal to lower bound
	@Test
	public void testContainsReturnsTrueWithValueEqualToLowerBound() {
		Range r = new Range(1.0, 2.0);
		assertTrue(r.contains(1.0));
	}

	// TC 5: Test contains returns true with value equal to upper bound
	@Test
	public void testContainsReturnsTrueWithValueEqualToUpperBound() {
		Range r = new Range(1.0, 2.0);
		assertTrue(r.contains(2.0));
	}

	// TC 6: Test contains within negative to positive range
	@Test
	public void testContainsReturnsTrueWithValueInNegativeRange() {
		Range r = new Range(-1.0, 2.0);
		assertTrue(r.contains(-0.5));
	}

	// TC 7: Test contains returns false with value smaller than lower bound in negative range
	@Test
	public void testContainsReturnsFalseWithValueSmallerThanLowerBoundInNegativeRange() {
		Range r = new Range(-2.0, -1.0);
		assertFalse(r.contains(-2.5));
	}

	// TC 8: Test contains returns false with value greater than upper bound in negative range
	@Test
	public void testContainsReturnsFalseWithValueGreaterThanUpperBoundInNegativeRange() {
		Range r = new Range(-2.0, -1.0);
		assertFalse(r.contains(0.0));
	}

	// TC 9: Test contains returns true with value equal to lower bound
	@Test
	public void testContainsReturnsTrueWithValueEqualToLowerBoundInNegativeRange() {
		Range r = new Range(-2.0, -1.0);
		assertTrue(r.contains(-2.0));
	}

	// TC 10: Test contains returns true with value equal to upper bound
	@Test
	public void testContainsReturnsTrueWithValueEqualToUpperBoundInNegativeRange() {
		Range r = new Range(-2.0, -1.0);
		assertTrue(r.contains(-1.0));
	}
	// TC 11: Test contains returns false with NaN value
	@Test
	public void testContainsReturnsFalseWithNaNValue() {
		Range r = new Range(-1.0, 1.0);
		assertFalse(r.contains(Double.NaN));
	}

	// TC 12: Test contains returns true for maximum upper bound
	@Test
	public void testContainsReturnsTrueForMaxValueUpperBound() {
		Range r = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
		assertTrue(r.contains(Double.MAX_VALUE));
	}

	// TC 13: Test contains returns true for minimum lower bound
	@Test
	public void testContainsReturnsTrueForMinValueLowerBound() {
		Range r = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
		assertTrue(r.contains(Double.MIN_VALUE));
	}

	// TC 14: Test contains returns true for maximum upper bound plus one
	@Test
	public void testContainsReturnsTrueForMaxDoublePlusOne() {
		Range r = new Range(0.0, Double.MAX_VALUE);
		assertTrue(r.contains(Double.MAX_VALUE + 1));
	}

	// TC 15: Test contains returns true for minimum lower bound minus one
	@Test
	public void testContainsReturnsTrueForMinDoubleMinusOne() {
		Range r = new Range(Double.MIN_VALUE, 0.0);
		assertTrue(r.contains(Double.MIN_VALUE - 1));
	}

	// TC 16: Test contains does not compile with null
	/**
	@Test
	public void testContainsReturnsFalseWithNullValue() {
		Range r = new Range(-1.0, 1.0);
		assertFalse(r.contains(null));
	}
	**/

	//**************************************************************************************************************
	// Tests for expand()

	// TC 1: Test expand with positive lower and upper margins
	@Test
	public void testExpandReturnsSucceedsWithPositiveUpperMarginAndPositiveLowerMargin() {
		Range r = new Range(1.0, 10.0);
		Range r2 = Range.expand(r, 0.5, 0.5);
		assertEquals(r2.getUpperBound(), 14.5, 0.0);
		assertEquals(r2.getLowerBound(), -3.5, 0.0);
	}

	// TC 2: Test expand with negative lower and positive upper margins
	@Test
	public void testExpandReturnsSucceedsWithNegativeLowerMarginAndPositiveUpperMargin() {
		Range r = new Range(1.0, 10.0);
		Range r2 = Range.expand(r, -0.5, 0.5);
		assertEquals(r2.getUpperBound(), 14.5, 0.0);
		assertEquals(r2.getLowerBound(), 5.5, 0.0);
	}

	// TC 3: Test expand with positive lower and negative upper margins
	@Test
	public void testExpandReturnsSucceedsWithPositiveLowerMarginAndNegativeUpperMargin() {
		Range r = new Range(1.0, 10.0);
		Range r2 = Range.expand(r, 0.5, -0.5);
		assertEquals(r2.getUpperBound(), 5.5, 0.0);
		assertEquals(r2.getLowerBound(), -3.5, 0.0);
	}

	// TC 4: Test expand with negative lower and negative upper margins
	@Test
	public void testExpandReturnsSucceedsWithNegativeLowerMarginAndNegativeUpperMargin() {
		Range r = new Range(1.0, 10.0);
		Range r2 = Range.expand(r, -0.5, -0.5);
		assertEquals(r2.getUpperBound(), 5.5, 0.0);
		assertEquals(r2.getLowerBound(), 5.5, 0.0);
	}

	// TC 5: Test expand zero value lower margin and positive upper margin
	@Test
	public void testExpandReturnsSucceedsWithZeroLowerMarginAndPositiveUpperMargin() {
		Range r = new Range(1.0, 10.0);
		Range r2 = Range.expand(r, 0.0, 0.5);
		assertEquals(r2.getUpperBound(), 1.0, 0.0);
		assertEquals(r2.getLowerBound(), 14.5, 0.0);
	}

	// TC 6: Test expands positive lower margin and zero upper margin
	@Test
	public void testExpandReturnsSucceedsWithPositiveLowerMarginAndZeroUpperMargin() {
		Range r = new Range(1.0, 10.0);
		Range r2 = Range.expand(r, 0.5, 0.0);
		assertEquals(r2.getUpperBound(), 10.0, 0.0);
		assertEquals(r2.getLowerBound(), -3.5, 0.0);
	}

	// TC 10: Test expand with invalid range
	@Test
	public void testExpandReturnsThrowsIllegalArgumentException() {
		try {
			Range r = new Range(10.0, 1.0);
			Range.expand(r, 0.5, 0.5);
		} catch(Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}

	// TC 11: Test expand() with negative upper margin
	@Test
	public void testExpandThrowsIllegalArgumentExceptionWithNullRange(){
		try {
			Range.expand(null, 0.5, 0.5);
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
	//assertEquals("Did not return expected output", 5.5, B5.getCentralValue());
	//  }

	//**************************************************************************************************************
	// Tests for constrain()
	//TC1
	@Test
	public void testConstrainWithinRange() {
		Range C1 = new Range(1, 10);
		assertEquals("constrain: Did not return expected output", 9.0, C1.constrain(9));
	}
	//TC2
	@Test
	public void testConstrainOutsideRange() {
		Range C2 = new Range(1, 10);
		assertEquals("constrain: Did not return expected output", 10.0, C2.constrain(11));
	}
	//TC3
	@Test
	public void testConstrainOutsideRangeNegative() {
		Range C3 = new Range(1, 10);
		assertEquals("constrain: Did not return expected output", 1, C3.constrain(-10));
	}
	//TC4
	@Test
	public void testConstrainNegativeWithinRange() {
		Range C4 = new Range(-10, -1);
		assertEquals("constrain: Did not return expected output", -9.0, C4.constrain(-9));
	}
	
	
	//TC 5
	@Test
	public void testConstrainDecimal() {
		Range C5 = new Range(1, 10);
		assertEquals("constrain: Did not return expected output", 10.0, C5.constrain(12.3));
	}
	//6
	@Test
	public void testConstrainsReturnsFalseWithNaNValue() {
		Range r = new Range(-1.0, 1.0);
		assertEquals("constrain: Did not return expected output",1.0, r.constrain(Double.NaN));
	}
	//TC8
	@Test
	public void testConstrainsReturnsMaxValueForMaxValueUpperBound() {
		Range r = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
		assertEquals("constrain: Did not return expected output",Double.MAX_VALUE, r.constrain(Double.MAX_VALUE));
	}
	//TC9
	@Test
	public void testConstrainsReturnsMinValueForMinValueLowerBound() {
		Range r = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
		assertEquals("constrain: Did not return expected output",Double.MIN_VALUE, r.constrain(Double.MIN_VALUE));
	}

	//TC10
	@Test
	public void testConstrainsReturnsMaxDoubleForMaxDoublePlusOne() {
		Range r = new Range(0.0, Double.MAX_VALUE);
		assertEquals("constrain: Did not return expected output",Double.MAX_VALUE, r.constrain(Double.MAX_VALUE+1));
	}

	// TC11
	@Test
	public void testConstrainsReturnsMinDoubleForMinDoubleMinusOne() {
		Range r = new Range(Double.MIN_VALUE, 0.0);
		assertEquals("constrain: Did not return expected output",Double.MIN_VALUE, r.constrain(Double.MIN_VALUE-1));
	}


	//**************************************************************************************************************
	// Tests for shift()
	//TC1
	@Test
	public void testShiftby1() {
		Range D1 = new Range(0, 10);
		Range d1 = new Range(1, 11);
		assertEquals("shift: Did not return expected output", d1, Range.shift(D1, 1));
	}
	//TC2
	@Test
	public void testShiftByMoreThan1() {
		Range D2 = new Range(0, 10);
		Range d2 = new Range(10, 20);
		assertEquals("shift: Did not return expected output", d2, Range.shift(D2, 10));
	}
	//TC3
	@Test
	public void testShiftByNegative() {
		Range D3 = new Range(0, 10);
		Range d3 = new Range(-1, 9);
		assertEquals("shift: Did not return expected output", d3, Range.shift(D3, -1));
	}
	//TC4
	@Test
	public void testShiftByDecimal() {
		Range D4 = new Range(0, 10);
		Range d4 = new Range(0.5, 10.5);
		assertEquals("shift: Did not return expected output", d4, Range.shift(D4, 0.5));
	}
	//TC5
	@Test
	public void testShiftByLargeNegativeDecimal() {
		Range D5 = new Range(0, 10);
		Range d5 = new Range(-100, -90);
		assertEquals("shift: Did not return expected output", d5, Range.shift(D5, -100));
	}



}

