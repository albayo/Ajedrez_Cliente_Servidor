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
			
				casillas=(Casilla[]) in.readObject();
				
				
				if(!mostrado) {
					i.mostrar();
					mostrado=true;					

					if(casillas!=null)	{
//						System.out.println("MUEVE");
						i.setMovido(false);
						i.mover(casillas);
						i.setMovido(false);
					}
					
					while(i.getMovido()==false) {
						Thread.sleep(100);

					}
					casillas=i.getCasillas();
					
					out.writeObject(casillas);
					
				}
				else {
					if(casillas!=null) {
						i.setMovido(false);
						i.mover(casillas);
						i.setMovido(false);
						while(i.getMovido()==false) {
							Thread.sleep(100);
						}
						
						casillas=i.getCasillas();
						out.writeObject(casillas);
						
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
