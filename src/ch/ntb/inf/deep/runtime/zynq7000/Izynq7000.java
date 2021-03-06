package ch.ntb.inf.deep.runtime.zynq7000;

import ch.ntb.inf.deep.runtime.arm32.Iarm32;

// Auto generated file (2017-12-06 13:39:53)

public interface Izynq7000 extends Iarm32 {

	// System constants of CPU zynq7000
	public static final int globalTimer = 0xf8f00200;
	public static final int cpuPrivateReg = 0xf8900000;
	public static final int slcrReg = 0xf8000000;
	public static final int gpioController = 0xe000a000;
	public static final int uartController = 0xe0000000;
	public static final int ioReg = 0xe0000000;
	public static final int heapSize = 0x8000;
	public static final int stackSize = 0x1000;
	public static final int sysTabBaseAddr = 0x1000;
	public static final int excpCodeSize = 0x1000;
	public static final int excpCodeBase = 0x0;
	public static final int OCM_Size = 0x30000;
	public static final int OCM_BaseAddr = 0x0;

	// Specific registers of CPU zynq7000
	public static final int GPIO_DATA0 = 0xe000a040;
	public static final int GPIO_DIR0 = 0xe000a204;
	public static final int GTCR_L = 0xf8f00200;
	public static final int GTCR_U = 0xf8f00204;
	public static final int GTCR = 0xf8f00208;
	public static final int SLCR_LOCK = 0xf8000004;
	public static final int SLCR_UNLOCK = 0xf8000008;
	public static final int SLCR_LOCKSTA = 0xf800000c;
	public static final int SLCR_UART_CLK_CTRL = 0xf8000154;
	public static final int SLCR_MIO_PIN_00 = 0xf8000700;
	public static final int SLCR_MIO_PIN_07 = 0xf800071c;
	public static final int SLCR_MIO_PIN_10 = 0xf8000728;
	public static final int SLCR_MIO_PIN_11 = 0xf800072c;
	public static final int SLCR_MIO_PIN_48 = 0xf80007c0;
	public static final int SLCR_MIO_PIN_49 = 0xf80007c4;
	public static final int SLCR_OCM_CFG = 0xf8000910;
	public static final int UART0_CR = 0xe0000000;
	public static final int UART0_MR = 0xe0000004;
	public static final int UART0_BAUDGEN = 0xe0000018;
	public static final int UART0_SR = 0xe000002c;
	public static final int UART0_FIFO = 0xe0000030;
	public static final int UART1_CR = 0xe0001000;
	public static final int UART1_MR = 0xe0001004;
	public static final int UART1_BAUDGEN = 0xe0001018;
	public static final int UART1_SR = 0xe000102c;
	public static final int UART1_FIFO = 0xe0001030;
}