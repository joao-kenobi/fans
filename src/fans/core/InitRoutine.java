package fans.core;

import fans.core.arquivo.FileManager;
import fans.core.enums.BusRegisters;
import fans.core.enums.CpuRegisters;
import fans.core.enums.DmaRegisters;

public class InitRoutine extends AsmBase {
	
	private void reset() {
		label("reset", () -> {
			
			sei(); // turn off IRQs
			clc();
			xce(); // turn off 6502 emulation mode
			rep("#$38"); // AXY16 and clear decimal mode.
			ldx("#$1fff");
			txs(); // set the stack pointer
			phk();
			plb(); // set b to current bank, 00
			
			// Initialize the CPU I/O registers to predictable values
			lda("#$4200");
			tcd(); // temporarily move direct page to S-CPU I/O area
			ldaSta("#$FF00", "$00");
			
			stz(new String[] {"$00", "$02", "$04","$06", "$08", "$0A", "$0C"});
			
			// Initialize the PPU registers to predictable values
			lda("#$2100");
			tcd(); // temporarily move direct page to PPU I/O area
			ldaSta("#$0080", "$00"); // Enable forced blank
			
			stz(new String[] {"$02", "$05", "$07", "$09", "$0B", "$16", "$24", "$26", "$28", "$2A", "$2C", "$2E"});
			
			ldxStx("#$0030", "$30"); // Disable color math
			ldySty("#$00E0", "$32"); // Clear red, green, and blue components of COLDATA, also 0 to 2133, normal video at 224 pixels high
			
			// now clear the regs that need 8-bit writes
			a8Bit();
			sta("$15");
			stz(new String[] {"$1A", "$21", "$23"});
			
			// The scroll registers $210D-$2114 need double 8-bit writes
			rawAsm(".repeat 8, I");
			rawAsm(" stz $0D+I");
			rawAsm(" stz $0D+I");
			rawAsm(".endrepeat");
			
			// As do the mode 7 registers, which we set to the identity matrix
			// [ $0100	$0000 ]
			// [ $0000	$0100 ]
			
			lda("#$01");
			stz("$1B");
			sta("$1B");
			stz("$1C", 2);
			stz("$1D", 2);
			stz("$1E", 2);
			stz("$1F", 2);
			stz("$20", 2);
			axy16Bit();
			lda("#$0000");
			tcd(); // return direct page to real zero page
		});
	}
	
	private void clearWram() {

		label("clear_wram", () -> {
			int channel = 0;
			
			a16Bit();
			xy8Bit();
			stz(new BusRegisters[] {BusRegisters.WMADDL, BusRegisters.WMADDM});
			
			ldaSta("#$8008", DmaRegisters.DMAPX.channel(channel)); // fixed transfer to WRAM data 2180, and 4301 register
			ldaSta("#.loword(DMAZero)", DmaRegisters.A1TXL.channel(channel)); // and 4303 register
			ldxStx("#^DMAZero", DmaRegisters.A1BX.channel(channel)); // bank #
			
			stz(DmaRegisters.DASXL.channel(channel)); // and 4306 = size 0000 = $10000
			ldxStx("#1", CpuRegisters.MDMAEN); // start dma, channel {channel} // DMA_ENABLE, clear the 1st half of WRAM
			stx(CpuRegisters.MDMAEN); // DMA_ENABLE, clear the 2nd half of WRAM
			
			a8Bit();
			xy16Bit();
			jsr("clear_palette");
			jsr("dma_palette");
			jsr("clear_oam");
//			jsr DMA_OAM
//			jsr Clear_VRAM
//
//		;	a8Bit();
//			lda #1
//			sta $420d ;fastROM
//
//			AXY16
//			jml Main ;should jump into the $80 bank, fast ROM
		});
	}
	
	private void clearOam() {
		label("clear_oam", () -> {
			// fills the buffer with 224 for low table
			// and $00 for high table 
			
			php();
			a8Bit();
			xy16Bit();
			
			clearOam("SpriteEmptyVal", "#$200");
			clearOam("SpriteUpperEmpty", "#$0020");
			
			plp();
			rts();
		});
		
	}
	
	private void clearOam(String label, String dasxlValue) {
		int channel = 0;
		
		ldxStx("#.loword(oam_lo_buffer)", BusRegisters.WMADDL);
		stz(BusRegisters.WMADDH);
		ldxStx("#$8008", DmaRegisters.DMAPX.channel(channel)); // fixed transfer to WRAM data 2180
		ldxStx("#.loword("+label+")", DmaRegisters.A1TXL.channel(channel));// and 4303
		ldxStx("#^"+label, DmaRegisters.A1BX.channel(channel)); // bank #
		
		ldxStx(dasxlValue, DmaRegisters.DASXL.channel(channel)); //size 512 bytes and 4306 register
		startDma(channel);
	}
	
	private void emptyValueLabels() {
		label("SpriteUpperEmpty"); // my sprite code assumes hi table of zero
		
		label("DMAZero", () -> {
			rawAsm(".word $0000");
		});
		
		label("SpriteEmptyVal", () -> {
			rawAsm(".byte 224");
		});
	}
	
	public void createInitAsmFile() {
		rawAsm(".segment \"CODE\"");
		
		label("NMI", () -> {
			bit(CpuRegisters.RDNMI); // it is required to read this register
			// in the NMI handler
			inc("in_nmi"); // size of A doesn't matter
			rti();
		});
		
		label("IRQ", () -> {
			bit(CpuRegisters.TIMEUP); // it is required to read this register in the IRQ handler
		});
		
		label("IRQ_end", () -> {
			rti();
		});
		
		reset();
		clearOam();
		emptyValueLabels();
		
		String fileName = super.getDevKitFolder()+"/framework/asm/includes/ca65/init.asm";
		FileManager.writeFile(super.getGeneratedCode(), fileName);
	}
}