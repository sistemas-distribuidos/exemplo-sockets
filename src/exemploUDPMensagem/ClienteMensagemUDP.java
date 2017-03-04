package exemploUDPMensagem;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;


public class ClienteMensagemUDP {

	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket pacote = new DatagramPacket(new byte[512],512);
		//Informando o IP do destinatário
		//pacote.setAddress(InetAddress.getByName("127.0.0.1")); //local
		//pacote.setAddress(InetAddress.getByName("10.14.1.26")); //servidor
		pacote.setAddress(InetAddress.getByName("10.14.15.255")); //broadcast
		//Informando a porta do destinatário
		pacote.setPort(2000);
		String mensagem = JOptionPane.showInputDialog("Digite a mensagem.");
		pacote.setData(mensagem.getBytes());
		System.out.println("Enviando mensagem...");
		socket.send(pacote);
		pacote = new DatagramPacket(new byte[512],512);
		System.out.println("Aguardando resposta...");
		socket.receive(pacote);
		System.out.println("Mensagem Recebida...");
		System.out.println(new String(pacote.getData()));
		
	}
	
}

