package arquivo;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;


public class ServidorArquivo {

	public static void main(String[] args) throws IOException {

		// instancia o server socket, socket que escuta em uma porta
		ServerSocket sc = new ServerSocket(8888);
		// fica esperando pela conexão
		Socket socket = sc.accept();
		// pergunta pelo nome do arquivo que será salvo
		JFileChooser choser = new JFileChooser();
		choser.showSaveDialog(null);
		File arquivo = choser.getSelectedFile();
		// Stream usado para escrever o arquivo no disco
		BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(arquivo));

		int bt = 0;
		while ( ( bt = socket.getInputStream().read() ) != -1  ) {
			// escreve os bytes
			fout.write(bt);
		}
		fout.close();
		socket.close();
		sc.close();


	}




}
