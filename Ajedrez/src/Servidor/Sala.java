package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


import Juego.Juego;
import interfaz.InterfazGrafica;

public class Sala implements Runnable{

	private Socket con;
	private List<Partida> salas;
	public Sala(Socket s,List<Partida>l) {
		this.con=s;
		this.salas=l;
	}
	
	public static void serializar(InterfazGrafica i,DataOutputStream out) {
		try {
			ObjectOutputStream oos =new ObjectOutputStream (out);
			oos.writeObject(i);
			oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static InterfazGrafica deserializar(DataInputStream in) {
	
		InterfazGrafica i;
		try {
			ObjectInputStream oos =new ObjectInputStream (in);
			i=(InterfazGrafica)oos.readObject();
			i.addClick();
			oos.close();
			return i;
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		if(salas.isEmpty()) {
			System.out.println("constructor");
			salas.add(new Partida(con));
			System.out.println("crea");
		}
		else {
			boolean metido=false;
			for(int i=0;i<salas.size() && metido==false;i++) {
				if(salas.get(i).getJugador2()==null) {
					salas.get(i).addPlayer(con);
					metido=true;
					System.out.println("segundo jugadors");
					
					salas.get(i).jugar();
				}
			}
			if(metido==false) {
				Partida p=new Partida(con);
				salas.add(p);
			}
		}
		
	}
}


