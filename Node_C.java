import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Node_C {
	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the port number: ");
		int port_no=sc.nextInt();

		ServerSocket ss=new ServerSocket(port_no);
		Socket s=ss.accept();
		System.out.println("--Node C online--");
		
		DataInputStream dis=new DataInputStream(s.getIntputStream());
		String msg_received=(String)dis.readUTF();
		System.out.println("Message Received:\n"+msg_received);

		ss.close();

	}
}