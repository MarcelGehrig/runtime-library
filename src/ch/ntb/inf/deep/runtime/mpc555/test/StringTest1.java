package ch.ntb.inf.deep.runtime.mpc555.test;

import java.io.PrintStream;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI2;

/*changes:
 * 22.02.11 NTB/Martin Z�ger	OutT replaced by System.out
 * 11.02.11	NTB/Urs Graf		creation
 */

public class StringTest1 {
	static String str1, str2;
	
	
	static {
		SCI2.start(9600, SCI2.NO_PARITY, (short)8);
		System.out = new PrintStream(SCI2.out);
		
		System.out.println("hello world");
		str1 = new String(new char[] {'a', 'b', 'c'});
		System.out.println(str1);
		char[] a1 = new char[] {'1', '2', '3', '4', '5', '6'};
		str1 = new String(a1, 2, 3);
		System.out.println(str1);
		str2 = new String(a1, 0, 6);
		char[] a2 = new char[str2.length()];
		for (int i = 0; i < str2.length(); i++) a2[i] = str2.charAt(i);
		a2[0] = str2.charAt(0);
		System.out.println(str2);
		System.out.println("done");
	}
}
