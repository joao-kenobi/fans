package fans.core.enums;

import fans.core.interfaces.IRegisterAddress;

/**
 * reference: http://baltimorebarcams.com/eb/snes/docs/65816/SNES%20Registers.html
 * 
 *
 */
public enum DmaRegisters implements IRegisterAddress {
	
	/**
	 * $43x0
	 */
	DMAPX("$43x0"),
	/**
	 * $43x1
	 */
	BBADX("$43x1"),
	A1TXL("$43x2"),
	A1TXH("$43x3"),
	A1BX("$43x4"),
	DASXL("$43x5");
	
	private String address;
	private String chosenAddress;
	private int channel;
	
	private DmaRegisters(String address) {
		this.address = address;
	}
	
	public DmaRegisters channel(int channel) {
		this.channel = channel;
		chosenAddress = address.replace("x", Integer.toString(channel));
		return this;
	}
	
	public String getAddress() {
		return chosenAddress;
	}
	
	public String toString() {
		return super.toString().replace("X", channel+"");
	}
}
