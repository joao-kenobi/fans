package fans.core;

import java.util.ArrayList;
import java.util.List;

import fans.core.arquivo.FileManager;
import fans.core.constants.IniDispConstants;
import fans.core.enums.BusRegisters;
import fans.core.enums.CpuRegisters;
import fans.core.interfaces.IRegisterAddress;
import fans.core.interfaces.IRegisterValue;
import fans.core.interfaces.MethodBody;

public abstract class AsmBase {
	protected StringBuilder output = new StringBuilder();
	protected boolean isA8Bit;
	protected boolean isX8Bit;
	protected boolean isY8Bit;
	
	protected String getGeneratedCode() {
		return output.toString();
	}
	
	protected void buildAsmFile() {
		String file = getDevKitFolder()+"/output/main.asm";
		FileManager.writeFile(getGeneratedCode(), file);
		
		compile();
	}

	private void compile() {
		try {
			//compile.bat
			List<String> commands = new ArrayList<String>();
			commands.add("cd "+getDevKitFolder()+"/assemblers/ca65");
			commands.add("compile.bat");
			
			String executionOutput= CmdRunner.run(commands);
			boolean hasError = executionOutput.length() > 1;
			
			if (!hasError) {
				commands.clear();
				commands.add("cd "+getDevKitFolder()+"/assemblers/ca65");
				commands.add("run.bat");
				CmdRunner.run(commands);
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String getDevKitFolder() {
		return FileManager.getCurrentProjectDirectory()+"/../..";
	}
	
	protected String getHomeFolder() {
		return FileManager.getCurrentProjectDirectory()+"/home/";
	}
	
	
	protected void sei() {
		output.append("sei").append("\n");
	}
	
	protected void cld() {
		output.append("cld").append("\n");
	}
	
	protected void clc() {
		output.append("clc").append("\n");
	}
	
	protected void tcd() {
		output.append("tcd").append("\n");
	}
	
	protected void txs() {
		output.append("txs").append("\n");
	}
	
	protected void adc(String value) {
		output.append("adc ").append(value).append("\n");
	}
	
	protected void adcSta(String value, IRegisterAddress register) {
		adcSta(value, register.getAddress());
	}
	
	protected void adcSta(String value, String target) {
		adc(value);
		sta(target);
	}
	
	
	protected void andSta(String value, IRegisterAddress register) {
		andSta(value, register.getAddress());
	}
	
	protected void andSta(String value, String target) {
		and(value);
		sta(target);
	}
	
	protected void hirom() {
		output.append("hirom").append("\n");
	}
	
	
	protected void sep(String value) {
		output.append("sep ").append(value).append("\n");
	}
	
	protected void cmp(String value) {
		output.append("cmp ").append(value).append("\n");
	}
	
	protected void rts() {
		output.append("rts").append("\n");
	}
	
	protected void rti() {
		output.append("rti").append("\n");
	}
	
	protected void beq(String value) {
		output.append("beq ").append(value).append("\n");
	}
	
	protected void bcc(String value) {
		output.append("bcc ").append(value).append("\n");
	}
	
	protected void eor(String value) {
		output.append("eor ").append(value).append("\n");
	}
	
	protected void bcs(String value) {
		output.append("bcs ").append(value).append("\n");
	}
	
	
	protected void inc(String value, int times) {
		for (int i = 0; i < times; i++) {
			inc(value);
		}
	}
	
	protected void inc(String value) {
		output.append("inc ").append(value).append("\n");
	}
	
	protected void dec(String value, int times) {
		for (int i = 0; i < times; i++) {
			dec(value);
		}
	}
	
	protected void dec(String value) {
		output.append("dec ").append(value).append("\n");
	}
	
	protected void ldaSta(IRegisterValue registerValue, IRegisterAddress register) {
		ldaSta(registerValue.getValue(), register.getAddress()+" ;"+register);
	}
	
	
	protected void ldaSta(IRegisterAddress register, String valueOrAddress) {
		ldaSta(register.getAddress()+" ;"+register, valueOrAddress);
	}
	
	protected void ldaSta(String valueOrAddress, IRegisterAddress register) {
		ldaSta(valueOrAddress, register.getAddress()+" ;"+register);
	}
	
	protected void setA_W(String valueOrAddress, IRegisterAddress register) {
		setA_W(valueOrAddress, register.getAddress()+" ;"+register);
	}
	
	protected void ldaStaTwice(String valueOrAddress, IRegisterAddress register) {
		String prefix = valueOrAddress.contains("#")? "#" : "";
		prefix += valueOrAddress.contains("$")? "$" : "";
		prefix += valueOrAddress.contains("%")? "%" : "";
		
		ldaSta(prefix+lowByte(valueOrAddress), register.getAddress()+" ;"+register);
		ldaSta(prefix+highByte(valueOrAddress), register.getAddress());
	}
	
	
	protected void ldaSta(String valueOrAddress, String... targets) {
		
		if (isZero(valueOrAddress)) {
			for (String target : targets) {				
				stz(target);
			}
			
			return;
		}
		
		StringBuilder commands = new StringBuilder();
		commands.append("\n");
		commands.append("lda ").append(valueOrAddress).append("\n");
		
		for (String target : targets) {
			commands.append("sta ").append(target).append("\n");
		}
		
		output.append(commands.toString());
	}
	
	protected void ldaBeq(String valueOrAddress, String beqParameter) {
		lda(valueOrAddress);
		beq(beqParameter);
	}
	
	protected void ldaCmp(String valueOrAddress, String valueOfComparison) {
		lda(valueOrAddress);
		cmp(valueOfComparison);
	}
	
	protected void setA_W(String valueOrAddress, String address) {
		StringBuilder commands = new StringBuilder();
		commands.append("lda.w ").append(valueOrAddress).append("\n");
		commands.append("sta.w ").append(address).append("\n");
		
		output.append(commands.toString());
	}
	
	
	protected void ldxStxTwice(String valueOrAddress, IRegisterAddress register) {
		String prefix = valueOrAddress.contains("#")? "#" : "";
		prefix += valueOrAddress.contains("$")? "$" : "";
		prefix += valueOrAddress.contains("%")? "%" : "";
		
		ldxStx(prefix+lowByte(valueOrAddress), register.getAddress()+" ;"+register);
		ldxStx(prefix+highByte(valueOrAddress), register.getAddress());
	}
	
	protected void ldxStx(String valueOrAddress, IRegisterAddress register) {
		ldxStx(valueOrAddress, register.getAddress()+" ;"+register);
	}
	
	protected void setX_W(String valueOrAddress, IRegisterAddress register) {
		ldxStx_W(valueOrAddress, register.getAddress()+" ;"+register);
	}
	
	protected void ldxStx(String valueOrAddress, String address) {
		if (isZero(valueOrAddress)) {			
			stz(address);
			return;
		}
		
		StringBuilder commands = new StringBuilder();
		commands.append("\n");
		commands.append("ldx ").append(valueOrAddress).append("\n");
		commands.append("stx ").append(address).append("\n");
		
		output.append(commands.toString());
	}
	
	protected void ldxStx_W(String valueOrAddress, String address) {
		StringBuilder commands = new StringBuilder();
		commands.append("ldx.w ").append(valueOrAddress).append("\n");
		commands.append("stx.w ").append(address).append("\n");
		
		output.append(commands.toString());
	}
	
	protected String xce() {
		output.append("xce").append("\n");
		return "xce";
	}
	
	protected void phb() {
		output.append("phb").append("\n");
	}
	
	protected void php() {
		output.append("php").append("\n");
	}
	
	/**
	 * name: Push program bank<br>
	 * description: Pushes the bank value of the currently executed opcode (which is PHK) onto the stack<br>
	 * size: 8 bit
	 */
	protected void phk() {
		output.append("phk").append("\n");
	}
	
	protected void plb() {
		output.append("plb").append("\n");
	}
	
	protected void plp() {
		output.append("plp").append("\n");
	}
	
	protected void inx() {
		output.append("inx").append("\n");
	}
	
	protected void lda(IRegisterAddress register) {
		output.append("lda ").append(register.getAddress()).append("\n");
	}
	
	protected void lda(String value) {
		output.append("lda ").append(value).append("\n");
	}
	
	protected void ldx(String value) {
		output.append("ldx ").append(value).append("\n");
	}
	
	protected void ldy(String value) {
		output.append("ldy ").append(value).append("\n");
	}
	
	protected void ldySty(String valueOrAddress, String... targets) {
		
		if (isZero(valueOrAddress)) {
			for (String target : targets) {				
				stz(target);
			}
			
			return;
		}
		
		ldy(valueOrAddress);
		
		for (String target : targets) {
			sty(target);
		}
	}
	
	protected void sty(String value) {
		output.append("sty ").append(value).append("\n");
	}
	
	protected void sta(IRegisterAddress register) {
		sta(register.getAddress());
	}
	
	protected void sta(String value) {
		output.append("sta ").append(value).append("\n");
	}
	
	protected void stz(String[] values) {
		for (String value : values) {
			stz(value);
		}
	}
	
	protected void stz(String value, int times) {
		for (int i = 0; i < times; i++) {
			stz(value);
		}
	}
	
	protected void stz(String value) {
		output.append("stz ").append(value).append("\n");
	}
	
	protected void cpx(String value) {
		output.append("cpx ").append(value).append("\n");
	}
	
	protected void bne(String value) {
		output.append("bne ").append(value).append("\n");
	}
	protected void stz(IRegisterAddress register) {
		stz(register.getAddress()+" ;"+register);
	}
	
	protected void rep(String value) {
		output.append("rep ").append(value).append("\n");
	}
	
	protected void jmp(String value) {
		output.append("jmp ").append(value).append("\n");
	}
	
	protected void jsr(String value) {
		output.append("jsr ").append(value).append("\n");
	}
	
	protected void bra(String value) {
		output.append("bra ").append(value).append("\n");
	}
	
	protected void org(String value) {
		output.append("org ").append(value).append("\n");
	}
	
	protected void bit(IRegisterAddress register) {
		bit(register.getAddress()+" ;"+register);
	}
	
	protected void bit(String value) {
		output.append("bit ").append(value).append("\n");
	}
	
	protected void and(String value) {
		output.append("and ").append(value).append("\n");
	}
	
	protected void lsr(String value) {
		output.append("lsr ").append(value).append("\n");
	}
	
	
	protected void label(String name) {
		output.append("\n").append(name).append(":").append("\n");
	}
	
	protected void label(String name, MethodBody methodBody) {
		output.append("\n").append(name).append(":").append("\n");
		methodBody.body();
	}
	
	protected void label(String name, MethodBody methodBody, String labelEndName) {
		output.append("\n").append(name).append(":").append("\n");
		methodBody.body();
		output.append("\n").append(labelEndName).append(":").append("\n");
	}
	
	protected void rawAsm(String value) {
		output.append(value).append("\n");
	}
	
	
	// === USEFUL METHODS =================================================================================================

	
	/**
	 * sep #$20
	 */
	protected void a8Bit() {
		sep("#$20 ; A 8 BIT MODE");
		isA8Bit = true;
	}
	
	/**
	 * sep #$10
	 */
	protected void xy8Bit() {
		sep("#$10 ; X,Y 8 BIT MODE");
		isX8Bit = true;
		isY8Bit = true;
	}
	
	protected void axy8Bit() {
		sep("#$30 ; A,X,Y 8 BIT MODE");
	}
	
	protected void a16Bit() {		
		rep("#$20  ; A 16 BIT MODE");
		isA8Bit = false;
	}
	
	/**
	 * rep #$30
	 */
	protected void axy16Bit() {
		rep("#$30 ; A,X,Y 16 BIT MODE");
	}
	
	protected void xy16Bit() {
		rep("#$10 ; X,Y 16 BIT MODE");
	}
	
	
	
	private String lowOrHighByte(String valueOrAddress, boolean extractLowByte) {
		String valueOrAddressClone = new String(valueOrAddress);
		valueOrAddressClone = removePrefixes(valueOrAddressClone);
		
		int subLength = valueOrAddressClone.length()/2;		
		
		if (extractLowByte) {
			return valueOrAddressClone.substring(subLength, valueOrAddressClone.length());
		}
		
		return valueOrAddressClone.substring(0, subLength);
	}
	
	protected String lowByte(IRegisterAddress register) {
		return lowByte(register.getAddress());
	}
	
	protected String lowByte(String valueOrAddress) {	
		boolean extractLowByte = true;
		return lowOrHighByte(valueOrAddress, extractLowByte);
	}
	
	protected String highByte(IRegisterAddress register) {
		return highByte(register.getAddress());
	}
	
	protected String highByte(String valueOrAddress) {	
		boolean extractLowByte = false;
		return lowOrHighByte(valueOrAddress, extractLowByte);
	}
	
	protected String removePrefixes(String value) {
		return value.replace("#",  "").replace("$",  "").replace("%",  "");
	}
	
	protected boolean isZero(String value) {
		try {
			long longNumber = Long.parseLong(removePrefixes(value));
			
			if (longNumber == 0L) {				
				return true;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * LDA #$0f  ; FULL_BRIGHT<br>
	 * STA $2100 ; INIDISP
	 */
	protected void initScreen() {
		ldaSta(IniDispConstants.FULL_BRIGHT, BusRegisters.INIDISP);
	}
	
	protected void startDma(int channel) {
		ldaSta("#%"+Integer.toBinaryString(channel+1), CpuRegisters.MDMAEN); // start dma, channel {channel}
	}
	
	/**
	 * <pre>
	 * _foreverLoop:
	 *     jmp _foreverLoop
	 * </pre>
	 */
	protected void foreverLoop() {
		output.append("\n").append("_foreverLoop").append(":").append("\n");
		output.append("jmp _foreverLoop").append("\n");
	}
	// =======================================================================================================
}
