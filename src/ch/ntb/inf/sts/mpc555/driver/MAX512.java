package ch.ntb.inf.sts.mpc555.driver;

import ch.ntb.inf.sts.internal.SYS;

/**
 * Driver for the Maxim512 Digital to Analog Converter.<br>
 * PCS0 is used.
 * 
 * @author NTB 14.04.2009<br>
 *         Simon Pertschy<br>
 */
public class MAX512 {
	
	public final static int chnA = 0;
	public final static int chnB = 1;
	public final static int chnC = 2;
	
	private static short disDACs = 0;

	
	/**
	 * Disable one channel.
	 * @param ch the desired channel ({@link chnA}, {@link chnB}, {@link chnC})
	 */
	public static void disable(int ch){
		disDACs |= 0x0800 << ch;
		SYS.PUT2(QSMCM.TR + 2 * ch,disDACs);
	}
	
	/**
	 * Enable a disabled channel.
	 * @param ch the desired channel ({@link chnA}, {@link chnB}, {@link chnC})
	 */
	public static void enable(int ch){
		disDACs &=  ~(0x0800 << ch);
		SYS.PUT2(QSMCM.TR + 2 * ch,disDACs);
	}
	
	/**
	 * Write a value to the DAC.
	 * @param ch the desired channel ({@link chnA}, {@link chnB}, {@link chnC})
	 * @param val the desired output value
	 */
	public static void write(int ch, byte val){
		SYS.PUT2(QSMCM.TR + 2 * ch, (disDACs | (0x0100  << ch)| (0xFF & val))); // Write data to transmit ram
	}
	
	/**
	 * Initializes the SPI.
	 */
	public static void init(){
		SYS.PUT2(QSMCM.SPCR1, 0x0); 	//disable QSPI 
		SYS.PUT1(QSMCM.PQSPAR, 0x0B); // use PCS0, MOSI, MISO for QSPI 
		SYS.PUT1(QSMCM.DDRQS, 0x0E); 	//SCK, MOSI, PCS's outputs; MISO is input 
		SYS.PUT2(QSMCM.PORTQS, 0xFF); 	//all Pins, in case QSPI disabled, are high 
		SYS.PUT2(QSMCM.SPCR0, 0x8014); // QSPI is master, 16 bits per transfer, inactive state of SCLK is high (CPOL=1), data changed on leading edge (CPHA=1), clock = 1 MHz 
		SYS.PUT2(QSMCM.SPCR2, 0x4200); 	// no interrupts, wraparound mode, NEWQP=0, ENDQP=7 		
		SYS.PUT1(QSMCM.CR, 0x6E); //Cont off, BITS of SPCR0, DT from SPCR1, CS0 low
		SYS.PUT1(QSMCM.CR + 1, 0x6E); //Cont off, BITS of SPCR0, DT from SPCR1, CS0 low
		SYS.PUT1(QSMCM.CR + 2, 0x6E); //Cont off, BITS of SPCR0, DT from SPCR1, CS0 low
		SYS.PUT2(QSMCM.SPCR1, 0x08010);	//enable QSPI, delay 13us after transfer
	}
	
}
