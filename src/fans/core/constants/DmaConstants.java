package fans.core.constants;

public class DmaConstants {
	/**
	 * 000 = 1 register write once (1 byte: p)
	 */
	public static final String TRANSFER_MODE_0 = "#$00";
	
	/**
	 * 001 = 2 registers write once (2 bytes: p, p+1)
	 */
	public static final String TRANSFER_MODE_1 = "#$01";
	
	/**
	 * 010 = 1 register write twice (2 bytes: p, p)
	 */
	public static final String TRANSFER_MODE_2 = "#$02";
	
	public static final int CHANNEL_0 = 0;
	public static final int CHANNEL_1 = 1;
}
