package ch.ntb.inf.deep.runtime.mpc5200.driver;

import ch.ntb.inf.deep.runtime.mpc5200.phyCoreMpc5200tiny;
import ch.ntb.inf.deep.unsafe.US;

public class UART3 implements phyCoreMpc5200tiny{
	public static UART3OutputStream out;

	public static final byte NO_PARITY = 0, ODD_PARITY = 1, EVEN_PARITY = 2;

	// Error states
	public static final int IDLE_LINE_DET = 4, OVERRUN_ERR = 3, NOISE_ERR = 2,
			FRAME_ERR = 1, PARITY_ERR = 0, LENGTH_NEG_ERR = -1,
			OFFSET_NEG_ERR = -2, NULL_POINTER_ERR = -3;
	
	private static final int FIFO_LENGTH = 512;

	/**
	 * <p>Initialize and start the <i>UART 3</i>.</p>
	 * <p>This method has to be called before using the UART3! The number of
	 * stop bits can't be set. There is always one stop bit!<p>
	 * 
	 * @param baudRate
	 *            The baud rate. Allowed Range: 64 to 500'000 bits/s.
	 * @param parity
	 *            Parity bits configuration. Possible values: {@link #NO_PARITY},
	 *            {@link #ODD_PARITY} or {@link #EVEN_PARITY}.
	 * @param data
	 *            Number of data bits. Allowed values are 5 to 8 bits. 
	 */
	public static void start(int baudRate, short parity, short data) {
		US.PUT1(PSC3Base + PSCCR, 0xa); // disable Tx, Rx
		US.PUT2(PSC3Base + PSCCSR, 0xff00); // CSR, prescaler 16
		US.PUT4(PSC3Base + PSCSICR, 0); // select UART mode
		if (parity == NO_PARITY)
			US.PUT1(PSC3Base + PSCMR1, 0x30 | (data-5) & 3); 
		else {
			if (parity == ODD_PARITY)
				US.PUT1(PSC3Base + PSCMR1, 0x24 | (data-5) & 3);
			else
				US.PUT1(PSC3Base + PSCMR1, 0x20 | (data-5) & 3);
		}
		US.PUT1(PSC3Base + PSCMR2, 0x7); // MR2, 1 stop bit
		int divider = 16500000 / baudRate; // IPB clock = 66MHz, prescaler = 4
		US.PUT1(PSC3Base + PSCCTUR, divider >> 8); 
		US.PUT1(PSC3Base + PSCCTLR, divider); 
		US.PUT1(PSC3Base + PSCTFCNTL, 0x1); // no frames
		US.PUT4(GPSPCR, US.GET4(GPSPCR) | 0x400);	// use pins on PCS3 for UART
		US.PUT1(PSC3Base + PSCCR, 0x5); // enable Tx, Rx
		
	}
	
	/**
	 * Writes a given byte into the transmit buffer.
	 * A call of this method is not blocking! That means
	 * the byte is lost if the buffer is already full!
	 * 
	 * @param b
	 *            Byte to write.
	 */
	public static void write(byte b) {
		US.PUT1(PSC3Base + PSCTxBuf, b); 
	}

	/**
	 * Writes a given number of bytes into the transmit fifo.
	 * A call of this method is not blocking! There will only as
	 * many bytes written, which are free in the fifo.
	 * 
	 * @param b
	 *            Array of bytes to send.
	 * @param off
	 *            Offset to the data which should be sent.
	 * @param len
	 *            Number of bytes to send.
	 * @return the number of bytes written.
	 *            {@link #NULL_POINTER_ERR} if the array reference
	 *            was null (b == null).
	 */
	public static int write(byte[] b) {
		return write(b, 0, b.length);
	}

	/**
	 * Writes a given number of bytes into the transmit fifo.
	 * A call of this method is not blocking! There will only as
	 * many bytes written, which are free in the fifo.
	 * 
	 * @param b
	 *            Array of bytes to send.
	 * @param off
	 *            Offset to the data which should be sent.
	 * @param len
	 *            Number of bytes to send.
	 * @return the number of bytes written.
	 *            {@link #LENGTH_NEG_ERR} if the given number of
	 *            bytes was negative (len < 0).
	 *            {@link #OFFSET_NEG_ERR} if the given offset was
	 *            negative (off < 0).
	 *            {@link #NULL_POINTER_ERR} if the array reference
	 *            was null (b == null).
	 */
	public static int write(byte[] b, int off, int len) {
		if (b == null)
			return NULL_POINTER_ERR;
		if (len < 0)
			return LENGTH_NEG_ERR;
		if (off < 0)
			return OFFSET_NEG_ERR;
		if (len + off > b.length)
			len = b.length - off;
		int bufferSpace = availToWrite();
		if (bufferSpace < len)
			len = bufferSpace;
		for (int i = 0; i < len; i++) {
			write(b[off + i]);
		}
		return len;
	}

	/**
	 * Returns the number of free bytes available in the transmit fifo.
	 * It is possible, to send the returned number of bytes in one
	 * nonblocking transfer.
	 * 
	 * @return the available free bytes in the transmit fifo.
	 */
	public static int availToWrite() {
		return FIFO_LENGTH - US.GET2(PSC3Base + PSCTFNUM);
	}

	static {
		out = new UART3OutputStream();
	}
}