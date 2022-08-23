package fans.core.enums;

import fans.core.interfaces.IRegisterAddress;

/**
 * reference: https://wiki.superfamicom.org/registers
 * 
 *
 */
public enum BusRegisters implements IRegisterAddress {
	INIDISP("$2100"),
	
	
	/**
	 <pre>
name: BG Mode and Character Size<br>
address: $2105
explanation:

 $2105  wb+++-
    DCBAemmm

    A/B/C/D   = BG character size for BG1/BG2/BG3/BG4
         mmm  = BG Mode
        e     = Mode 1 BG3 priority bit
        Mode     BG depth  OPT  Priorities
                 1 2 3 4        Front -> Back
        -=-------=-=-=-=----=---============---
         0       2 2 2 2    n    3AB2ab1CD0cd
         1       4 4 2      n    3AB2ab1C 0c
                    * if e set: C3AB2ab1  0c
         2       4 4        y    3A 2B 1a 0b
         3       8 4        n    3A 2B 1a 0b
         4       8 2        y    3A 2B 1a 0b
         5       4 2        n    3A 2B 1a 0b
         6       4          y    3A 2  1a 0
         7       8          n    3  2  1a 0
         7+EXTBG 8 7        n    3  2B 1a 0b
    </pre>
	 */
	BGMODE("$2105"),
	/**
	 * name: BG1 Tilemap Address and Size<br>
	 * address: $2107
	 */
	BG1SC("$2107"),
	BG2SC("$2108"),
	BG3SC("$2109"),
	BG4SC("$210A"),
	/**
	 * name: BG1 and 2 Chr Address<br>
	 * address: $210b
	 */
	BG12NBA("$210b"),
	BG34NBA("$210c"),
	/**
	 * $2121
	 */
	CGADD("$2121"),
	
	/**
	<pre>
	name: CGRAM Address<br>
	
	$2122  ww+++-
        -bbbbbgg gggrrrrr
This writes to CGRAM, effectively setting the palette colors. Accesses to CGRAM are handled just like accesses to the low table of OAM, see $2104 for details. Note that the color values are stored in BGR order.
	</pre>
	 */
	CGDATA("$2122"),
	/***
<b>Video Port Control</b> 
<pre>
  $2115  wb++?<>
    i---mmii
    i          = Address increment mode^:
                 0 => increment after writing $2118/reading $2139
                 1 => increment after writing $2119/reading $213A
          ii   = Address increment amount
                 00 = Normal increment by 1
                 01 = Increment by 32
                 10 = Increment by 128
                 11 = Increment by 128
        mm     = Address remapping
                 00 = No remapping
                 01 = Remap addressing aaaaaaaaBBBccccc => aaaaaaaacccccBBB
                 10 = Remap addressing aaaaaaaBBBcccccc => aaaaaaaccccccBBB
                 11 = Remap addressing aaaaaaBBBccccccc => aaaaaacccccccBBB
</pre>
	 */
	VMAIN("$2115"),
	VMADDL("$2116"),
	/**
	 * $2118
	 */
	VMDATAL("$2118"),
	VMDATAH("$2119"),
	TM("$212C"),
	RDNMI("$4210"),
	JOY1H("$4219"),
	/**
	 * OAM Address Registers (Low) 	$2102 	OAMADDL 	single 	write 	f-blank, v-blank
	 */
	OAMADDL("$2102"),
	/**
	 * OAM Data Write Register 	$2104 	OAMDATA 	single 	write 	f-blank, v-blank
	 */
	OAMDATA("$2104"),
	
	/**
	Object Size and Character Size Register<br><br>
	Address: $2101<br>
	Style: single<br>
	Access: write<br>
	Timing: f-blank, v-blank<br>
	
 <pre>
 $2101  wb++?- 
     sssnnbbb
     sss       = Object size:
           000 =  8x8  and 16x16 sprites
           001 =  8x8  and 32x32 sprites
           010 =  8x8  and 64x64 sprites
           011 = 16x16 and 32x32 sprites
           100 = 16x16 and 64x64 sprites
           101 = 32x32 and 64x64 sprites
           110 = 16x32 and 32x64 sprites ('undocumented')
           111 = 16x32 and 32x32 sprites ('undocumented')
        nn     = Name Select
          bbb  = Name Base Select (Addr>>14)
 </pre>
	 */
	OBSEL("$2101")
	;
	
	private String address;
	
	private BusRegisters(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
}
