package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaz.InterfazGrafica;

public class Cliente {
	public static void main(String[] args) {
		boolean end=false;
		String cadena;
		try(Socket s =new Socket("localhost",80);	
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in =new DataInputStream (s.getInputStream());)
			//FileInputStream fin=new FileInputStream(n);
			//FileOutputStream fout=new FileOutputStream(n);)

		{
			
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
}
