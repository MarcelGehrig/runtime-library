package ch.ntb.inf.deep.runtime.mpc555.test;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI2;

public class InterfaceTest2 implements IA, IB {

	public int mA1() {
		return 100;
	}
	
	public int mA2() {
		return 200;
	}
	public int mB1() {
		return -100;
	}
	
	public int mB2() {
		return -200;
	}


	static {
		SCI2.start(9600, SCI2.NO_PARITY, (short)8);
		System.out = new PrintStream(SCI2.out);
		System.out.println("start");
		
		IA obj1 = new InterfaceTest2();
		System.out.println(obj1.mA1());
		System.out.println(obj1.mA2());
		IB obj2 = new InterfaceTest2();
		System.out.println(obj2.mB1());
		System.out.println(obj2.mB2());
		System.out.println("test ok");

	}
}


interface IB {
	int mB1();
	int mB2();
}

