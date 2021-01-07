package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import interfaz.InterfazGrafica;

public class Cliente {
	public static void main(String[] args) {
		boolean mostrado=false;
		boolean turno;
		InterfazGrafica i=new InterfazGrafica();
		
		
		try(Socket s =new Socket("localhost",80);	
			ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in =new ObjectInputStream (s.getInputStream());)
		{
			while(!i.fin() || i==null) {

				i=(InterfazGrafica) in.readObject();
				
				if(i!=null) {
					System.out.println("leido");
					if(mostrado==false) {
						i.mostrar();
						
						mostrado=true;
					}
					i.pintar();
					
					i.addClick();
					
					while(i.getMovido()==false ) {
//						if(i.fin()) break;
					}
					
					out.writeObject(i);
				}
			}
//			if(i.getJuego().mate(true)){
//
//				System.out.println("GANAN NEGRAS");
//			}
//			else if(i.getJuego().mate(false)){
//
//				System.out.println("GANAN BLANCAS");
//			}
//			else if(i.getJuego().ahogado(true) || i.getJuego().ahogado(false)) {
//				System.out.println("TABLAS");
//			}
			
			
			
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}
