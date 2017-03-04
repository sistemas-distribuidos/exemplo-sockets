package masterSlaveSample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Escravo {
	
	public static void main(String[] args) throws Exception {
		//INICIO Aguardando UDP
		DatagramPacket packet = new DatagramPacket(new byte[256], 256);
		DatagramSocket socket = new DatagramSocket(2000);
		socket.receive(packet);
		String ip = packet.getSocketAddress().toString();
		ip = ip.split(":")[0];
		packet.setData(ip.getBytes());
		socket.send(packet);
		socket.close();
		//FIM Aguardando UDP
		
		//INICIO Escutando TCP
		ServerSocket ss = new ServerSocket(2000);
		Socket sc = ss.accept();
		InetAddress masterIp = sc.getInetAddress();
		BufferedReader reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
		String limite = reader.readLine();
		
		reader.close();
		sc.close();
		ss.close();
		//FIM Escutando TCP
		System.out.println(limite);
		int limiteInicial = Integer.valueOf(limite.split("-")[0]);
		int limiteFinal = Integer.valueOf(limite.split("-")[1]);
		//calcula primo
		
		int totalPrimo = 0;
		//INICIO Responder total TCP
		Socket scResposta = new Socket(masterIp,2001);
		PrintWriter outStream = new PrintWriter(new OutputStreamWriter(scResposta.getOutputStream()));
		outStream.write("total|"+totalPrimo);
		outStream.close();
		scResposta.close();
		//FIM Responder total TCP
	}
}
