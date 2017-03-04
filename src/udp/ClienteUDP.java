package udp;

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

public class ClienteUDP {

	public static void main(String[] args) throws Exception{
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(new byte[128], 128);
		packet.setAddress(InetAddress.getByName("127.0.0.1"));
		packet.setPort(2000);
		packet.setData("QAP".getBytes());
		socket.send(packet);
		socket.receive(packet);
		String ip = packet.getSocketAddress().toString();
		System.out.println("IP envio mensagem: "+ip);
	}
	
}
