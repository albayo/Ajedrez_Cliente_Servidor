package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaz.InterfazGrafica;

public class Partida {
	private InterfazGrafica i;
	private Player j1=null,j2=null;
	private boolean turno;
	public Partida(Socket s) {
		if(j1==null) j1=new Player(s,true);
		else j2=new Player(s,false);
		turno=true;
		i=new InterfazGrafica();
		while(j2==null) {}
		try {
			while(!fin()) {
			
				if(turno==j1.getColor()) {
					mover(j1);
					AtenderPeticion.serializar(i, new DataOutputStream(j2.getSocket().getOutputStream()));
				}
				else {
					mover(j2);
					AtenderPeticion.serializar(i, new DataOutputStream(j1.getSocket().getOutputStream()));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	public void mover(Player j) throws IOException {
		InterfazGrafica i2;
		AtenderPeticion.serializar(i, new DataOutputStream(j.getSocket().getOutputStream()));
		i2=AtenderPeticion.deserializar(new DataInputStream(j.getSocket().getInputStream()));
		i2.ejecutar();
		i2.setMovido(false);
		i=i2;
		
	}

}
