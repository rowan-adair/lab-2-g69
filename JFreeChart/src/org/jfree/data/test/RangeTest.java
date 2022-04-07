package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
	private Range rangeObjectUnderTest; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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
	
	public void testGetLength() {
	    Range r1 = new Range(2, 2);
	    assertEquals("getLength: Did not return the expected output", 0.0, r1.getLength());
	     
	    Range r2 = new Range(4, 9);
	    assertEquals("getLength: Did not return the expected output", 5.0, r2.getLength());
	   
	    Range r3 = new Range(-99, -99);
	    assertEquals("getLength: Did not return the expected output", 0.0, r3.getLength());
	   
	    Range r4 = new Range(-11, -4);
	    assertEquals("getLength: Did not return the expected output", 7.0, r4.getLength());
	   
	    Range r5 = new Range(-5, 3);
	    assertEquals("getLength: Did not return the expected output", 8.0, r5.getLength());
}
	//my own tests
	
	@Test
	public void testContainsint() {
		Range R1 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R1.contains(3));
	}
	@Test
	public void testContainsnegative() {
		Range R2 = new Range(-10,10);
		assertEquals("contains: Did not return expected output", true, R2.contains(-9));
	}
	@Test
	public void testContainsLowerBound() {
		
		Range R3 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R3.contains(1));	
	}
	@Test
	public void testContainsDecimal() {
		Range R4 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R4.contains(2.5));
		}
	@Test	
	public void testContainsUpperBound() {
		Range R5 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R5.contains(10));
	}
	
	@Test
	
	public void testExpandby1() {
		Range A1 = new Range(1, 10);
		Range a1 = new Range(-17,19);
	    assertEquals("Expand: Did not return expected output", a1, Range.expand(A1, 2, 2));	
	}
	@Test
   public void testExpandbynegativelowerBound() {    
	    Range A2 = new Range(1, 10);
		Range a2 = new Range(19,19);
	    assertEquals("Expand(): Did not return expected output", a2, Range.expand(A2, -2, 2));	
	    }
	@Test
   public void testExpandbyDecimal() {
	    Range A3 = new Range(1, 10);
		Range a3 = new Range(-3.5,14.5);
	    assertEquals("Expand(): Did not return expected output", a3, Range.expand(A3, 0.5, 0.5));	
	    }
	@Test
   public void testExpandbyNegativeDecimal() {
	    Range A4 = new Range(1, 10);
		Range a4 = new Range(5.5,10);
	    assertEquals("Expand: Did not return expected output", a4, Range.expand(A4, -0.5, 1));	
	  }
	@Test
  public void testExpandNegUpperMargin() {
	  try {
	    Range A5 = new Range(1, 10);
		Range a5 = new Range(1,1);
	    assertEquals("Expand(): Did not return expected output", a5, Range.expand(A5, 0, -2));	
  }catch(Exception e) {
	  assertEquals(IllegalArgumentException.class, e.getClass());
  }
  }
	
	@Test
	 public void testGetCentral1() {
		Range B1 = new Range(1,10);
		assertEquals("getCentral() Did not return expected output", 5.5, B1.getCentralValue());	
	}
	@Test
	public void testGetCentralNegativeLowerBound() {
		Range B2 = new Range(-10,10);
		assertEquals("getCentral(): Did not return expected output", 0.0, B2.getCentralValue());	
		}
	@Test
	public void testGetCentralAllNegative() {
	    Range B3 = new Range(-10,-1);
		assertEquals("getCentral(): Did not return expected output", -5.5, B3.getCentralValue());	
		}
	@Test
	public void testGetCentralNoRange() {
		Range B4 = new Range(10,10);
		assertEquals("getCentral(): Did not return expected output", 10.0, B4.getCentralValue());	
		}
	//@Test
	//public void testGetCentral5() {
	//	Range B5 = new Range(1,10);
		//assertEquals("contains: Did not return expected output", 5.5, B5.getCentralValue());		
	  //  }
	
	
	@Test
	public void testConstrainWithinRange() {
		Range C1 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 9.0, C1.constrain(9));	
	}
	public void testConstrainOutsideRange() {
		Range C2 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 10.0, C2.constrain(11));	
		}
	public void testConstrainOutsideRangeNegative() {
		Range C3 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 1, C3.constrain(-10));	
		}
	public void testConstrainNegativeWithinRange() {
	    Range C4 = new Range(-10,-1);
		assertEquals("contains: Did not return expected output", -9.0, C4.constrain(-9));	
		}
	public void testConstrainDecimal() {
		Range C5 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 10.0, C5.constrain(12.3));	
	}
	
	
	
	
	@Test
	public void testShiftby1() {
		Range D1 = new Range(0,10);
		Range d1 = new Range(1,11);
		assertEquals("contains: Did not return expected output", d1, Range.shift(D1, 1));	
	}
	@Test
		public void testShiftbyMoreThan1() {
		Range D2 = new Range(0,10);
		Range d2 = new Range(10,20);
		assertEquals("contains: Did not return expected output", d2, Range.shift(D2, 10));	
		}
	@Test
		public void testShiftbyNegative() {
		Range D3 = new Range(0,10);
		Range d3 = new Range(-1,9);
		assertEquals("contains: Did not return expected output", d3, Range.shift(D3, -1));	
		}
	@Test
		public void testShiftbyDecimal() {
		Range D4 = new Range(0,10);
		Range d4 = new Range(0.5,10.5);
		assertEquals("contains: Did not return expected output", d4, Range.shift(D4, 0.5));	
		}
	@Test
		public void testShiftbyLargeNegDecimal() {
		Range D5 = new Range(0,10);
		Range d5 = new Range(-100,-90);
		assertEquals("contains: Did not return expected output", d5, Range.shift(D5, -100));	
	}
	
