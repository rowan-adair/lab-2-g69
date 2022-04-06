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
	public void testContains1() {
		Range R1 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R1.contains(11));
	}
	public void testContains2() {
		Range R2 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R2.contains(11));
	}
	
	public void testContains3() {
		Range R3 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R3.contains(11));
		}
		
	public void testContains4() {
		Range R4 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R4.contains(11));
		}
		
	public void testContains5() {
		Range R5 = new Range(1,10);
		assertEquals("contains: Did not return expected output", true, R5.contains(11));
	}
	@Test
	
	public void testExpand1() {
		Range A1 = new Range(1, 10);
		Range a1 = new Range(0,16);
	    assertEquals("contains: Did not return expected output", a1, Range.expand(A1, 0, 16));	
	}
	
   public void testExpand2() {    
	    Range A2 = new Range(1, 10);
		Range a2 = new Range(0,16);
	    assertEquals("contains: Did not return expected output", a2, Range.expand(A2, 0, 16));	
	    }
   
   public void testExpand3() {
	    Range A3 = new Range(1, 10);
		Range a3 = new Range(0,16);
	    assertEquals("contains: Did not return expected output", a3, Range.expand(A3, 0, 16));	
	    }
   
   public void testExpand4() {
	    Range A4 = new Range(1, 10);
		Range a4 = new Range(0,16);
	    assertEquals("contains: Did not return expected output", a4, Range.expand(A4, 0, 16));	
	  }
   
  public void testExpand5() {
	    Range A5 = new Range(1, 10);
		Range a5 = new Range(0,16);
	    assertEquals("contains: Did not return expected output", a5, Range.expand(A5, 0, 16));	
	
	}
	@Test
	 public void testGetCentral1() {
		Range B1 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 5.5, B1.getCentralValue());	
	}
	public void testGetCentral2() {
		Range B2 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 5.5, B2.getCentralValue());	
		}
	public void testGetCentral3() {
	    Range B3 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 5.5, B3.getCentralValue());	
		}
	public void testGetCentral4() {
		Range B4 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 5.5, B4.getCentralValue());	
		}
	public void testGetCentral5() {
		Range B5 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 5.5, B5.getCentralValue());		
	    }
	@Test
	public void testConstrain1() {
		Range C1 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 9.0, C1.constrain(9));	
	}
	public void testConstrain2() {
		Range C2 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 9.0, C2.constrain(9));	
		}
	public void testConstrain3() {
		Range C3 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 9.0, C3.constrain(9));	
		}
	public void testConstrain4() {
	    Range C4 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 9.0, C4.constrain(9));	
		}
	public void testConstrain5() {
		Range C5 = new Range(1,10);
		assertEquals("contains: Did not return expected output", 9.0, C5.constrain(9));	
	
	}
	@Test
	public void testShift1() {
		Range D1 = new Range(0,10);
		Range d1 = new Range(1,11);
		assertEquals("contains: Did not return expected output", d1, Range.shift(D1, 1));	
	}
		public void testShift2() {
		Range D2 = new Range(0,10);
		Range d2 = new Range(1,11);
		assertEquals("contains: Did not return expected output", d2, Range.shift(D2, 1));	
		}
		public void testShift3() {
		Range D3 = new Range(0,10);
		Range d3 = new Range(1,11);
		assertEquals("contains: Did not return expected output", d3, Range.shift(D3, 1));	
		}
		public void testShift4() {
		Range D4 = new Range(0,10);
		Range d4 = new Range(1,11);
		assertEquals("contains: Did not return expected output", d4, Range.shift(D4, 1));	
		}
		public void testShift5() {
		Range D5 = new Range(0,10);
		Range d5 = new Range(1,11);
		assertEquals("contains: Did not return expected output", d5, Range.shift(D5, 1));	

	}
	
	/**
	public static void main (String[] args) {
		Range C1 = new Range(-10,10);
		System.out.println(Range.expand(C1, -2, 2));
	   //System.out.println(Range.shift(new Range(1,10), 30));
	//	System.out.println(C1);
		//System.out.println(new Range(2,6));
		//testConstrain();
	}
	**/
}
