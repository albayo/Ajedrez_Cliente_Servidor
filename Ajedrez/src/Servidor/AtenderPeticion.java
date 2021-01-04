package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JFrame;

import interfaz.InterfazGrafica;


public class AtenderPeticion implements Runnable{
	private  CyclicBarrier c;
	private Socket con1,con2;	
	public AtenderPeticion(CyclicBarrier c,Socket s,Socket s2) {

		this.c = c;
		this.con1=s;
		this.con2=s2;
	}
	public static void main(String[] args) throws IOException {
		//AtenderPeticion a =new AtenderPeticion(new CyclicBarrier(2+1),new Socket());
		InterfazGrafica i=new InterfazGrafica();
		AtenderPeticion.serializar(i, "Serializar.txt");
		i=AtenderPeticion.deserializar("Serializar.txt");
		
//		FileOutputStream out=new FileOutputStream("Hola.txt");
//		DataInputStream in=new DataInputStream(new FileInputStream("Serializar.txt"));
			
//		AtenderPeticion.recibir(out, in);
		
		FileInputStream in= new FileInputStream("Serializar.txt");
		DataOutputStream out=new DataOutputStream(new FileOutputStream("hola.txt"));
		
		AtenderPeticion.leerDeFichero(in, out);
		i=AtenderPeticion.deserializar("hola.txt");
		i.ejecutar();
		
		
	}
	public static void leerDeFichero(FileInputStream in, DataOutputStream out) throws IOException {
		byte [] buff= new byte[132*32];
		int leidos=in.read(buff);
		while(leidos!= -1) {
			out.write(buff,0,leidos);
			leidos=in.read(buff);
		}
	}
	public static void recibir(FileOutputStream out,DataInputStream in) throws IOException {
		byte [] buff= new byte[132*32];
		int leidos=in.read(buff);
		while(leidos!= -1) {
			out.write(buff,0,leidos);
			leidos=in.read(buff);
		}
	}
	public void atenderPeticion() {
		try {
			InterfazGrafica i=new InterfazGrafica();
			DataInputStream in1=new DataInputStream(con1.getInputStream());
			DataOutputStream out1=new DataOutputStream(con1.getOutputStream());
			
			DataInputStream in2=new DataInputStream(con2.getInputStream());
			DataOutputStream out2=new DataOutputStream(con2.getOutputStream());
			
			FileInputStream fin=new FileInputStream("Serializar.txt");
			FileOutputStream fout=new FileOutputStream("Serializar.txt");
			
			while(!i.fin()) {
				AtenderPeticion.serializar(i, "Serializar.txt");
				System.out.println("Serializa");
				leerDeFichero(fin, out1);
				System.out.println("lee");
				recibir(fout,in1);
				System.out.println("recibe");
				leerDeFichero(fin, out2);
				System.out.println("lee2");
				recibir(fout,in2);
				System.out.println("recibe2");
				i=AtenderPeticion.deserializar("Serializar.txt");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void serializar(InterfazGrafica i,String n) {
		
		File s2=new File(n);
		s2.delete();
		File s=new File(n);
		try {
			FileOutputStream f=new FileOutputStream(s);
			ObjectOutputStream oos =new ObjectOutputStream (f);
			oos.writeObject(i);
			oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static InterfazGrafica deserializar(String n) {
		File s=new File(n);
		InterfazGrafica i;
		try {
			FileInputStream f=new FileInputStream(s);
			ObjectInputStream oos =new ObjectInputStream (f);
			//if(oos.se)
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		try {
			
			//c.await();
			System.out.println("atender");
			atenderPeticion();
		
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BrokenBarrierException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
