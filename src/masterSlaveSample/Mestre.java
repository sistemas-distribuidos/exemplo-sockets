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
import java.util.ArrayList;

public class Mestre {

	public static void main(String[] args) throws Exception{
		int qtdeEscravo = 1;
		ArrayList<InetAddress> listaIp = new ArrayList<InetAddress>();
		for(int i=0;i<qtdeEscravo;i++){
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(new byte[128], 128);
			packet.setAddress(InetAddress.getByName("127.0.0.1"));
			packet.setPort(2000);
			packet.setData("QAP".getBytes());
			socket.send(packet);
			socket.receive(packet);
			String ip = packet.getSocketAddress().toString();
			ip = ip.substring(1).split(":")[0];
			System.out.println(ip);
			listaIp.add(InetAddress.getByName(ip));
		}
		
		for(InetAddress addr : listaIp){
			Socket sc = new Socket(addr, 2000);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()),true);
			out.println("1-1000");
			out.close();
			sc.close();
		}
		
		ServerSocket ss = new ServerSocket(2001);
		while(true){
			Socket sc = ss.accept();
			BufferedReader reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println(reader.readLine());
		}
		
		
		
		
		
	}
	
}
