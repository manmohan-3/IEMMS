//SHIVAAAAHHHH


package com.iemms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

interface Addition{
	int add(int a,int b);
}
public class MockitoPractice {
	
	@Test
	public void AdditionTest() {
		Addition addition=mock(Addition.class);
		when(addition.add(10, 20)).thenReturn(30);
		//here in thenReturn we can return 40 also even 10+20=30...
		int result=addition.add(10, 20);
		assertEquals(30,result);
	}

}
