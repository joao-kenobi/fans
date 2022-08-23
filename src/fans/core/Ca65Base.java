package fans.core;

import fans.core.constants.Joy1Consttants;
import fans.core.enums.BusRegisters;
import fans.core.enums.CpuRegisters;
import fans.core.enums.DmaRegisters;
import fans.core.interfaces.IJoypadReader;
import fans.core.interfaces.MethodBody;

public abstract class Ca65Base extends AsmBase {
	
	public Ca65Base() {
		
		rawAsm(".p816");
		rawAsm(".smart");
		before();
		rawAsm(".include \"../framework/asm/includes/ca65/macros.asm\"");
		rawAsm(".include \"../framework/asm/includes/ca65/init.asm\"");
		
		segment("CODE");
		label("main");
		rawAsm(".a16"); // the setting from init code
		rawAsm(".i16");
		phk();
		plb();
		init();
		
		rawAsm(".include \"../framework/asm/includes/ca65/header.asm\"");
	}
	
	protected abstract void before();
	
	protected void include(String filePath) {
		output.append(".include \"").append("../"+filePath).append("\"\n");
	}
	
	protected void incbin(String filePath) {
		output.append(".incbin \"").append(super.getHomeFolder()+filePath).append("\"\n");
	}
	
	protected void segment(String value, MethodBody methodBody) {
		segment(value);
		methodBody.body();
	}
	
	protected void segment(String value) {
		output.append(".segment \"").append(value).append("\"\n");
	}
	
	protected void org(String value) {
		output.append(".org ").append(value);
	}
	
	protected void dmaToCgram(String source, int length, String transferMode, int channel) {
		dmaToCgram(source, "#"+Integer.toString(length), transferMode, channel);
	}
	
	protected void dmaToCgram(String source, String length, String transferMode, int channel) {
		String destination = "#$"+lowByte(BusRegisters.CGDATA); // #$22
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
		
		ldaSta("#$01", CpuRegisters.MDMAEN); // start dma, channel {channel}
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
				checkPressedButton(Joy1Consttants.KEY_RIGHT, "@not_right");
			}
		});
		
		//moveXHandle("@right");
		label("@right", () -> {
			reader.onKeyRight();
		});
		
		label("@not_right", () -> {
			checkPressedButton(Joy1Consttants.KEY_UP, "@not_up");
		});
		
		//moveYHandle("@up");
		label("@up", () -> {
			reader.onKeyUp();
		});
		
		label("@not_up", () -> {
			checkPressedButton(Joy1Consttants.KEY_DOWN, "@not_down");
		});
		
		//moveYHandle("@down");
		label("@down", () -> {
			reader.onKeyDown();
		});
		
		label("@not_down", () -> {
			a8Bit();
		});
	}
	
	private void checkPressedButton(String keyToCheck, String labelToGoIfNotPressed) {
		lda("pad1");
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
	
	protected void blockMove(int length, String sourceAddress, String destinationAddress) {
		callMacro("blockMove", length, sourceAddress, destinationAddress);
		
		//phb();
//		rawAsm(".if .asize = 8");
//		rawAsm("rep #$30");
//		rawAsm(".elseif .isize = 8");
//		rawAsm("rep #$30");
//		rawAsm(".endif");
//		rawAsm("lda #"+(length-1));
//		rawAsm("ldx #.loword("+sourceAddress+")");
//		rawAsm("ldy #.loword("+destinationAddress+")");	
//		//mvn src_bank, dst_bank
//		rawAsm(".byte $54, ^"+destinationAddress+", ^"+sourceAddress+"");
		//plb();
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
}
