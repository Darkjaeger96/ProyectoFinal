package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logs {
	
	public static void crearLog(String accion, String id){
		
		try {
			FileWriter ficheroEscribir = new FileWriter("logs/bd.log");
			BufferedWriter outWriter = new BufferedWriter(ficheroEscribir);
			
			Date date = new Date();
			String printInFile = date.toString() + "\t" + accion + "\t" + id;
			
			outWriter.write(printInFile);
			outWriter.newLine();
			outWriter.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String leerFicheroLog() throws IOException{
		String cadenaFinal = "";
		FileReader ficheroLeer = new FileReader("logs/bd.log");
		BufferedReader b = new BufferedReader(ficheroLeer);
		String cadena;
		while((cadena = b.readLine())!= null) {
			cadenaFinal += cadena + "\n";
		}
		b.close();
		return cadenaFinal;
	}
}