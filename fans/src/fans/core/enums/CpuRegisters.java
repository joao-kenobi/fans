package fans.core.enums;

import fans.core.interfaces.IRegisterAddress;

public enum CpuRegisters implements IRegisterAddress {
	
	
	/**
	 * Register: Interrupt Enable Register<br>
	 * Address: $4200<br>
	 * Name: NMITIMEN<br>
	 * Style: single<br>
	 * Access: write<br>
	 * Timing: any time
	 * <pre>
	 * $4200  wb+++?
        n-yx---a
        n        = Enable NMI.^
          x/y    = IRQ enable.
                   0/0 => No IRQ will occur
                   0/1 => An IRQ will occur sometime just after the V Counter reaches the value set in $4209/a.
                   1/0 => An IRQ will occur sometime just after the H Counter reaches the value set in $4207/8.
                   1/1 => An IRQ will occur sometime just after the H Counter reaches the value set in $4207/8 when V Counter equals the value set in $4209/a.
               a = Auto-Joypad Read Enable.^^</pre>
	 */
	NMITIMEN("$4200"),
	WRIO("$4201"),
	WRMPYA("$4202"),
	WRMPYB("$4203"),
	WRDIVL("$4204"),
	WRDIVH("$4205"),
	WRDIVB("$4206"),
	HTIMEL("$4207"),
	HTIMEH("$4208"),
	VTIMEL("$4209"),
	VTIMEH("$420A"),
	MDMAEN("$420B"),
	HDMAEN("$420C"),
	MEMSEL("$420D"),
	RDNMI("$4210"),
	TIMEUP("$4211"),
	HVBJOY("$4212"),
	RDIO("$4213"),
	RDDIVL("$4214"),
	RDDIVH("$4215"),
	RDMPYL("$4216"),
	RDMPYH("$4217"),
	JOY1L("$4218"),
	JOY1H("$4219"),
	JOY2L("$421A"),
	JOY2H("$421B"),
	JOY3L("$421C"),
	JOY3H("$421D"),
	JOY4L("$421E"),
	JOY4H("$421F"),
	;
	
	private String address;
	
	private CpuRegisters(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
}
