package exemploUDPDistribuido;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Escravo primo.
 * @author romulofc
 *
 */
public class RecebePrimos {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		DatagramSocket socket = new DatagramSocket(2001);
		
		DatagramPacket hello = new DatagramPacket(new byte[128],128);
		socket.receive(hello);
		
		String msgHello = new String(hello.getData(),0,hello.getLength());
		if ( msgHello.equals("HELLO")) {
			
			hello.setData("IN".getBytes());
			socket.send(hello);

			
			DatagramPacket pacoteIntervalo = new DatagramPacket(new byte[128],128);
			socket.receive(pacoteIntervalo);
			
			String intervalo = new String( pacoteIntervalo.getData(), 0,pacoteIntervalo.getLength() );
			
			String[] intStr = intervalo.split("/");
			
			int x = new Integer(intStr[0]);
			int y = new Integer(intStr[1]);
			
			int n = 4; // numero de threads
			
			int intervaloThread = (y-x)/n;
			
			PrimoThread[] threads = new PrimoThread[n];
			for (int i = 0; i < n; i++) {
				threads[i] = new PrimoThread(x+intervaloThread*i,x+intervaloThread*(i+1));
				threads[i].start();
			}
			
			int totalPrimos = 0;
			for (int i = 0; i < n; i++) {
				threads[i].join();
				totalPrimos+=threads[i].getTotalPrimos();
			}
			
			pacoteIntervalo.setData(String.valueOf(totalPrimos).getBytes());
			socket.send(pacoteIntervalo);
			
		}
		
	}	
}