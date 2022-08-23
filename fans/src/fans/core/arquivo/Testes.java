package fans.core.arquivo;

import java.util.ArrayList;
import java.util.List;

public class Testes {
	public static void main(String[] args) {
		List<String> linhasArquivo = FileManager.readFile("C:/ambiente_desenvolvimento/Bradesco/pessoas/Marcela/planilhas_oferta_negocio/20_04_2022/planilha/FP_GERAL_UPLOAD_MAIO21_agoravai.csv");
		
		List<String> lista = new ArrayList<String>();
		
		for (String linha : linhasArquivo) {
			String[] partes = linha.split(";");
			
			String cotacao = partes[0]+"-"+partes[1];
			
			//if (lista.contains(cotacao)) {
				//System.out.println(cotacao);
				System.out.println("SELECT NSTUDO_RCUPC_PROPN_COTAC FROM DBPROD.RCUPC_PROPN_COTAC WHERE NSTUDO_RCUPC_PROPN_COTAC = "+partes[0]+" AND NCOTAC_RCUPC_PROPN = "+partes[1]+";");
			//}
			
			lista.add(cotacao);
		}
	}
}
