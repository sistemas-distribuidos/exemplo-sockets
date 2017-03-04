package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClienteTCP {

	public static void main(String[] args) throws Exception {
		Socket sc = new Socket("127.0.0.1", 2000);
		String msg = JOptionPane.showInputDialog("Digita a MSG:");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				sc.getOutputStream()), true);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				sc.getInputStream()));
		out.println(msg);
		System.out.println(reader.readLine());
		out.close();
		reader.close();
		sc.close();
	}
}