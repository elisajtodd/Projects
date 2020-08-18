import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class labw67Test {

	@Test
	public static void testislandWater2() {
		labw67 test = new labw67();
		int[] arr = {1,3,2,4,1,3,1,4,5,2,2,1,4,2,2};
		int result = test.islandWater2(arr);
		assertEquals(15, result);
	}

}
