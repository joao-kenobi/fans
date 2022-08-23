package fans.core;

import fans.core.enums.CpuRegisters;

public abstract class AsarSnesBase extends AsmBase {
	
	public AsarSnesBase() {
		include("../includes/asar/header.asm");
		org("$8000");
		label("main", () -> {			
			init();
		});
	}
	
	protected abstract void init();
	
	public void include(String filePath) {
		output.append("incsrc \"").append(filePath).append("\"\n");
	}
	
	public void setA_W(String valueOrAddress, String address) {
		StringBuilder commands = new StringBuilder();
		commands.append("lda.w ").append(valueOrAddress).append("\n");
		commands.append("sta.w ").append(address).append("\n");
		
		output.append(commands.toString());
	}
	
	public void ldxStx(String valueOrAddress, CpuRegisters registers) {
		ldxStx(valueOrAddress, registers.getAddress()+" ;"+registers);
	}
	
	public void setX_W(String valueOrAddress, CpuRegisters registers) {
		ldxStx_W(valueOrAddress, registers.getAddress()+" ;"+registers);
	}
	
	public void ldxStx(String valueOrAddress, String address) {
		StringBuilder commands = new StringBuilder();
		commands.append("ldx ").append(valueOrAddress).append("\n");
		commands.append("stx ").append(address).append("\n");
		
		output.append(commands.toString());
	}
	
	public void ldxStx_W(String valueOrAddress, String address) {
		StringBuilder commands = new StringBuilder();
		commands.append("ldx.w ").append(valueOrAddress).append("\n");
		commands.append("stx.w ").append(address).append("\n");
		
		output.append(commands.toString());
	}	
}
