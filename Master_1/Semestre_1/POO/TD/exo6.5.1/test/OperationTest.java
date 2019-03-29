

public class OperationTest {
	public Stack<Double> pile;
	
	MoteurRPN MR1 = new MoteurRPN();
	MoteurRPN MR2 = new MoteurRPN();
	MoteurRPN MR3 = new MoteurRPN();
	MoteurRPN MR4 = new MoteurRPN();
	
	@Test
	public void testplus() {
		
		double a = 4.0;
		MR1.push((double) 2);
		MR1.push((double) 2);
		MR1.calcul(Operation.PLUS);
		double test = MR1.pop();
		assertEquals(test,a,0.001);
		
		
	}
	
	@Test
	public void testmoins() {
		double a = 4.0;
		MR2.push((double) 2);
		MR2.push((double) 6);
		MR2.calcul(Operation.MOINS);
		double test = MR2.pop();
		assertEquals(test,a,0.001);
	}
	
	@Test
	public void testmult() {
		double a = 12.0;
		MR3.push((double) 3);
		MR3.push((double) 4);
		MR3.calcul(Operation.MULT);
		double test = MR3.pop();
		assertEquals(test,a,0.001);
	}
	
	@Test
	public void testdiv() {
		double a = 4.0;
		MR4.push((double) 2);
		MR4.push((double) 8);
		MR4.calcul(Operation.DIV);
		double test = MR4.pop();
		assertEquals(test,a,0.001);
	}
	
	@Test
	public void testExceptionPileVide() {
		Stack<Double> p = new Stack<Double>();
			boolean a = false;
			try {
				p.pop();
			} catch (EmptyStackException e) {
				a = true;
			}
			assertTrue(a);
	}

}