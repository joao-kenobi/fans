package fans.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdRunner {
	   
	
	public static void main(String[] args) throws IOException {
		List<String> commands =  new ArrayList<String>();
		commands.add("cd C:/");
		commands.add("dir");
		run(commands);
	}
	
	public static String run(List<String> commands) {
		StringBuilder command = new StringBuilder();
		StringBuilder output = new StringBuilder(); 
		
		for (String c : commands) {
			command.append(c);
			command.append(" && ");			
		}
		
		command.setLength(command.length()-4);
		
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command.toString());
			builder.redirectErrorStream(true);
			Process process = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), "ISO-8859-1"));
			String line = null;
			
			
			while (true) {
				line = r.readLine();
				if (line == null) { 
					break;
				}
				
				output.append(line);
				System.out.println(line);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return output.toString();
	}
}
