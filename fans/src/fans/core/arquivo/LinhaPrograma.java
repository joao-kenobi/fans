package fans.core.arquivo;

import java.util.ArrayList;
import java.util.List;

public class LinhaPrograma {
	public String programCounter;
	public String label = "";
	public String opcode;
	public String parametroOpcode;
	public String enderecoJump = "";
	
	private static List<String> opcodesJump = new ArrayList<String>();
	
	static {		
		opcodesJump.add("bcc");
		opcodesJump.add("bcs");
		opcodesJump.add("beq");
		opcodesJump.add("bmi");
		opcodesJump.add("bne");
		opcodesJump.add("bpl");
		opcodesJump.add("bra");
		opcodesJump.add("brl");
		opcodesJump.add("bvc");
		opcodesJump.add("bvs");
		opcodesJump.add("jml");
		opcodesJump.add("jmp");
		opcodesJump.add("jsl");
		opcodesJump.add("jsr");
	}
	
	
	public void preencher(String linha) {
		if (linha.contains("mvn")) {
			String[] partesLinha = linha.split(" ");
			linha = "";
			
			for (int i = 0; i < partesLinha.length; i++) {
				if (i == 2) {
					linha += partesLinha[i]+partesLinha[i+1];
					i++;
				}
				else {					
					linha += partesLinha[i]+" ";
				}
				
			}
		}
		
		String[] partesLinha = linha.split(" ");
		
		programCounter = partesLinha[0].trim();
		opcode = partesLinha[1].trim();
		parametroOpcode = partesLinha[2].trim().toUpperCase();
		
		if (isLinhaContemJump(opcode)) {
			enderecoJump = obterEnderecoJump(linha);
		}
	}
	
	private static boolean isLinhaContemJump(String linha) {
		for (String opcode : opcodesJump) {
			
			if (linha.toLowerCase().contains(opcode)) {
				return true;
			}
		}
		
		return false;
	}
	
	private String obterEnderecoJump(String linha) {
		int i1 = linha.indexOf("[")+1;
		int i2 = linha.indexOf("]");
		
		return linha.substring(i1, i2);
	}
}
