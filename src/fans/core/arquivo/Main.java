package fans.core.arquivo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
	
	static List<String> opcodesJump = new ArrayList<String>();
	
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
		opcodesJump.add("jmp");
		opcodesJump.add("jsl");
	}
	
	/**
	 * 1) Encontrar um "jump", guardar o endereco
	 * 2) Se encontrar outro "jump" e este tiver o mesmo endereco do anterior
	 * 	2.1) Verificar se o endereco da proxima linha tambem e igual
	 * 		2.2) Se for igual, ignorar todas as linha a partir dai ate que a linha apos o "jump" nao tenha o mesmo endereco do passo 1
	 * 
	 */
//	public static void main(String[] args) {
//		
//		GerenciadorArquivo gerenciadorArquivo = new GerenciadorArquivo();
//		List<String> linhasArquivo = gerenciadorArquivo.lerArquivo("C:/ambiente_desenvolvimento/test/snes-lab/snes-lab/emuladores/Mesen-S/Debugger/splits/teste.txt");
//		
//		Set<String> enderecosProcurar = new LinkedHashSet<String>();
//		String enderecoJumpEncontrado = "";
//		boolean maisDeUmJumpEncontradoParaMesmoEndereco = false;
//		
//		int i = 0;
//		for (String linha : linhasArquivo) {
//			String[] trechosLinha = linha.split(" ");
//			
//			if (maisDeUmJumpEncontradoParaMesmoEndereco) {
//				boolean isJumpEncontrado = false;
//				
//				for (String opcode : opcodesJump) {	
//					isJumpEncontrado = linha.toLowerCase().contains(opcode);
//					
//					if (isJumpEncontrado) {						
//						break;
//					}
//				}
//				
//				if (isJumpEncontrado) {
//					if (i < linhasArquivo.size()-1) {
//						String proximaLinha = linhasArquivo.get(i+1);
//						String endereco = obterEnderecoProcurar(trechosLinha);
//						
//						if (!endereco.equals(enderecoJumpEncontrado)) {
//							//System.out.println(linha.trim());
//						}
//					}
//				}
//				else {
//					continue;
//				}
//			}
//			else {				
//				
//				for (String opcode : opcodesJump) {
//					
//					boolean isJumpEncontrado = linha.toLowerCase().contains(opcode);
//					
//					if (isJumpEncontrado) {
//						// passo 1
//						String enderecoJump = obterEnderecoProcurar(trechosLinha);
//						
//						if (enderecoJumpEncontrado.equals(enderecoJump)) {
//							maisDeUmJumpEncontradoParaMesmoEndereco = true;
//							continue;
//						}
//						
//						enderecoJumpEncontrado = enderecoJump;
//						break;
//					}
//				}
//			}
//			
//			
//			System.out.println(linha.trim());
//			i++;
//		}
//	}
	
public static void main(String[] args) {
	
		//List<String> linhasArquivo = GerenciadorArquivo.lerArquivo("C:/ambiente_desenvolvimento/test/snes-lab/snes-lab/emuladores/Mesen-S/Debugger/splits/teste.txt");
	List<String> linhasArquivo = FileManager.readFile("C:/ambiente_desenvolvimento/test/snes-lab/snes-lab/emuladores/Mesen-S/Debugger/splits/Trace_2_000004.txt");
		
		Set<String> enderecos = new LinkedHashSet<String>();
		List<String> linhas = new ArrayList<String>();
		
		boolean foiEncontradoUmJump = false;
		boolean foiEncontradoUmLoop = false;
		
		String enderecoJumpAnterior = "";
		
		for (String linha : linhasArquivo) {
			String[] trechosLinha = linha.split(" ");
			String programCounter = trechosLinha[0].trim();
			
			
			if (!foiEncontradoUmLoop) {				
				if (foiEncontradoUmJump) {
					enderecos.add(programCounter);
				}
				
				if (isLinhaContemJump(linha)) {
					String enderecoJump = obterEnderecoJump(trechosLinha);
					//linha = linha.replace("$", "").replace(enderecoJump, "lbl_"+enderecoJump+"_"+i);
					
					if (enderecoJumpAnterior.equals(enderecoJump)) {
						foiEncontradoUmLoop = true;
					}
					
					enderecos.add(programCounter);
					foiEncontradoUmJump = true;
					enderecoJumpAnterior = enderecoJump;
				}
				
				linhas.add(linha);
			}
			else {
				if (!enderecos.contains(programCounter)) {
					linhas.add(linha);
				}
			}
		}
		
		for (String linha : linhas) {
			System.out.println(linha);
		}
	}

	private static String obterEnderecoJump(String[] trechosLinha) {
		String enderecoProcurar = "";
		
		if (trechosLinha.length > 3) {
			enderecoProcurar = trechosLinha[3].trim().replace("[", "").replace("]", "").replace("$", "");
		}
		else {
			enderecoProcurar = trechosLinha[2].replace("$", "");
		}
		
		return enderecoProcurar;
	}
	
	private static boolean isLinhaContemJump(String linha) {
		for (String opcode : opcodesJump) {
			
			if (linha.toLowerCase().contains(opcode)) {
				return true;
			}
		}
		
		return false;
	}
}
