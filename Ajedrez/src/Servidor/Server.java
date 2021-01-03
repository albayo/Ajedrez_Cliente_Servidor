package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static void main(String[] args) {
		final CyclicBarrier starter = new CyclicBarrier(2);
		ExecutorService pool = Executors.newCachedThreadPool();
		ServerSocket socket;
		int contador=0;
		try {
			socket = new ServerSocket(80);
			System.out.println("Soy server");
			while (true) {
				 Socket con1 = null;
				 Socket con2=null;
				if(contador==0) {
					con1 = socket.accept();
					contador++;
					System.out.println("contador = 1");
				}
				if(contador==1) {
					con2 = socket.accept();
					contador--;
					if(con1!=null && con2!=null) {
						System.out.println("contador = 2");
						AtenderPeticion a=new AtenderPeticion(starter,con1,con2);
						
						pool.execute(a);
						
						//starter.await();
						//starter.reset();
						System.out.println("fin");
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}finally {
			pool.shutdown();
		}
	}
	
}
