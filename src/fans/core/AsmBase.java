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
	
	protected void compileAndRun() {
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

	protected void addCommand(String command) {
		output.append(command).append("\n");
	}
	
	protected void addCommand(String command, String parameter) {
		output.append(command).append(" ").append(parameter).append("\n");
	}
	
	protected void sei() {
		addCommand("sei");
	}
	
	protected void cld() {
		addCommand("cld");
	}
	
	protected void clc() {
		addCommand("clc");
	}
	
	protected void tcd() {
		addCommand("tcd");
	}
	
	protected void txs() {
		addCommand("txs");
	}
	
	protected void adc(String value) {
		addCommand("adc", value);
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
		addCommand("hirom");
	}
	
	
	protected void sep(String value) {
		addCommand("sep", value);
	}
	
	protected void cmp(String value) {
		addCommand("cmp", value);
	}
	
	protected void rts() {
		addCommand("rts");
	}
	
	protected void rti() {
		addCommand("rti");
	}
	
	protected void beq(String value) {
		addCommand("beq", value);
	}
	
	protected void bcc(String value) {
		addCommand("bcc", value);
	}
	
	protected void eor(String value) {
		addCommand("eor", value);
	}
	
	protected void bcs(String value) {
		addCommand("bcs", value);
	}
	
	
	protected void inc(String value, int times) {
		for (int i = 0; i < times; i++) {
			inc(value);
		}
	}
	
	protected void inc(String value) {
		addCommand("inc", value);
	}
	
	protected void dec(String value, int times) {
		for (int i = 0; i < times; i++) {
			dec(value);
		}
	}
	
	protected void dec(String value) {
		addCommand("dec", value);
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
		
		lda(valueOrAddress);
		
		for (String target : targets) {
			sta(target);
		}
	}
	
	protected void ldaBeq(String valueOrAddress, String beqParameter) {
		lda(valueOrAddress);
		beq(beqParameter);
	}
	
	protected void ldaCmp(String valueOrAddress, String valueOfComparison) {
		lda(valueOrAddress);
		cmp(valueOfComparison);
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
	
	protected void ldxStx(String valueOrAddress, String target) {
		if (isZero(valueOrAddress)) {			
			stz(target);
			return;
		}
		
		ldx(valueOrAddress);
		stx(target);
	}
	
	protected void ldxStx_W(String valueOrAddress, String address) {
		StringBuilder commands = new StringBuilder();
		commands.append("ldx.w ").append(valueOrAddress).append("\n");
		commands.append("stx.w ").append(address).append("\n");
		
		output.append(commands.toString());
	}
	
	protected void xce() {
		addCommand("xce");
	}
	
	protected void phb() {
		addCommand("phb");
	}
	
	protected void php() {
		addCommand("php");
	}
	
	/**
	 * name: Push program bank<br>
	 * description: Pushes the bank value of the currently executed opcode (which is PHK) onto the stack<br>
	 * size: 8 bit
	 */
	protected void phk() {
		addCommand("phk");
	}
	
	protected void plb() {
		addCommand("plb");
	}
	
	protected void plp() {
		addCommand("plp");
	}
	
	protected void inx() {
		addCommand("inx");
	}
	
	protected void lda(IRegisterAddress register) {
		lda(register.getAddress());
	}
	
	protected void lda(String value) {
		addCommand("lda", value);
	}
	
	protected void ldx(String value) {
		addCommand("ldx", value);
	}
	
	protected void ldy(String value) {
		addCommand("ldy", value);
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
		addCommand("sty", value);
	}
	
	protected void sta(IRegisterAddress register) {
		sta(register.getAddress());
	}
	
	protected void sta(String value) {
		addCommand("sta", value);
	}
	
	protected void stx(IRegisterAddress register) {
		stx(register.getAddress());
	}
	
	protected void stx(String value) {
		addCommand("stx", value);
	}
	
	
	protected void stz(IRegisterAddress[] registers) {
		for (IRegisterAddress register : registers) {
			stz(register);
		}
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
		addCommand("stz", value);
	}
	
	protected void cpx(String value) {
		addCommand("cpx", value);
	}
	
	protected void bne(String value) {
		addCommand("bne", value);
	}
	protected void stz(IRegisterAddress register) {
		stz(register.getAddress()+" ;"+register);
	}
	
	protected void rep(String value) {
		addCommand("rep", value);
	}
	
	protected void jmp(String value) {
		addCommand("jmp", value);
	}
	
	protected void jsr(String value) {
		addCommand("jsr", value);
	}
	
	protected void jml(String value) {
		addCommand("jml", value);
	}
	
	protected void bra(String value) {
		addCommand("bra", value);
	}
	
	protected void org(String value) {
		addCommand("org", value);
	}
	
	protected void bit(IRegisterAddress register) {
		bit(register.getAddress()+" ;"+register);
	}
	
	protected void bit(String value) {
		addCommand("bit", value);
	}
	
	protected void and(String value) {
		addCommand("and", value);
	}
	
	protected void lsr(String value) {
		addCommand("lsr", value);
	}
	
	
	protected void label(String name) {
		output.append("\n").append(name).append(":").append("\n");
	}
	
	protected void label(String name, MethodBody methodBody) {
		label(name);
		methodBody.body();
	}
	
	
	/**
	 * create two labels like this:<br>
	 * my_label:<br>
	 * ; -- your code<br>
	 * my_label_end:
	 * @param name
	 * @param methodBody
	 */
	protected void labelWithEnd(String name, MethodBody methodBody) {
		label(name, methodBody, name+"_end");
	}
	
	protected void label(String name, MethodBody methodBody, String labelEndName) {
		label(name);
		methodBody.body();
		label(labelEndName);
	}
	
	protected void rawAsm(String value) {
		addCommand(value);
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
		label("_foreverLoop");
		jmp("_foreverLoop");
	}
	// =======================================================================================================
}
