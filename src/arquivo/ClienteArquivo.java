package arquivo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ClienteArquivo {

	public static void main(String[] args) throws IOException {

		
		JFileChooser choser = new JFileChooser();
		choser.showOpenDialog(null);
		File arquivo = choser.getSelectedFile();
		
		String ip = JOptionPane.showInputDialog("Digite o IP para envio:");
		
		
		Socket socket = new Socket(ip, 8888);

		BufferedInputStream fin = new BufferedInputStream(
									new FileInputStream(arquivo));

		int bt = 0;
		while ( ( bt = fin.read()) != -1   )
			socket.getOutputStream().write(bt);
		
		fin.close();
		socket.close();

	}

}
