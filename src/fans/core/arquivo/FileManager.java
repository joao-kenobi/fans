package fans.core.arquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileManager {
    private static final int BUFFER = Byte.MAX_VALUE;

    public static List<String> readFile(String caminhoArquivo) {
        List<String> linhas = new ArrayList<String>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream (caminhoArquivo), "ISO-8859-1"));
            String linha = null;

            while ((linha = reader.readLine()) != null) {
                linhas.add(linha.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return linhas;
    }
    
    public static void writeFile(String content, String fileName) {
    	try {
			FileWriter myWriter = new FileWriter(fileName);
			myWriter.write(content);
			myWriter.close();
		}
    	catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void adicionarArquivoAoZip(String nomeArquivo, InputStream is, ZipOutputStream zout) throws IOException {
        zout.putNextEntry(new ZipEntry(nomeArquivo));

        int read = 0;
        byte[] bytes = new byte[BUFFER];
        
        while ((read = is.read(bytes)) != -1) {
            zout.write(bytes, 0, read);
        }
    }
    
    public static String getCurrentProjectDirectory() {
    	return System.getProperty("user.dir").replaceAll("\\\\", "/");
	}
}