package fans.core;

import fans.core.constants.DmaConstants;
import fans.core.constants.Joy1Consttants;
import fans.core.constants.NmiTIMenConstants;
import fans.core.enums.BusRegisters;
import fans.core.enums.CpuRegisters;
import fans.core.enums.DmaRegisters;
import fans.core.interfaces.IJoypadReader;
import fans.core.interfaces.MethodBody;

public abstract class Ca65Base extends AsmBase {
	
	protected static final String IN_NMI_VARIABLE = "in_nmi";
	
	private static final String TEMP1_VARIABLE = "temp1";
	private static final String PAD1_VARIABLE = "pad1";
	private static final String PAD1_NEW_VARIABLE = "pad1_new";
	private static final String PAD2_VARIABLE = "pad2";
	private static final String PAD2_NEW_VARIABLE = "pad2_new";
	
	protected static final String PALETTE_BUFFER_VARIABLE = "palette_buffer";
	protected static final String PALETTE_BUFFER_END_LABEL = "palette_buffer_end";
	
	protected static final String OAM_LO_BUFFER_VARIABLE = "oam_lo_buffer";
	protected static final String OAM_HI_BUFFER_VARIABLE = "oam_hi_buffer";
	protected static final String OAM_BUFFER_END_LABEL = "oam_buffer_end";
	
	public Ca65Base() {
		new InitRoutine().createInitAsmFile();
		
		
		rawAsm(".p816");
		rawAsm(".smart");
		
		
		defaultBefore();
		before();
		//rawAsm(".include \"../framework/asm/includes/ca65/macros.asm\"");
		rawAsm(".include \"../framework/asm/includes/ca65/init.asm\"");
		rawAsm(".include \"../framework/asm/includes/ca65/header.asm\"");
		
		segment("CODE");
		label("main");
		a8Bit();
		xy16Bit();
		phk();
		plb();
		init();
		
	}
	
	protected void before() {
		
	}
	
	protected void defaultBefore() {
		rawAsm("\n; === DEFAULT ZEROPAGE SECTION ===");
		segment("ZEROPAGE", () -> {
			variable(IN_NMI_VARIABLE, 2);
			
			variable(TEMP1_VARIABLE, 2);
			variable(PAD1_VARIABLE, 2);
			variable(PAD1_NEW_VARIABLE, 2);
			variable(PAD2_VARIABLE, 2);
			variable(PAD2_NEW_VARIABLE, 2);
		});
		
		rawAsm("; === END DEFAULT ZEROPAGE SECTION ===\n");
		
		rawAsm("; === DEFAULT BSS SECTION ===");
		segment("BSS", () -> {			
			variable(PALETTE_BUFFER_VARIABLE, 512);
			label(PALETTE_BUFFER_END_LABEL);

			variable(OAM_LO_BUFFER_VARIABLE, 512); // low table
			variable(OAM_HI_BUFFER_VARIABLE, 32); // high table
			label(OAM_BUFFER_END_LABEL);
		});
		rawAsm("; === END DEFAULT BSS SECTION ===\n");
	}
	
	protected abstract void init();
	
	/**
	 * .a8 command
	 */
	protected void _a8() {
		addCommand(".a8");
		isA8Bit = true;
		isA16Bit = false;
	}
	
	/**
	 * .a16 command
	 */
	protected void _a16() {
		addCommand(".a8");
		isA8Bit = false;
		isA16Bit = true;
	}
	
	/**
	 * .i16 command
	 */
	protected void _i16() {
		addCommand(".i16");
		isX16Bit = true;
		isY16Bit = true;
		
		isX8Bit = false;
		isY8Bit = false;
	}
	
	protected void include(String filePath) {
		output.append(".include \"").append("../"+filePath).append("\"\n");
	}
	
	protected void incbin(String filePath) {
		output.append(".incbin \"").append(super.getHomeFolder()+filePath).append("\"\n");
	}
	
	protected void zeroPageSegment(MethodBody methodBody) {
		segment("ZEROPAGE");
		methodBody.body();
	}
	
	protected void segment(String value, MethodBody methodBody) {
		segment(value);
		methodBody.body();
	}
	
	protected void segment(String value) {
		output.append(".segment \"").append(value).append("\"\n");
	}
	
	protected void variable(String name, int size) {
		output.append(name).append(": .res ").append(size).append("\n");
	}
	
	protected void org(String value) {
		output.append(".org ").append(value);
	}
	
	protected void dmaToCgram(String source, int length, String transferMode, int channel) {
		dmaToCgram(source, "#"+Integer.toString(length), transferMode, channel);
	}
	
	protected void dmaBufferToCgram(String transferMode, int channel) {
		String length = "#("+PALETTE_BUFFER_VARIABLE+"_end-"+PALETTE_BUFFER_VARIABLE+")";
		
		dmaToCgram(PALETTE_BUFFER_VARIABLE, length, transferMode, channel);
	}
	
	protected void dmaToCgram(String source, String transferMode, int channel) {
		String sourceWithoutPrefixes = removePrefixes(source);
		String destination = "#$"+lowByte(BusRegisters.CGDATA); // #$22
		String length = "#("+sourceWithoutPrefixes+"_end-"+sourceWithoutPrefixes+")";
		
		dma(source, destination, length, transferMode, channel);
	}
	
	protected void dmaToCgram(String source, String length, String transferMode, int channel) {
		String destination = "#$"+lowByte(BusRegisters.CGDATA); // #$22
		dma(source, destination, length, transferMode, channel);
	}
	
	protected void dmaToVram(String source, String transferMode, int channel) {
		String sourceWithoutPrefixes = removePrefixes(source);
		String destination = "#$"+lowByte(BusRegisters.VMDATAL); // #$18
		String length = "#("+sourceWithoutPrefixes+"_end - "+sourceWithoutPrefixes+")";
		
		dma(source, destination, length, transferMode, channel);
	}
	
	protected void dmaToVram(String source, String length, String transferMode, int channel) {
		String destination = "#$"+lowByte(BusRegisters.VMDATAL); // #$18
		dma(source, destination, length, transferMode, channel);
	}
	
	protected void dmaToOam(String source, int length, String transferMode, int channel) {
		dmaToOam(source, "#"+Integer.toString(length), transferMode, channel);
	}
	
	protected void dmaToOam(String source, String length, String transferMode, int channel) {
		String destination = "#$"+lowByte(BusRegisters.OAMDATA); // #$04
		dma(source, destination, length, transferMode, channel);
	}
	
	
	protected void dmaBufferToOam(String transferMode, int channel) {
		dmaToOam(OAM_LO_BUFFER_VARIABLE, "#("+OAM_BUFFER_END_LABEL+" - "+OAM_LO_BUFFER_VARIABLE+")", DmaConstants.TRANSFER_MODE_0, DmaConstants.CHANNEL_0);
	}
	
	protected void dma(String source, int channel) {
		String destination = null;
		String transferMode = null;
		dma(source, destination, "#("+source+"_end-"+source+")", transferMode, channel);
	}
	
	protected void dma(String source, String length, int channel) {
		String destination = null;
		String transferMode = null;
		dma(source, destination, length, transferMode, channel);
	}
	
	protected void dma(String source, String destination, int length, String transferMode, int channel) {
		dma(source, destination, "#$"+Integer.toString(length), transferMode, channel);
	}
	
	protected void dma(String source, String destination, String length, String transferMode, int channel) {
		rawAsm("\n; === DMA START  === ");
		if (transferMode != null) {
			ldaSta(transferMode, DmaRegisters.DMAPX.channel(channel));
		}
		
		if (destination != null) {			
			ldaSta(destination, DmaRegisters.BBADX.channel(channel)); // destination
		}
		
		ldxStx("#.loword("+source+")", DmaRegisters.A1TXL.channel(channel)); // source
		ldaSta("#^"+source, DmaRegisters.A1BX.channel(channel)); // bank
		ldxStx(length, DmaRegisters.DASXL.channel(channel)); // length
		
		startDma(channel);
		rawAsm("; === DMA END  === \n");
	}
	protected void readJoypad1(IJoypadReader reader) {
		checkPressedButton(Joy1Consttants.KEY_LEFT, "@not_left");
		//moveXHandle("@left");
		label("@left", () -> {
			reader.onKeyLeft();
		});
		
		label("@not_left", () -> {
			int previousLength = output.length();
			reader.onKeyNotLeft();
			boolean hasImplementation = output.length() > previousLength;
			
			if (!hasImplementation) {
				a16Bit();
				checkPressedButton(Joy1Consttants.KEY_RIGHT, "@not_right");
			}
		});
		
		label("@right", () -> {
			reader.onKeyRight();
		});
		
		label("@not_right", () -> {
			int previousLength = output.length();
			reader.onKeyNotRight();
			boolean hasImplementation = output.length() > previousLength;
			
			if (!hasImplementation) {				
				a16Bit();
				checkPressedButton(Joy1Consttants.KEY_UP, "@not_up");
			}
			
		});
		
		label("@up", () -> {
			reader.onKeyUp();
		});
		
		label("@not_up", () -> {
			int previousLength = output.length();
			reader.onKeyNotUp();
			boolean hasImplementation = output.length() > previousLength;
			
			if (!hasImplementation) {				
				a16Bit();
				checkPressedButton(Joy1Consttants.KEY_DOWN, "@not_down");
			}
		});
		
		label("@down", () -> {
			reader.onKeyDown();
		});
		
		label("@not_down", () -> {
			a8Bit();
		});
	}
	
	private void checkPressedButton(String keyToCheck, String labelToGoIfNotPressed) {
		lda(PAD1_VARIABLE);
		and(keyToCheck);
		beq(labelToGoIfNotPressed);
	}
	
	protected void setBGMode(String value) {
		ldaSta(value, BusRegisters.BGMODE);
	}
	
	protected void setBG1TilemapAddress(String address) {
		ldaSta(address, BusRegisters.BG1SC);
	}
	
	protected void setBG2TilemapAddress(String address) {
		ldaSta(address, BusRegisters.BG2SC);
	}
	
	protected void setBG3TilemapAddress(String address) {
		ldaSta(address, BusRegisters.BG3SC);
	}
	
	protected void setBG4TilemapAddress(String address) {
		ldaSta(address, BusRegisters.BG4SC);
	}
	
	protected void setBG1And2CharacterAddress(String address) {
		ldaSta(address, BusRegisters.BG12NBA);
	}
	
	protected void setBG3And4CharacterAddress(String address) {
		ldaSta(address, BusRegisters.BG34NBA);
	}
	
	/**
	 * Write to OBSEL register
	 * @param value
	 */
	protected void setObjectAndCharacterSize(String value) {
		ldaSta(value, BusRegisters.OBSEL);
	}
	
	protected void enableMainScreenDesignation(String value) {
		ldaSta(value, BusRegisters.TM);
	}
	
	protected void enableSubscreenDesignation(String value) {
		ldaSta(value, BusRegisters.TS);
	}
	
	protected void enableNmiAndAutoJoypadRead() {		
		ldaSta(NmiTIMenConstants.ENABLE_NMI_AND_AUTO_JOYPAD_READ, CpuRegisters.NMITIMEN);
	}
	
	protected void blockMove(String sourceAddress, String destinationAddress, int length) {
		blockMove(sourceAddress, destinationAddress, "#"+length);
	}
	
	protected void blockMove(String sourceAddress, String destinationAddress) {
		String source = removePrefixes(sourceAddress);
		
		blockMove(sourceAddress, destinationAddress, "#("+source+"_end-"+source+")");
	}
	
	protected void blockMove(String sourceAddress, String destinationAddress, String length) {
		//callMacro("blockMove", length, sourceAddress, destinationAddress);
		
		phb();
		rawAsm(".if .asize = 8");
		axy16Bit();
		rawAsm(".elseif .isize = 8");
		axy16Bit();
		rawAsm(".endif");
		lda(length);
		ldx("#.loword("+sourceAddress+")");
		ldy("#.loword("+destinationAddress+")");	
		//mvn src_bank, dst_bank
		rawAsm(".byte $54, ^"+destinationAddress+", ^"+sourceAddress);
		//rawAsm("mvn ^"+destinationAddress+", ^"+sourceAddress);
		plb();
	}
	
	protected void callMacro(String name, Object... args) {
		StringBuilder parameters = new StringBuilder(); 
		
		for (Object arg : args) {
			parameters.append(arg).append(", ");
		}
		
		if(parameters.length() > 0){
			parameters.deleteCharAt(parameters.length()-2);
		}
		
		rawAsm(name+" "+parameters.toString());
	}
	
	protected void waitNMI() {
		label("wait_nmi", () -> {
			_a8();
			_i16();
			lda(IN_NMI_VARIABLE);
			
			checkAgain();
		});
	}
	
	protected void checkAgain() {
		label("@check_again", () -> {
			wai();
			cmp(IN_NMI_VARIABLE);
			beq("@check_again");
			rts();
		});
	}
	
	protected void padPoll() {
		label("pad_poll", () -> {			
			_a8();
			_i16();
			// reads both controllers to pad1, pad1_new, pad2, pad2_new
			// auto controller reads done, call this once per main loop
			// copies the current controller reads to these variables
			// pad1, pad1_new, pad2, pad2_new (all 16 bit)
			php();
			a8Bit();
			_wait();
				
			a16Bit();
			ldaSta(PAD1_VARIABLE, TEMP1_VARIABLE); // save last frame
			ldaSta(CpuRegisters.JOY1L, PAD1_VARIABLE); // controller 1
			
			eor(TEMP1_VARIABLE);
			andSta(PAD1_VARIABLE, PAD1_NEW_VARIABLE);
			ldaSta(PAD2_VARIABLE, TEMP1_VARIABLE); // save last frame
			ldaSta(CpuRegisters.JOY2L, PAD2_VARIABLE); // controller 2
			
			eor(TEMP1_VARIABLE);
			andSta(PAD2_VARIABLE, PAD2_NEW_VARIABLE);
			plp();
			rts();
		});
	}
	
	protected void _wait() {
		label("@wait", () -> {
			// wait till auto-controller reads are done
			lda(CpuRegisters.HVBJOY);
			lsr("a");
			bcs("@wait");
		});
	}
	
	protected void clearOam() {
		jsr("clear_oam");
	}
}