package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Juego.Juego;
import interfaz.InterfazGrafica;

public class Server2 {
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		ServerSocket socket =null;
		Socket con1 = null;
		List<Partida> salas=new ArrayList<>();
		try {
			socket = new ServerSocket(80);
			while (true) {
				try {
					con1 = socket.accept();
					Sala s=new Sala(con1,salas);
					pool.execute(s);
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}finally {
			pool.shutdown();
		}
	}
	
}
