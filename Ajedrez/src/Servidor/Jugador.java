package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaz.InterfazGrafica;

public class Jugador {
	private static Integer cont;
	
	public static void main(String[] args) {
		if(cont==null) cont=0;
		else cont=1;
		String n="Serializar.txt";
		InterfazGrafica i=new InterfazGrafica();
		boolean turno=false;
		System.out.println(cont);
		if(cont==0) {
			turno=true;
			cont++;
		}
		else {
			cont--;
			turno=false;
		}
		try(Socket s =new Socket("localhost",80);	
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in =new DataInputStream (s.getInputStream());
			FileInputStream fin=new FileInputStream(n);
			FileOutputStream fout=new FileOutputStream(n);)

		{
			System.out.println(cont);
			System.out.println("Jugador "+ (cont));
			while(!i.fin()) {
				while(cont==0 ) {
					
					
				}
				i=AtenderPeticion.deserializar(in);
				i.ejecutar();
				System.out.println("ejecuta");
				//System.out.println(i.getTurno()+ " "+turno);
				while(i.getTurno()==turno) {
					
					
				}
				System.out.println(i.getTurno()+ " "+turno);
//				i.setEnabled(false);
				AtenderPeticion.serializar(i, out);
				System.out.println(cont);
				if(cont==0 )cont++;
				if(cont==1)cont--;
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

