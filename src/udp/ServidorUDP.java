package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	
	public static void main(String[] args) throws Exception {
		DatagramPacket packet = new DatagramPacket(new byte[256], 256);
		DatagramSocket socket = new DatagramSocket(2000);
		socket.receive(packet);
		String ip = packet.getSocketAddress().toString();
		ip = ip.split(":")[0];
		packet.setData(ip.getBytes());
		socket.send(packet);
		socket.close();
	}
}
