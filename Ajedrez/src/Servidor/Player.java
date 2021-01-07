package Servidor;

import java.net.Socket;

public class Player {
	private Socket s;
	private boolean color;
	public Player(Socket s, boolean color) {
		this.s = s;
		this.color = color;
	}
	public Socket getSocket() {
		return s;
	}
	public void setSocket(Socket s) {
		this.s = s;
	}
	public boolean getColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	
}
