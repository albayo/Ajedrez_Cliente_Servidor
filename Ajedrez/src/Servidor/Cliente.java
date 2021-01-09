package Servidor;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.Casilla;
import interfaz.InterfazGrafica;

public class Cliente {
	public static void main(String[] args) {
		boolean mostrado=false;
		boolean turno;
		Casilla [] casillas=new Casilla[2];
		InterfazGrafica i=new InterfazGrafica();
//		JLabel j=new JLabel("Oponente Desconectado");
//		JFrame jp=new JFrame();
		try(Socket s =new Socket("localhost",80);	
			ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in =new ObjectInputStream (s.getInputStream());)
		{
			System.out.println("¡¡BIENVENIDO AL AJEDREZ ONLINE!!");
			System.out.println("ESPERANDO A UN RIVAL DIGNO");
			while(!i.fin()) {
			
				casillas=(Casilla[]) in.readObject();
				if(!mostrado) {
					
					i.mostrar();
					mostrado=true;					
					if(casillas==null){
						System.out.println("¡¡ERES BLANCAS!!");
						System.out.println("MUEVE, TU RIVAL YA ESTÁ EN LINEA");
						}
					if(casillas!=null)	{
						System.out.println("¡¡ERES NEGRAS!!");
						System.out.println("MUEVE, TU RIVAL YA ESTÁ EN LINEA");
						i.setMovido(false);
						i.mover(casillas);
						i.setMovido(false);
					}
					
					while(i.getMovido()==false) {
						Thread.sleep(1);
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
					else {
						System.out.println("OPONENTE DESCONECTADO");
						System.out.println("¡¡HAS GANADO!!");
//						j.setText("OPONENTE DESCONECTADO,HAS GANADO!!");
//						jp.add(j);
//						jp.pack();
//						jp.setPreferredSize(new Dimension(1000,1000));
//						int d=(int) (i.getLocation().getX()+250);
//						int y=(int) (i.getLocation().getY()+270);
//						jp.setLocation(d,y);
//						jp.setVisible(true);
					}
				}		

				
			}
		}	
			
			
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
