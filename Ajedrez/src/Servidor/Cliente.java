package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Juego.Casilla;
import interfaz.InterfazGrafica;

public class Cliente {
	public static void main(String[] args) {
		boolean mostrado=false;
		boolean turno;
		Casilla [] casillas=new Casilla[2];
		InterfazGrafica i=new InterfazGrafica();
		
		
		try(Socket s =new Socket("localhost",80);	
			ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in =new ObjectInputStream (s.getInputStream());)
		{
			while(!i.fin() || i==null) {
				System.out.println("antesd de leer");
				casillas=(Casilla[]) in.readObject();
				System.out.println("leido");
				
				if(!mostrado) {
					i.mostrar();
					mostrado=true;					
					
					if(casillas!=null)	i.mover(casillas);
					
					while(i.getMovido()==false) {
					}
					casillas=i.getCasillas();
					System.out.println("antes de enviar");
					out.writeObject(casillas);
					System.out.println("enviar");
					i.setMovido(false);
				}
				else {
					if(casillas!=null) {
						i.mover(casillas);
						while(i.getMovido()==false) {}
						casillas=i.getCasillas();
						out.writeObject(casillas);
						i.setMovido(false);
					}
				}		

				
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
			
			
			
			
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
