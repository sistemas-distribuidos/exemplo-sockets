package exemploUDPMensagem;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServidorMensagemUDP {

	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(2000);
		while ( true ) {
			DatagramPacket packet = new DatagramPacket(new byte[512],512);
			System.out.println("Aguardando conexão...");
			socket.receive(packet);
			System.out.println("Informação recebida...");
			String mensagemRecebida = new String(packet.getData(),0, packet.getLength());
			System.out.println(packet.getAddress() + ":" + packet.getPort() + ">" + mensagemRecebida);
			packet.setData(("Resposta para " + packet.getAddress()).getBytes());
			socket.send(packet);
		}
	} 
}
