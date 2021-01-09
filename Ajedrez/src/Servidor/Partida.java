package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import Juego.Casilla;
import interfaz.InterfazGrafica;

public class Partida {
	private InterfazGrafica i;
	private Casilla[] casillas;
	private Player j1=null,j2=null;
	private boolean turno;

	public InterfazGrafica getInterfaz() {
		return i;
	}
	public Partida(Socket s) {
		if(j1==null) j1=new Player(s,true);
		else j2=new Player(s,false);
		turno=true;

	}
	public void addPlayer(Socket s) {
		j2=new Player(s,false);
	}
	public Player getJugador1() {
		return j1;
	}
	public void setJugador1(Player j1) {
		this.j1 = j1;
	}
	public Player getJugador2() {
		return j2;
	}
	public void setJugador2(Player j2) {
		this.j2 = j2;
	}

	public boolean fin() {
		return i.fin();
	}

	public void jugar() {
		i=new InterfazGrafica();
		casillas=null;
		ObjectOutputStream out1=null;
		ObjectOutputStream out2=null;
		try {
			out1=new ObjectOutputStream(j1.getSocket().getOutputStream());
			ObjectInputStream in1=new ObjectInputStream(j1.getSocket().getInputStream());
			out2=new ObjectOutputStream(j2.getSocket().getOutputStream());
			ObjectInputStream in2=new ObjectInputStream(j2.getSocket().getInputStream());

			while(!fin()) {
				out1.writeObject(casillas);

				casillas=(Casilla[]) in1.readObject();
				i.setMovido(false);
				i.mover(casillas);


				out2.writeObject(casillas);
				casillas=(Casilla[]) in2.readObject();
				i.setMovido(false);
				i.mover(casillas);

			}

			if(i.getJuego().mate(true)){

				out1.writeObject(casillas);
			}
		}catch(SocketException e1) {
			try {
				if(i.getTurno())out2.writeObject(null);

				else out1.writeObject(null);
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}



}
