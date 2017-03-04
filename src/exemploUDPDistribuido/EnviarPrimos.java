package exemploUDPDistribuido;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * Mestre primo.
 * @author romulofc
 *
 */
public class EnviarPrimos {

	public static void main(String[] args) throws IOException {
		
		int x = 1;
		int y = 10000000;
		
		
		DatagramPacket hello = new DatagramPacket(new byte[128],128);
		hello.setData("HELLO".getBytes());
		hello.setAddress(InetAddress.getByName("127.0.0.1"));
		hello.setPort(2001);
		
		DatagramSocket socket = new DatagramSocket();
		socket.send(hello);
		
		
		ArrayList<DatagramPacket> escravos = new ArrayList<DatagramPacket>();
		
		// esperar as respostas
		boolean aguardando = true;
		
		socket.setSoTimeout(5000);
		while (aguardando) {
			DatagramPacket in = new DatagramPacket(new byte[128],128);
			try {
				socket.receive(in);
				escravos.add(in);
			} catch (SocketTimeoutException e) {
				aguardando = false;
				continue;
			}
			System.out.println(in.getAddress() + " está na rede de calculo");
		}
		
		System.out.println("Enviando intervalos para " + escravos.size() + " escravos");
	
		int intervalo = (y-x)/escravos.size();
		
		for ( int i = 0; i < escravos.size(); i++ ) {
			
			int inicio = x + intervalo*i;
			int fim = x + intervalo*(i+1);
			
			String intervaloStr = inicio + "/" + fim;
			
			DatagramPacket pacoteIntervalo = new DatagramPacket(new byte[128],128);
			pacoteIntervalo.setData(intervaloStr.getBytes());
			pacoteIntervalo.setAddress( escravos.get(i).getAddress() );
			pacoteIntervalo.setPort( escravos.get(i).getPort());
			
			socket.send(pacoteIntervalo);
			System.out.println("Enviado intervalo " + intervaloStr + " para " + pacoteIntervalo.getAddress() );
		}
 		
		socket.setSoTimeout(60000);
		
		int totalPrimos = 0;
		
		for ( int i = 0; i < escravos.size(); i++ ) {
		
			DatagramPacket respTotal = new DatagramPacket(new byte[128],128);
			socket.receive(respTotal);
			String resp = new String(respTotal.getData(),0,respTotal.getLength());
			System.out.println( respTotal.getAddress() + " - " + resp + " primos"  );
			totalPrimos += new Integer(resp);
			
		}
		
		System.out.println("Total de Primos: " + totalPrimos);
		
	}	
}