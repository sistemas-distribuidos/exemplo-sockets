package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(2000);
		Socket sc = ss.accept();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()),true);
		System.out.println("Lendo...");
		System.out.println(reader.readLine());
		System.out.println("Escrevendo...");
		out.println("Num Rand. " + Math.random());
		
		reader.close();
		out.close();
		sc.close();
		ss.close();
	}
}
