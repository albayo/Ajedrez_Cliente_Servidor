package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaz.InterfazGrafica;

public class Jugador {

	public static void main(String[] args) {
		String n="Serializar.txt";
		InterfazGrafica i=new InterfazGrafica();
		try(Socket s =new Socket("localhost",80);	
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in =new DataInputStream (s.getInputStream());
			FileInputStream fin=new FileInputStream(n);
			FileOutputStream fout=new FileOutputStream(n);)

		{
			while(!i.fin()) {
				System.out.println("Soy el 2");
				i.setEnabled(true);
				AtenderPeticion.recibir(fout, in);
				System.out.println("hola");
				i=AtenderPeticion.deserializar(n);
				i.ejecutar();
				System.out.println("ejecuta");
				boolean turno=i.getTurno();
				if(i.getTurno()!=turno) {
					i.setEnabled(false);
				}
				AtenderPeticion.serializar(i, n);
				AtenderPeticion.leerDeFichero(fin, out);
				
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

