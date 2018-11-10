import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Node_A{
	public static void main(String args[]) throws Exception{

		Scanner sc=new Scanner(System.in);
		
		System.out.print("Host IP: ");
		String host_ip=sc.nextLine();
		System.out.print("Port Number: ");
		String port_no=sc.nextLine();
		
		//connecting to other node
		Socket s = new Socket(host_ip,Integer.parseInt(port_no));
		System.out.println("Connection Established with node C");

		//for message reading and sending
		System.out.println("Enter the message to be sent!");
		String msg_transmit=sc.nextLine();
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF(msg_transmit);
		dout.flush();
		dout.close();

	}
}
